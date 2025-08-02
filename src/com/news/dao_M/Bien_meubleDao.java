package com.news.dao_M;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans_M.Bien_meuble;

public interface Bien_meubleDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerBien_meuble(Bien_meuble bien_meuble, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerBien_meuble(Bien_meuble bien_meuble, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierBien_meuble(Bien_meuble bien_meuble, AtomicReference<String> errorMsg);
	public boolean modifierBien_meubleS(Bien_meuble bien_meuble, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Bien_meuble getBien_meuble(int id);
	public Bien_meuble getBien_meuble(String bien);
	public Boolean getBien_meubleVerifie(String bien);
	  
	/* ++++ LES METHODES LISTER ++++ */
   
}
