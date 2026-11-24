package com.saga_pattern.hotel_service.repository;

import com.saga_pattern.hotel_service.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HotelRepository extends JpaRepository<Hotel, UUID> {

    Optional<Hotel> findByHotelId(String hotelId);
}
