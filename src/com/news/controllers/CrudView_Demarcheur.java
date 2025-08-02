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
import com.news.beans.Demearcheur;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.beans.Zone;
import com.news.dao.DemearcheurDI;
import com.news.dao.ZoneDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_demarcheur")
public class CrudView_Demarcheur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/view_demarcheur.jsp";


	DemearcheurDI demearcheurDI = new DemearcheurDI();
	Demearcheur demearcheur = new Demearcheur();
	
	 ZoneDI zoneDI = new ZoneDI();
	 Zone zone = new Zone();
	
	Agence agence = new Agence();
	Site site = new Site();
	Users users	=	new Users();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();			
		String idPM = request.getParameter("idPM");
		String page = request.getParameter("page");
		String crud = request.getParameter("crud");
		String id	=	request.getParameter("id");
			
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
			if(crud.equals("idPM")) {
               zone.setId(Long.parseLong(idPM));
				if(zoneDI.supprimerZone(zone, errorMsg)) {
					request.setAttribute("message", new Message("zone demarcheur suppriméé avec succès."+ errorMsg.get(), 0, "green"));
				}else request.setAttribute("message", new Message("Echec suppression zone demarcheur. " + errorMsg.get(), 0, "red") );
			}
			
			if(crud.equals("id")) {
			  int idU = Integer.parseInt(id);
			  demearcheur = demearcheurDI.getDemearcheur(idU);			
			}
			request.setAttribute("demearcheur", demearcheur);
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		String id = request.getParameter("id");
		String page = request.getParameter("page");
		String crud = request.getParameter("crud");		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String tels = request.getParameter("tels");
		String tel = request.getParameter("tel");
		String adresse = request.getParameter("adresse");
		String matri = request.getParameter("matri");
		String nbre = request.getParameter("nbre");
					
			if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
			
			site = (Site) session.getAttribute("site");
			agence = (Agence) session.getAttribute("agence");
			
			
	
			 if(crud.equals("ZONED")) {
				
				for(int i = 1; i < Integer.parseInt(nbre) + 1; i++) {
					String vil = request.getParameter("vil"+i); 
					String com = request.getParameter("com"+i);
					String zon = request.getParameter("zon"+i);
				
				if(zoneDI.getZoneVerifie(matri,zon) == false) {
					zone.setCode(agence.getCode());
					zone.setMatricule(matri);
					zone.setTel(tels);
					zone.setVille(vil);
					zone.setCommune(com);				
					zone.setZone(zon);
					
					if(zoneDI.creerZone(zone, errorMsg)) {							
						request.setAttribute("message", new Message("zone demarcheur enregistré avec succès."+ errorMsg.get(), 0, "green"));
					}else request.setAttribute("message", new Message("Echec enregistrement zone demarcheur. " + errorMsg.get(), 0, "red") );					
				}else request.setAttribute("message", new Message("Cette zone demarcheur existe déjà." + errorMsg.get(), 0, "red") );
			}
				request.setAttribute("demearcheur", demearcheur);
			}
			
			String matricule = "AGIL"+"-"+tels;
			if(crud.equals("MODIFIER")) {
				demearcheur = demearcheurDI.getDemearcheur(Integer.parseInt(id));
				
				demearcheur.setMatricule(matricule);
				demearcheur.setNom(nom);
				demearcheur.setPrenom(prenom);
				demearcheur.setTels(tels);
				demearcheur.setTel(tel);
				demearcheur.setZone(adresse);
				demearcheur.setCode(agence.getCode());
				  demearcheur.setId(Long.parseLong(id));
				if(demearcheurDI.modifierDemearcheur(demearcheur, errorMsg)) {
					request.setAttribute("demearcheur", demearcheur);
					request.setAttribute("message", new Message("demarcheur modifié avec succès."+ errorMsg.get(), 0, "green"));
				} else request.setAttribute("message", new Message("Echec modification demarcheur. " + errorMsg.get(), 0, "red") );
			}	
			
			
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}

}
