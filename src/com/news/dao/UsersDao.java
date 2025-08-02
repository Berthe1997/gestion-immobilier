package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Users;

public interface UsersDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerUser(Users user, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerUser(Users user, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
    public boolean modifierUser(Users user, AtomicReference<String> errorMsg);
 
    
    /* ++++ LES METHODES TROUVER ++++ */
    public Users getUsers(int id);
    public Users getUsers(String email);
  
    
    /* ++++ LES METHODES LISTER ++++ */
    public List<Users> getAllUser();
}
