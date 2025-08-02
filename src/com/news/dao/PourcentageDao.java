package com.news.dao;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Pourcentage;



public interface PourcentageDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerPourcentage(Pourcentage pourcentage, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerPourcentage(Pourcentage pourcentage, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierPourcentage(Pourcentage pourcentage, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Pourcentage getPourcentage(int id);
	public Pourcentage getPourcentage(String pourcentages);
	public Pourcentage getPourcentageN(int nbre);
	public Boolean getPourcentageVerifie(String pourcentages);
}
