package com.news.beans_GT;

public class Terrain {

	private Long id;
	private String libelle;
	private String code;
	private String adresse;
	private String superficie;
	private String type;
	private String proprietaire;	
	private String contact;
	private String statut_ju;
	private String lot;
	private String ilot;
	private int prix;
	private int prixG;
	private int statut;
	private String lotissement;
	private String date_achat;
	
			
	public int getPrixG() {
		return prixG;
	}
	public void setPrixG(int prixG) {
		this.prixG = prixG;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getSuperficie() {
		return superficie;
	}
	public void setSuperficie(String superficie) {
		this.superficie = superficie;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getStatut_ju() {
		return statut_ju;
	}
	public void setStatut_ju(String statut_ju) {
		this.statut_ju = statut_ju;
	}
	public String getLot() {
		return lot;
	}
	public void setLot(String lot) {
		this.lot = lot;
	}
	public String getIlot() {
		return ilot;
	}
	public void setIlot(String ilot) {
		this.ilot = ilot;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public int getStatut() {
		return statut;
	}
	public void setStatut(int statut) {
		this.statut = statut;
	}
	public String getLotissement() {
		return lotissement;
	}
	public void setLotissement(String lotissement) {
		this.lotissement = lotissement;
	}
	public String getDate_achat() {
		return date_achat;
	}
	public void setDate_achat(String date_achat) {
		this.date_achat = date_achat;
	}
}
