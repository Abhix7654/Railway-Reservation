package com.railway.reservation.service.feign;


import com.railway.reservation.service.dto.Train;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "TRAIN-SERVICE")
public interface TrainClient {

    @GetMapping("/api/trains/get/{id}")
    Train getTrainById(@PathVariable("id") Long trainId);

    @PutMapping("/api/trains/updateSeats/{id}")
    void updateAvailableSeats(@PathVariable("id") Long trainId, @RequestParam("seats") int seats);
}
