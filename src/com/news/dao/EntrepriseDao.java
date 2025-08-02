package com.news.dao;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Entreprise;

public interface EntrepriseDao {
	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerEntreprise(Entreprise entreprise, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerEntreprise(Entreprise entreprise, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierEntreprise(Entreprise entreprise, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Entreprise getEntreprise(int id);

	public Boolean getEntrepriseVerifie(String entreprise);
}
