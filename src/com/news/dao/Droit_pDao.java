package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Droit_p;

public interface Droit_pDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerDroit_p(Droit_p droit_p, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerDroit_p(Droit_p droit_p, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
    public boolean modifierDroit_p(Droit_p droit_p, AtomicReference<String> errorMsg);
 
    
    /* ++++ LES METHODES TROUVER ++++ */
    public Droit_p getDroit_p(int id);
    public Droit_p getDroit_p(String matricule);
    
    /* ++++ LES METHODES LISTER ++++ */
    public List<Droit_p> getAllDroit_p(String proprietaire);
    
}
