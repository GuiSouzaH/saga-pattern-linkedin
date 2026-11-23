package com.saga_pattern.flight_service.messaging;

import com.saga_pattern.flight_service.config.RabbitMQConfig;
import com.saga_pattern.flight_service.dto.FlightReservedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class FlightEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publish(FlightReservedEvent event) {
        log.info("Publicando evento de reserva de voo. commandId={}, sagaId={}",
                event.commandId(),
                event.sagaId());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.SAGA_EXCHANGE,
                RabbitMQConfig.FLIGHT_RESERVED_ROUTING_KEY,
                event
        );

    }

}
