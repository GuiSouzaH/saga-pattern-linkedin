package com.saga_pattern.orchestrator.dto;

import com.saga_pattern.orchestrator.model.enums.CommandType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReserveHotelCommand(
        @NotBlank
        String commandId,

        @NotBlank
        String sagaId,

        @NotBlank
        String bookingId,

        @NotBlank
        String hotelId,

        @NotNull
        CommandType commandType,

        @NotNull
        LocalDateTime createdAt
) {
}
