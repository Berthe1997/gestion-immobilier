package com.news.dao_M;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans_M.Resident_bien;

public interface Resident_bienDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerResident_bien(Resident_bien resident_bien, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerResident_bien(Resident_bien resident_bien, AtomicReference<String> errorMsg);
	public boolean supprimerResident_bienM(Resident_bien resident_bien, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierResident_bien(Resident_bien resident_bien, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Resident_bien getResident_bien(int id);
	public Resident_bien getResident_bien(String code);
	public Boolean getResident_bienVerifie(String code);
	
	/* ++++ LES METHODES LISTER ++++ */
	 public List<Resident_bien> getAllResident_bien(String matricule);
}
