package com.example.model;

import java.util.Date;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="Expiration")
@Transactional
public class Expiration {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expiration_generator")
    Long idExpiration;
    Date dateDebut ;
    Date dateFin;
    public Expiration() {
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "vehicule_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Avion avion;

    public Expiration( Date dateDebut, Date dateFin, Avion avion) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.avion = avion;
    }

    public Long getIdExpiration() {
        return this.idExpiration;
    }

    public void setIdExpiration(Long idExpiration) {
        this.idExpiration = idExpiration;
    }

    public Date getDateDebut() {
        return this.dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return this.dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Avion getAvion() {
        return avion;
    }

    public static ArrayList<Expiration> expireAt(List<Expiration> list, int mois){
        Date androany=new Date();
        ArrayList<Expiration>ex=new ArrayList<Expiration>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getDateFin().getMonth()==(androany.getMonth()+mois)){
                ex.add(list.get(i));
            }
        }
        return ex;
    }

}
