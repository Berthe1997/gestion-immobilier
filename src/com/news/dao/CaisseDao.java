package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Caisse;

public interface CaisseDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerCaisse(Caisse caisse, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerCaisse(Caisse caisse, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
    public boolean modifierCaisse(Caisse caisse, AtomicReference<String> errorMsg);
    public boolean modifierO(Caisse caisse, AtomicReference<String> errorMsg);
    
    /* ++++ LES METHODES TROUVER ++++ */
    public Caisse getCaisse(int id);
    public Caisse getCaisse(String code);
    public boolean getVerifierCalC(String codeS,String nom);
    public boolean getVerifierCalC1(String codeS,String code);
   
    /* ++++ LES METHODES LISTER ++++ */
    public List<Caisse> getAllCaisse();
    public List<Caisse> getAllCalC(String codeS);
    public List<Caisse> getAllCaisseO(String codeS);
}
