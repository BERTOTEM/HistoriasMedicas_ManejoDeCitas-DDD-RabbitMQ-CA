package co.com.retoca.usecase.agregardia;

import co.com.retoca.model.agenda.Agenda;
import co.com.retoca.model.agenda.values.AgendaId;
import co.com.retoca.model.agenda.values.DiaId;
import co.com.retoca.model.agenda.values.DisponibilidadHoraria;
import co.com.retoca.model.generic.DomainEvent;
import co.com.retoca.usecase.generic.UseCaseForCommand;
import co.com.retoca.usecase.generic.commands.AgregarDiaCommand;
import co.com.retoca.usecase.generic.gateways.DomainEventRepository;
import co.com.retoca.usecase.generic.gateways.EventBus;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AgregarDiaUseCase  extends UseCaseForCommand<AgregarDiaCommand> {

    private final DomainEventRepository repository;
    private final EventBus bus;

    public AgregarDiaUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }


    @Override
    public Flux<DomainEvent> apply(Mono<AgregarDiaCommand> agregarDiaCommandMono) {
        return agregarDiaCommandMono.flatMapMany(agregarDiaCommand -> repository.findById(agregarDiaCommand.getAgendaID())
                .collectList()
                .flatMapIterable(events ->{
                    Agenda agenda=Agenda.from(AgendaId.of(agregarDiaCommand.getAgendaID()),events);
                    agenda.agregarDia(DiaId.of(agregarDiaCommand.getDiaId()),
                            agregarDiaCommand.getDisponibilidadHorarias());
                    return agenda.getUncommittedChanges();
                }).map(event ->{
                    bus.publish(event);
                    return event;
                }).flatMap(event ->{
                    return repository.saveEvent(event);
                }).flatMap(event -> {
                    return repository.save(event);
                })
        );
    }
}
