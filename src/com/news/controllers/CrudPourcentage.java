package com.news.controllers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.beans.Agence;
import com.news.beans.Pourcentage;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.PourcentageDI;
import com.news.dao.UsersDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/pourcentage")
public class CrudPourcentage extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String POURCENTAGE = "/WEB-INF/views/taux.jsp";

	 Site site = new Site();	
	 Agence agence = new Agence();
	
	PourcentageDI pourcentageDI = new PourcentageDI();
	UsersDI userDI	=	new UsersDI();
	
	Users users	=	new Users();
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
			
			pourcentages = pourcentageDI.getPourcentage(idpr);			
			pourcentages.setId(idp);
		
			pourcentageDI.supprimerPourcentage(pourcentages, errorMsg);
			
		}
								
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(POURCENTAGE).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	//	String id = request.getParameter("id");
		String page = request.getParameter("page");
		String crud = request.getParameter("crud");
		String pourcentag = request.getParameter("pourcentage");
		String taux = request.getParameter("taux");
		String comment = request.getParameter("commentaire");
		
				
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		}
		
		if(crud.equals("AJOUTER")) {
			Boolean verifier = pourcentageDI.getPourcentageVerifie(pourcentag);
			if(verifier == false) {
				pourcentages.setLibelle(pourcentag);
				pourcentages.setTaux(Integer.parseInt(taux));
				pourcentages.setCommentaire(comment);
				pourcentages.setNbre(1);
				pourcentages.setCode(agence.getCode());
				pourcentages.setSite(site.getSite());
				if(pourcentageDI.creerPourcentage(pourcentages, errorMsg)) {
					
					request.setAttribute("message", new Message("pourcentage enregistré avec succès."+ errorMsg.get(), 0, "green"));
				}else request.setAttribute("message", new Message("Echec enregistrement pourcentage. " + errorMsg.get(), 0, "red") );
			} else request.setAttribute("message", new Message("Cet pourcentage existe déjà. " + errorMsg.get(), 0, "red") );
			
		}
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(POURCENTAGE).forward(request, response);
	}

}
