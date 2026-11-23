package com.saga_pattern.orchestrator.listener;

import com.saga_pattern.orchestrator.config.RabbitMQConfig;
import com.saga_pattern.orchestrator.dto.FlightReservedEvent;
import com.saga_pattern.orchestrator.service.OrchestratorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class FlightReservedEventListener {

    private final OrchestratorService orchestratorService;

    @RabbitListener(queues = RabbitMQConfig.FLIGHT_RESERVED_QUEUE)
    public void flightReserved(FlightReservedEvent event) {
        log.info(
                "Evento FlightReservedEvent recebido. eventId={}, sagaId={}, flightId={}",
                event.eventId(),
                event.sagaId(),
                event.flightId()
        );

        orchestratorService.handleFlightReserved(event);
    }
}
