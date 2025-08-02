package com.news.dao;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Service_technique;

public interface Service_techniqueDao {
public boolean creerService_technique(Service_technique service_technique, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerService_technique(Service_technique service_technique, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierService_technique(Service_technique service_technique, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Service_technique getService_technique(int id);
}
