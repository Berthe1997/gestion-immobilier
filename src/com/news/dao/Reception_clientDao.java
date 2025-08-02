package com.news.dao;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Reception_client;

public interface Reception_clientDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerReception_client(Reception_client reception_client, AtomicReference<String> errorMsg);	
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerReception_client(Reception_client reception_client, AtomicReference<String> errorMsg);
		 
   /* ++++ LES METHODES DE MODIFICATION ++++ */
		public boolean modifierReception_client(Reception_client reception_client, AtomicReference<String> errorMsg);
		public boolean modifierReception_clientS(Reception_client reception_client, AtomicReference<String> errorMsg);

		/* ++++ LES METHODES TROUVER ++++ */
		public Reception_client getReception_client(int id);
		public Reception_client getReception_client(String matricule);
		public Boolean getReception_clientVerifie(String matricule);
}
