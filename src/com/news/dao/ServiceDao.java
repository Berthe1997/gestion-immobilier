package com.news.dao;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Service;


public interface ServiceDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerService(Service service, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerService(Service service, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
    public boolean modifierService(Service service, AtomicReference<String> errorMsg);
   
    public Boolean getVerifierService(String site,String service);
    /* ++++ LES METHODES TROUVER ++++ */
    public Service getService(int id);
    public Service getService(String services);
    
}
