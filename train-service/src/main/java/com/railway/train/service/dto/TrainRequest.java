package com.railway.train.service.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TrainRequest {
    private String name;
    private String source;
    private String destination;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private double fare;
}
