package com.saga_pattern.orchestrator.exceptions;

public class SagaNotFoundException extends RuntimeException {
    public SagaNotFoundException(String message) {
        super(message);
    }
}
