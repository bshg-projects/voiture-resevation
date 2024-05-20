package com.example.voitureresevation.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoitureDto {
    private Long id;
    private String marque;
    private String coleur;
    private String categorie;
    private int nbrPlaces;
    private int vitesse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String value) {
        this.marque = value;
    }

    public String getColeur() {
        return coleur;
    }

    public void setColeur(String value) {
        this.coleur = value;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String value) {
        this.categorie = value;
    }

    public int getNbrPlaces() {
        return nbrPlaces;
    }

    public void setNbrPlaces(int value) {
        this.nbrPlaces = value;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int value) {
        this.vitesse = value;
    }
}