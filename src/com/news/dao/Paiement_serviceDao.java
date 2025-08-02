package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Paiement_service;




public interface Paiement_serviceDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerPaiement_service(Paiement_service paiement_service, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerPaiement_service(Paiement_service paiement_service, AtomicReference<String> errorMsg);
			
	 /* ++++ LES METHODES TROUVER ++++ */
	public Paiement_service getPaiement_service(int id);
	public Paiement_service getPaiement_service(String matricule);	
	public Boolean getVerifierPaiement_service(String propriete,String matricule,String ans,String mois);

	
	   
	/* ++++ LES METHODES LISTER ++++ */  
    public List<Paiement_service> getAllPaiement_service(String propriete,String matricule);
    
}
