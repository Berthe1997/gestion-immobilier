package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Ville;

public interface VilleDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerVille(Ville ville, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerVille(Ville ville, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
    public boolean modifierVille(Ville ville, AtomicReference<String> errorMsg);
 
    
    /* ++++ LES METHODES TROUVER ++++ */
    public Ville getVille(int id);
    public Boolean getVerifierVille(String ville);
    
    /* ++++ LES METHODES LISTER ++++ */
    public List<Ville> getAllVille();
}
