package com.railway.reservation.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(unique = true)
    private String pnr;


    @CreationTimestamp
    private LocalDate reservationDate;
}
