package com.example.voitureresevation.ws.converter;

import com.example.voitureresevation.entity.Reservation;
import com.example.voitureresevation.ws.dto.ReservationDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationConverter {
    protected void configure(boolean value) {
    }

    public final ReservationDto toDto(Reservation item) {
        this.configure(false);
        var dto = item != null ? convertToDto(item) : null;
        this.configure(true);
        return dto;
    }

    public final Reservation toItem(ReservationDto d) {
        return d != null ? convertToItem(d) : null;
    }

    public final List<Reservation> toItem(List<ReservationDto> dtos) {
        if (dtos == null) return null;
        List<Reservation> list = new ArrayList<>();
        dtos.forEach(it -> list.add(toItem(it)));
        return list;
    }

    public final List<ReservationDto> toDto(List<Reservation> items) {
        if (items == null) return null;
        List<ReservationDto> list = new ArrayList<>();
        items.forEach(it -> list.add(toDto(it)));
        return list;
    }

    protected Reservation convertToItem(ReservationDto dto) {
        var item = new Reservation();
        item.setId(dto.getId());
        item.setDateDebut(dto.getDateDebut());
        item.setDateFin(dto.getDateFin());
        return item;
    }

    protected ReservationDto convertToDto(Reservation item) {
        var dto = new ReservationDto();
        dto.setId(item.getId());
        dto.setDateDebut(item.getDateDebut());
        dto.setDateFin(item.getDateFin());
        return dto;
    }
}