package com.railway.train.service.repository;

import com.railway.train.service.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrainRepository extends JpaRepository<Train, Long> {
    @Modifying
    @Query("UPDATE Train t SET t.availableSeats = :seats WHERE t.id = :trainId")
    void updateAvailableSeats(@Param("trainId") Long trainId, @Param("seats") int seats);

}
