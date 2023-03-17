package co.com.retoca.usecase.veragenda;

import co.com.retoca.usecase.generic.commands.AgregarDiaCommand;
import co.com.retoca.usecase.generic.gateways.DomainEventRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class VerAgendaUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Mock
    private VerAgendaUseCase useCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new VerAgendaUseCase(repository);

        //MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testVerAgendaUseCase() {
        String name = "Agenda1";
        List<AgregarDiaCommand> expectedAgenda = Arrays.asList(new AgregarDiaCommand("Agenda1","10/08/2023",new ArrayList<>()));

        // Mockear el comportamiento de repository.VerAgenda3
        when(repository.VerAgenda3(name)).thenReturn(Flux.fromIterable(expectedAgenda));

        // Invocar al método apply de la clase VerAgendaUseCase
        Flux<AgregarDiaCommand> result = useCase.apply(name);

        // Verificar que el resultado esperado es igual al obtenido
        StepVerifier.create(result)
                .expectNextSequence(expectedAgenda)
                .verifyComplete();
    }
}