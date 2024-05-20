package com.example.voitureresevation.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@SequenceGenerator(name = "reservation_seq", sequenceName = "reservation_seq", allocationSize = 1)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_seq")
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate value) {
        this.dateDebut = value;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate value) {
        this.dateFin = value;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Reservation reservation) {
            return reservation.getId().equals(this.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }
}