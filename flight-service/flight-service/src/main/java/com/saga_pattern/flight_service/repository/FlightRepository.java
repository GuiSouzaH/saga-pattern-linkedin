package com.saga_pattern.flight_service.repository;

import com.saga_pattern.flight_service.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {

    Optional<Flight> findByFlightId(String flightId);
}
