package co.com.retoca.usecase.veragenda;

import co.com.retoca.model.agenda.events.DiaAgregado;
import co.com.retoca.model.generic.DomainEvent;
import co.com.retoca.usecase.generic.commands.AgregarDiaCommand;
import co.com.retoca.usecase.generic.gateways.DomainEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import reactor.core.publisher.Flux;


public class VerAgendaUseCase {

    private final DomainEventRepository repository;



    public VerAgendaUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }

    public Flux<AgregarDiaCommand> apply(String name) {
        return repository.VerAgenda3(name);
    }

}
