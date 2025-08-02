package com.news.dao;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Proprietaire_m;

public interface Proprietaire_mDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerProprietaire_m(Proprietaire_m proprietaire_m, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerProprietaire_m(Proprietaire_m proprietaire_m, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
    public boolean modifierProprietaire_m(Proprietaire_m proprietaire_m, AtomicReference<String> errorMsg);
 
    
    /* ++++ LES METHODES TROUVER ++++ */
    public Proprietaire_m getProprietaire_m(int id);
    public Proprietaire_m getProprietaire_m(String matricule);
	public Boolean getVerifierProprietaire_m(String matricule);
}
