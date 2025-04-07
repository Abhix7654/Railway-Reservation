package com.railway.reservation.service.service;

import com.railway.reservation.service.dto.Train;
import com.railway.reservation.service.entity.Reservation;
import com.railway.reservation.service.feign.TrainClient;
import com.railway.reservation.service.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private TrainClient trainClient;

    @Autowired
    private ReservationRepository repository;

    @Override
    public Reservation bookReservation(Reservation reservation) {
        reservation.setReservationDate(LocalDate.now());
        reservation.setStatus("PENDING");
        Train train = trainClient.getTrainById(reservation.getTrainId());

        double totalFare = train.getFare() * reservation.getNumberOfSeats();
        reservation.setFare(totalFare);
        String pnr = "PNR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        reservation.setPnr(pnr);



        return repository.save(reservation);
    }

    @Override
    public Reservation getReservationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));
    }

    @Override
    public List<Reservation> getAllReservations() {
        return repository.findAll();
    }

    @Override
    public String cancelReservation(Long id) {
        Reservation reservation = getReservationById(id);

        if ("CONFIRMED".equalsIgnoreCase(reservation.getStatus())) {
            Train train = trainClient.getTrainById(reservation.getTrainId());
            trainClient.updateAvailableSeats(reservation.getTrainId(),
                    train.getAvailableSeats() + reservation.getNumberOfSeats());
        }

        reservation.setStatus("CANCELLED");
        repository.save(reservation);
        return "Reservation cancelled successfully";

    }

    @Override
    public void updateStatusAfterPayment(Long id){
        Reservation reservation = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with ID: " + id));

        Train train = trainClient.getTrainById(reservation.getTrainId());
        if (train == null) {
            throw new RuntimeException("Train not found with ID: " + reservation.getTrainId());
        }

        if (train.getAvailableSeats() >= reservation.getNumberOfSeats()) {
            trainClient.updateAvailableSeats(reservation.getTrainId(),
                    train.getAvailableSeats() - reservation.getNumberOfSeats());
            reservation.setStatus("CONFIRMED");
        } else {
            reservation.setStatus("WAITING");
        }

      repository.save(reservation);

    }
}
