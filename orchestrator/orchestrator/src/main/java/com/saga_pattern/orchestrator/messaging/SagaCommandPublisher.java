package com.saga_pattern.orchestrator.messaging;

import com.saga_pattern.orchestrator.config.RabbitMQConfig;
import com.saga_pattern.orchestrator.dto.ReserveFlightCommand;
import com.saga_pattern.orchestrator.dto.ReserveHotelCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class SagaCommandPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void flightPublish(ReserveFlightCommand command) {
        log.info("Publicando comando de reserva de voo. commandId={}, sagaId={}",
                command.commandId(),
                command.sagaId());

        rabbitTemplate.convertAndSend(RabbitMQConfig.SAGA_EXCHANGE, "flight.reserve", command);
    }

    public void hotelPublish(ReserveHotelCommand command) {
        log.info("Publicando comando de reserva de hotel. commandId={}, sagaId={}",
                command.commandId(),
                command.sagaId());

        rabbitTemplate.convertAndSend(RabbitMQConfig.SAGA_EXCHANGE, "hotel.reserve", command);
    }
}
