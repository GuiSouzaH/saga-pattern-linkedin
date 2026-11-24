package com.saga_pattern.orchestrator.service;

import com.saga_pattern.orchestrator.dto.BookingCreatedEvent;
import com.saga_pattern.orchestrator.dto.FlightReservedEvent;
import com.saga_pattern.orchestrator.dto.HotelReservationResultEvent;

public interface OrchestratorService
{
    void startSaga(BookingCreatedEvent event);

    void handleFlightReserved(FlightReservedEvent event);

    void handleHotelReservationResult(HotelReservationResultEvent event);
}
