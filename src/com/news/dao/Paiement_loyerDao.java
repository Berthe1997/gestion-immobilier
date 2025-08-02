package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Paiement_loyer;


public interface Paiement_loyerDao {
	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerPaiement_loyer(Paiement_loyer paiement_loyer, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerPaiement_loyer(Paiement_loyer paiement_loyer, AtomicReference<String> errorMsg);
	
	 /* ++++ LES METHODES TROUVER ++++ */
		public Paiement_loyer getPaiement_loyer(int id);
				   
	/* ++++ LES METHODES LISTER ++++ */  
    public List<Paiement_loyer> getAllPaiement_loyer(String site,String matricule);
    public List<Paiement_loyer> getAllPaiement_loyer(String code,String site,String date,String caisse);
}
