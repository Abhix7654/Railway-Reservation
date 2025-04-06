package com.railway.payment.service.feign;

import com.railway.payment.service.dto.ReservationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "reservation-service", url = "http://localhost:8083/api/reservations")
public interface ReservationClient {

    @GetMapping("/{reservationId}")
    ReservationDto getReservationById(@PathVariable Long reservationId);
}
