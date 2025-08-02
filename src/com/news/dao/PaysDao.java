package com.news.dao;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Pays;


public interface PaysDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerpays(Pays pays, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerpays(Pays pays, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
    public boolean modifierpays(Pays pays, AtomicReference<String> errorMsg);
 
    
    /* ++++ LES METHODES TROUVER ++++ */
    public Pays getPays(int id);
    public Boolean getVerifierPays(String pay);
}
