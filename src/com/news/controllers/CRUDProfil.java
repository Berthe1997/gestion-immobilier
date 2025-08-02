package com.news.controllers;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.beans.Pourcentage;
import com.news.beans.Profil;
import com.news.beans.Users;
import com.news.dao.PourcentageDI;
import com.news.dao.ProfilDI;
import com.news.dao.UsersDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/profil")
public class CRUDProfil extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String PROFIL = "/WEB-INF/views/profile.jsp";

	
	ProfilDI profilDI = new ProfilDI();
	UsersDI userDI	=	new UsersDI();
	
	Users users	=	new Users();
	Profil profil = new Profil();
	
	PourcentageDI pourcentageDI = new PourcentageDI();
	Pourcentage pourcentages = new Pourcentage();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String page = request.getParameter("page");
		String crud = request.getParameter("crud");
		
		users = (Users) session.getAttribute("users");
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		}
		if(crud.equals("id")) {
			Long idp = Long.parseLong(id);
			int idpr = Integer.parseInt(id);
			profil = profilDI.getProfil(idpr);
			//Long idR = role.getId();
			profil.setId(idp);
			//role.setId(idR);
			//roleDI.supprimerRole(role, errorMsg);
			profilDI.supprimerProfil(profil, errorMsg);
			
		}
		
		
		
		List<Profil> listProfil = profilDI.getAllProfil();
		request.setAttribute("listProfil", listProfil);
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(PROFIL).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String page = request.getParameter("page");
		String crud = request.getParameter("crud");
		String prof = request.getParameter("profil");
		String comment = request.getParameter("commentaire");
		
	

		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		}
		
				
		if(crud.equals("AJOUTER")) {
			Boolean verifier = profilDI.getProfilVerifie(prof);
			if(verifier == false) {
				profil.setProfil(prof);
				profil.setCommentaire(comment);
				if(profilDI.creerProfil(profil, errorMsg)) {
					/*role.setCodeEc(eco.getCode());
					role.setProfil(prof);
					roleDI.creerRole(role, errorMsg);*/
					request.setAttribute("message", new Message("Profil enregistré avec succès."+ errorMsg.get(), 0, "green"));
				}else request.setAttribute("message", new Message("Echec enregistrement profil. " + errorMsg.get(), 0, "red") );
			} else request.setAttribute("message", new Message("Cet profil existe déjà. " + errorMsg.get(), 0, "red") );
			
			
			
			
		}
		if(crud.equals("MODIFIER")) {
			Long idp = Long.parseLong(id);
			//int idpr = Integer.parseInt(id);
		//	profil = profilDI.getProfil(idpr);
			String profActuel = profil.getProfil();
			List<Profil> listVerifie = profilDI.getAllVerifie(profActuel);
			Boolean verifier = false;
			for(Profil pro : listVerifie) {
				if(pro.getProfil().equals(prof)) {verifier = true;}
			}
			if(verifier == false) {
				profil.setProfil(prof);
				profil.setCommentaire(comment);
				profil.setId(idp);			
				//	Long idR = role.getId();
				if(profilDI.modifierProfil(profil, errorMsg)) {
				//	role.setProfil(prof);
				//	role.setId(idR);
				//	roleDI.modifierRoleP(role, errorMsg);
					
					
					request.setAttribute("message", new Message("Profil modifié avec succès."+ errorMsg.get(), 0, "green"));
				}else request.setAttribute("message", new Message("Echec modification profil. " + errorMsg.get(), 0, "red") );
			} else request.setAttribute("message", new Message("Cet profil existe déjà. " + errorMsg.get(), 0, "red") );
		}
		if(crud.equals("SUPPRIMER")) {
			Long idp = Long.parseLong(id);
			int idpr = Integer.parseInt(id);
			profil = profilDI.getProfil(idpr);
			//Long idR = role.getId();
			profil.setId(idp);
			//role.setId(idR);
			//roleDI.supprimerRole(role, errorMsg);
			profilDI.supprimerProfil(profil, errorMsg);
			
		}
		
		List<Profil> listProfil = profilDI.getAllProfil();
		request.setAttribute("listProfil", listProfil);
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(PROFIL).forward(request, response);
	}
	
	
			
	
	
}
