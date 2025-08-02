package com.news.beans;

public class Compte {
	private Long id;
	private String code;
	private String site;
	private String matricule;
	private int soldeP;
	private int soldeA;
	private int soldeI;
	
	
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
	public int getSoldeP() {
		return soldeP;
	}
	public void setSoldeP(int soldeP) {
		this.soldeP = soldeP;
	}
	public int getSoldeA() {
		return soldeA;
	}
	public void setSoldeA(int soldeA) {
		this.soldeA = soldeA;
	}
	public int getSoldeI() {
		return soldeI;
	}
	public void setSoldeI(int soldeI) {
		this.soldeI = soldeI;
	}
	
}
