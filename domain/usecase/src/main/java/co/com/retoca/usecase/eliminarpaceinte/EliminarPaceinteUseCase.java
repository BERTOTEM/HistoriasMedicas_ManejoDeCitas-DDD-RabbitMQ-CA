package co.com.retoca.usecase.eliminarpaceinte;

import co.com.retoca.usecase.generic.gateways.DomainEventRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class EliminarPaceinteUseCase {
    private final DomainEventRepository repository;

    public EliminarPaceinteUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }

    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "Id is required");
        return repository.DeleteById(id);

    }
}
