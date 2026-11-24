package com.saga_pattern.orchestrator.listener;

import com.saga_pattern.orchestrator.config.RabbitMQConfig;
import com.saga_pattern.orchestrator.dto.HotelReservationResultEvent;
import com.saga_pattern.orchestrator.service.OrchestratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class HotelReservationResultEventListener {

    private final OrchestratorService orchestratorService;

    @RabbitListener(queues = RabbitMQConfig.HOTEL_RESPONSE_QUEUE)
    public void receive(HotelReservationResultEvent event) {
        log.info(
                "Resultado da reserva de hotel recebido. eventId={}, sagaId={}, hotelId={}",
                event.eventId(),
                event.sagaId(),
                event.hotelId()
        );

        orchestratorService.handleHotelReservationResult(event);
    }
}
