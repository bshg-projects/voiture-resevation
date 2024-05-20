package com.example.voitureresevation.ws.converter;

import com.example.voitureresevation.entity.Administrateur;
import com.example.voitureresevation.ws.dto.AdministrateurDto;
import com.example.voitureresevation.zsecurity.ws.converter.AppUserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdministrateurConverter {
    @Autowired
    private AppUserConverter appUserConverter;
    @Autowired
    private ReservationConverter reservationConverter;
    @Autowired
    private OffreConverter offreConverter;
    @Autowired
    private ContratConverter contratConverter;
    private boolean reservations = true;
    private boolean offres = true;
    private boolean contrat = true;

    protected void configure(boolean value) {
        this.contratConverter.setAdministrateur(value);
    }

    public final AdministrateurDto toDto(Administrateur item) {
        this.configure(false);
        var dto = item != null ? convertToDto(item) : null;
        this.configure(true);
        return dto;
    }

    public final Administrateur toItem(AdministrateurDto d) {
        return d != null ? convertToItem(d) : null;
    }

    public final List<Administrateur> toItem(List<AdministrateurDto> dtos) {
        if (dtos == null) return null;
        List<Administrateur> list = new ArrayList<>();
        dtos.forEach(it -> list.add(toItem(it)));
        return list;
    }

    public final List<AdministrateurDto> toDto(List<Administrateur> items) {
        if (items == null) return null;
        List<AdministrateurDto> list = new ArrayList<>();
        items.forEach(it -> list.add(toDto(it)));
        return list;
    }

    protected Administrateur convertToItem(AdministrateurDto dto) {
        var item = new Administrateur();
        appUserConverter.convertToChildItem(dto, item);
        item.setNom(dto.getNom());
        item.setPrenom(dto.getPrenom());
        item.setAge(dto.getAge());
        item.setLocalite(dto.getLocalite());
        item.setReservations(reservationConverter.toItem(dto.getReservations()));
        item.setOffres(offreConverter.toItem(dto.getOffres()));
        item.setContrat(contratConverter.toItem(dto.getContrat()));
        return item;
    }

    protected AdministrateurDto convertToDto(Administrateur item) {
        var dto = new AdministrateurDto();
        appUserConverter.convertToChildDto(item, dto);
        dto.setNom(item.getNom());
        dto.setPrenom(item.getPrenom());
        dto.setAge(item.getAge());
        dto.setLocalite(item.getLocalite());
        dto.setReservations(reservations ? reservationConverter.toDto(item.getReservations()) : null);
        dto.setOffres(offres ? offreConverter.toDto(item.getOffres()) : null);
        dto.setContrat(contrat ? contratConverter.toDto(item.getContrat()) : null);
        return dto;
    }

    public void setReservations(boolean value) {
        this.reservations = value;
    }

    public void setOffres(boolean value) {
        this.offres = value;
    }

    public void setContrat(boolean value) {
        this.contrat = value;
    }

    public void setReservationConverter(ReservationConverter value) {
        this.reservationConverter = value;
    }

    public ReservationConverter getReservationConverter() {
        return reservationConverter;
    }

    public void setOffreConverter(OffreConverter value) {
        this.offreConverter = value;
    }

    public OffreConverter getOffreConverter() {
        return offreConverter;
    }

    public void setContratConverter(ContratConverter value) {
        this.contratConverter = value;
    }

    public ContratConverter getContratConverter() {
        return contratConverter;
    }
}