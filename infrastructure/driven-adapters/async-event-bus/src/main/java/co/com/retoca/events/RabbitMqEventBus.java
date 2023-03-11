package co.com.retoca.events;

import co.com.retoca.events.data.Notification;
import co.com.retoca.model.generic.DomainEvent;
import co.com.retoca.serializer.JSONMapper;
import co.com.retoca.usecase.generic.gateways.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventBus implements EventBus {
    public static final String EXCHANGE = "core-Paciente-events";
    public static final String ROUTING_KEY = "events.routing.key";
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqEventBus.class);
    private final RabbitTemplate rabbitTemplate;
    private final JSONMapper serializer;

    public RabbitMqEventBus(RabbitTemplate rabbitTemplate, JSONMapper serializer) {
        this.rabbitTemplate = rabbitTemplate;
        this.serializer = serializer;
    }

    @Override
    public void publish(DomainEvent event) {
        var notification = new Notification(
                event.getClass().getTypeName(),
                serializer.writeToJson(event)
        );
        LOGGER.info(String.format("Message sent -> %s", event.toString()));
        rabbitTemplate.convertAndSend(
                this.EXCHANGE, this.ROUTING_KEY, notification.serialize().getBytes()
        );

    }

    @Override
    public void publishError(Throwable errorEvent) {

    }
}
