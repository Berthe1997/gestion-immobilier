package com.news.beans_M;

public class Reservation {

	private Long id;
	private String agence;
	private String code_bien;
	private String code_reservation;
	private String client;
	private String date_entree;	
	private String date_sortie;
	private int reduction;
	private int montant_T;
	private String statut;
	private int commissionA;
	private int montant_P;	
	private String date_A;
	private String site;	
	private int nombre;
	private int montant_net;
	private String mode;
	private String heure;
	
	
	
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
	public String getCode_bien() {
		return code_bien;
	}
	public void setCode_bien(String code_bien) {
		this.code_bien = code_bien;
	}
	public String getCode_reservation() {
		return code_reservation;
	}
	public void setCode_reservation(String code_reservation) {
		this.code_reservation = code_reservation;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getDate_entree() {
		return date_entree;
	}
	public void setDate_entree(String date_entree) {
		this.date_entree = date_entree;
	}
	public String getDate_sortie() {
		return date_sortie;
	}
	public void setDate_sortie(String date_sortie) {
		this.date_sortie = date_sortie;
	}
	public int getReduction() {
		return reduction;
	}
	public void setReduction(int reduction) {
		this.reduction = reduction;
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
	public int getCommissionA() {
		return commissionA;
	}
	public void setCommissionA(int commissionA) {
		this.commissionA = commissionA;
	}
	public int getMontant_P() {
		return montant_P;
	}
	public void setMontant_P(int montant_P) {
		this.montant_P = montant_P;
	}
	public String getDate_A() {
		return date_A;
	}
	public void setDate_A(String date_A) {
		this.date_A = date_A;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public int getNombre() {
		return nombre;
	}
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public int getMontant_net() {
		return montant_net;
	}
	public void setMontant_net(int montant_net) {
		this.montant_net = montant_net;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
}
