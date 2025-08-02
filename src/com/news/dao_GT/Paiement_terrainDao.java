package com.news.dao_GT;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans_GT.Paiement_terrain;

public interface Paiement_terrainDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerPaiement_terrain(Paiement_terrain paiement_terrain, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerPaiement_terrain(Paiement_terrain paiement_terrain, AtomicReference<String> errorMsg);
	public boolean supprimerPaiement_terrainS(Paiement_terrain paiement_terrain, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Paiement_terrain getPaiement_terrain(int id);
	public Paiement_terrain getPaiement_terrain(String matricule);
	public Paiement_terrain getPaiement_terrainC(String code);
}
