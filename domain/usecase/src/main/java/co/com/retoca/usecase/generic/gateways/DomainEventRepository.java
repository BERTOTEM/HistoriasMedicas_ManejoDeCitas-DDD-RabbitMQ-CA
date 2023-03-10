package co.com.retoca.usecase.generic.gateways;


import co.com.retoca.model.paciente.Paciente;
import co.com.retoca.model.paciente.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DomainEventRepository {
    Flux<DomainEvent> findById(String aggregateId);
    Mono<Boolean>existsById(String aggregateId);
    Mono<DomainEvent> saveEvent(DomainEvent event);
    Mono<DomainEvent>save(DomainEvent event);

}
