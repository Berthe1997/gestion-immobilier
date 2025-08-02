package com.news.dao;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Quartier;

public interface QuartierDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerQuartier(Quartier quartier, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerQuartier(Quartier quartier, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
    public boolean modifierQuartier(Quartier quartier, AtomicReference<String> errorMsg);
 
    
    /* ++++ LES METHODES TROUVER ++++ */
    public Quartier getQuartier(int id);
    public Boolean getVerifierQuartier(String quartie);
}
