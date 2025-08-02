package com.news.dao;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Proprietaire;


public interface ProprietaireDao {
	
	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerProprietaire(Proprietaire proprietaire, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerProprietaire(Proprietaire proprietaire, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
    public boolean modifierProprietaire(Proprietaire proprietaire, AtomicReference<String> errorMsg);
 
    
    /* ++++ LES METHODES TROUVER ++++ */
    public Proprietaire getProprietaire(int id);
    public Proprietaire getProprietaire(String matricule);
	public Boolean getVerifierProprietaire(String matricule);
}
