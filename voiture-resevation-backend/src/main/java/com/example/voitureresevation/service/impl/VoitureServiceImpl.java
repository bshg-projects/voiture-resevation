package com.example.voitureresevation.service.impl;

import com.example.voitureresevation.entity.Voiture;
import com.example.voitureresevation.exception.NotFoundException;
import com.example.voitureresevation.repository.VoitureRepository;
import com.example.voitureresevation.service.facade.VoitureService;
import com.example.voitureresevation.zutils.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoitureServiceImpl implements VoitureService {
    //--------------- FIND -------------------------------------
    public Voiture findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Voiture> findAll() {
        return repository.findAll();
    }

    public List<Voiture> findAllOptimized() {
        return findAll();
    }

    @Override
    public Pagination<Voiture> findPaginated(int page, int size) {
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
    public Voiture create(Voiture item) {
        if (item == null) return null;
        return repository.save(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Voiture> create(List<Voiture> items) {
        List<Voiture> result = new ArrayList<>();
        if (items == null || items.isEmpty()) return result;
        items.forEach(it -> result.add(create(it)));
        return result;
    }

    //--------------- UPDATE -----------------------------------
    @Transactional(rollbackFor = Exception.class)
    public Voiture update(Voiture item) {
        if (item == null || item.getId() == null) return null;
        var oldItem = findById(item.getId());
        if (oldItem == null) throw new NotFoundException("Unknown Voiture To Be Updated!");
        return repository.save(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Voiture> update(List<Voiture> items) {
        List<Voiture> result = new ArrayList<>();
        if (items == null || items.isEmpty()) return result;
        items.forEach(it -> result.add(update(it)));
        return result;
    }

    //--------------- DELETE -----------------------------------
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        Voiture item = findById(id);
        if (item != null) delete(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Voiture item) {
        repository.delete(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Voiture> items) {
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
    private VoitureRepository repository;
}