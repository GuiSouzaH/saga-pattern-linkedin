package com.saga_pattern.hotel_service.service;

import com.saga_pattern.hotel_service.dto.ReserveHotelCommand;

public interface HotelService {

    void reserveHotel(ReserveHotelCommand command);
}
