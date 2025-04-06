package com.railway.payment.service.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendPaymentNotification(Long reservationId, String status) {
        // For now just log it or print. Later send email/SMS.
        System.out.println("Notification sent for Reservation ID: " + reservationId + " with status: " + status);
    }
}
