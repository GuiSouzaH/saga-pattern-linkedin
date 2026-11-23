package com.saga_pattern.booking_service.model.enums;

public enum EventType {

    BOOKING_CREATED((short) 0, "Criado");

    private short code;
    private String description;

    EventType(short code, String description)
    {
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
