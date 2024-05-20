package com.example.voitureresevation.service.impl;

import com.example.voitureresevation.entity.Client;
import com.example.voitureresevation.entity.Contrat;
import com.example.voitureresevation.exception.NotFoundException;
import com.example.voitureresevation.repository.ClientRepository;
import com.example.voitureresevation.service.facade.ClientService;
import com.example.voitureresevation.service.facade.ContratService;
import com.example.voitureresevation.zutils.pagination.Pagination;
import com.example.voitureresevation.zutils.service.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    //--------------- FIND -------------------------------------
    public Client findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public List<Client> findAllOptimized() {
        return findAll();
    }

    @Override
    public Pagination<Client> findPaginated(int page, int size) {
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
    public Client create(Client item) {
        if (item == null) return null;
        Client saved = repository.save(item);
        createAssociatedList(saved);
        return saved;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Client> create(List<Client> items) {
        List<Client> result = new ArrayList<>();
        if (items == null || items.isEmpty()) return result;
        items.forEach(it -> result.add(create(it)));
        return result;
    }

    //--------------- UPDATE -----------------------------------
    @Transactional(rollbackFor = Exception.class)
    public Client update(Client item) {
        if (item == null || item.getId() == null) return null;
        var oldItem = findById(item.getId());
        if (oldItem == null) throw new NotFoundException("Unknown Client To Be Updated!");
        Client saved = repository.save(item);
        updateAssociatedList(saved);
        return saved;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Client> update(List<Client> items) {
        List<Client> result = new ArrayList<>();
        if (items == null || items.isEmpty()) return result;
        items.forEach(it -> result.add(update(it)));
        return result;
    }

    //--------------- DELETE -----------------------------------
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        Client item = findById(id);
        if (item != null) delete(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Client item) {
        deleteAssociated(item);
        repository.delete(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Client> items) {
        if (items == null || items.isEmpty()) return;
        items.forEach(this::delete);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteByIdIn(List<Long> ids) {
        ids.forEach(id -> {
            Client item = findById(id);
            if (item != null) {
                deleteAssociated(item);
            }
        });
        repository.deleteByIdIn(ids);
    }

    //--------------- FIND AND DELETE BYs ----------------------
//----------------------------------------------------------
    public void createAssociatedList(Client item) {
        if (item == null || item.getId() == null) return;
        ServiceHelper.createList(item, Client::getContrat, Contrat::setClient, contratService::create);
    }

    public void updateAssociatedList(Client item) {
        if (item == null || item.getId() == null) return;
        ServiceHelper.updateList(
                item, contratService.findByClientId(item.getId()),
                item.getContrat(), Contrat::setClient,
                contratService::update,
                contratService::delete
        );
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAssociated(Client item) {
        deleteAssociatedList(item);
    }

    public void deleteAssociatedList(Client item) {
        contratService.deleteByClientId(item.getId());
    }

    //----------------------------------------------------------
    @Autowired
    private ClientRepository repository;
    @Lazy
    @Autowired
    private ContratService contratService;
}