package com.example.voitureresevation.ws.converter;

import com.example.voitureresevation.entity.Contrat;
import com.example.voitureresevation.ws.dto.ContratDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContratConverter {
    @Autowired
    private AdministrateurConverter administrateurConverter;
    @Autowired
    private ClientConverter clientConverter;
    private boolean administrateur = true;
    private boolean client = true;

    protected void configure(boolean value) {
        this.administrateurConverter.setContrat(value);
        this.clientConverter.setContrat(value);
    }

    public final ContratDto toDto(Contrat item) {
        this.configure(false);
        var dto = item != null ? convertToDto(item) : null;
        this.configure(true);
        return dto;
    }

    public final Contrat toItem(ContratDto d) {
        return d != null ? convertToItem(d) : null;
    }

    public final List<Contrat> toItem(List<ContratDto> dtos) {
        if (dtos == null) return null;
        List<Contrat> list = new ArrayList<>();
        dtos.forEach(it -> list.add(toItem(it)));
        return list;
    }

    public final List<ContratDto> toDto(List<Contrat> items) {
        if (items == null) return null;
        List<ContratDto> list = new ArrayList<>();
        items.forEach(it -> list.add(toDto(it)));
        return list;
    }

    protected Contrat convertToItem(ContratDto dto) {
        var item = new Contrat();
        item.setId(dto.getId());
        item.setPrix(dto.getPrix());
        item.setCode(dto.getCode());
        item.setAdministrateur(administrateurConverter.toItem(dto.getAdministrateur()));
        item.setClient(clientConverter.toItem(dto.getClient()));
        return item;
    }

    protected ContratDto convertToDto(Contrat item) {
        var dto = new ContratDto();
        dto.setId(item.getId());
        dto.setPrix(item.getPrix());
        dto.setCode(item.getCode());
        dto.setAdministrateur(administrateur ? administrateurConverter.toDto(item.getAdministrateur()) : null);
        dto.setClient(client ? clientConverter.toDto(item.getClient()) : null);
        return dto;
    }

    public void setAdministrateur(boolean value) {
        this.administrateur = value;
    }

    public void setClient(boolean value) {
        this.client = value;
    }

    public void setAdministrateurConverter(AdministrateurConverter value) {
        this.administrateurConverter = value;
    }

    public AdministrateurConverter getAdministrateurConverter() {
        return administrateurConverter;
    }

    public void setClientConverter(ClientConverter value) {
        this.clientConverter = value;
    }

    public ClientConverter getClientConverter() {
        return clientConverter;
    }
}