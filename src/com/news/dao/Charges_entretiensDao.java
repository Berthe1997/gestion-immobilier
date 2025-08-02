package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Charges_entretiens;

public interface Charges_entretiensDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerCharges_entretiens(Charges_entretiens charges_entretiens, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerCharges_entretiens(Charges_entretiens charges_entretiens, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
   public boolean modifierCharges_entretiens(Charges_entretiens charges_entretiens, AtomicReference<String> errorMsg);
 
    
    /* ++++ LES METHODES TROUVER ++++ */
    public Charges_entretiens getCharges_entretiens(int id);
    
    public Charges_entretiens getCharges_entretiens(String site,String ans,String mois);
    public Charges_entretiens getCharges_entretiensD(String site,String ans,String mois);
   
    public Boolean getVerifierCharges_entretiens(String site,String ans,String mois);
    
    /* ++++ LES METHODES LISTER ++++ */
    public List<Charges_entretiens> getAllCharges_entretiens(String site,String ans,String mois);
    
}
