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
import com.news.beans.Reception_client;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.DemearcheurDI;
import com.news.dao.Reception_clientDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/reception_cli")
public class CrudReception_client extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/reception_client.jsp";
	private static final String FORMAT_DATE = "yyy-MM-dd";

	 Reception_clientDI reception_clientDI = new Reception_clientDI();
	 Reception_client reception_client = new Reception_client();
	 
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
				reception_client = reception_clientDI.getReception_client(Integer.parseInt(id));
				reception_client.setId(Long.parseLong(id));
				reception_clientDI.supprimerReception_client(reception_client, errorMsg);
			}
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		String id = request.getParameter("id");
		String page = request.getParameter("page");
		String crud = request.getParameter("crud");		
		String nom = request.getParameter("nom");		
		String piece = request.getParameter("piece");		
		String tel = request.getParameter("tel");
		String zone = request.getParameter("zone");
		String prix = request.getParameter("prix");
		String statut = request.getParameter("statut");
		
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
		
				
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
		DateTime dt = new DateTime();
		org.joda.time.format.DateTimeFormatter formattere = DateTimeFormat.forPattern(FORMAT_DATE);
		String dating = dt.toString(formattere);
					
		String matricule = "AGIL"+"-"+tel;
		if(crud.equals("AJOUTER")) {
			
			if(reception_clientDI.getReception_clientVerifie(matricule) == false) {
				
				reception_client.setMatricule(matricule);
				reception_client.setNom(nom);
				reception_client.setTel(tel);
				reception_client.setDate_appel(dating);
				reception_client.setChambre(piece);
				reception_client.setStatutP(0);
				reception_client.setPrix(Integer.parseInt(prix));
				reception_client.setZone(zone);
				reception_client.setStatut(statut);
				reception_client.setCode(agence.getCode());
		
				if(reception_clientDI.creerReception_client(reception_client, errorMsg)) {																
					request.setAttribute("message", new Message("reception_client enregistré avec succès."+ errorMsg.get(), 0, "green"));																									
				}else request.setAttribute("message", new Message("Echec enregistrement reception_client. " + errorMsg.get(), 0, "red") );
				
			}else request.setAttribute("message", new Message("Cette reception_client existe déjà." + errorMsg.get(), 0, "red") );
						
	}
		
	    if(crud.equals("MODIFIER")) {
	    	reception_client = reception_clientDI.getReception_client(Integer.parseInt(id));
			reception_client.setDate_appel(dating);
			reception_client.setChambre(piece);
			reception_client.setStatutP(0);
			reception_client.setPrix(Integer.parseInt(prix));
			reception_client.setZone(zone);
			reception_client.setStatut(statut);
			
			reception_client.setId(Long.parseLong(id));
			if(reception_clientDI.modifierReception_client(reception_client, errorMsg)) {
				request.setAttribute("reception_client", reception_client);
				request.setAttribute("message", new Message("Demande enregistré avec succès."+ errorMsg.get(), 0, "green"));
			} else request.setAttribute("message", new Message("Echec enregistrement Demande. " + errorMsg.get(), 0, "red") );
		}	
					
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}
}
