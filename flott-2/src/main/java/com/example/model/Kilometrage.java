package com.example.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.transaction.annotation.Transactional;
@Entity
@Table(name="Kilometrage")
@Transactional
public class Kilometrage {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kilometrage_generator")
	Long idkilometrage;
	Date daterealize;
	double debutkm;
	double finkm;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "avion_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	Avion avion;
	public Long getIdkilometrage() {
		return idkilometrage;
	}
	public void setIdkilometrage(Long idkilometrage) {
		this.idkilometrage = idkilometrage;
	}
	public Date getDaterealize() {
		return daterealize;
	}
	public void setDaterealize(Date daterealize) {
		this.daterealize = daterealize;
	}
	public double getDebutkm() {
		return debutkm;
	}
	public void setDebutkm(double debutkm) {
		this.debutkm = debutkm;
	}
	public double getFinkm() {
		return finkm;
	}
	public void setFinkm(double finkm) {
		this.finkm = finkm;
	}
	public Avion getVehicule() {
		return avion;
	}
	public void setIdVehicule(Avion idVehicule) {
		this.avion = idVehicule;
	}
	public Kilometrage(Date daterealize, double debutkm, double finkm, Avion idAvion) {
		super();
		this.daterealize = daterealize;
		this.debutkm = debutkm;
		this.finkm = finkm;
		this.avion = idAvion;
	}
	public Kilometrage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	
}
