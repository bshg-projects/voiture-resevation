package com.example.voitureresevation.repository;

import com.example.voitureresevation.entity.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {
    int deleteByIdIn(List<Long> ids);

    int deleteByReservationsId(Long id);

    List<Administrateur> findByReservationsId(Long id);

    int deleteByOffresId(Long id);

    List<Administrateur> findByOffresId(Long id);
}