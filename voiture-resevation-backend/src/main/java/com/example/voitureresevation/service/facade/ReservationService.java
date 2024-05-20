package com.example.voitureresevation.service.facade;

import com.example.voitureresevation.entity.Reservation;
import com.example.voitureresevation.zutils.pagination.Pagination;

import java.util.List;

public interface ReservationService {
    Reservation findById(Long id);

    List<Reservation> findAllOptimized();

    List<Reservation> findAll();

    Pagination<Reservation> findPaginated(int page, int size);

    Reservation create(Reservation item);

    List<Reservation> create(List<Reservation> item);

    Reservation update(Reservation item);

    List<Reservation> update(List<Reservation> item);

    void deleteById(Long id);

    void delete(Reservation item);

    void delete(List<Reservation> items);

    void deleteByIdIn(List<Long> ids);
}