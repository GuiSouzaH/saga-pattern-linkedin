package com.saga_pattern.orchestrator.listener;

import com.saga_pattern.orchestrator.config.RabbitMQConfig;
import com.saga_pattern.orchestrator.dto.BookingCreatedEvent;
import com.saga_pattern.orchestrator.service.OrchestratorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class BookingCreatedEventListener {

    private final OrchestratorService orchestratorService;

    @RabbitListener(queues = RabbitMQConfig.ORCHESTRATOR_QUEUE)
    public void receive(BookingCreatedEvent event) {
        log.info(
                "Evento BookingCreatedEvent recebido. eventId={}, sagaId={}, bookingId={}",
                event.eventId(),
                event.sagaId(),
                event.bookingId()
        );

        orchestratorService.startSaga(event);
    }
}
