package com.news.dao;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Depot;


public interface DepotDao {
	
	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerDepot(Depot depot, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerDepot(Depot depot, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
    public boolean modifierDepot(Depot depot, AtomicReference<String> errorMsg);
   

    /* ++++ LES METHODES TROUVER ++++ */
    public Depot getDepot(int id);
    
    public Depot getDepotS(String site);
   

}
