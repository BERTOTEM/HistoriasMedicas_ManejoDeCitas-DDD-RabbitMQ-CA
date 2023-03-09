package co.com.retoca.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "core-Paciente-events";
    public static final String EVENTS_QUEUE = "events.queue";
    public static final String ROUTING_KEY = "events.routing.key";
    @Bean
    public Queue eventsQueue(){
        return new Queue(EVENTS_QUEUE);
    }

    @Bean
    public TopicExchange eventsExchange(){
        return new TopicExchange(EXCHANGE);
    }
    @Bean
    public Binding eventsBinding(){
        return BindingBuilder.bind(this.eventsQueue()).to(this.eventsExchange()).with(ROUTING_KEY);
    }


}
