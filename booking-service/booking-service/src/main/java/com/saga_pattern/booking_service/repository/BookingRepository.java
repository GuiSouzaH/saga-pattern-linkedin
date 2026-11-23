package com.saga_pattern.booking_service.repository;

import com.saga_pattern.booking_service.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    Optional<Booking> findByCustomerId(String customerId);

}
