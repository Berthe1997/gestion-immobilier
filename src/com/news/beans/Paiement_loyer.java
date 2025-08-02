package com.news.beans;

public class Paiement_loyer {
	private Long id;
	private String code;
	private String site;
	private String matricule;
	private String type;
	private int montant;
	private String statut;
	private String date;
	private String caisse;
	private String caissier;
	private int annee;
	private String mois;
	private int montantA;
	private int montantI;
	private int montantP;
	private String modeP;
	private String heure;
	
	private Locataire locataire;
		
		
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCaisse() {
		return caisse;
	}
	public void setCaisse(String caisse) {
		this.caisse = caisse;
	}
	public String getCaissier() {
		return caissier;
	}
	public void setCaissier(String caissier) {
		this.caissier = caissier;
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
	public int getMontantA() {
		return montantA;
	}
	public void setMontantA(int montantA) {
		this.montantA = montantA;
	}
	public int getMontantI() {
		return montantI;
	}
	public void setMontantI(int montantI) {
		this.montantI = montantI;
	}
	public int getMontantP() {
		return montantP;
	}
	public void setMontantP(int montantP) {
		this.montantP = montantP;
	}
	public String getModeP() {
		return modeP;
	}
	public void setModeP(String modeP) {
		this.modeP = modeP;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	public Locataire getLocataire() {
		return locataire;
	}
	public void setLocataire(Locataire locataire) {
		this.locataire = locataire;
	}
	
}
