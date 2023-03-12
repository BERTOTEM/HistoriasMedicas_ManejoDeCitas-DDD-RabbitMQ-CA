package co.com.retoca.usecase.generic.gateways;


import co.com.retoca.model.agenda.events.DiaAgregado;
import co.com.retoca.model.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DomainEventRepository {
    Flux<DomainEvent> findById(String aggregateId);
    Mono<Boolean>existsById(String aggregateId);
    Mono<DomainEvent> saveEvent(DomainEvent event);
    Mono<DomainEvent>save(DomainEvent event);
    Mono<Boolean>esistsByFecha(String diaId);
    Mono<DomainEvent> findDyFecha(String id, String oldValue, String newValue);


}
