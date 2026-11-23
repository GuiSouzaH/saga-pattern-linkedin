package com.saga_pattern.flight_service.listener;

import com.saga_pattern.flight_service.config.RabbitMQConfig;
import com.saga_pattern.flight_service.dto.ReserveFlightCommand;
import com.saga_pattern.flight_service.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class FlightCommandListener {

    private final FlightService flightService;

    @RabbitListener(queues = RabbitMQConfig.FLIGHT_QUEUE)
    public void receive (ReserveFlightCommand command) {
        flightService.reserveFlight(command);
    }

}
