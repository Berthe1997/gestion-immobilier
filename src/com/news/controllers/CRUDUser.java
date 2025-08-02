package com.news.controllers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.beans.*;
import com.news.dao.GestionnaireDI;
import com.news.dao.UserSiteDI;
import com.news.dao.UsersDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/utilisateurs")
public class CRUDUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	private static final String UTILISATEUR = "/WEB-INF/views/utilisateur.jsp";
	private static final String UTILISATEURs = "/WEB-INF/views/view_utilisateur.jsp";
	
	UsersDI userDI = new UsersDI();
	UserSiteDI userSiteDI = new UserSiteDI();
		
	Site site = new Site();
	Users users	=	new Users();
	UserSite userSite = new UserSite();
    Agence agences = new Agence();
	
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
		String email	=	request.getParameter("email");
		
		users = (Users) session.getAttribute("users");
		site = (Site) session.getAttribute("site");
		agences = (Agence) session.getAttribute("agence");
		
			if(verifieSess.sessions(request, response) == false) {
		response.sendRedirect( request.getContextPath() + INDEX );
		return;
	} 
			
			if(crud.equals("id")) {
				int idUS = Integer.parseInt(id);
				users = userDI.getUsers(idUS);
											
				userDI.supprimerUser(users, errorMsg);
				
				
				userSite.setUserEmail(email);
				userSiteDI.supprimerUserEcS(userSite, errorMsg);
				
			}
		
		
		
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
	
		
		site = (Site) session.getAttribute("site");
		
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		}
		
		Gestionnaire Nger = gestionnaireDI.getGestionnaire(nomUser);
		
		int act =0;
		if(crud.equals("AJOUTER")) {
			users = userDI.getUsers(email);
			if(users == null) {
				Users util	=	new Users();
				
				if(Nger != null) {
					util.setNom(Nger.getNom() +" "+Nger.getPrenom());											
				util.setContact(Nger.getTel());
				}
				util.setEmail(email);
				util.setPassword(password);
				util.setActive(act);
				util.setRole(role);
				util.setMatricule(nomUser);
				util.setSite(agences.getCode());
				
				gestionnaire.setService(role);
				gestionnaire.setMatricule(nomUser);
				gestionnaireDI.modifierGestionnaireS(gestionnaire, errorMsg);
				
				if(userDI.creerUser(util, errorMsg)) {
					request.setAttribute("message", new Message("Utilisateur enregistré avec succès."+ errorMsg.get(), 0, "green"));
				}else request.setAttribute("message", new Message("Echec enregistrement Utilisateur. " + errorMsg.get(), 0, "red") );
			}else request.setAttribute("message", new Message("Cet utilisateur existe déjà. " + errorMsg.get(), 0, "red") );
		}
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(UTILISATEUR).forward(request, response);
	}
}
