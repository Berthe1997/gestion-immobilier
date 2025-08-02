package com.news.fonctions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.beans.Site;
import com.news.beans.UserSite;
import com.news.beans.Users;

public class VerifieSession {

	public VerifieSession() {}
	
	Users users	=	new Users();
	UserSite userSite	=	new UserSite();
	Site site = new Site();

	public Boolean sessions(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		Boolean verif = true;
		if(session.getAttribute("users") == null) {
			 verif = false;
		} else {
			if(session.getAttribute("site") == null) {
				verif = false;
			} else {
				if(session.getAttribute("userSite") == null) {
					verif = false;
				}
			}
		}
		
		return verif;
	}
}
