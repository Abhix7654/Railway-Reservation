package com.railway.payment.service.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long reservationId;
    private Double amount;
    private String paymentMethod;
}
