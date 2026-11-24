package com.saga_pattern.booking_service.service;

import com.saga_pattern.booking_service.dto.BookingRequest;
import com.saga_pattern.booking_service.model.Booking;

import java.util.Optional;

public interface BookingService {

    Booking createBooking(BookingRequest bookingRequest);


    Optional<Booking> getBookingByCustomerId(String customerId);
}
