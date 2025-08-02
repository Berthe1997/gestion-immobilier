package com.news.controllers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.news.beans.Quartier;
import com.news.dao.QuartierDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/quartier")
public class CrudQuartier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/quartier.jsp";
	public static final String QUAT = "/WEB-INF/views/view_quartier.jsp";

	Quartier quartier = new Quartier();
	QuartierDI quartierDI = new QuartierDI();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	HttpSession session = request.getSession();
		String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
			
		
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
			
			if(crud.equals("id")) {
				quartier = quartierDI.getQuartier(Integer.parseInt(id));
				quartier.setId(Long.parseLong(id));
				quartierDI.supprimerQuartier(quartier, errorMsg);
			}
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	HttpSession session = request.getSession();
		//	String id = request.getParameter("id");
			String page = request.getParameter("page");
			String crud = request.getParameter("crud");
			String nom = request.getParameter("nom");	
			String pays = request.getParameter("pays");
			String ville = request.getParameter("ville");
			String commune = request.getParameter("commune");
			String commentaire = request.getParameter("commentaire");
			
			if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
			
			if(crud.equals("AJOUTER")) {
				
				if(quartierDI.getVerifierQuartier(nom) == false) {
					
					quartier.setQuartier(nom);	 
					quartier.setPays(pays);
					quartier.setVille(ville);			
					quartier.setCommune(commune);
					quartier.setCommentaire(commentaire);
			
					if(quartierDI.creerQuartier(quartier, errorMsg)) {																
						request.setAttribute("message", new Message("Quartier enregistr� avec succ�s."+ errorMsg.get(), 0, "green"));
					}else request.setAttribute("message", new Message("Echec enregistrement Quartier. " + errorMsg.get(), 0, "red") );
					
				}else request.setAttribute("message", new Message("Cet Quartier existe d�j�." + errorMsg.get(), 0, "red") );			
		}
			
		
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}
	
}
