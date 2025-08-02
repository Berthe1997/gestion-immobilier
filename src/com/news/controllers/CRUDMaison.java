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
import com.news.beans.Locataire;
import com.news.beans.Maison;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.LocataireDI;
import com.news.dao.MaisonDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/maison")
public class CRUDMaison extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/maison.jsp";
	
	Agence agence = new Agence();
	 Site site = new Site();
	 Users users	=	new Users();
	 
		MaisonDI maisonDI = new MaisonDI();
		Maison maison = new Maison();
		
		 LocataireDI locataireDI = new LocataireDI();
		 Locataire locataire = new Locataire();

	
	 AtomicReference<String> errorMsg = new  AtomicReference<>("");
		VerifieSession verifieSess = new VerifieSession();
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String crud	=	request.getParameter("crud");
			String page	=	request.getParameter("page");
			String id	=	request.getParameter("id");
			
           String matricule= request.getParameter("matricule");
        	
           agence = (Agence) session.getAttribute("agence");
			users = (Users) session.getAttribute("users");
			site = (Site) session.getAttribute("site");
			
				if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
			
				Locataire Nloc = locataireDI.getLocataire(matricule);	
				
				if(crud.equals("id")) {
					
					maison = maisonDI.getMaison(Integer.parseInt(id));
					maison.setId(Long.parseLong(id));
					maisonDI.supprimerMaison(maison, errorMsg);
					
					 if(Nloc != null) {
						    locataire.setNum_porte("");
						    locataire.setMatricule(matricule);
						    locataireDI.modifierLocataireM(locataire, errorMsg);
						    
					} 
						    
						   			
				}
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}
		
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();		
			String page = request.getParameter("page");
			String crud = request.getParameter("crud");			
			String maisons = request.getParameter("maisons");		
			String piece= request.getParameter("piece");
			String chambre= request.getParameter("chambre");
			String type= request.getParameter("type");
			String etage= request.getParameter("etage");
			String ascenceur= request.getParameter("ascenceur");
			String balcon= request.getParameter("balcon");
			String terrasse= request.getParameter("terrasse");
			String prix= request.getParameter("prix");
			String occupe= request.getParameter("occupe");
			String matricule= request.getParameter("matricule");
			String nom_prenom= request.getParameter("nom_prenom");
			String num_compt= request.getParameter("num_compt");
			String num_sodeci= request.getParameter("num_sodeci");
			String salon= request.getParameter("salon");
			
			agence = (Agence) session.getAttribute("agence");
			users = (Users) session.getAttribute("users");
			site = (Site) session.getAttribute("site");
			
				if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
			
			if(crud.equals("AJOUTER")) {
				
				if(maisonDI.getMaisonVerifie(site.getSite(),maisons) == false) {
					
					maison.setSite(site.getSite());
					maison.setMaison(maisons);
					maison.setPiece(piece);
					maison.setChambre(chambre);
					maison.setType(type);
					maison.setEtage(etage);
					if(ascenceur != null) maison.setAscenceur(Integer.parseInt(ascenceur));
					else maison.setAscenceur(0);
					if(balcon != null) maison.setBalcon(Integer.parseInt(balcon));
					else maison.setBalcon(0);
					if(terrasse != null) maison.setTerrasse(Integer.parseInt(terrasse));
					else maison.setTerrasse(0);
					maison.setPrix(Integer.parseInt(prix));
					if(occupe != null) maison.setOccupe(Integer.parseInt(occupe));
					else maison.setOccupe(0);													
					maison.setMatricule(matricule);
					maison.setNom_prenom(nom_prenom);
					maison.setNumero_compteur(num_compt);
					maison.setNumero_sodeci(num_sodeci);
					maison.setSalon(salon);
					maison.setCode(agence.getCode());
															
					if(maisonDI.creerMaison(maison, errorMsg)) {																
						request.setAttribute("message", new Message("Maison enregistré avec succès."+ errorMsg.get(), 0, "green"));
					}else request.setAttribute("message", new Message("Echec enregistrement Maison. " + errorMsg.get(), 0, "red") );
					
				}else request.setAttribute("message", new Message("Cet Maison existe déjà." + errorMsg.get(), 0, "red") );			
		}
			
					
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}

}
