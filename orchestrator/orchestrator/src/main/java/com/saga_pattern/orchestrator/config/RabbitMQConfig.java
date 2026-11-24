package com.saga_pattern.orchestrator.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
public class RabbitMQConfig {

    public static final String SAGA_EXCHANGE = "saga-exchange";
    public static final String ORCHESTRATOR_QUEUE = "orchestrator-queue";
    public static final String ORCHESTRATOR_ROUTING_KEY = "orchestrator-routing-key";
    public static final String FLIGHT_RESERVED_QUEUE = "orchestrator-flight-reserved-queue";
    public static final String FLIGHT_RESERVED_ROUTING_KEY = "flight.reserved";
    public static final String HOTEL_RESPONSE_QUEUE = "orchestrator-hotel-response-queue";
    public static final String HOTEL_RESERVED_ROUTING_KEY = "hotel.reserved";
    public static final String HOTEL_RESERVATION_FAILED_ROUTING_KEY = "hotel.reservation.failed";

    @Bean
    public DirectExchange orchestratorExchange() {
        return new DirectExchange(SAGA_EXCHANGE, true, false);
    }

    @Bean
    public Queue orchestratorQueue() {
        return new Queue(ORCHESTRATOR_QUEUE, true);
    }

    @Bean
    public Queue flightReservedEventQueue() {
        return new Queue(FLIGHT_RESERVED_QUEUE, true);
    }

    @Bean
    public Queue hotelResponseQueue() {
        return new Queue(HOTEL_RESPONSE_QUEUE, true);
    }

    @Bean
    public Binding orchestratorBinding(@Qualifier("orchestratorQueue") Queue orchestratorQueue,
                                       DirectExchange sagaExchange) {
        return BindingBuilder
                .bind(orchestratorQueue)
                .to(sagaExchange)
                .with(ORCHESTRATOR_ROUTING_KEY);
    }

    @Bean
    public Binding flightReservedEventBinding(@Qualifier("flightReservedEventQueue") Queue flightReservedEventQueue,
                                              DirectExchange sagaExchange) {
        return BindingBuilder
                .bind(flightReservedEventQueue)
                .to(sagaExchange)
                .with(FLIGHT_RESERVED_ROUTING_KEY);
    }

    @Bean
    public Binding hotelReservedEventBinding(@Qualifier("hotelResponseQueue") Queue hotelResponseQueue,
                                             DirectExchange sagaExchange) {
        return BindingBuilder
                .bind(hotelResponseQueue)
                .to(sagaExchange)
                .with(HOTEL_RESERVED_ROUTING_KEY);
    }

    @Bean
    public Binding hotelReservationFailedEventBinding(
            @Qualifier("hotelResponseQueue") Queue hotelResponseQueue,
            DirectExchange sagaExchange
    ) {
        return BindingBuilder
                .bind(hotelResponseQueue)
                .to(sagaExchange)
                .with(HOTEL_RESERVATION_FAILED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
