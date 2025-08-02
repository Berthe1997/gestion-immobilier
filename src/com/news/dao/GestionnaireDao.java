package com.news.dao;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Gestionnaire;


public interface GestionnaireDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerGestionnaire(Gestionnaire gestionnaire, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerGestionnaire(Gestionnaire gestionnaire, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
    public boolean modifierGestionnaire(Gestionnaire gestionnaire, AtomicReference<String> errorMsg);
    public boolean modifierGestionnaireS(Gestionnaire gestionnaire, AtomicReference<String> errorMsg);
 
    
    /* ++++ LES METHODES TROUVER ++++ */
    public Gestionnaire getGestionnaire(int id);
    public Gestionnaire getGestionnaire(String matricule);
	public Boolean getVerifierGestionnaire(String site,String matricule);
	public Integer getLastIndex(String code);
}
