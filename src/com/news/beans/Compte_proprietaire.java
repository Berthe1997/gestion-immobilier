package com.news.beans;

public class Compte_proprietaire {

	private Long id;
	private String proprietaire;
	private String matricule;
	private String propriete;
	private int montant_APC;
	private int ans;
	private String mois;
	private int retraitM;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getPropriete() {
		return propriete;
	}
	public void setPropriete(String propriete) {
		this.propriete = propriete;
	}
	public int getMontant_APC() {
		return montant_APC;
	}
	public void setMontant_APC(int montant_APC) {
		this.montant_APC = montant_APC;
	}
	public int getAns() {
		return ans;
	}
	public void setAns(int ans) {
		this.ans = ans;
	}
	public String getMois() {
		return mois;
	}
	public void setMois(String mois) {
		this.mois = mois;
	}
	public int getRetraitM() {
		return retraitM;
	}
	public void setRetraitM(int retraitM) {
		this.retraitM = retraitM;
	}
	
}
