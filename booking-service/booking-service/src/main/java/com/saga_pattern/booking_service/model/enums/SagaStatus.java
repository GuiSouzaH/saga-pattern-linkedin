package com.saga_pattern.booking_service.model.enums;

public enum SagaStatus {

    PENDING((short) 0, "Pendente"),
    COMPLETED((short) 1, "Completo"),
    COMPENSATING((short) 2, "Compensando"),
    COMPENSATED((short) 3, "Compensado"),
    FAILED((short) 4, "Falhou");


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