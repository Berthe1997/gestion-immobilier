package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Credit_debitP;

public interface Credit_debitPDao {
	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerCredit_debitP(Credit_debitP credit_debitP, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerCredit_debitP(Credit_debitP credit_debitP, AtomicReference<String> errorMsg);
				   
	/* ++++ LES METHODES LISTER ++++ */  
    public List<Credit_debitP> getAllCredit_debitP(String site,String matricule);
}
