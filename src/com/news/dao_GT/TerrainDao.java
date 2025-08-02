package com.news.dao_GT;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans_GT.Terrain;


public interface TerrainDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerTerrain(Terrain terrain, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerTerrain(Terrain terrain, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierTerrain(Terrain terrain, AtomicReference<String> errorMsg);
	public boolean modifierTerrainS(Terrain terrain, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Terrain getTerrain(int id);
	public Terrain getTerrain(String code);
	public Boolean getTerrainVerifie(String code);
	public Integer getLastIndex(String code);
}
