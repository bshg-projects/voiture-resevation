package com.example.voitureresevation.repository;

import com.example.voitureresevation.entity.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {
    int deleteByIdIn(List<Long> ids);
}