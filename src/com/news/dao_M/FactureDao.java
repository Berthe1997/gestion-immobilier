package com.news.dao_M;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans_M.Facture;


public interface FactureDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerFacture(Facture facture, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerFacture(Facture facture, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierFacture(Facture facture, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Facture getFacture(int id);
	public Facture getFacture(String code);
	public Boolean getFactureVerifie(String code);
}
