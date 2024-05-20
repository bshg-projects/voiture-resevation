package com.example.voitureresevation.service.facade;

import com.example.voitureresevation.entity.Client;
import com.example.voitureresevation.zutils.pagination.Pagination;

import java.util.List;

public interface ClientService {
    Client findById(Long id);

    List<Client> findAllOptimized();

    List<Client> findAll();

    Pagination<Client> findPaginated(int page, int size);

    Client create(Client item);

    List<Client> create(List<Client> item);

    Client update(Client item);

    List<Client> update(List<Client> item);

    void deleteById(Long id);

    void delete(Client item);

    void delete(List<Client> items);

    void deleteByIdIn(List<Long> ids);
}