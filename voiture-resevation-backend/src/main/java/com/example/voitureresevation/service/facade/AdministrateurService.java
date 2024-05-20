package com.example.voitureresevation.service.facade;

import com.example.voitureresevation.entity.Administrateur;
import com.example.voitureresevation.zutils.pagination.Pagination;

import java.util.List;

public interface AdministrateurService {
    Administrateur findById(Long id);

    List<Administrateur> findAllOptimized();

    List<Administrateur> findAll();

    Pagination<Administrateur> findPaginated(int page, int size);

    Administrateur create(Administrateur item);

    List<Administrateur> create(List<Administrateur> item);

    Administrateur update(Administrateur item);

    List<Administrateur> update(List<Administrateur> item);

    void deleteById(Long id);

    void delete(Administrateur item);

    void delete(List<Administrateur> items);

    void deleteByIdIn(List<Long> ids);

    int deleteByReservationsId(Long id);

    List<Administrateur> findByReservationsId(Long id);

    int deleteByOffresId(Long id);

    List<Administrateur> findByOffresId(Long id);
}