package co.com.retoca.usecase.agregarcita;

import co.com.retoca.model.paciente.Cita;
import co.com.retoca.model.paciente.Paciente;
import co.com.retoca.model.paciente.generic.DomainEvent;
import co.com.retoca.model.paciente.values.CitaId;
import co.com.retoca.model.paciente.values.PacienteId;
import co.com.retoca.model.paciente.values.RevisionDeCitaMedica;
import co.com.retoca.usecase.generic.UseCaseForCommand;
import co.com.retoca.usecase.generic.commands.AgregarCitaCommand;
import co.com.retoca.usecase.generic.gateways.DomainEventRepository;
import co.com.retoca.usecase.generic.gateways.EventBus;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AgregarCitaUseCase  extends UseCaseForCommand<AgregarCitaCommand> {

    private final DomainEventRepository repository;
    private final EventBus bus;

    public AgregarCitaUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }


    @Override
    public Flux<DomainEvent> apply(Mono<AgregarCitaCommand> agregarCitaCommandMono) {
        return agregarCitaCommandMono.flatMapMany(agregarCitaCommand ->repository.findById(agregarCitaCommand.getPacienteId())
                .collectList()
                .flatMapIterable(events ->{
                    Paciente paciente =Paciente.from(PacienteId.of(agregarCitaCommand.getPacienteId()),events);
                    paciente.AgregarCita(CitaId.of(agregarCitaCommand.getCitaId()),
                            new RevisionDeCitaMedica(agregarCitaCommand.getRevisionDeCitaMedica()));
                    return paciente.getUncommittedChanges();
                }).map(event ->{
                    bus.publish(event);
                    return event;
                }).flatMap(event ->{
                    return repository.saveEvent(event);
                }));
    }
}
