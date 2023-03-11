package co.com.retoca.usecase.crearpaciente;


import co.com.retoca.model.paciente.Paciente;
import co.com.retoca.model.generic.DomainEvent;
import co.com.retoca.model.paciente.values.*;
import co.com.retoca.usecase.generic.UseCaseForCommand;
import co.com.retoca.usecase.generic.commands.CrearPacienteCommand;
import co.com.retoca.usecase.generic.gateways.DomainEventRepository;
import co.com.retoca.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CrearPacienteUseCase extends UseCaseForCommand<CrearPacienteCommand> {
    private  final DomainEventRepository repository;
    private final EventBus bus;

    public CrearPacienteUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CrearPacienteCommand> crearPacienteCommandMono) {
        return crearPacienteCommandMono.flatMapMany(crearPacienteCommand ->
                repository.existsById(crearPacienteCommand.getPacienteId())
                        .flatMapMany(exists -> {
                            System.out.println(exists);
                            if (exists) {
                                return Mono.error(new RuntimeException("El paciente ya existe"));
                            } else {
                                Paciente paciente = new Paciente(
                                        PacienteId.of(crearPacienteCommand.getPacienteId()),
                                        new Correo(crearPacienteCommand.getCorreo()),
                                        new Nombre(crearPacienteCommand.getNombre()),
                                        new Telefono(crearPacienteCommand.getTelefono()),
                                        new Edad(crearPacienteCommand.getEdad())
                                );
                                return Flux.fromIterable(paciente.getUncommittedChanges())
                                        .flatMap(event -> repository.saveEvent(event))
                                        .flatMap(domainEvent -> repository.save(domainEvent))
                                        .doOnNext(event -> bus.publish(event));
                            }
                        }).onErrorResume(error ->Flux.empty())
        );
}
}
