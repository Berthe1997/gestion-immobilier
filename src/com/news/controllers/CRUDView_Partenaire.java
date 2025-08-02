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
import com.news.beans.Partenaire;
import com.news.beans.Site;
import com.news.dao.PartenaireDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_partenaire")
public class CRUDView_Partenaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	private static final String PARTENAIRE = "/WEB-INF/views/view_partenaire.jsp";
	
	Site site = new Site();

	Agence agence = new Agence();
	
	PartenaireDI partenaireDI = new PartenaireDI();
	Partenaire partenaire = new Partenaire();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	HttpSession session = request.getSession();
			//String crud	=	request.getParameter("crud");
			String page	=	request.getParameter("page");
			String id	=	request.getParameter("id");
				
			
				if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
				
				int idU = Integer.parseInt(id);
				partenaire = partenaireDI.getPartenaire(idU);
				request.setAttribute("partenaire", partenaire);
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(PARTENAIRE).forward(request, response);
		}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
	    String id	=	request.getParameter("id");
		String nom	=	request.getParameter("nom");
		String prenom	=	request.getParameter("prenom");
		String type	=	request.getParameter("type");
		String adresse	=	request.getParameter("adresse");
		String email	=	request.getParameter("email");
		String tel	=	request.getParameter("tel");
		String site_web	=	request.getParameter("site_web");
		String date_crea	=	request.getParameter("date_crea");
				
		
		 agence = (Agence) session.getAttribute("agence");
		  site = (Site) session.getAttribute("site");
			
			if(verifieSess.sessions(request, response) == false) {
		response.sendRedirect( request.getContextPath() + INDEX );
		return;
			}
			
			String matricule	=agence.getCode()+"-"+tel;
			
			if(crud.equals("MODIFIER")) {
				partenaire = partenaireDI.getPartenaire(Integer.parseInt(id));
				
				partenaire.setNom(nom);
				partenaire.setPrenom(prenom);
				partenaire.setMatricule(matricule);
				partenaire.setType(type);
				partenaire.setAdresse(adresse);
				partenaire.setEmail(email);
				partenaire.setTel(tel);
				partenaire.setSite_web(site_web);
				partenaire.setDate_crea(date_crea);
				partenaire.setId(Long.parseLong(id));
				
				if(partenaireDI.modifierPartenaire(partenaire, errorMsg)) {
					request.setAttribute("partenaire", partenaire);
					request.setAttribute("message", new Message("Partenaire modifié avec succès."+ errorMsg.get(), 0, "green"));
				} else request.setAttribute("message", new Message("Echec modification Partenaire. " + errorMsg.get(), 0, "red") );
			}	
			
				
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(PARTENAIRE).forward(request, response);
		}

}
