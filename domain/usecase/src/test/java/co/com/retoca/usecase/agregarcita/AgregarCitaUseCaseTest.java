package co.com.retoca.usecase.agregarcita;

import co.com.retoca.model.agenda.events.AgendaCreada;
import co.com.retoca.model.agenda.events.DiaAgregado;
import co.com.retoca.model.generic.DomainEvent;
import co.com.retoca.model.paciente.events.CitaAgregada;
import co.com.retoca.model.paciente.events.PacienteCreado;
import co.com.retoca.usecase.agregardia.AgregarDiaUseCase;
import co.com.retoca.usecase.crearagenda.CrearAgendaUseCase;
import co.com.retoca.usecase.generic.commands.AgregarCitaCommand;
import co.com.retoca.usecase.generic.commands.AgregarDiaCommand;
import co.com.retoca.usecase.generic.commands.CrearAgendaCommand;
import co.com.retoca.usecase.generic.gateways.DomainEventRepository;
import co.com.retoca.usecase.generic.gateways.EventBus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class) //esta anotación se utiliza para habilitar el uso de Mockito en la prueba.
class AgregarCitaUseCaseTest {

    @Mock//esta anotación se utiliza para crear un objeto simulado (mock)
    private DomainEventRepository repository;
    @Mock
    private EventBus eventBus;



    private AgregarCitaUseCase useCase;

    @BeforeEach//Esta anotación se utiliza para marcar el método "setUp()" que se ejecuta antes de cada prueba. En este método se inicializa la instancia del caso de uso con los objetos simulados.
    void setUp(){

        MockitoAnnotations.openMocks(this);
        useCase = new AgregarCitaUseCase(repository,eventBus);
    }
    @Test
    void AgregarCitaUseCasaHappyPass()  {
        //Arrange
        final String AGGREGATE_ID = "1017261707";


        PacienteCreado event = new PacienteCreado("jrt@gmail","julia","3003413827","22");


        event.setAggregateRootId(AGGREGATE_ID);

        AgregarCitaCommand command =new AgregarCitaCommand(AGGREGATE_ID,"10/02/2023","hola","1 hora","08:00","jrt@gmail");



        Mockito.when(repository.findById(command.getPacienteId())).thenReturn(Flux.just(event));
        Mockito.when(repository.saveEvent(ArgumentMatchers.any(CitaAgregada.class))).thenAnswer(
                invocationOnMock -> Mono.just(invocationOnMock.getArgument(0)));

        Mockito.doAnswer(i -> null).when(eventBus).publish(ArgumentMatchers.any(DomainEvent.class));

        //Act
        Flux<DomainEvent> result = useCase.apply(Mono.just(command));

        StepVerifier.create(result)
                //Assert
                .expectNextMatches(MonoP -> {

            assert MonoP.aggregateRootId().equals(AGGREGATE_ID);
            System.out.println(MonoP);
            return true;
        })
                .verifyComplete();
    }

}