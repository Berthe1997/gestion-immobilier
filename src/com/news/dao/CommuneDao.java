package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Commune;

public interface CommuneDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerCommune(Commune commune, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerCommune(Commune commune, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
    public boolean modifierCommune(Commune commune, AtomicReference<String> errorMsg);
 
    
    /* ++++ LES METHODES TROUVER ++++ */
    public Commune getCommune(int id);
    public Boolean getVerifierCommune(String commun);
    
    /* ++++ LES METHODES LISTER ++++ */
    public List<Commune> getAllCommune(String ville);
}
