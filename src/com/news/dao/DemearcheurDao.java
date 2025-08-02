package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Demearcheur;

public interface DemearcheurDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerDemearcheur(Demearcheur demearcheur, AtomicReference<String> errorMsg);	
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerDemearcheur(Demearcheur demearcheur, AtomicReference<String> errorMsg);
		 
   /* ++++ LES METHODES DE MODIFICATION ++++ */
		public boolean modifierDemearcheur(Demearcheur demearcheur, AtomicReference<String> errorMsg);	

		/* ++++ LES METHODES TROUVER ++++ */
		public Demearcheur getDemearcheur(int id);
		public Demearcheur getDemearcheur(String matricule);
		public Boolean getDemearcheurVerifie(String matricule);
		
		public Demearcheur getDemearcheurC(String code);
		
		/* ++++ LES METHODES LISTER ++++ */
	    public List<Demearcheur> getAllDemearcheur(String code);
}
