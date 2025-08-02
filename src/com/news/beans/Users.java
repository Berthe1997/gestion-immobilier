package com.news.beans;

public class Users {

	private Long id;
	private String nom;
	private String contact;
	private String email;
	private String password;
	private String role;
	private int active;
	private String matricule;
	private String site;
	
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public String getNom() { return nom; }
	public void setNom(String nom) { this.nom = nom; }

	public String getContact() { return contact; }
	public void setContact(String contact) { this.contact = contact; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
}
