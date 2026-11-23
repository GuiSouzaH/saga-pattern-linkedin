package com.saga_pattern.orchestrator.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
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
