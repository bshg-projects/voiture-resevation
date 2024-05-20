package com.example.voitureresevation.entity;

import jakarta.persistence.*;

/**
 * This Class is for Association between 'Administrateur' and 'Client'
 */
@Entity
@Table(name = "contrat")
@SequenceGenerator(name = "contrat_seq", sequenceName = "contrat_seq", allocationSize = 1)
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contrat_seq")
    private Long id;
    private double prix;
    private int code;
    @ManyToOne(fetch = FetchType.LAZY)
    private Administrateur administrateur;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    public Contrat() {
    }

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

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur value) {
        this.administrateur = value;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client value) {
        this.client = value;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Contrat contrat) {
            return contrat.getId().equals(this.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }
}