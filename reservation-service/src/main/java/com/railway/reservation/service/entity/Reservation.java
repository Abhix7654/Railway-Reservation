package com.railway.reservation.service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long trainId;

    private LocalDate travelDate;

    private int numberOfSeats;

    private String status;

    private String pnr;

    public Reservation() {}

    public Reservation(Long userId, Long trainId, LocalDate travelDate, int numberOfSeats, String status, String pnr) {
        this.userId = userId;
        this.trainId = trainId;
        this.travelDate = travelDate;
        this.numberOfSeats = numberOfSeats;
        this.status = status;
        this.pnr = pnr;
    }


}
