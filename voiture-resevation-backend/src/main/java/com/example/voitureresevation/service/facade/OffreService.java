package com.example.voitureresevation.service.facade;

import com.example.voitureresevation.entity.Offre;
import com.example.voitureresevation.zutils.pagination.Pagination;

import java.util.List;

public interface OffreService {
    Offre findById(Long id);

    List<Offre> findAllOptimized();

    List<Offre> findAll();

    Pagination<Offre> findPaginated(int page, int size);

    Offre create(Offre item);

    List<Offre> create(List<Offre> item);

    Offre update(Offre item);

    List<Offre> update(List<Offre> item);

    void deleteById(Long id);

    void delete(Offre item);

    void delete(List<Offre> items);

    void deleteByIdIn(List<Long> ids);
}