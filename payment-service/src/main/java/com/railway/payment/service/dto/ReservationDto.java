package com.railway.payment.service.dto;

import lombok.Data;

@Data
public class ReservationDto {
    private Long reservationId;
    private Long userId;
    private Long trainId;
    private String status;
    private double fare;

}
