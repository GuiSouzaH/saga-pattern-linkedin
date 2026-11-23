package com.saga_pattern.flight_service.model;

import com.saga_pattern.flight_service.model.enums.FlightStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String flightId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightStatus flightStatus;

    private String bookingId;

    private String sagaId;

    @Version
    private Long version;

}
