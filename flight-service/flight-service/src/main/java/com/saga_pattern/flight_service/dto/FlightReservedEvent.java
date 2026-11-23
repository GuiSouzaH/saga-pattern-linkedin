package com.saga_pattern.flight_service.dto;

import com.saga_pattern.flight_service.model.enums.EventType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FlightReservedEvent(
        @NotBlank
        String eventId,

        @NotBlank
        String commandId,

        @NotBlank
        String sagaId,

        @NotBlank
        String bookingId,

        @NotBlank
        String flightId,

        @NotNull
        EventType eventType,

        @NotBlank
        String source,

        @NotNull
        LocalDateTime createdAt
) {
}
