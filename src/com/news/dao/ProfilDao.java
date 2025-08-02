package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Profil;

public interface ProfilDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerProfil(Profil profil, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerProfil(Profil profil, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierProfil(Profil profil, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Profil getProfil(int id);
	public Profil getProfil(String profil);
	public Boolean getProfilVerifie(String profil);
	  
	/* ++++ LES METHODES LISTER ++++ */
    public List<Profil> getAllProfil();
    public List<Profil> getAllVerifie(String profil);
}
