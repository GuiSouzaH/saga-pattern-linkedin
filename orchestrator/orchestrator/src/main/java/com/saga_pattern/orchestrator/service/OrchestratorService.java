package com.saga_pattern.orchestrator.service;

import com.saga_pattern.orchestrator.dto.BookingCreatedEvent;
import com.saga_pattern.orchestrator.dto.FlightReservedEvent;

public interface OrchestratorService
{
    void startSaga(BookingCreatedEvent event);

    void handleFlightReserved(FlightReservedEvent event);
}
