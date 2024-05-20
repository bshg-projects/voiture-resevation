package com.example.voitureresevation.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "voiture")
@SequenceGenerator(name = "voiture_seq", sequenceName = "voiture_seq", allocationSize = 1)
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voiture_seq")
    private Long id;
    private String marque;
    private String coleur;
    private String categorie;
    private int nbrPlaces;
    private int vitesse;

    public Voiture() {
    }

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

    @Override
    public boolean equals(Object object) {
        if (object instanceof Voiture voiture) {
            return voiture.getId().equals(this.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }
}