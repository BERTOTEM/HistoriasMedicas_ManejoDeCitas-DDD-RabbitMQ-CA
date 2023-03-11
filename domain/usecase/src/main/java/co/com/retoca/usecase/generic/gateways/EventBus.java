package co.com.retoca.usecase.generic.gateways;


import co.com.retoca.model.generic.DomainEvent;

public interface EventBus {
    void publish(DomainEvent event);

    void publishError(Throwable errorEvent);
}
