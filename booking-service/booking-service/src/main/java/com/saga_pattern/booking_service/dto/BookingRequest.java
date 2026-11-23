package com.saga_pattern.booking_service.dto;

import jakarta.validation.constraints.NotBlank;

public record BookingRequest(
        @NotBlank
        String customerId,

        @NotBlank
        String flightId,

        @NotBlank
        String hotelId,

        @NotBlank
        String carId
) {
}
