package com.news.dao_M;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans_M.Ar_resident_bien;

public interface Ar_resident_bienDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerAr_resident_bien(Ar_resident_bien ar_resident_bien, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerAr_resident_bien(Ar_resident_bien ar_resident_bien, AtomicReference<String> errorMsg);
		
	/* ++++ LES METHODES TROUVER ++++ */
	public Ar_resident_bien getAr_resident_bien(int id);
	public Ar_resident_bien getAr_resident_bien(String matricule);
	
	/* ++++ LES METHODES LISTER ++++ */
	 public List<Ar_resident_bien> getAllAr_resident_bien(String matricule);
}
