package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Compte_proprietaire;


public interface Compte_proprietaireDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerCompte_proprietaire(Compte_proprietaire compte_proprietaire, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	//public boolean supprimerCompte_proprietaire(Compte_proprietaire Compte_proprietaire, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
    public boolean modifierCompte_proprietaire(Compte_proprietaire compte_proprietaire, AtomicReference<String> errorMsg);
    public boolean modifierCompte_proprietaireR(Compte_proprietaire compte_proprietaire, AtomicReference<String> errorMsg);
    
    /* ++++ LES METHODES TROUVER ++++ */
    public Compte_proprietaire getCompte_proprietaire(int id);
    public Compte_proprietaire getCompte_proprietaire(String matricule);
    
    public Compte_proprietaire getCompte_proprietaire(String propriete,int ans,String mois);
    
    public Boolean getVerifierCompte_proprietaire(String propriete,int ans,String mois);
    
    /* ++++ LES METHODES LISTER ++++ */
    public List<Compte_proprietaire> getAllCompte_proprietaire(String propriete,int ans);
    public List<Compte_proprietaire> getAllCompte_proprietaire(String propriete,int ans,String mois);
}
