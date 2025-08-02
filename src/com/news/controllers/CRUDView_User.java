package com.news.controllers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.beans.Gestionnaire;
import com.news.beans.Site;
import com.news.beans.UserSite;
import com.news.beans.Users;
import com.news.dao.GestionnaireDI;
import com.news.dao.UserSiteDI;
import com.news.dao.UsersDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_utilisateurs")
public class CRUDView_User extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	private static final String UTILISATEUR = "/WEB-INF/views/view_utilisateur.jsp";
	private static final String UTILISATEURs = "/WEB-INF/views/utilisateur.jsp";
	
	UsersDI userDI = new UsersDI();
	UserSiteDI userSiteDI = new UserSiteDI();
		
	Site site = new Site();
	Users users	=	new Users();
	UserSite userSite = new UserSite();
	
	 GestionnaireDI gestionnaireDI = new GestionnaireDI();
	 Gestionnaire gestionnaire = new Gestionnaire();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
		String idUE	=	request.getParameter("idUE");
		
		users = (Users) session.getAttribute("users");
		site = (Site) session.getAttribute("site");
		
			if(verifieSess.sessions(request, response) == false) {
		response.sendRedirect( request.getContextPath() + INDEX );
		return;
	} 
		
			
			if(crud.equals("id")) {	}
				
		if(crud.equals("supp")) {	
			Long iduE = Long.parseLong(idUE);
			userSite.setId(iduE);
			userSiteDI.supprimerUserEc(userSite, errorMsg);
			
		}
		
		int idU = Integer.parseInt(id);
		users = userDI.getUsers(idU);
		
		request.setAttribute("page", page);

		request.setAttribute("utilisateur", users);
		this.getServletContext().getRequestDispatcher(UTILISATEUR).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
		String nomUser	=	request.getParameter("nomUser");
		String contact	=	request.getParameter("contact");
		String email	=	request.getParameter("email");
		String password	=	request.getParameter("password");
		String active	=	request.getParameter("active");
		String role	=	request.getParameter("role");
		String sit	=	request.getParameter("site");
		String agence	=	request.getParameter("agence");
	
		String emails	=	request.getParameter("emails");
			
		site = (Site) session.getAttribute("site");
		
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		}
		
		Gestionnaire Nger = gestionnaireDI.getGestionnaire(nomUser);
		
		int act =0;
		
		if(crud.equals("MODIFIER")) {
			Users util	=	new Users();
			Long idU = Long.parseLong(id);
			if(active != null) {act = Integer.parseInt(active);}
			
			if(Nger != null) {
				util.setNom(Nger.getNom() +" "+Nger.getPrenom());											
			util.setContact(Nger.getTel());
			}
			util.setEmail(email);
			util.setPassword(password);
			util.setActive(act);
			util.setRole(role);
			util.setMatricule(nomUser);
			util.setSite(site.getSite());
			util.setId(idU);
			
			gestionnaire.setService(role);
			gestionnaire.setMatricule(nomUser);
			gestionnaireDI.modifierGestionnaireS(gestionnaire, errorMsg);
			
			if(userDI.modifierUser(util, errorMsg)) {
				int idUS = Integer.parseInt(id);
				users = userDI.getUsers(idUS);	
				
			//	userSite.setUserEmail(email);
			//	userSite.setUserEmail(emails);
			//	userSiteDI.modifierUserEcM(userSite, errorMsg);
	
				request.setAttribute("utilisateur", users);
				request.setAttribute("message", new Message("Utilisateur modifié avec succès."+ errorMsg.get(), 0, "green"));
			}else request.setAttribute("message", new Message("Echec modification Utilisateur. " + errorMsg.get(), 0, "red") );
		
			
		}

		if(crud.equals("ENREGISTRER")) {
			Boolean verifie = userSiteDI.getUsersEc(email, sit);
			if(verifie == false) {
				userSite.setSite(sit);
				userSite.setUserEmail(email);
				userSite.setCode(agence);
				int idUS = Integer.parseInt(id);
				users = userDI.getUsers(idUS);
		
				request.setAttribute("utilisateur", users);
				if(userSiteDI.creerUserSite(userSite, errorMsg)) {
					request.setAttribute("message", new Message("SITE ajouté avec succès."+ errorMsg.get(), 0, "green"));
				}else request.setAttribute("message", new Message("Echec ajout SITE. " + errorMsg.get(), 0, "red") );
			} else request.setAttribute("message", new Message("Cet SITE existe déjà pour cet Utilisateur. " + errorMsg.get(), 0, "red") );
			
		}
		
		request.setAttribute("utilisateur", users);
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(UTILISATEUR).forward(request, response);
	}

}
