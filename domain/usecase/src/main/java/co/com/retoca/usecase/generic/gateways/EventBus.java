package co.com.retoca.usecase.generic.gateways;


import co.com.retoca.model.paciente.generic.DomainEvent;

public interface EventBus {
    void publish(DomainEvent event);

    void publishError(Throwable errorEvent);
}
