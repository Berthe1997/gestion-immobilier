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
import com.news.beans.Droit_p;
import com.news.beans.Proprietaire;
import com.news.beans.Site;
import com.news.beans.Urgence_p;
import com.news.beans.Users;
import com.news.dao.Droit_pDI;
import com.news.dao.ProprietaireDI;
import com.news.dao.Urgence_pDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/droit_cas")
public class CrudDroit_casU extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/view_proprietaire.jsp";
	public static final String IMMOBILIER_M = "/WEB-INF/views/view_proprietaire_M.jsp";
	
	Site site = new Site();
	 Users users	=	new Users();
	 Agence agence = new Agence();
	 
	 ProprietaireDI proprietaireDI = new ProprietaireDI();
	 Proprietaire proprietaire = new Proprietaire();
	 
	 Droit_pDI droit_pDI = new Droit_pDI();
	 Droit_p droit_p = new Droit_p();  
	 
	 Urgence_pDI urgence_pDI = new Urgence_pDI();
	 Urgence_p urgence_p = new Urgence_p();
	 	
	 AtomicReference<String> errorMsg = new  AtomicReference<>("");
		VerifieSession verifieSess = new VerifieSession();
   
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();			
		String page = request.getParameter("page");
		String crud = request.getParameter("crud");	
				
		//==============AYANT DROIT===============================//
		String proprietaireA = request.getParameter("proprietaireA");
		String nomA = request.getParameter("nomA");
		String prenomA = request.getParameter("prenomA");
		String sexeA = request.getParameter("sexeA");
		String emailA= request.getParameter("emailA");
		String telA= request.getParameter("telA");
		String telsA= request.getParameter("telsA");
		String adresseA= request.getParameter("adresseA");
		String fonctionA= request.getParameter("fonctionA");
		//==============CAS URGENCE===============================//
		String proprietaireC = request.getParameter("proprietaireC");
		String nomC = request.getParameter("nomC");
		String prenomC = request.getParameter("prenomC");
		String sexeC = request.getParameter("sexeC");
		String emailC= request.getParameter("emailC");
		String telC= request.getParameter("telC");
		String telsC= request.getParameter("telsC");
		String adresseC= request.getParameter("adresseC");
		String fonctionC= request.getParameter("fonctionC");
		
		users = (Users) session.getAttribute("users");
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
			
				
			if(crud.equals("cas")) {
          	  
            	urgence_p.setProprietaire(proprietaireC);
      			urgence_p.setNom(nomC);
      			urgence_p.setPrenom(prenomC);
      			urgence_p.setSexe(sexeC);
      			urgence_p.setEmail(emailC);
      			urgence_p.setFonction(fonctionC);
      			urgence_p.setAdresse(adresseC);
      			urgence_p.setTel(telC);
      			urgence_p.setTels(telsC);
      			urgence_p.setMatricule(proprietaireC+"U");
      			
      			if(urgence_pDI.creerUrgence_p(urgence_p, errorMsg)) {
      				
					request.setAttribute("message", new Message("Contact Urgent enregistré avec succès."+ errorMsg.get(), 0, "green"));
				} else request.setAttribute("message", new Message("Echec enregistrement Contact Urgent. " + errorMsg.get(), 0, "red") );
			      
      			proprietaire = proprietaireDI.getProprietaire(proprietaireC);
	            request.setAttribute("proprietaire", proprietaire);	
	            
	             droit_p = droit_pDI.getDroit_p(proprietaireC);
				 request.setAttribute("droit_p", droit_p);
					
				 urgence_p = urgence_pDI.getUrgence_p(proprietaireC);
				 request.setAttribute("urgence_p", urgence_p);
              }	
    	 
		if(crud.equals("ayantD")) {
			droit_p.setProprietaire(proprietaireA);
			droit_p.setNom(nomA);
			droit_p.setPrenom(prenomA);
			droit_p.setSexe(sexeA);
			droit_p.setEmail(emailA);
			droit_p.setFonction(fonctionA);
			droit_p.setAdresse(adresseA);
			droit_p.setTel(telA);
			droit_p.setTels(telsA);
			droit_p.setMatricule(proprietaireA+"D");
			
			if(droit_pDI.creerDroit_p(droit_p, errorMsg)) {	
				
				request.setAttribute("message", new Message("Ayant Droit  enregistré avec succès."+ errorMsg.get(), 0, "green"));
			} else request.setAttribute("message", new Message("Echec enregistrement Ayant Droit. " + errorMsg.get(), 0, "red") );
		
			proprietaire = proprietaireDI.getProprietaire(proprietaireA);
            request.setAttribute("proprietaire", proprietaire);	
            
             droit_p = droit_pDI.getDroit_p(proprietaireA);
			 request.setAttribute("droit_p", droit_p);
				
			 urgence_p = urgence_pDI.getUrgence_p(proprietaireA);
			 request.setAttribute("urgence_p", urgence_p);
		
		
		}	
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	
			
	
	}			  

}
