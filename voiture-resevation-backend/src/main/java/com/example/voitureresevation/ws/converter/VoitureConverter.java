package com.example.voitureresevation.ws.converter;

import com.example.voitureresevation.entity.Voiture;
import com.example.voitureresevation.ws.dto.VoitureDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VoitureConverter {
    protected void configure(boolean value) {
    }

    public final VoitureDto toDto(Voiture item) {
        this.configure(false);
        var dto = item != null ? convertToDto(item) : null;
        this.configure(true);
        return dto;
    }

    public final Voiture toItem(VoitureDto d) {
        return d != null ? convertToItem(d) : null;
    }

    public final List<Voiture> toItem(List<VoitureDto> dtos) {
        if (dtos == null) return null;
        List<Voiture> list = new ArrayList<>();
        dtos.forEach(it -> list.add(toItem(it)));
        return list;
    }

    public final List<VoitureDto> toDto(List<Voiture> items) {
        if (items == null) return null;
        List<VoitureDto> list = new ArrayList<>();
        items.forEach(it -> list.add(toDto(it)));
        return list;
    }

    protected Voiture convertToItem(VoitureDto dto) {
        var item = new Voiture();
        item.setId(dto.getId());
        item.setMarque(dto.getMarque());
        item.setColeur(dto.getColeur());
        item.setCategorie(dto.getCategorie());
        item.setNbrPlaces(dto.getNbrPlaces());
        item.setVitesse(dto.getVitesse());
        return item;
    }

    protected VoitureDto convertToDto(Voiture item) {
        var dto = new VoitureDto();
        dto.setId(item.getId());
        dto.setMarque(item.getMarque());
        dto.setColeur(item.getColeur());
        dto.setCategorie(item.getCategorie());
        dto.setNbrPlaces(item.getNbrPlaces());
        dto.setVitesse(item.getVitesse());
        return dto;
    }
}