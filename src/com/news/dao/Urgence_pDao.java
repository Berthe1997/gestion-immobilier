package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


import com.news.beans.Urgence_p;

public interface Urgence_pDao {
	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerUrgence_p(Urgence_p urgence_p, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerUrgence_p(Urgence_p urgence_p, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
    public boolean modifierUrgence_p(Urgence_p urgence_p, AtomicReference<String> errorMsg);
     
    /* ++++ LES METHODES TROUVER ++++ */
    public Urgence_p getUrgence_p(int id);
    public Urgence_p getUrgence_p(String matricule);
    
    /* ++++ LES METHODES LISTER ++++ */
    public List<Urgence_p> getAllUrgence_p(String proprietaire);
}
