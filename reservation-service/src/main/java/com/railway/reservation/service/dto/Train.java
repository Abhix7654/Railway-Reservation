package com.railway.reservation.service.dto;


import lombok.Data;

@Data
public class Train {
    private Long id;
    private String name;
    private int availableSeats;
}
