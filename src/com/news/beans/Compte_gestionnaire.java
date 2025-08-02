package com.news.beans;

public class Compte_gestionnaire {
	
	private Long id;
	private String gestionnaire;
	private String propriete;
	private int montant_commission;
	private int ans;
	private String mois;
	private int commission_E;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGestionnaire() {
		return gestionnaire;
	}
	public void setGestionnaire(String gestionnaire) {
		this.gestionnaire = gestionnaire;
	}
	public String getPropriete() {
		return propriete;
	}
	public void setPropriete(String propriete) {
		this.propriete = propriete;
	}
	public int getMontant_commission() {
		return montant_commission;
	}
	public void setMontant_commission(int montant_commission) {
		this.montant_commission = montant_commission;
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
	public int getCommission_E() {
		return commission_E;
	}
	public void setCommission_E(int commission_E) {
		this.commission_E = commission_E;
	}
	
}
