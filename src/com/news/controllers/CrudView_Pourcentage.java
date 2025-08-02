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

@WebServlet("/view_pourcentage")
public class CrudView_Pourcentage extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String POURCENTAGE = "/WEB-INF/views/view_taux.jsp";

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
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
			
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
			int idU = Integer.parseInt(id);
			pourcentages = pourcentageDI.getPourcentage(idU);
			request.setAttribute("pourcentages", pourcentages);
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(POURCENTAGE).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
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
		
		if(crud.equals("MODIFIER")) {
			pourcentages = pourcentageDI.getPourcentage(Integer.parseInt(id));
			
			pourcentages.setLibelle(pourcentag);
			pourcentages.setTaux(Integer.parseInt(taux));
			pourcentages.setCommentaire(comment);
			pourcentages.setNbre(1);
			pourcentages.setCode(agence.getCode());
			pourcentages.setSite(site.getSite());
			pourcentages.setId(Long.parseLong(id));
			if(pourcentageDI.modifierPourcentage(pourcentages, errorMsg)) {
				request.setAttribute("pourcentages", pourcentages);
				request.setAttribute("message", new Message("pourcentages modifié avec succès."+ errorMsg.get(), 0, "green"));
			} else request.setAttribute("message", new Message("Echec modification pourcentages. " + errorMsg.get(), 0, "red") );
		}	
		
		
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(POURCENTAGE).forward(request, response);
	}

}
