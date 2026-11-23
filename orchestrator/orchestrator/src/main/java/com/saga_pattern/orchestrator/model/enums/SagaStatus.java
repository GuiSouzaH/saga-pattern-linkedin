package com.saga_pattern.orchestrator.model.enums;

public enum SagaStatus {

    STARTED((short) 0, "Iniciado"),
    IN_PROGRESS((short) 1, "Em progresso"),
    COMPENSATING((short) 2, "Compensando"),
    COMPLETED((short) 3, "Completo"),
    COMPENSATED((short) 4, "Compensado"),
    FAILED((short) 5, "Falhou"),
    COMPENSATED_FAILED((short) 6, "Compensação Falhou"),;


    private short code;
    private String description;

    SagaStatus(short code, String description) {
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