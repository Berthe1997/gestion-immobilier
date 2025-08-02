package com.news.dao;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Compte;


public interface CompteDao {
	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerCompte(Compte compte, AtomicReference<String> errorMsg);
		
	/* ++++ LES METHODES DE MODIFICATION ++++ */
    public boolean modifierCompte(Compte compte, AtomicReference<String> errorMsg);
   
    /* ++++ LES METHODES TROUVER ++++ */
    public Compte getCompte(String code,String site);
    public Boolean getVerifierCompte(String code,String site);
}
