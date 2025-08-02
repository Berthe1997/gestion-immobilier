package com.news.controllers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.news.beans.Agence;
import com.news.beans.Demearcheur;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.DemearcheurDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/demarcheur")
public class CrudDemarcheur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/demarcheur.jsp";
	private static final String FORMAT_DATE = "yyy-MM-dd";

	DemearcheurDI demearcheurDI = new DemearcheurDI();
	Demearcheur demearcheur = new Demearcheur();
	
	Agence agence = new Agence();
	Site site = new Site();
	Users users	=	new Users();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
			
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
			if(crud.equals("id")) {
				demearcheur = demearcheurDI.getDemearcheur(Integer.parseInt(id));
				demearcheur.setId(Long.parseLong(id));
				demearcheurDI.supprimerDemearcheur(demearcheur, errorMsg);
			}
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		String page = request.getParameter("page");
		String crud = request.getParameter("crud");		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String tels = request.getParameter("tels");
		String tel = request.getParameter("tel");
		String zone = request.getParameter("zone");
		
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
		
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
		DateTime dt = new DateTime();
		org.joda.time.format.DateTimeFormatter formattere = DateTimeFormat.forPattern(FORMAT_DATE);
		String dating = dt.toString(formattere);
					
		String matricule = "AGIL"+"-"+tels;
		if(crud.equals("AJOUTER")) {
			
			if(demearcheurDI.getDemearcheurVerifie(matricule) == false) {
				
				demearcheur.setMatricule(matricule);
				demearcheur.setNom(nom);
				demearcheur.setPrenom(prenom);
				demearcheur.setDate_ajout(dating);
				demearcheur.setTels(tels);
				demearcheur.setTel(tel);
				demearcheur.setZone(zone);
				demearcheur.setCode(agence.getCode());
		
				if(demearcheurDI.creerDemearcheur(demearcheur, errorMsg)) {																
					request.setAttribute("message", new Message("demarcheur enregistré avec succès."+ errorMsg.get(), 0, "green"));
				}else request.setAttribute("message", new Message("Echec enregistrement demarcheur. " + errorMsg.get(), 0, "red") );
				
			}else request.setAttribute("message", new Message("Cette demarcheur existe déjà." + errorMsg.get(), 0, "red") );			
	}
					
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}

}
