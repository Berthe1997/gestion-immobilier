package com.news.dao_M;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans_M.Client_bien;

public interface Client_bienDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerClient_bien(Client_bien client_bien, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerClient_bien(Client_bien client_bien, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierClient_bien(Client_bien client_bien, AtomicReference<String> errorMsg);
	public boolean modifierClient_bienM(Client_bien client_bien, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Client_bien getClient_bien(int id);
	public Client_bien getClient_bien(String matricule);
	public Boolean getClient_bienVerifie(String matricule);
	public Integer getLastIndex(String code);
	
	/* ++++ LES METHODES LISTER ++++ */
	 public List<Client_bien> getAllClient_bien(String matricule);
}
