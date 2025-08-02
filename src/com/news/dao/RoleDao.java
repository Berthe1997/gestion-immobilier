package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Roles;


public interface RoleDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerRole(Roles role, AtomicReference<String> errorMsg);
		
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerRole(Roles role, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierRole(Roles role, AtomicReference<String> errorMsg);
	public boolean modifierRoleP(Roles role, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Roles getRole(int id);
	public Roles getRole(String site, String profil);
	  
	/* ++++ LES METHODES LISTER ++++ */
    public List<Roles> getAllRole();
    public List<Roles> getAllRole(String site,String profil);
}
