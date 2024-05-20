package com.example.voitureresevation.service.impl;

import com.example.voitureresevation.entity.Reservation;
import com.example.voitureresevation.exception.NotFoundException;
import com.example.voitureresevation.repository.ReservationRepository;
import com.example.voitureresevation.service.facade.ReservationService;
import com.example.voitureresevation.zutils.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    //--------------- FIND -------------------------------------
    public Reservation findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Reservation> findAll() {
        return repository.findAll();
    }

    public List<Reservation> findAllOptimized() {
        return findAll();
    }

    @Override
    public Pagination<Reservation> findPaginated(int page, int size) {
        var pageable = PageRequest.of(page, size);
        var found = repository.findAll(pageable);
        var items = found.stream().toList();
        return new Pagination<>(
                items,
                found.getNumber(),
                found.getSize(),
                found.getTotalElements(),
                found.getTotalPages(),
                found.isFirst(),
                found.isLast()
        );
    }

    //--------------- CREATE -----------------------------------
    @Transactional(rollbackFor = Exception.class)
    public Reservation create(Reservation item) {
        if (item == null) return null;
        return repository.save(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Reservation> create(List<Reservation> items) {
        List<Reservation> result = new ArrayList<>();
        if (items == null || items.isEmpty()) return result;
        items.forEach(it -> result.add(create(it)));
        return result;
    }

    //--------------- UPDATE -----------------------------------
    @Transactional(rollbackFor = Exception.class)
    public Reservation update(Reservation item) {
        if (item == null || item.getId() == null) return null;
        var oldItem = findById(item.getId());
        if (oldItem == null) throw new NotFoundException("Unknown Reservation To Be Updated!");
        return repository.save(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Reservation> update(List<Reservation> items) {
        List<Reservation> result = new ArrayList<>();
        if (items == null || items.isEmpty()) return result;
        items.forEach(it -> result.add(update(it)));
        return result;
    }

    //--------------- DELETE -----------------------------------
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        Reservation item = findById(id);
        if (item != null) delete(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Reservation item) {
        repository.delete(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Reservation> items) {
        if (items == null || items.isEmpty()) return;
        items.forEach(this::delete);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteByIdIn(List<Long> ids) {
        repository.deleteByIdIn(ids);
    }

    //--------------- FIND AND DELETE BYs ----------------------
//----------------------------------------------------------
//----------------------------------------------------------
    @Autowired
    private ReservationRepository repository;
}