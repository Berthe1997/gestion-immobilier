package com.news.dao_GT;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans_GT.Dossier_client;

public interface Dossier_clientDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerDossier_client(Dossier_client dossier_client, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerDossier_client(Dossier_client dossier_client, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierDossier_client(Dossier_client dossier_client, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Dossier_client getDossier_client(int id);
	public Dossier_client getDossier_client(String matricule);
	public Boolean getDossier_clientVerifie(String matricule);
	public Integer getLastIndex(String code);
	
	/* ++++ LES METHODES LISTER ++++ */
    public List<Dossier_client> getAllDossier_client();
    public List<Dossier_client> getAllDossier_clientAN();
}
