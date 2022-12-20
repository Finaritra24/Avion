package com.example.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Log")
public class Log {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_generator")
	Long identifiant;
	String mdp;
	Date entre;
	public Date getEntre() {
		return entre;
	}
	public void setEntre(Date entre) {
		this.entre = entre;
	}
	public Long getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(Long identifiant) {
		this.identifiant = identifiant;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public Log(String mdp) {
		super();
		this.mdp = mdp;
	}
	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Log(Long identifiant, String mdp, Date entre) {
		super();
		this.identifiant = identifiant;
		this.mdp = mdp;
		this.entre = entre;
	}
	
	
	
}
