package com.railway.payment.service.service;

import com.railway.payment.service.dto.PaymentRequest;
import com.railway.payment.service.dto.PaymentResponse;
import com.railway.payment.service.entity.Payment;
import com.railway.payment.service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private NotificationService notificationService;

    public PaymentResponse processPayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setReservationId(request.getReservationId());
        payment.setAmount(request.getAmount());
        payment.setPaymentStatus("SUCCESS");
        payment.setTimestamp(LocalDateTime.now());

        paymentRepository.save(payment);

        notificationService.sendPaymentNotification(request.getReservationId(), payment.getPaymentStatus());

        return new PaymentResponse("Payment successful", "SUCCESS");
    }
}
