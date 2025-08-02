package com.news.beans;

public class Paiement_service {

	private Long id;
	private String locataire;
	private String matricule_locataire;
	private String propriete;
	private int montant_loyer;
	private String ans;
	private String mois;	
	private String type;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLocataire() {
		return locataire;
	}
	public void setLocataire(String locataire) {
		this.locataire = locataire;
	}
	public String getMatricule_locataire() {
		return matricule_locataire;
	}
	public void setMatricule_locataire(String matricule_locataire) {
		this.matricule_locataire = matricule_locataire;
	}
	public String getPropriete() {
		return propriete;
	}
	public void setPropriete(String propriete) {
		this.propriete = propriete;
	}
	public int getMontant_loyer() {
		return montant_loyer;
	}
	public void setMontant_loyer(int montant_loyer) {
		this.montant_loyer = montant_loyer;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	public String getMois() {
		return mois;
	}
	public void setMois(String mois) {
		this.mois = mois;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
