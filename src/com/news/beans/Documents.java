package com.news.beans;

public class Documents {

	private Long id;
	private String agence;
	private String client;
	private String type_doc;
	private String chemin_F;
	private String date_ajout;
	
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
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getType_doc() {
		return type_doc;
	}
	public void setType_doc(String type_doc) {
		this.type_doc = type_doc;
	}
	public String getChemin_F() {
		return chemin_F;
	}
	public void setChemin_F(String chemin_F) {
		this.chemin_F = chemin_F;
	}
	public String getDate_ajout() {
		return date_ajout;
	}
	public void setDate_ajout(String date_ajout) {
		this.date_ajout = date_ajout;
	}
	
}
