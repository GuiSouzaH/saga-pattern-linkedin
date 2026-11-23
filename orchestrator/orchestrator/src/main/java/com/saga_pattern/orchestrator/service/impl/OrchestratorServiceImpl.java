package com.saga_pattern.orchestrator.service.impl;

import com.saga_pattern.orchestrator.dto.BookingCreatedEvent;
import com.saga_pattern.orchestrator.dto.FlightReservedEvent;
import com.saga_pattern.orchestrator.dto.ReserveFlightCommand;
import com.saga_pattern.orchestrator.dto.ReserveHotelCommand;
import com.saga_pattern.orchestrator.exceptions.SagaNotFoundException;
import com.saga_pattern.orchestrator.messaging.SagaCommandPublisher;
import com.saga_pattern.orchestrator.model.SagaInstance;
import com.saga_pattern.orchestrator.model.enums.CommandType;
import com.saga_pattern.orchestrator.model.enums.CurrentStep;
import com.saga_pattern.orchestrator.model.enums.SagaStatus;
import com.saga_pattern.orchestrator.repository.OrchestratorRepository;
import com.saga_pattern.orchestrator.service.OrchestratorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class OrchestratorServiceImpl implements OrchestratorService {

    private final OrchestratorRepository orchestratorRepository;

    private final SagaCommandPublisher commandPublisher;

    @Override
    public void startSaga(BookingCreatedEvent event) {
        if (event == null) {
            return;
        }

        SagaInstance sagaInstance = SagaInstance.builder()
                .sagaId(event.sagaId())
                .bookingId(event.bookingId())
                .sagaStatus(SagaStatus.STARTED)
                .currentStep(CurrentStep.FLIGHT)
                .flightId(event.bookingRequest().flightId())
                .hotelId(event.bookingRequest().hotelId())
                .carId(event.bookingRequest().carId())
                .build();

        orchestratorRepository.save(sagaInstance);

        ReserveFlightCommand command = new ReserveFlightCommand(
                UUID.randomUUID().toString(),
                event.sagaId(),
                event.bookingId(),
                event.bookingRequest().flightId(),
                CommandType.RESERVE_FLIGHT,
                LocalDateTime.now()
        );

        commandPublisher.flightPublish(command);

    }

    @Override
    public void handleFlightReserved(FlightReservedEvent event) {
        if (event == null) {
            return;
        }

        SagaInstance saga = orchestratorRepository.findBySagaId(event.sagaId())
                .orElseThrow(() -> new SagaNotFoundException(event.sagaId()));

        if (saga.getCurrentStep() != CurrentStep.FLIGHT) {
            return;
        }
            saga.setSagaStatus(SagaStatus.IN_PROGRESS);
            saga.setCurrentStep(CurrentStep.HOTEL);
            orchestratorRepository.save(saga);


        ReserveHotelCommand command = new ReserveHotelCommand(
                UUID.randomUUID().toString(),
                saga.getSagaId(),
                saga.getBookingId(),
                saga.getHotelId(),
                CommandType.RESERVE_HOTEL,
                LocalDateTime.now()
        );

        commandPublisher.hotelPublish(command);
    }


}
