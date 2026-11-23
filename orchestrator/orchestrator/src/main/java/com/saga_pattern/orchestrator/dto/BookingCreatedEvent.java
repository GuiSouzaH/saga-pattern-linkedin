package com.saga_pattern.orchestrator.dto;

import com.saga_pattern.orchestrator.model.enums.EventType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BookingCreatedEvent(

        @NotBlank
        String eventId,

        @NotBlank
        String sagaId,

        @NotBlank
        String bookingId,

        @NotNull
        EventType eventType,

        @Valid @NotNull
        BookingRequest bookingRequest,

        @NotBlank
        String source,

        @NotNull
        LocalDateTime createdAt

) {
}
