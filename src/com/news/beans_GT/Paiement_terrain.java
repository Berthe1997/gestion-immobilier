package com.news.beans_GT;

public class Paiement_terrain {
	private Long id;
	private String terrain;
	private String client;
	private int montantP;	
	private int montantR;
	private String modeP;
	private String dateP;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTerrain() {
		return terrain;
	}
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public int getMontantP() {
		return montantP;
	}
	public void setMontantP(int montantP) {
		this.montantP = montantP;
	}
	public int getMontantR() {
		return montantR;
	}
	public void setMontantR(int montantR) {
		this.montantR = montantR;
	}
	public String getModeP() {
		return modeP;
	}
	public void setModeP(String modeP) {
		this.modeP = modeP;
	}
	public String getDateP() {
		return dateP;
	}
	public void setDateP(String dateP) {
		this.dateP = dateP;
	}
		
}
