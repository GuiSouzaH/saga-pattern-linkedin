package com.saga_pattern.flight_service.service;

import com.saga_pattern.flight_service.dto.ReserveFlightCommand;

public interface FlightService {
    void reserveFlight(ReserveFlightCommand command);
}
