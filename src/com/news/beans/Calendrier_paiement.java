package com.news.beans;

public class Calendrier_paiement {

	private Long id;
	private int annee;
	private String mois;
	private String site;
	private String locataire;
	private String matricule;
	private int montant_loyer;
	private int montant_P;
	private int montant_R;
	private int penalite;
	private int montant_S;
	private String statut;
	private String date_paiement;
	private int taux_I;
	private int taux_A;
	private String dateP;
	private String modeP;
	private String code;
	
	private Locataire locataires;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public String getMois() {
		return mois;
	}
	public void setMois(String mois) {
		this.mois = mois;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getLocataire() {
		return locataire;
	}
	public void setLocataire(String locataire) {
		this.locataire = locataire;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public int getMontant_loyer() {
		return montant_loyer;
	}
	public void setMontant_loyer(int montant_loyer) {
		this.montant_loyer = montant_loyer;
	}
	public int getMontant_P() {
		return montant_P;
	}
	public void setMontant_P(int montant_P) {
		this.montant_P = montant_P;
	}
	public int getMontant_R() {
		return montant_R;
	}
	public void setMontant_R(int montant_R) {
		this.montant_R = montant_R;
	}
	public int getPenalite() {
		return penalite;
	}
	public void setPenalite(int penalite) {
		this.penalite = penalite;
	}
	public int getMontant_S() {
		return montant_S;
	}
	public void setMontant_S(int montant_S) {
		this.montant_S = montant_S;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getDate_paiement() {
		return date_paiement;
	}
	public void setDate_paiement(String date_paiement) {
		this.date_paiement = date_paiement;
	}
	public int getTaux_I() {
		return taux_I;
	}
	public void setTaux_I(int taux_I) {
		this.taux_I = taux_I;
	}
	public int getTaux_A() {
		return taux_A;
	}
	public void setTaux_A(int taux_A) {
		this.taux_A = taux_A;
	}	
	public String getDateP() {
		return dateP;
	}
	public void setDateP(String dateP) {
		this.dateP = dateP;
	}
	public String getModeP() {
		return modeP;
	}
	public void setModeP(String modeP) {
		this.modeP = modeP;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Locataire getLocataires() {
		return locataires;
	}
	public void setLocataires(Locataire locataires) {
		this.locataires = locataires;
	}
	
}
