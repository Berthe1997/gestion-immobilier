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
import com.news.beans.Gestion_caution;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.Gestion_cautionDI;
import com.news.dao.UsersDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/caution")
public class CrudGestion_Caution extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String CAUTION = "/WEB-INF/views/gestion_caution.jsp";
	
  UsersDI userDI	=	new UsersDI();	
  Users users	=	new Users();
  
  Gestion_cautionDI gestion_cautionDI = new Gestion_cautionDI();
  Gestion_caution gestion_caution = new Gestion_caution();
  
  Site site = new Site();
  Agence agence = new Agence();
  
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
			
			gestion_caution = gestion_cautionDI.getGestion_caution(idpr);			
			gestion_caution.setId(idp);
		
			gestion_cautionDI.supprimerGestion_caution(gestion_caution, errorMsg);
			
		}
								
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(CAUTION).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String page = request.getParameter("page");
		String crud = request.getParameter("crud");
		String code = request.getParameter("code");
		String sit = request.getParameter("sit");
		String mois = request.getParameter("mois");
		
		 agence = (Agence) session.getAttribute("agence");
		 site = (Site) session.getAttribute("site");
		
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		}
		
		if(crud.equals("AJOUTER")) {			
			if(gestion_cautionDI.getVerifierGestion_caution(code,sit) == false) {
				gestion_caution.setCode(code);
				gestion_caution.setSite(sit);
				gestion_caution.setMois(Integer.parseInt(mois));
				
				if(gestion_cautionDI.creerGestion_caution(gestion_caution, errorMsg)) {
					
					request.setAttribute("message", new Message("Caution enregistré avec succès."+ errorMsg.get(), 0, "green"));
				}else request.setAttribute("message", new Message("Echec enregistrement Caution. " + errorMsg.get(), 0, "red") );
			} else request.setAttribute("message", new Message("Cet Caution existe déjà. " + errorMsg.get(), 0, "red") );
			
		}
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(CAUTION).forward(request, response);
	}

}
