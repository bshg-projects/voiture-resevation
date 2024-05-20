package com.example.voitureresevation.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto {
    private Long id;
    private String nom;
    private String prenom;
    private int age;
    private String localite;
    private String adresse;
    private LocalDate datePromis;
    private List<ContratDto> contrat;

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

    public List<ContratDto> getContrat() {
        return contrat;
    }

    public void setContrat(List<ContratDto> value) {
        this.contrat = value;
    }
}