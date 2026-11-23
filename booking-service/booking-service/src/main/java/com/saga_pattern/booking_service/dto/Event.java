package com.saga_pattern.booking_service.dto;

import com.saga_pattern.booking_service.model.enums.EventType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Event(

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

