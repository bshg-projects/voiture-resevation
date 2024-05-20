package com.example.voitureresevation.repository;

import com.example.voitureresevation.entity.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OffreRepository extends JpaRepository<Offre, Long> {
    int deleteByIdIn(List<Long> ids);
}