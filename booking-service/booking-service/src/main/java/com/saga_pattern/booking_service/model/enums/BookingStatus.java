package com.saga_pattern.booking_service.model.enums;

public enum BookingStatus {

    PENDING ((short) 0, "Pendente"),
    APPROVED ((short) 1, "Aprovado"),
    REJECTED ((short) 2, "Rejeitado");

    private short code;
    private String description;

    BookingStatus(short code, String description) {
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
