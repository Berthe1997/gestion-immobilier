package com.news.dao;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Partenaire;


public interface PartenaireDao {
	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerPartenaire(Partenaire partenaire, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerPartenaire(Partenaire partenaire, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierPartenaire(Partenaire partenaire, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Partenaire getPartenaire(int id);

	public Boolean getPartenaireVerifie(String matricule);
}
