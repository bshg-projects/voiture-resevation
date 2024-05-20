package com.example.voitureresevation.entity;

import com.example.voitureresevation.zsecurity.entity.AppUser;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "administrateur")
public class Administrateur extends AppUser {
    private String nom;
    private String prenom;
    private int age;
    private String localite;
    @ManyToOne(fetch = FetchType.LAZY)
    private Reservation reservations;
    @ManyToOne(fetch = FetchType.LAZY)
    private Offre offres;
    @OneToMany(mappedBy = "administrateur")
    private List<Contrat> contrat;

    public Administrateur() {
        super();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String value) {
        this.nom = value;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String value) {
        this.prenom = value;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int value) {
        this.age = value;
    }

    public String getLocalite() {
        return localite;
    }

    public void setLocalite(String value) {
        this.localite = value;
    }

    public Reservation getReservations() {
        return reservations;
    }

    public void setReservations(Reservation value) {
        this.reservations = value;
    }

    public Offre getOffres() {
        return offres;
    }

    public void setOffres(Offre value) {
        this.offres = value;
    }

    public List<Contrat> getContrat() {
        return contrat;
    }

    public void setContrat(List<Contrat> value) {
        this.contrat = value;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Administrateur administrateur) {
            return administrateur.getId().equals(this.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }
}