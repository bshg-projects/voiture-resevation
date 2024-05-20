package com.example.voitureresevation.repository;

import com.example.voitureresevation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    int deleteByIdIn(List<Long> ids);
}