package com.railway.train.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String source;

    private String destination;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    private double fare;
}
