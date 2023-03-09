package co.com.retoca.usecase.crearpaciente;


import co.com.retoca.model.paciente.Paciente;
import co.com.retoca.model.paciente.generic.DomainEvent;
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
        return crearPacienteCommandMono.flatMapIterable(crearPacienteCommand -> {
            Paciente paciente=new Paciente(PacienteId.of(crearPacienteCommand.getPacienteId())
                    ,new Correo(crearPacienteCommand.getCorreo()),
                    new Nombre(crearPacienteCommand.getNombre()),
                    new Telefono(crearPacienteCommand.getTelefono()),
                    new Edad(crearPacienteCommand.getEdad()));
            return paciente.getUncommittedChanges();

        }).flatMap(event ->{
            return repository.saveEvent(event);
        }).map(event ->{
            bus.publish(event);
            return  event;
        });
    }
}
