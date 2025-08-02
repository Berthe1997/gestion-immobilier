package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Agence;


public interface AgenceDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerAgence(Agence agence, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerAgence(Agence agence, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierAgence(Agence agence, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Agence getAgence(int id);
	public Agence getAgence(String code);
	public Boolean getAgenceVerifie(String agence);
	  
	/* ++++ LES METHODES LISTER ++++ */
    public List<Agence> getAllAgence();
 
}
