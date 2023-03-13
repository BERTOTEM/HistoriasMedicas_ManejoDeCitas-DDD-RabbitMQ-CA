package co.com.retoca.mongo;

import co.com.retoca.model.agenda.Dia;
import co.com.retoca.model.agenda.events.DiaAgregado;
import co.com.retoca.model.generic.DomainEvent;
import co.com.retoca.model.paciente.events.CitaAgregada;
import co.com.retoca.mongo.data.StoredEvent;

import co.com.retoca.serializer.JSONMapper;
import co.com.retoca.usecase.generic.gateways.DomainEventRepository;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.Date;
import java.util.function.Function;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Repository
public class MongoRepositoryAdapter implements DomainEventRepository{
    private final ReactiveMongoTemplate template;

    private final JSONMapper eventSerializer;

    public MongoRepositoryAdapter(ReactiveMongoTemplate template, JSONMapper eventSerializer) {
        this.template = template;
        this.eventSerializer = eventSerializer;
    }

    @Override
    public Flux<DomainEvent> findById(String aggregateId) {
        var query = new Query(Criteria.where("aggregateRootId").is(aggregateId));
        return template.find(query, StoredEvent.class)
                .sort(Comparator.comparing(event -> event.getOccurredOn()))
                .map(storeEvent -> storeEvent.deserializeEvent(eventSerializer));
    }

    @Override
    public Mono<Boolean> existsById(String aggregateId) {
        var query = new Query(Criteria.where("aggregateRootId").is(aggregateId));
        return template.exists(query, StoredEvent.class);
    }

    @Override
    public Mono<DomainEvent> saveEvent(DomainEvent event) {
        StoredEvent eventStored = new StoredEvent();
        eventStored.setAggregateRootId(event.aggregateRootId());
        eventStored.setTypeName(event.getClass().getTypeName());
        eventStored.setOccurredOn(new Date());
        eventStored.setEventBody(StoredEvent.wrapEvent(event, eventSerializer));
        return template.save(eventStored)
                .map(storeEvent -> storeEvent.deserializeEvent(eventSerializer));
    }

    @Override
    public Mono<DomainEvent> save(DomainEvent event) {
        return template.save(event);
    }

    @Override
    public Mono<DiaAgregado> saveDia(DiaAgregado diaAgregado) {
        return template.save(diaAgregado);
    }

    @Override
    public Mono<Boolean> esistsByFecha(String diaId) {
        var query = new Query(Criteria.where("diaId").is(diaId));
        return template.exists(query,"diaAgregado");
    }

    @Override
    public Mono<DiaAgregado> findDyFecha(String id, String oldValue, String newValue) {
        Query query = new Query(Criteria.where("diaId").is(id).and("disponibilidadHorarias").is(oldValue));
        Update update = new Update().set("disponibilidadHorarias.$", newValue);
        try {
            return template.findAndModify(query, update, options().returnNew(true), DiaAgregado.class, "diaAgregado").map(diaAgregado -> {
                System.out.println(diaAgregado);
                return diaAgregado;
            } );
        } catch (Exception e) {
            throw new RuntimeException("No se encontró ningún objeto con el índice '$' en la base de datos.", e);
        }
    }

    @Override
    public Flux<DomainEvent> HistorialPaciente(String aggregateRootId) {
        var query = new Query(Criteria.where("aggregateRootId").is(aggregateRootId));
        return template.find(query,StoredEvent.class).sort(Comparator.comparing(event -> event.getOccurredOn()))
                .map(storeEvent -> storeEvent.deserializeEvent(eventSerializer));
    }

    @Override
    public Flux<DiaAgregado> VerAgenda(String aggregateRootId) {
        var query = new Query(Criteria.where("aggregateRootId").is(aggregateRootId));
        return template.find(query, DiaAgregado.class);

    }


}
