package com.example.voitureresevation.service.facade;

import com.example.voitureresevation.entity.Contrat;
import com.example.voitureresevation.zutils.pagination.Pagination;

import java.util.List;

public interface ContratService {
    Contrat findById(Long id);

    List<Contrat> findAllOptimized();

    List<Contrat> findAll();

    Pagination<Contrat> findPaginated(int page, int size);

    Contrat create(Contrat item);

    List<Contrat> create(List<Contrat> item);

    Contrat update(Contrat item);

    List<Contrat> update(List<Contrat> item);

    void deleteById(Long id);

    void delete(Contrat item);

    void delete(List<Contrat> items);

    void deleteByIdIn(List<Long> ids);

    int deleteByAdministrateurId(Long id);

    List<Contrat> findByAdministrateurId(Long id);

    int deleteByClientId(Long id);

    List<Contrat> findByClientId(Long id);
}