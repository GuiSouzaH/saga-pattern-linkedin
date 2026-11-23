package com.saga_pattern.flight_service.model.enums;

public enum EventType {

    FLIGHT_RESERVED((short) 0, "Voo reservado");

    private short code;
    private String description;

    EventType(short code, String description) {
        this.code = code;
        this.description = description;
    }

    public short getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
