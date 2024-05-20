package com.example.voitureresevation.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "client")
@SequenceGenerator(name = "client_seq", sequenceName = "client_seq", allocationSize = 1)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    private Long id;
    private String nom;
    private String prenom;
    private int age;
    private String localite;
    private String adresse;
    private LocalDate datePromis;
    @OneToMany(mappedBy = "client")
    private List<Contrat> contrat;

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String value) {
        this.adresse = value;
    }

    public LocalDate getDatePromis() {
        return datePromis;
    }

    public void setDatePromis(LocalDate value) {
        this.datePromis = value;
    }

    public List<Contrat> getContrat() {
        return contrat;
    }

    public void setContrat(List<Contrat> value) {
        this.contrat = value;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Client client) {
            return client.getId().equals(this.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }
}