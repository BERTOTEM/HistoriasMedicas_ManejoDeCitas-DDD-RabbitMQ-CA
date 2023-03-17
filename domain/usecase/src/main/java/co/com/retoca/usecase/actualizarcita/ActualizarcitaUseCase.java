package co.com.retoca.usecase.actualizarcita;

import co.com.retoca.model.generic.DomainEvent;
import co.com.retoca.model.paciente.Paciente;
import co.com.retoca.model.paciente.values.*;
import co.com.retoca.usecase.generic.UseCaseForCommand;
import co.com.retoca.usecase.generic.commands.ActualizarCitaCommand;

import co.com.retoca.usecase.generic.gateways.DomainEventRepository;
import co.com.retoca.usecase.generic.gateways.EventBus;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ActualizarcitaUseCase   extends UseCaseForCommand<ActualizarCitaCommand> {
    private  final DomainEventRepository repository;
    private final EventBus bus;

    public ActualizarcitaUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<ActualizarCitaCommand> actualizarCitaCommandMono) {
        return actualizarCitaCommandMono.flatMapMany(actualizarCitaCommand -> repository.findById(actualizarCitaCommand.getCitaId())
                .collectList()
                .flatMapIterable(domainEvents -> {
                    Paciente paciente=Paciente.from(PacienteId.of(actualizarCitaCommand.getPacienteId()),domainEvents);
                    paciente.actualizarCita(CitaId.of(actualizarCitaCommand.getCitaId()),
                    new RevisionDeCitaMedica(actualizarCitaCommand.getRevisionDeCitaMedica()),
                            new Duracion(actualizarCitaCommand.getDuracion()),
                            new Hora(actualizarCitaCommand.getHora()),
                            new Correo(actualizarCitaCommand.getCorreo()));
                    String fecha = actualizarCitaCommand.getCitaId();
                    String horaAnterior = actualizarCitaCommand.getHora();
                    String horaNueva = actualizarCitaCommand.getHora() + " Reservado por: " + actualizarCitaCommand.getPacienteId();
                    repository.findDyFecha(fecha,horaNueva,horaAnterior).subscribe();
                    return paciente.getUncommittedChanges();
                })
                .map(event -> {
                    bus.publish(event);
                    return event;
                }).flatMap(event -> {
                    return repository.saveEvent(event);
                }));
    }
}
