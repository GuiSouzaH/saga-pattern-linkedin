package com.saga_pattern.orchestrator.model.enums;

public enum EventType {

    BOOKING_CREATED((short) 0, "Criado"),
    FLIGHT_RESERVED((short) 1, "Voo Reservado"),
    HOTEL_RESERVED((short) 2, "Hotel Reservado"),
    HOTEL_RESERVATION_FAILED((short) 3, "Reserva de Hotel Falhou");

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
