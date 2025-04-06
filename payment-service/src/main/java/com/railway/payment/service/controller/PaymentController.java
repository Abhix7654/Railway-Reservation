package com.railway.payment.service.controller;

import com.railway.payment.service.dto.PaymentRequest;
import com.railway.payment.service.dto.PaymentResponse;
import com.railway.payment.service.dto.ReservationDto;
import com.railway.payment.service.dto.UserDto;
import com.railway.payment.service.feign.ReservationClient;
import com.railway.payment.service.feign.UserServiceClient;
import com.railway.payment.service.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private ReservationClient reservationClient;
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserServiceClient userServiceClient;

    @PostMapping("/pay")
    public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest paymentRequest,
                                                       HttpServletRequest request) {

        String email = (String) request.getAttribute("userId");
        UserDto user = userServiceClient.getUserByEmail(email);
        Long userId = user.getId();
        ReservationDto reservation = reservationClient.getReservationById(paymentRequest.getReservationId());

        if (!userId.equals(reservation.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new PaymentResponse("You are not authorized to make this payment.", "FAILED"));
        }

        PaymentResponse response = paymentService.processPayment(paymentRequest);
        return ResponseEntity.ok(response);
    }

}