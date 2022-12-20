package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Avion")
public class Avion  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicule_generator")
    Long idAvion;

    String modele;
    String img;
    public Long getIdAvion() {
        return idAvion;
    }
    public void setIdAvion(Long idAvion) {
        this.idAvion= idAvion;
    }

    public Avion(Long idAvion, String modele,String img) {
        super();
        this.modele=modele;
        this.idAvion = idAvion;
        this.img=img;
    }
    public Avion() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }
}
