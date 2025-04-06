package com.railway.reservation.service.controller;


import com.railway.reservation.service.entity.Reservation;
import com.railway.reservation.service.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
@Autowired
    private  ReservationService service;


    @PostMapping("/book")
    public ResponseEntity<Reservation> book(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(service.bookReservation(reservation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getReservationById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getAll() {
        return ResponseEntity.ok(service.getAllReservations());
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(service.cancelReservation(id));
    }
}
