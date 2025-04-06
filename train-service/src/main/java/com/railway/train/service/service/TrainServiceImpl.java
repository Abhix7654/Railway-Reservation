package com.railway.train.service.service;

import com.railway.train.service.entity.Train;
import com.railway.train.service.repository.TrainRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository;

    public TrainServiceImpl(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Override
    public String addTrain(Train train) {
        trainRepository.save(train);
        return "Added Successfully ";
    }

    @Override
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @Override
    public String deleteTrain(Long id) {
        trainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Train not found with id: " + id));

        trainRepository.deleteById(id);
        return "Deleted Successfully";
    }


    @Override
    public String updateTrain(Long id, Train train) {
        Train existing = trainRepository.findById(id).orElseThrow();
        existing.setName(train.getName());
        existing.setSource(train.getSource());
        existing.setDestination(train.getDestination());
        existing.setDepartureTime(train.getDepartureTime());
        existing.setArrivalTime(train.getArrivalTime());
        existing.setFare(train.getFare());
        existing.setAvailableSeats(train.getAvailableSeats());
       trainRepository.save(existing);
        return "Updated Sucessfully";
    }
    @Override
    public Train getTrainById(Long id) {
        return trainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Train not found with id: " + id));
    }
    @Override
    public void updateAvailableSeats(Long id, int availableSeats) {
        Train train = trainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Train not found with id: " + id));
        train.setAvailableSeats(availableSeats);
        trainRepository.save(train);
    }


}
