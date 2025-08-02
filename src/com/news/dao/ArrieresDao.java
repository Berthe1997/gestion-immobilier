package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Arrieres;

public interface ArrieresDao {
	
	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerArrieres(Arrieres arrieres, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerArrieres(Arrieres arrieres, AtomicReference<String> errorMsg);
		
	/* ++++ LES METHODES DE MODIFICATION ++++ */
    public boolean modifierArrieres(Arrieres arrieres, AtomicReference<String> errorMsg);
     
    /* ++++ LES METHODES TROUVER ++++ */
    public Arrieres getArrieres(int id);
    public Arrieres getArrieres(String matricule);
    public Boolean getVerifierArrieres(String code,String site,String matricule,String ans,String mois);
    
    /* ++++ LES METHODES LISTER PDF ++++ */
    public List<Arrieres> getAllArrieres(String site,String matricule);

}
