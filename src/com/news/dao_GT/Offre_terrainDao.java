package com.news.dao_GT;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans_GT.Offre_terrain;

public interface Offre_terrainDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerOffre_terrain(Offre_terrain offre_terrain, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerOffre_terrain(Offre_terrain offre_terrain, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierOffre_terrain(Offre_terrain offre_terrain, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Offre_terrain getOffre_terrain(int id);
	public Offre_terrain getOffre_terrain(String num_offre);
	public Boolean getOffre_terrainVerifie(String num_offre);
	public Integer getLastIndex(String code);
	
	/* ++++ LES METHODES LISTER ++++ */
    public List<Offre_terrain> getAllOffre_terrain();
}
