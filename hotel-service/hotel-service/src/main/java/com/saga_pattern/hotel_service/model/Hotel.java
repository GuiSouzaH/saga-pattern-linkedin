package com.saga_pattern.hotel_service.model;

import com.saga_pattern.hotel_service.model.enums.HotelStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "hotels")
@Getter
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String hotelId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HotelStatus hotelStatus;

    private String bookingId;

    private String sagaId;

    @Version
    private Long version;

    public boolean isAvailable() {
        return hotelStatus == HotelStatus.AVAILABLE;
    }

    public boolean isReservedFor(String requestedBookingId, String requestedSagaId) {
        return hotelStatus == HotelStatus.RESERVED
                && Objects.equals(bookingId, requestedBookingId)
                && Objects.equals(sagaId, requestedSagaId);
    }

    public void reserveFor(String requestedBookingId, String requestedSagaId) {
        if (!isAvailable()) {
            throw new IllegalStateException("Hotel is not available");
        }

        hotelStatus = HotelStatus.RESERVED;
        bookingId = requestedBookingId;
        sagaId = requestedSagaId;
    }
}
