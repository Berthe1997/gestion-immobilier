package com.news.dao_M;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans_M.Ar_client_bien;

public interface Ar_client_bienDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerAr_client_bien(Ar_client_bien ar_client_bien, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerAr_client_bien(Ar_client_bien ar_client_bien, AtomicReference<String> errorMsg);
		
	/* ++++ LES METHODES TROUVER ++++ */
	public Ar_client_bien getAr_client_bien(int id);	
	public Ar_client_bien getAr_client_bien(String matricule);
		
	/* ++++ LES METHODES LISTER ++++ */
	 public List<Ar_client_bien> getAllAr_client_bien(String matricule);
}
