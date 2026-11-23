package com.saga_pattern.flight_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {

    public static final String SAGA_EXCHANGE = "saga-exchange";
    public static final String FLIGHT_QUEUE = "flight-command-queue";
    public static final String FLIGHT_ROUTING_KEY = "flight.reserve";
    public static final String FLIGHT_RESERVED_ROUTING_KEY = "flight.reserved";

    @Bean
    public DirectExchange sagaExchange() {return new DirectExchange(SAGA_EXCHANGE, true, false);}

    @Bean
    public Queue flightCommandQueue() {return new Queue(FLIGHT_QUEUE, true);}

    @Bean
    public Binding flightCommandBinding(Queue flightCommandQueue, DirectExchange sagaExchange) {
        return BindingBuilder
                .bind(flightCommandQueue)
                .to(sagaExchange)
                .with(FLIGHT_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
