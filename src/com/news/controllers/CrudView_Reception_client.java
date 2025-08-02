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
import com.news.beans.Reception_client;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.Reception_clientDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_receptioncli")
public class CrudView_Reception_client extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/view_reception_client.jsp";


	 Reception_clientDI reception_clientDI = new Reception_clientDI();
	 Reception_client reception_client = new Reception_client();
	
	Agence agence = new Agence();
	Site site = new Site();
	Users users	=	new Users();
	
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
			reception_client = reception_clientDI.getReception_client(idU);
			request.setAttribute("reception_client", reception_client);
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		String id = request.getParameter("id");
		String page = request.getParameter("page");
		String crud = request.getParameter("crud");		
		String nom = request.getParameter("nom");		
		String chambre = request.getParameter("chambre");
		String salon = request.getParameter("salon");
		String tel = request.getParameter("tel");
		String zone = request.getParameter("zone");
		String prix = request.getParameter("prix");
		String date = request.getParameter("date");
		
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
		
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
			String matricule = "AGIL"+"-"+tel;
			if(crud.equals("MODIFIER")) {
				reception_client = reception_clientDI.getReception_client(Integer.parseInt(id));
				
				reception_client.setMatricule(matricule);
				reception_client.setNom(nom);
				reception_client.setTel(tel);
				reception_client.setDate_appel(date);
				reception_client.setChambre(chambre);
			//	reception_client.setSalon(salon);
				reception_client.setPrix(Integer.parseInt(prix));
				reception_client.setZone(zone);
				reception_client.setCode(agence.getCode());
				  reception_client.setId(Long.parseLong(id));
				if(reception_clientDI.modifierReception_client(reception_client, errorMsg)) {
					request.setAttribute("reception_client", reception_client);
					request.setAttribute("message", new Message("reception_client modifié avec succès."+ errorMsg.get(), 0, "green"));
				} else request.setAttribute("message", new Message("Echec modification reception_client. " + errorMsg.get(), 0, "red") );
			}	
			
			
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}


}
