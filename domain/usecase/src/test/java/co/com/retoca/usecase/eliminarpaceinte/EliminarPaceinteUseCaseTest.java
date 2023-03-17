package co.com.retoca.usecase.eliminarpaceinte;

import co.com.retoca.usecase.generic.gateways.DomainEventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@ExtendWith(MockitoExtension.class)
class EliminarPaceinteUseCaseTest {
    @Mock
    private DomainEventRepository repository;
    @Mock
    private EliminarPaceinteUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        useCase = new EliminarPaceinteUseCase(repository);
    }

    @Test
    void shouldDeletePacienteById() {
        // Arrange
        String id = "1017261707";
        Mockito.when(repository.DeleteById(id)).thenReturn(Mono.empty()); // Configurar el mock


        // Act
        Mono<Void> result = useCase.apply(id);

        Mockito.verify(repository, Mockito.times(1)).DeleteById(id);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        // Arrange
        String id = null;

        // Act
        Throwable throwable = Assertions.assertThrows(NullPointerException.class, () -> useCase.apply(id).block());

        // Assert
        Assertions.assertEquals("Id is required", throwable.getMessage());
    }
}