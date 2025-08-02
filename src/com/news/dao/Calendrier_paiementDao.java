package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Calendrier_paiement;

public interface Calendrier_paiementDao {
	
	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerCalendrier_paiement(Calendrier_paiement calendrier_paiement, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerCalendrier_paiement(Calendrier_paiement calendrier_paiement, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierCalendrier_paiement(Calendrier_paiement calendrier_paiement, AtomicReference<String> errorMsg);
	public boolean modifierCalendrier_paiementP(Calendrier_paiement calendrier_paiement, AtomicReference<String> errorMsg);
	public boolean modifierCalendrier_paiementS(Calendrier_paiement calendrier_paiement, AtomicReference<String> errorMsg);
	
	public boolean modifierCalendrier_paiementL(Calendrier_paiement calendrier_paiement, AtomicReference<String> errorMsg);
	
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Boolean getCalendrier_paiementVerifie(String matricule,int ans);
	public Calendrier_paiement getCalendrier_paiement(int ans,String mois,String matricule);
	public Calendrier_paiement getCalendrier_paiement(String matricule);
	public Calendrier_paiement getCalendrier_paiement(int ans,String matricule);
	public Calendrier_paiement getCalendrier_paiement(String mois,String matricule);
	public Calendrier_paiement getCalendrier_paiement(int id);
	public Calendrier_paiement getCalendrier_paiementAn(String matricule);
	  
	/* ++++ LES METHODES LISTER ++++ */
    public List<Calendrier_paiement> getAllCalendrier_paiement();
    public List<Calendrier_paiement> getAllCalendrier_paiement(String matricule);
    public List<Calendrier_paiement> getAllCalendrier_paiementAR(int ans,String matricule);
 
    /* ++++ LES METHODES LISTER ++++ */
    public List<Calendrier_paiement> getAllCalendrier_paiement(String site,String matricule);
    public List<Calendrier_paiement> getAllCalendrier_paiementT(String site,String matricule);
    public List<Calendrier_paiement> getAllCalendrier_paiementM(int id);
    
    public List<Calendrier_paiement> getAllCalendrier_paiement1(String site,int ans,String matricule); 
    public List<Calendrier_paiement> getAllCalendrier_paiement2(String site,int ans,String matricule); 
    public List<Calendrier_paiement> getAllCalendrier_paiement3(String site,int ans,String matricule); 
    public List<Calendrier_paiement> getAllCalendrier_paiement4(String site,int ans,String matricule); 
    public List<Calendrier_paiement> getAllCalendrier_paiement5(String site,int ans,String matricule); 
    
    /* ++++ LES METHODES LISTER PDF ++++ */
    public List<Calendrier_paiement> getAllCalendrier_paiementPDF(String site,String matricule);
    public List<Calendrier_paiement> getAllCalendrier_paiementPDFM(String site,String mois);
    public List<Calendrier_paiement> getAllCalendrier_paiementPDF(String site);
    
    public List<Calendrier_paiement> getAllCalendrier_paiementPDFST(String site,int ans,String statut);
    public List<Calendrier_paiement> getAllCalendrier_paiementPDF(String site,String matricule,int ans);
    public List<Calendrier_paiement> getAllCalendrier_paiementPDF(String site,String matricule,int ans,String mois);
    
    /* ++++ LES METHODES LISTER PDF ++++ */
    public List<Calendrier_paiement> getAllCalendrier_paiementPDFA(String site,int ans);
    public List<Calendrier_paiement> getAllCalendrier_paiementPDFL(String site,int ans);
    
}
