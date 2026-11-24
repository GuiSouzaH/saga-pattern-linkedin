package com.saga_pattern.hotel_service.messaging;

import com.saga_pattern.hotel_service.config.RabbitMQConfig;
import com.saga_pattern.hotel_service.dto.HotelReservationResultEvent;
import com.saga_pattern.hotel_service.model.enums.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HotelEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publish(HotelReservationResultEvent event) {
        String routingKey = routingKeyFor(event.eventType());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.SAGA_EXCHANGE,
                routingKey,
                event
        );
    }

    private String routingKeyFor(EventType eventType) {
        if (eventType == EventType.HOTEL_RESERVED) {
            return RabbitMQConfig.HOTEL_RESERVED_ROUTING_KEY;
        }

        return RabbitMQConfig.HOTEL_RESERVATION_FAILED_ROUTING_KEY;
    }
}
