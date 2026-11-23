package com.saga_pattern.flight_service.dto;

import com.saga_pattern.flight_service.model.enums.CommandType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReserveFlightCommand(
        @NotBlank
        String commandId,

        @NotBlank
        String sagaId,

        @NotBlank
        String bookingId,

        @NotBlank
        String flightId,

        @NotNull
        CommandType commandType,

        @NotNull
        LocalDateTime createdAt


) {
}
