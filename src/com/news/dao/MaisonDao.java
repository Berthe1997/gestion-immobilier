package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Maison;

public interface MaisonDao {
	
	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerMaison(Maison maison, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerMaison(Maison maison, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierMaison(Maison maison, AtomicReference<String> errorMsg);
	
	public boolean modifierMaisonL(Maison maison, AtomicReference<String> errorMsg);
	
	public boolean modifierMaisons(Maison maison, AtomicReference<String> errorMsg);
	
	public boolean modifierMaisonM(Maison maison, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Maison getMaison(int id);	
	public Maison getMaison(String matricule);	
	public Boolean getMaisonVerifie(String site,String maisons);
	  
	/* ++++ LES METHODES LISTER ++++ */
    public List<Maison> getAllMaison();
}
