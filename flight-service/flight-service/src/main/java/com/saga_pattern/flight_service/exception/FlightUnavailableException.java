package com.saga_pattern.flight_service.exception;

public class FlightUnavailableException extends RuntimeException {
    public FlightUnavailableException(String message) {
        super(message);
    }
}
