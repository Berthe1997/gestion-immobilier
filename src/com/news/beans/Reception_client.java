package com.news.beans;

public class Reception_client {

	private Long id;
	private String matricule;
	private String nom;
	private String tel;
	private String date_appel;
	private String chambre;
	private int statutP;
	private int prix;
	private String zone;
	private String statut;
	private String code;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDate_appel() {
		return date_appel;
	}
	public void setDate_appel(String date_appel) {
		this.date_appel = date_appel;
	}
	public String getChambre() {
		return chambre;
	}
	public void setChambre(String chambre) {
		this.chambre = chambre;
	}
	public int getStatutP() {
		return statutP;
	}
	public void setStatutP(int statutP) {
		this.statutP = statutP;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
