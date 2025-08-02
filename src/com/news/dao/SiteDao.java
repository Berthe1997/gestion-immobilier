package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Site;


public interface SiteDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerSite(Site site, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerSite(Site site, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierSite(Site site, AtomicReference<String> errorMsg);
	
	 /* ++++ LES METHODES TROUVER ++++ */
	public Site getSite(int id);
	public Site getSite(String sites);
	public Boolean getVerifierSite(String sites);
//	public Boolean getVerifierEcoleSite(String nomA,String nomN);
	
	   
	/* ++++ LES METHODES LISTER ++++ */
    public List<Site> getAllSite();
    public List<Site> getAllSiteM(String matricule);
    public List<Site> getAllSite(String sites);
    public List<Site> getAllSiteC(String code);

}
