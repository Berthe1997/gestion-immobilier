package com.news.beans_M;

public class Historique_statut_b {

	private Long id;
	private String agence;
	private String bien;
	private String ancien_statut;
	private String nouveau_statut;
	private String date_changement;
	private String motif;
	
	
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
	public String getAncien_statut() {
		return ancien_statut;
	}
	public void setAncien_statut(String ancien_statut) {
		this.ancien_statut = ancien_statut;
	}
	public String getNouveau_statut() {
		return nouveau_statut;
	}
	public void setNouveau_statut(String nouveau_statut) {
		this.nouveau_statut = nouveau_statut;
	}
	public String getDate_changement() {
		return date_changement;
	}
	public void setDate_changement(String date_changement) {
		this.date_changement = date_changement;
	}
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}
}
