package com.saga_pattern.orchestrator.model.enums;

public enum CommandType {

    RESERVE_FLIGHT((short) 0, "Voo reservado"),
    RESERVE_HOTEL((short) 1, "Hotel reservado");

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
