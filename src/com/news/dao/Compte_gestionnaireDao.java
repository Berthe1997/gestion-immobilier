package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Compte_gestionnaire;

public interface Compte_gestionnaireDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerCompte_gestionnaire(Compte_gestionnaire compte_gestionnaire, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	//public boolean supprimerCompte_gestionnaire(Compte_gestionnaire compte_gestionnaire, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
    public boolean modifierCompte_gestionnaire(Compte_gestionnaire compte_gestionnaire, AtomicReference<String> errorMsg);
    public boolean modifierCompte_gestionnaireS(Compte_gestionnaire compte_gestionnaire, AtomicReference<String> errorMsg);
 
    
    /* ++++ LES METHODES TROUVER ++++ */
    public Compte_gestionnaire getCompte_gestionnaire(int id);
    public Compte_gestionnaire getCompte_gestionnaire(String matricule);
    public Compte_gestionnaire getCompte_gestionnaire(String propriete,int ans,String mois);
    public Compte_gestionnaire getCompte_gestionnaire(String propriete,int ans);
    
    public Boolean getVerifierCompte_gestionnaire(String propriete,int ans,String mois);
    
    /* ++++ LES METHODES LISTER ++++ */
    public List<Compte_gestionnaire> getAllCompte_gestionnaire(String propriete,int ans);
    public List<Compte_gestionnaire> getAllCompte_gestionnaire(String propriete,int ans,String mois);
}
