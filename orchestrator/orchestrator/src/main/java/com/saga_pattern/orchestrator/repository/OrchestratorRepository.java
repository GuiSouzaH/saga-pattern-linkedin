package com.saga_pattern.orchestrator.repository;

import com.saga_pattern.orchestrator.model.SagaInstance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrchestratorRepository extends JpaRepository<SagaInstance, UUID> {

    Optional<SagaInstance> findBySagaId(String sagaId);
}
