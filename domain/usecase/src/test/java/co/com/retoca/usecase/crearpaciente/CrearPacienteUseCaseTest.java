package co.com.retoca.usecase.crearpaciente;

import co.com.retoca.model.generic.DomainEvent;
import co.com.retoca.model.paciente.Paciente;
import co.com.retoca.model.paciente.values.*;
import co.com.retoca.usecase.generic.commands.CrearPacienteCommand;
import co.com.retoca.usecase.generic.gateways.DomainEventRepository;
import co.com.retoca.usecase.generic.gateways.EventBus;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static reactor.core.publisher.Mono.when;


class CrearPacienteUseCaseTest {
    @Mock
    private final DomainEventRepository repository = Mockito.mock(DomainEventRepository.class);
    @Mock
    private final EventBus bus = Mockito.mock(EventBus.class);

    @Test
    public void testApply() {
        CrearPacienteCommand command = new CrearPacienteCommand(
                "1", "test@test.com", "Test", "123456789", "30");
        Paciente paciente = new Paciente(
                PacienteId.of(command.getPacienteId()),
                new Correo(command.getCorreo()),
                new Nombre(command.getNombre()),
                new Telefono(command.getTelefono()),
                new Edad(command.getEdad())
        );
        Mockito.when(repository.existsById(command.getPacienteId())).thenReturn(Mono.just(false));
        Mockito.when(repository.saveEvent(any(DomainEvent.class))).thenReturn(Mono.empty());

        CrearPacienteUseCase useCase = new CrearPacienteUseCase(repository, bus);
        Flux<DomainEvent> result = useCase.apply(Mono.just(command));

        result.subscribe();
        Mockito.verify(repository, Mockito.times(1)).existsById(command.getPacienteId());
        Mockito.verify(repository, Mockito.times(1)).saveEvent(any(DomainEvent.class));

    }



}