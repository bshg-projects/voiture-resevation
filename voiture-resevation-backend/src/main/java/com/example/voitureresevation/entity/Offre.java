package com.example.voitureresevation.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "offre")
@SequenceGenerator(name = "offre_seq", sequenceName = "offre_seq", allocationSize = 1)
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offre_seq")
    private Long id;
    private String code;

    public Offre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        this.code = value;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Offre offre) {
            return offre.getId().equals(this.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }
}