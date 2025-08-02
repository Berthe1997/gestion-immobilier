package com.news.dao_GT;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans_GT.Client_acheteur;


public interface Client_acheteurDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerClient_acheteur(Client_acheteur client_acheteur, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerClient_acheteur(Client_acheteur client_acheteur, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierClient_acheteur(Client_acheteur client_acheteur, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Client_acheteur getClient_acheteur(int id);
	public Client_acheteur getClient_acheteur(String matricule);
	public Boolean getClient_acheteurVerifie(String matricule);
	public Integer getLastIndex(String code);
	
	/* ++++ LES METHODES LISTER ++++ */
    public List<Client_acheteur> getAllClient_acheteur();
    public List<Client_acheteur> getAllClient_acheteurAN();
}
