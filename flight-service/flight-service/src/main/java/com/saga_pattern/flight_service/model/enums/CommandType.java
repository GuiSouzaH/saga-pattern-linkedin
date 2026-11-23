package com.saga_pattern.flight_service.model.enums;

public enum CommandType {

    RESERVE_FLIGHT((short) 0, "Voo reservado");

    private short code;
    private String description;

    CommandType(short code, String description) {
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
