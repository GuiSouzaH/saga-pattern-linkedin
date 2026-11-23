package com.saga_pattern.flight_service.exception;

public class FlightNotFoundException extends RuntimeException                                                                                      {
    public FlightNotFoundException(String message) {
        super(message);
    }
}
