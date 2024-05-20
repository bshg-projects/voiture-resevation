package com.example.voitureresevation.service.impl;

import com.example.voitureresevation.entity.Contrat;
import com.example.voitureresevation.exception.NotFoundException;
import com.example.voitureresevation.repository.ContratRepository;
import com.example.voitureresevation.service.facade.AdministrateurService;
import com.example.voitureresevation.service.facade.ClientService;
import com.example.voitureresevation.service.facade.ContratService;
import com.example.voitureresevation.zutils.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContratServiceImpl implements ContratService {
    //--------------- FIND -------------------------------------
    public Contrat findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Contrat> findAll() {
        return repository.findAll();
    }

    public List<Contrat> findAllOptimized() {
        return findAll();
    }

    @Override
    public Pagination<Contrat> findPaginated(int page, int size) {
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
    public Contrat create(Contrat item) {
        if (item == null) return null;
// check if administrateur exists
        var administrateur = item.getAdministrateur();
        if (administrateur != null) {
            if (administrateur.getId() == null) item.setAdministrateur(null);
            else {
                var found = administrateurService.findById(administrateur.getId());
                if (found == null) throw new NotFoundException("Unknown Given Administrateur");
                item.setAdministrateur(found);
            }
        }
// check if client exists
        var client = item.getClient();
        if (client != null) {
            if (client.getId() == null) item.setClient(null);
            else {
                var found = clientService.findById(client.getId());
                if (found == null) throw new NotFoundException("Unknown Given Client");
                item.setClient(found);
            }
        }
        return repository.save(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Contrat> create(List<Contrat> items) {
        List<Contrat> result = new ArrayList<>();
        if (items == null || items.isEmpty()) return result;
        items.forEach(it -> result.add(create(it)));
        return result;
    }

    //--------------- UPDATE -----------------------------------
    @Transactional(rollbackFor = Exception.class)
    public Contrat update(Contrat item) {
        if (item == null || item.getId() == null) return null;
        var oldItem = findById(item.getId());
        if (oldItem == null) throw new NotFoundException("Unknown Contrat To Be Updated!");
        return repository.save(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Contrat> update(List<Contrat> items) {
        List<Contrat> result = new ArrayList<>();
        if (items == null || items.isEmpty()) return result;
        items.forEach(it -> result.add(update(it)));
        return result;
    }

    //--------------- DELETE -----------------------------------
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        Contrat item = findById(id);
        if (item != null) delete(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Contrat item) {
        repository.delete(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Contrat> items) {
        if (items == null || items.isEmpty()) return;
        items.forEach(this::delete);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteByIdIn(List<Long> ids) {
        repository.deleteByIdIn(ids);
    }

    //--------------- FIND AND DELETE BYs ----------------------
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByAdministrateurId(Long id) {
        if (id == null) return 0;
        return repository.deleteByAdministrateurId(id);
    }

    @Override
    public List<Contrat> findByAdministrateurId(Long id) {
        return repository.findByAdministrateurId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByClientId(Long id) {
        if (id == null) return 0;
        return repository.deleteByClientId(id);
    }

    @Override
    public List<Contrat> findByClientId(Long id) {
        return repository.findByClientId(id);
    }

    //----------------------------------------------------------
//----------------------------------------------------------
    @Autowired
    private ContratRepository repository;
    @Lazy
    @Autowired
    private AdministrateurService administrateurService;
    @Lazy
    @Autowired
    private ClientService clientService;
}