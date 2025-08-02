package com.news.beans_M;

public class Bien_meuble {

	private Long id;
	private String agence;
	private String bien;
	private String type;
	private String adresse;
	private String statut;
	private int loyer_nuit;
	private int caution;
	private String description;
	private String date_ajout;
	private String site;
	
	
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
	public String getBien() {
		return bien;
	}
	public void setBien(String bien) {
		this.bien = bien;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public int getLoyer_nuit() {
		return loyer_nuit;
	}
	public void setLoyer_nuit(int loyer_nuit) {
		this.loyer_nuit = loyer_nuit;
	}
	public int getCaution() {
		return caution;
	}
	public void setCaution(int caution) {
		this.caution = caution;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate_ajout() {
		return date_ajout;
	}
	public void setDate_ajout(String date_ajout) {
		this.date_ajout = date_ajout;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	
}
