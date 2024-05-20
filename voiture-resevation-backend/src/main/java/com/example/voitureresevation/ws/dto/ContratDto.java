package com.example.voitureresevation.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContratDto {
    private Long id;
    private double prix;
    private int code;
    private AdministrateurDto administrateur;
    private ClientDto client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double value) {
        this.prix = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int value) {
        this.code = value;
    }

    public AdministrateurDto getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(AdministrateurDto value) {
        this.administrateur = value;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto value) {
        this.client = value;
    }
}