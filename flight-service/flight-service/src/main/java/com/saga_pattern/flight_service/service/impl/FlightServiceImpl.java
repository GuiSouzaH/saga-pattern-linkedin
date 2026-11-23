package com.saga_pattern.flight_service.service.impl;

import com.saga_pattern.flight_service.dto.FlightReservedEvent;
import com.saga_pattern.flight_service.dto.ReserveFlightCommand;
import com.saga_pattern.flight_service.exception.FlightNotFoundException;
import com.saga_pattern.flight_service.exception.FlightUnavailableException;
import com.saga_pattern.flight_service.messaging.FlightEventPublisher;
import com.saga_pattern.flight_service.model.Flight;
import com.saga_pattern.flight_service.model.enums.EventType;
import com.saga_pattern.flight_service.model.enums.FlightStatus;
import com.saga_pattern.flight_service.repository.FlightRepository;
import com.saga_pattern.flight_service.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class FlightServiceImpl implements FlightService
{
    private final FlightRepository flightRepository;
    private final FlightEventPublisher flightEventPublisher;

    @Override
    @Transactional
    public void reserveFlight(ReserveFlightCommand command) {
        log.info(
                "Comando recebido. commandId={}, sagaId={}, flightId={}",
                command.commandId(),
                command.sagaId(),
                command.flightId()
        );

        Flight flight = flightRepository
                .findByFlightId(command.flightId())
                .orElseThrow(() -> new FlightNotFoundException(command.flightId()));

        if (flight.getFlightStatus() != FlightStatus.AVAILABLE) {
            throw new FlightUnavailableException(command.flightId());
        }

        flight.setFlightStatus(FlightStatus.RESERVED);
        flight.setBookingId(command.bookingId());
        flight.setSagaId(command.sagaId());

        flightRepository.save(flight);

        FlightReservedEvent event = FlightReservedEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .commandId(command.commandId())
                .sagaId(command.sagaId())
                .bookingId(command.bookingId())
                .flightId(command.flightId())
                .eventType(EventType.FLIGHT_RESERVED)
                .source("FLIGHT_SERVICE")
                .createdAt(LocalDateTime.now())
                .build();

        flightEventPublisher.publish(event);

        log.info("Reserva de voo realizada.");
    }

}
