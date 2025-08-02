package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Locataire;


public interface LocataireDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerLocataire(Locataire locataire, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerLocataire(Locataire locataire, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierLocataire(Locataire locataire, AtomicReference<String> errorMsg);
	public boolean modifierLocataireAn(Locataire locataire, AtomicReference<String> errorMsg);
	public boolean modifierLocataireAR(Locataire locataire, AtomicReference<String> errorMsg);
	
	public boolean modifierLocataireM(Locataire locataire, AtomicReference<String> errorMsg);
	public boolean modifierLocataireS(Locataire locataire, AtomicReference<String> errorMsg);
	public boolean modifierLocataireAM(Locataire locataire, AtomicReference<String> errorMsg);
	
	 /* ++++ LES METHODES TROUVER ++++ */
	public Locataire getLocataire(int id);
	public Locataire getLocataire(String matricule);
	public Locataire getLocataire(String site,String ans);
	public Boolean getVerifierLocataire(String site,String matricule);
	public Integer getLastIndex(String code,String site);
//	public Boolean getVerifierEcoleLocataire(String nomA,String nomN);
	
	   
	/* ++++ LES METHODES LISTER ++++ */
    public List<Locataire> getAllLocataire();
    public List<Locataire> getAllLocataire(String site);
    public List<Locataire> getAllLocataireM(String matricule);
    
    public List<Locataire> getAllLocataire(String site,String ans);
}
