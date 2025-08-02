package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.UserSite;


public interface UserSiteDao {

	public boolean creerUserSite(UserSite userSite, AtomicReference<String> errorMsg);
	public boolean supprimerUserEc(UserSite userSite, AtomicReference<String> errorMsg);
    public boolean modifierUserEc(UserSite userSite, AtomicReference<String> errorMsg);
    
    public boolean supprimerUserEcS(UserSite userSite, AtomicReference<String> errorMsg);
    public boolean modifierUserEcM(UserSite userSite, AtomicReference<String> errorMsg);
    
    public UserSite getUsersEc(int id);
    public Boolean getUsersEc(String email,String site);
    
    public List<UserSite> getAllUserEco();
    public List<UserSite> getAllUserEco(String email);
}
