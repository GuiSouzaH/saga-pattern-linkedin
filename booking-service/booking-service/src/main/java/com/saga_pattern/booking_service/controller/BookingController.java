package com.saga_pattern.booking_service.controller;

import com.saga_pattern.booking_service.dto.BookingRequest;
import com.saga_pattern.booking_service.model.Booking;
import com.saga_pattern.booking_service.service.impl.BookingServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingServiceImpl bookingServiceImpl;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody BookingRequest bookingRequest) {
        Booking newBooking = bookingServiceImpl.createBooking(bookingRequest);
        return ResponseEntity.ok(newBooking);
    }

    @GetMapping("/customerId/{customerId}")
    public ResponseEntity<Booking> getBookingByCustomerId(
            @PathVariable("customerId") String customerId
    ) {
        return bookingServiceImpl.getBookingByCustomerId(customerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
