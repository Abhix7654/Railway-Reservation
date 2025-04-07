package com.railway.reservation.service.service;


import com.railway.reservation.service.entity.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation bookReservation(Reservation reservation);
    Reservation getReservationById(Long id);
    List<Reservation> getAllReservations();
    String cancelReservation(Long id);
     void updateStatusAfterPayment(Long id);
}
