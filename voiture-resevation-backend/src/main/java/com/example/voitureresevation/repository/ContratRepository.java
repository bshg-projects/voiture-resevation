package com.example.voitureresevation.repository;

import com.example.voitureresevation.entity.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
    int deleteByIdIn(List<Long> ids);

    int deleteByAdministrateurId(Long id);

    List<Contrat> findByAdministrateurId(Long id);

    int deleteByClientId(Long id);

    List<Contrat> findByClientId(Long id);
}