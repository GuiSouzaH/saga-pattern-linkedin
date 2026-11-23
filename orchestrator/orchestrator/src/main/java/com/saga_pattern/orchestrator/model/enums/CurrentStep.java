package com.saga_pattern.orchestrator.model.enums;

public enum CurrentStep {

    FLIGHT((short) 0, "Voo"),
    HOTEL((short) 1, "Hotel"),
    CAR((short) 2, "Carro"),
    COMPENSATING((short) 3, "Compensando"),
    COMPLETED((short) 4, "Completado");

    private short code;
    private String description;

    CurrentStep(short code, String description) {
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
