package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.OuvertureFermetureCaisse;

public interface OuvertureFermetureCaisseDao {
	
	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerOuv(OuvertureFermetureCaisse ouvFer, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierOuvFer(OuvertureFermetureCaisse ouvFer, AtomicReference<String> errorMsg);
	public boolean modifierEncaisseDecaisse(OuvertureFermetureCaisse ouvFer, AtomicReference<String> errorMsg);
	public boolean modifierOF(OuvertureFermetureCaisse ouvFer, AtomicReference<String> errorMsg);
	public boolean modifierOFs(OuvertureFermetureCaisse ouvFer, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public OuvertureFermetureCaisse getOuvFer(Long id);
	public OuvertureFermetureCaisse getOuvFer(String code, String site,String codeCa,String date);
	public Boolean getOuvFerVerifier(String code, String site,String codeCa,String date);
	
	/* ++++ LES METHODES LISTER ++++ */
	public List<OuvertureFermetureCaisse> getOuv(String code, String site,String codeCaisse);
	public List<OuvertureFermetureCaisse> getOuvr(String code, String site,String codeCaisse);
	public List<OuvertureFermetureCaisse> getDernierCaisseOuverte(String code, String site,String codeCaisse);
	public List<OuvertureFermetureCaisse> getOuv(String code, String site,String codeCaisse,int un);
	public List<OuvertureFermetureCaisse> getOuv(String code, String site,int un);
	public List<OuvertureFermetureCaisse> getOuvA(String code,String user,String date);
	public List<OuvertureFermetureCaisse> getOuv(String code, String site);
}
