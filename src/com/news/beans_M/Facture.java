package com.news.beans_M;

public class Facture {

	private Long id;
	private String agence;
	private String code_reservation;
	private String date;
	private int montant_T;
	private String statut;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAgence() {
		return agence;
	}
	public void setAgence(String agence) {
		this.agence = agence;
	}
	public String getCode_reservation() {
		return code_reservation;
	}
	public void setCode_reservation(String code_reservation) {
		this.code_reservation = code_reservation;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getMontant_T() {
		return montant_T;
	}
	public void setMontant_T(int montant_T) {
		this.montant_T = montant_T;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
}
