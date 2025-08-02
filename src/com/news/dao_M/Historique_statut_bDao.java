package com.news.dao_M;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans_M. Historique_statut_b;

public interface Historique_statut_bDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerHistorique_statut_b( Historique_statut_b  historique_statut_b, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerHistorique_statut_b( Historique_statut_b  historique_statut_b, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierHistorique_statut_b( Historique_statut_b  historique_statut_b, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public  Historique_statut_b getHistorique_statut_b(int id);
	public  Historique_statut_b getHistorique_statut_b(String bien);
	public Boolean getHistorique_statut_bVerifie(String bien);
}
