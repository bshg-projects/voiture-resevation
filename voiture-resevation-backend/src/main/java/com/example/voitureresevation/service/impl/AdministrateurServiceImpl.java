package com.example.voitureresevation.service.impl;

import com.example.voitureresevation.entity.Administrateur;
import com.example.voitureresevation.entity.Contrat;
import com.example.voitureresevation.exception.NotFoundException;
import com.example.voitureresevation.repository.AdministrateurRepository;
import com.example.voitureresevation.service.facade.AdministrateurService;
import com.example.voitureresevation.service.facade.ContratService;
import com.example.voitureresevation.service.facade.OffreService;
import com.example.voitureresevation.service.facade.ReservationService;
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
public class AdministrateurServiceImpl implements AdministrateurService {
    //--------------- FIND -------------------------------------
    public Administrateur findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Administrateur> findAll() {
        return repository.findAll();
    }

    public List<Administrateur> findAllOptimized() {
        return findAll();
    }

    @Override
    public Pagination<Administrateur> findPaginated(int page, int size) {
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
    public Administrateur create(Administrateur item) {
        if (item == null) return null;
// check if reservations exists
        var reservations = item.getReservations();
        if (reservations != null) {
            if (reservations.getId() == null) item.setReservations(null);
            else {
                var found = reservationService.findById(reservations.getId());
                if (found == null) throw new NotFoundException("Unknown Given Reservations");
                item.setReservations(found);
            }
        }
// check if offres exists
        var offres = item.getOffres();
        if (offres != null) {
            if (offres.getId() == null) item.setOffres(null);
            else {
                var found = offreService.findById(offres.getId());
                if (found == null) throw new NotFoundException("Unknown Given Offres");
                item.setOffres(found);
            }
        }
        Administrateur saved = repository.save(item);
        createAssociatedList(saved);
        return saved;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Administrateur> create(List<Administrateur> items) {
        List<Administrateur> result = new ArrayList<>();
        if (items == null || items.isEmpty()) return result;
        items.forEach(it -> result.add(create(it)));
        return result;
    }

    //--------------- UPDATE -----------------------------------
    @Transactional(rollbackFor = Exception.class)
    public Administrateur update(Administrateur item) {
        if (item == null || item.getId() == null) return null;
        var oldItem = findById(item.getId());
        if (oldItem == null) throw new NotFoundException("Unknown Administrateur To Be Updated!");
        Administrateur saved = repository.save(item);
        updateAssociatedList(saved);
        return saved;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Administrateur> update(List<Administrateur> items) {
        List<Administrateur> result = new ArrayList<>();
        if (items == null || items.isEmpty()) return result;
        items.forEach(it -> result.add(update(it)));
        return result;
    }

    //--------------- DELETE -----------------------------------
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        Administrateur item = findById(id);
        if (item != null) delete(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Administrateur item) {
        deleteAssociated(item);
        repository.delete(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Administrateur> items) {
        if (items == null || items.isEmpty()) return;
        items.forEach(this::delete);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteByIdIn(List<Long> ids) {
        ids.forEach(id -> {
            Administrateur item = findById(id);
            if (item != null) {
                deleteAssociated(item);
            }
        });
        repository.deleteByIdIn(ids);
    }

    //--------------- FIND AND DELETE BYs ----------------------
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByReservationsId(Long id) {
        if (id == null) return 0;
        List<Administrateur> found = findByReservationsId(id);
        if (found == null) return 0;
        found.forEach(this::deleteAssociated);
        return repository.deleteByReservationsId(id);
    }

    @Override
    public List<Administrateur> findByReservationsId(Long id) {
        return repository.findByReservationsId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByOffresId(Long id) {
        if (id == null) return 0;
        List<Administrateur> found = findByOffresId(id);
        if (found == null) return 0;
        found.forEach(this::deleteAssociated);
        return repository.deleteByOffresId(id);
    }

    @Override
    public List<Administrateur> findByOffresId(Long id) {
        return repository.findByOffresId(id);
    }

    //----------------------------------------------------------
    public void createAssociatedList(Administrateur item) {
        if (item == null || item.getId() == null) return;
        ServiceHelper.createList(item, Administrateur::getContrat, Contrat::setAdministrateur, contratService::create);
    }

    public void updateAssociatedList(Administrateur item) {
        if (item == null || item.getId() == null) return;
        ServiceHelper.updateList(
                item, contratService.findByAdministrateurId(item.getId()),
                item.getContrat(), Contrat::setAdministrateur,
                contratService::update,
                contratService::delete
        );
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAssociated(Administrateur item) {
        deleteAssociatedList(item);
    }

    public void deleteAssociatedList(Administrateur item) {
        contratService.deleteByAdministrateurId(item.getId());
    }

    //----------------------------------------------------------
    @Autowired
    private AdministrateurRepository repository;
    @Lazy
    @Autowired
    private ReservationService reservationService;
    @Lazy
    @Autowired
    private OffreService offreService;
    @Lazy
    @Autowired
    private ContratService contratService;
}