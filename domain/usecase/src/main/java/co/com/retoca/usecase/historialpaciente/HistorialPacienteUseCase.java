package co.com.retoca.usecase.historialpaciente;

import co.com.retoca.model.generic.DomainEvent;
import co.com.retoca.model.paciente.events.CitaAgregada;
import co.com.retoca.usecase.generic.gateways.DomainEventRepository;
import reactor.core.publisher.Flux;

public class HistorialPacienteUseCase {

    private final DomainEventRepository repository;


    public HistorialPacienteUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }

    public Flux<DomainEvent> apply(String aggregateRootId) {
        return repository.HistorialPaciente(aggregateRootId);
    }

}
