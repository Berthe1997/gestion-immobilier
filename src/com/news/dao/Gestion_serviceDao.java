package com.news.dao;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Gestion_service;

public interface Gestion_serviceDao {
	public boolean creerGestion_service(Gestion_service gestion_service, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerGestion_service(Gestion_service gestion_service, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
//	public boolean modifierGestion_service(Gestion_service Gestion_service, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Gestion_service getGestion_service(int id);
	public Gestion_service getGestion_service(String matricule);

	//public Boolean getGestion_serviceVerifie(String Gestion_service);
}
