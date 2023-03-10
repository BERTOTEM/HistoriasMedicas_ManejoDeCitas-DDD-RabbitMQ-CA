package co.com.retoca.usecase.actualizarpaciente;

import co.com.retoca.model.paciente.Paciente;
import co.com.retoca.model.paciente.events.PacienteActualizado;
import co.com.retoca.model.paciente.generic.DomainEvent;
import co.com.retoca.model.paciente.values.*;
import co.com.retoca.usecase.generic.UseCaseForCommand;
import co.com.retoca.usecase.generic.commands.ActualizarPacienteCommand;
import co.com.retoca.usecase.generic.gateways.DomainEventRepository;
import co.com.retoca.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class ActualizarPacienteUseCase  extends UseCaseForCommand<ActualizarPacienteCommand> {
    private  final DomainEventRepository repository;
    private final EventBus bus;

    public ActualizarPacienteUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<ActualizarPacienteCommand> actualizarPacienteCommandMono) {
        return  actualizarPacienteCommandMono.flatMapMany(actualizarPacienteCommand ->
                repository.findById(actualizarPacienteCommand.getPacienteId())
                        .collectList().flatMapIterable(events -> {

                            Paciente paciente=Paciente.from(PacienteId.of(actualizarPacienteCommand.getPacienteId()),events);
                            paciente.actualizarPaciente(new PacienteId(actualizarPacienteCommand.getPacienteId()),
                                    new Correo(actualizarPacienteCommand.getCorreo()),
                                    new Nombre(actualizarPacienteCommand.getNombre()),
                                    new Telefono(actualizarPacienteCommand.getTelefono()),
                                    new Edad(actualizarPacienteCommand.getEdad()));
                            return paciente.getUncommittedChanges();
                        }).map(event ->{
                            bus.publish(event);
                            return event;
                        }).flatMap(event ->{
                            return repository.saveEvent(event);
                        })

        );

                        /*.flatMapIterable(events -> {
                            Paciente pacienteActualizado = new Paciente(
                                    PacienteId.of(actualizarPacienteCommand.getPacienteId()),
                                    new Correo(actualizarPacienteCommand.getCorreo()),
                                    new Nombre(actualizarPacienteCommand.getNombre()),
                                    new Telefono(actualizarPacienteCommand.getTelefono()),
                                    new Edad(actualizarPacienteCommand.getEdad())
                            );
                            return Flux.fromIterable(pacienteActualizado.getUncommittedChanges())
                                    .flatMap(event -> repository.saveEvent(event))
                                    .doOnNext(event -> bus.publish(event));
                        }).switchIfEmpty(Mono.error(new RuntimeException("El paciente no existe")))
                        .onErrorResume(error -> Flux.empty())*/

    }
    }

