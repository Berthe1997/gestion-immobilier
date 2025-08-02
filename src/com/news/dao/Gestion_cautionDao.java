package com.news.dao;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Gestion_caution;

public interface Gestion_cautionDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerGestion_caution(Gestion_caution gestion_caution, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerGestion_caution(Gestion_caution gestion_caution, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
  //  public boolean modifierGestion_caution(Gestion_caution gestion_caution, AtomicReference<String> errorMsg);
	
	public Boolean getVerifierGestion_caution(String code,String site);
	
	public Gestion_caution getGestion_caution(int id);
}
