package com.saga_pattern.hotel_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String SAGA_EXCHANGE = "saga-exchange";
    public static final String HOTEL_QUEUE = "hotel-command-queue";
    public static final String HOTEL_ROUTING_KEY = "hotel.reserve";
    public static final String HOTEL_RESERVED_ROUTING_KEY = "hotel.reserved";
    public static final String HOTEL_RESERVATION_FAILED_ROUTING_KEY = "hotel.reservation.failed";

    @Bean
    public DirectExchange sagaExchange() {
        return new DirectExchange(SAGA_EXCHANGE, true, false);
    }

    @Bean
    public Queue hotelQueue() {
        return new Queue(HOTEL_QUEUE, true);
    }

    @Bean
    public Binding hotelCommandBinding(Queue hotelQueue, DirectExchange sagaExchange) {
        return BindingBuilder
                .bind(hotelQueue)
                .to(sagaExchange)
                .with(HOTEL_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
