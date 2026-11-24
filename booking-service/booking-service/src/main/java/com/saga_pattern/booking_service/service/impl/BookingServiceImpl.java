package com.saga_pattern.booking_service.service.impl;

import com.saga_pattern.booking_service.dto.BookingRequest;
import com.saga_pattern.booking_service.dto.Event;
import com.saga_pattern.booking_service.model.Booking;
import com.saga_pattern.booking_service.model.enums.BookingStatus;
import com.saga_pattern.booking_service.model.enums.EventType;
import com.saga_pattern.booking_service.repository.BookingRepository;
import com.saga_pattern.booking_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final RabbitTemplate rabbitTemplate;

    @Override   
    public Booking createBooking(BookingRequest bookingRequest)
    {
        Booking newBooking = Booking.builder()
                .customerId(bookingRequest.customerId())
                .flightId(bookingRequest.flightId())
                .hotelId(bookingRequest.hotelId())
                .carId(bookingRequest.carId())
                .bookingStatus(BookingStatus.PENDING)
                .build();
        Booking savedBooking = bookingRepository.save(newBooking);

        Event event = Event.builder()
                .eventId(UUID.randomUUID().toString())
                .sagaId(UUID.randomUUID().toString())
                .bookingId(String.valueOf(savedBooking.getId()))
                .eventType(EventType.BOOKING_CREATED)
                .bookingRequest(bookingRequest)
                .source("BOOKING_SERVICE")
                .createdAt(LocalDateTime.now())
                .build();

        rabbitTemplate.convertAndSend("saga-exchange", "orchestrator-routing-key",event);

        return savedBooking;
    }

    @Override
    public Optional<Booking> getBookingByCustomerId(String customerId)
    {
        return bookingRepository.findByCustomerId(customerId);
    }
}
