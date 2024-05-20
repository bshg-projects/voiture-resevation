package com.example.voitureresevation.repository;

import com.example.voitureresevation.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    int deleteByIdIn(List<Long> ids);
}