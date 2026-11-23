package com.saga_pattern.orchestrator.model;

import com.saga_pattern.orchestrator.model.enums.CurrentStep;
import com.saga_pattern.orchestrator.model.enums.SagaStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Saga_Instance")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SagaInstance {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String sagaId;

    @Column(unique = true, nullable = false)
    private String bookingId;

    @Column(nullable = false)
    private String flightId;

    @Column(nullable = false)
    private String hotelId;

    @Column(nullable = false)
    private String carId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SagaStatus sagaStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrentStep currentStep;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    private String failureReason;
}
