package co.com.retoca.usecase.crearagenda;

import co.com.retoca.model.agenda.events.AgendaCreada;

import co.com.retoca.model.generic.DomainEvent;
import co.com.retoca.usecase.generic.commands.CrearAgendaCommand;
import co.com.retoca.usecase.generic.gateways.DomainEventRepository;
import co.com.retoca.usecase.generic.gateways.EventBus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@ExtendWith(MockitoExtension.class) //esta anotación se utiliza para habilitar el uso de Mockito en la prueba.
class CrearAgendaUseCaseTest {

    @Mock// esta anotación se utiliza para crear un objeto simulado (mock)
    private DomainEventRepository repository;
    @Mock
    private EventBus eventBus;

    private CrearAgendaUseCase useCase;

    @BeforeEach //Esta anotación se utiliza para marcar el método "setUp()" que se ejecuta antes de cada prueba. En este método se inicializa la instancia del caso de uso con los objetos simulados.
    void setUp(){
        //Arrange
        useCase = new CrearAgendaUseCase(repository,eventBus);
    }

    @Test
    void CrearAgendaUseCaseHappyPass(){
       final String POST_ID = "test-post-id";
       final String SEMANA = "Semana";

        //Arrange
        CrearAgendaCommand command = new CrearAgendaCommand(POST_ID,
                SEMANA);

        AgendaCreada event = new AgendaCreada(SEMANA);

        event.setAggregateRootId(POST_ID);

        Mockito.when(repository.saveEvent(ArgumentMatchers.any(AgendaCreada.class)))
                .thenAnswer(invocationOnMock ->
                        Mono.just(invocationOnMock.getArgument(0)));

        //Act
        Flux<DomainEvent> result = useCase.apply(Mono.just(command));


        StepVerifier.create(result)
                //Assert
                .expectNextMatches(event1 ->{
                    AgendaCreada agendaCreada = (AgendaCreada) event1;
                    Assertions.assertEquals(agendaCreada.getSemana(), event.getSemana());
                    return event1.aggregateRootId().equals(event.aggregateRootId()); })
                .verifyComplete();
    }

}