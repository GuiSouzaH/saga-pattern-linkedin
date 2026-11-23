package com.saga_pattern.flight_service.model.enums;

public enum FlightStatus
{
    AVAILABLE((short) 0, "Disponível"),
    RESERVED((short) 1, "Reservado");

    private short code;
    private String description;

    FlightStatus(short code, String description) {
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
