package com.railway.train.service.service;

import com.railway.train.service.entity.Train;

import java.util.List;

public interface TrainService {
    String addTrain(Train train);
    List<Train> getAllTrains();
    String deleteTrain(Long id);
    String updateTrain(Long id, Train train);
    Train getTrainById(Long id);
    void updateAvailableSeats(Long id, int availableSeats);
}