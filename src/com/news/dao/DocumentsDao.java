package com.news.dao;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans_M.Documents;


public interface DocumentsDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerDocuments(Documents documents, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerDocuments(Documents documents, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierDocuments(Documents documents, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Documents getDocuments(int id);
	public Documents getDocuments(String client);
	public Boolean getDocumentsVerifie(String agence,String client,String type_doc);
	public Boolean getDocumentsVerifieF(String agence,String client,String fichier);

	
}
