package com.saga_pattern.hotel_service.service.impl;

import com.saga_pattern.hotel_service.dto.HotelReservationResultEvent;
import com.saga_pattern.hotel_service.dto.ReserveHotelCommand;
import com.saga_pattern.hotel_service.messaging.HotelEventPublisher;
import com.saga_pattern.hotel_service.model.Hotel;
import com.saga_pattern.hotel_service.model.enums.EventType;
import com.saga_pattern.hotel_service.repository.HotelRepository;
import com.saga_pattern.hotel_service.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private static final String HOTEL_NOT_FOUND = "HOTEL_NOT_FOUND";
    private static final String HOTEL_UNAVAILABLE = "HOTEL_UNAVAILABLE";

    private final HotelRepository hotelRepository;
    private final HotelEventPublisher hotelEventPublisher;

    @Override
    @Transactional
    public void reserveHotel(ReserveHotelCommand command) {
        log.info(
                "Comando de reserva de hotel recebido. commandId={}, sagaId={}, hotelId={}",
                command.commandId(),
                command.sagaId(),
                command.hotelId()
        );

        Optional<Hotel> hotelOptional = hotelRepository.findByHotelId(command.hotelId());
        if (hotelOptional.isEmpty()) {
            publishResult(command, EventType.HOTEL_RESERVATION_FAILED, HOTEL_NOT_FOUND);
            return;
        }

        Hotel hotel = hotelOptional.get();
        if (hotel.isReservedFor(command.bookingId(), command.sagaId())) {
            publishResult(command, EventType.HOTEL_RESERVED, "");
            return;
        }

        if (!hotel.isAvailable()) {
            publishResult(command, EventType.HOTEL_RESERVATION_FAILED, HOTEL_UNAVAILABLE);
            return;
        }

        hotel.reserveFor(command.bookingId(), command.sagaId());
        hotelRepository.save(hotel);

        publishResult(command, EventType.HOTEL_RESERVED, "");
    }

    private void publishResult(
            ReserveHotelCommand command,
            EventType eventType,
            String failureReason
    ) {
        HotelReservationResultEvent event = HotelReservationResultEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .commandId(command.commandId())
                .sagaId(command.sagaId())
                .bookingId(command.bookingId())
                .hotelId(command.hotelId())
                .eventType(eventType)
                .failureReason(failureReason)
                .source("HOTEL_SERVICE")
                .createdAt(LocalDateTime.now())
                .build();

        hotelEventPublisher.publish(event);
    }
}
