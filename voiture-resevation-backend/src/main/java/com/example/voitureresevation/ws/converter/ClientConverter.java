package com.example.voitureresevation.ws.converter;

import com.example.voitureresevation.entity.Client;
import com.example.voitureresevation.ws.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientConverter {
    @Autowired
    private ContratConverter contratConverter;
    private boolean contrat = true;

    protected void configure(boolean value) {
        this.contratConverter.setClient(value);
    }

    public final ClientDto toDto(Client item) {
        this.configure(false);
        var dto = item != null ? convertToDto(item) : null;
        this.configure(true);
        return dto;
    }

    public final Client toItem(ClientDto d) {
        return d != null ? convertToItem(d) : null;
    }

    public final List<Client> toItem(List<ClientDto> dtos) {
        if (dtos == null) return null;
        List<Client> list = new ArrayList<>();
        dtos.forEach(it -> list.add(toItem(it)));
        return list;
    }

    public final List<ClientDto> toDto(List<Client> items) {
        if (items == null) return null;
        List<ClientDto> list = new ArrayList<>();
        items.forEach(it -> list.add(toDto(it)));
        return list;
    }

    protected Client convertToItem(ClientDto dto) {
        var item = new Client();
        item.setId(dto.getId());
        item.setNom(dto.getNom());
        item.setPrenom(dto.getPrenom());
        item.setAge(dto.getAge());
        item.setLocalite(dto.getLocalite());
        item.setAdresse(dto.getAdresse());
        item.setDatePromis(dto.getDatePromis());
        item.setContrat(contratConverter.toItem(dto.getContrat()));
        return item;
    }

    protected ClientDto convertToDto(Client item) {
        var dto = new ClientDto();
        dto.setId(item.getId());
        dto.setNom(item.getNom());
        dto.setPrenom(item.getPrenom());
        dto.setAge(item.getAge());
        dto.setLocalite(item.getLocalite());
        dto.setAdresse(item.getAdresse());
        dto.setDatePromis(item.getDatePromis());
        dto.setContrat(contrat ? contratConverter.toDto(item.getContrat()) : null);
        return dto;
    }

    public void setContrat(boolean value) {
        this.contrat = value;
    }

    public void setContratConverter(ContratConverter value) {
        this.contratConverter = value;
    }

    public ContratConverter getContratConverter() {
        return contratConverter;
    }
}