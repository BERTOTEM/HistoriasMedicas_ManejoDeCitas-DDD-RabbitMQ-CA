package co.com.retoca.usecase.crearagenda;

import co.com.retoca.model.agenda.Agenda;
import co.com.retoca.model.agenda.values.AgendaId;
import co.com.retoca.model.agenda.values.Semana;

import co.com.retoca.model.generic.DomainEvent;
import co.com.retoca.usecase.generic.UseCaseForCommand;
import co.com.retoca.usecase.generic.commands.CrearAgendaCommand;
import co.com.retoca.usecase.generic.gateways.DomainEventRepository;
import co.com.retoca.usecase.generic.gateways.EventBus;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CrearAgendaUseCase extends UseCaseForCommand<CrearAgendaCommand> {

    private  final DomainEventRepository repository;
    private final EventBus bus;

    public CrearAgendaUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }


    @Override
    public Flux<DomainEvent> apply(Mono<CrearAgendaCommand> crearAgendaCommandMono) {
        return crearAgendaCommandMono.flatMapIterable(command ->{
            Agenda agenda = new Agenda(AgendaId.of(command.getAngendaId()),
                    new Semana(command.getSemana()));
        return agenda.getUncommittedChanges();
        }).map(event ->{
            bus.publish(event);
            return event;
        }).flatMap(event ->{
            return repository.saveEvent(event);
        });
    }
}
