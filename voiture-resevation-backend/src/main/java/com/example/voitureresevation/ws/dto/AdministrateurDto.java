package com.example.voitureresevation.ws.dto;

import com.example.voitureresevation.zsecurity.ws.dto.AppUserDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdministrateurDto extends AppUserDto {
    private Long id;
    private String nom;
    private String prenom;
    private int age;
    private String localite;
    private ReservationDto reservations;
    private OffreDto offres;
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

    public ReservationDto getReservations() {
        return reservations;
    }

    public void setReservations(ReservationDto value) {
        this.reservations = value;
    }

    public OffreDto getOffres() {
        return offres;
    }

    public void setOffres(OffreDto value) {
        this.offres = value;
    }

    public List<ContratDto> getContrat() {
        return contrat;
    }

    public void setContrat(List<ContratDto> value) {
        this.contrat = value;
    }
}