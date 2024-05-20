package com.example.voitureresevation.service.facade;

import com.example.voitureresevation.entity.Voiture;
import com.example.voitureresevation.zutils.pagination.Pagination;

import java.util.List;

public interface VoitureService {
    Voiture findById(Long id);

    List<Voiture> findAllOptimized();

    List<Voiture> findAll();

    Pagination<Voiture> findPaginated(int page, int size);

    Voiture create(Voiture item);

    List<Voiture> create(List<Voiture> item);

    Voiture update(Voiture item);

    List<Voiture> update(List<Voiture> item);

    void deleteById(Long id);

    void delete(Voiture item);

    void delete(List<Voiture> items);

    void deleteByIdIn(List<Long> ids);
}