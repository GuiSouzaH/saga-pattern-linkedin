package com.saga_pattern.orchestrator.dto;

import com.saga_pattern.orchestrator.model.enums.EventType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record HotelReservationResultEvent(
        @NotBlank
        String eventId,

        @NotBlank
        String commandId,

        @NotBlank
        String sagaId,

        @NotBlank
        String bookingId,

        @NotBlank
        String hotelId,

        @NotNull
        EventType eventType,

        String failureReason,

        @NotBlank
        String source,

        @NotNull
        LocalDateTime createdAt
) {
}
