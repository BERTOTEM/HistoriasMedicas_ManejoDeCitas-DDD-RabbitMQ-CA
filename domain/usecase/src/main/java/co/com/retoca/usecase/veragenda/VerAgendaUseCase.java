package co.com.retoca.usecase.veragenda;

import co.com.retoca.model.agenda.events.DiaAgregado;
import co.com.retoca.model.generic.DomainEvent;
import co.com.retoca.usecase.generic.gateways.DomainEventRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;


public class VerAgendaUseCase {

    private final DomainEventRepository repository;


    public VerAgendaUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }

    public Flux<DiaAgregado> apply(String name) {
        return repository.VerAgenda(name);
    }

}
