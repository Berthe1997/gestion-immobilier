package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Impot_loyer;

public interface Impot_loyerDao {
	
	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerImpot_loyer(Impot_loyer impot_loyer, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	//public boolean supprimerImpot_loyer(Impot_loyer Impot_loyer, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
    public boolean modifierImpot_loyer(Impot_loyer impot_loyer, AtomicReference<String> errorMsg);
  
    /* ++++ LES METHODES TROUVER ++++ */
    public Impot_loyer getImpot_loyer(String matricule);
    
    public Impot_loyer getImpot_loyer(String site,int ans,String mois);
    
    public Boolean getVerifierImpot_loyer(String impot_loyer,int ans,String mois);
    
    /* ++++ LES METHODES LISTER ++++ */
    public List<Impot_loyer> getAllImpot_loyer(String propriete,int ans);
    public List<Impot_loyer> getAllImpot_loyer(String propriete,int ans,String mois);

}
