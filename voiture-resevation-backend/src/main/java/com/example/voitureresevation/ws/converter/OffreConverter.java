package com.example.voitureresevation.ws.converter;

import com.example.voitureresevation.entity.Offre;
import com.example.voitureresevation.ws.dto.OffreDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OffreConverter {
    protected void configure(boolean value) {
    }

    public final OffreDto toDto(Offre item) {
        this.configure(false);
        var dto = item != null ? convertToDto(item) : null;
        this.configure(true);
        return dto;
    }

    public final Offre toItem(OffreDto d) {
        return d != null ? convertToItem(d) : null;
    }

    public final List<Offre> toItem(List<OffreDto> dtos) {
        if (dtos == null) return null;
        List<Offre> list = new ArrayList<>();
        dtos.forEach(it -> list.add(toItem(it)));
        return list;
    }

    public final List<OffreDto> toDto(List<Offre> items) {
        if (items == null) return null;
        List<OffreDto> list = new ArrayList<>();
        items.forEach(it -> list.add(toDto(it)));
        return list;
    }

    protected Offre convertToItem(OffreDto dto) {
        var item = new Offre();
        item.setId(dto.getId());
        item.setCode(dto.getCode());
        return item;
    }

    protected OffreDto convertToDto(Offre item) {
        var dto = new OffreDto();
        dto.setId(item.getId());
        dto.setCode(item.getCode());
        return dto;
    }
}