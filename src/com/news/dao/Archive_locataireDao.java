package com.news.dao;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Archive_locataire;

public interface Archive_locataireDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerArchive_locataire(Archive_locataire archive_locataire, AtomicReference<String> errorMsg);	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerArchive_locataire(Archive_locataire archive_locataire, AtomicReference<String> errorMsg);
	 /* ++++ LES METHODES TROUVER ++++ */
		public Archive_locataire getArchive_locataire(int id);	
}
