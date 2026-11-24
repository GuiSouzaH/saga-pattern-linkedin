package com.saga_pattern.hotel_service.listener;

import com.saga_pattern.hotel_service.config.RabbitMQConfig;
import com.saga_pattern.hotel_service.dto.ReserveHotelCommand;
import com.saga_pattern.hotel_service.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HotelCommandListener {

    private final HotelService hotelService;

    @RabbitListener(queues = RabbitMQConfig.HOTEL_QUEUE)
    public void receive(ReserveHotelCommand command) {
        hotelService.reserveHotel(command);
    }
}
