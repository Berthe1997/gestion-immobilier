package com.news.controllers_GT;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.beans.Agence;
import com.news.beans_GT.Offre_terrain;
import com.news.controllers.Message;
import com.news.dao_GT.Offre_terrainDI;
import com.news.fonctions.Sms_Terrain;
import com.news.fonctions.VerifieSession;

@WebServlet("/offreT")
public class Crud_OffreT extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/gestion_T/offre_terrain.jsp";
	
	Offre_terrainDI offre_terrainDI= new Offre_terrainDI();
	Offre_terrain offre_terrain= new Offre_terrain();
	
	Agence agence = new Agence();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
			
		
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
			if(crud.equals("id")) {
				offre_terrain = offre_terrainDI.getOffre_terrain(Integer.parseInt(id));
				offre_terrain.setId(Long.parseLong(id));
				offre_terrainDI.supprimerOffre_terrain(offre_terrain, errorMsg);
				
				request.setAttribute("offre_terrain", offre_terrain);
			}
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		String page = request.getParameter("page");	
		String crud = request.getParameter("crud");			
		String nature = request.getParameter("nature");
		String lieu = request.getParameter("lieu");
		String lot = request.getParameter("lot");
		String ilot = request.getParameter("ilot");
		String superficie = request.getParameter("superficie");
		String prix = request.getParameter("prix");
		String caracteristique = request.getParameter("caracteristique");
		String document = request.getParameter("document");
		String statut = request.getParameter("statut");
		String date_ajout = request.getParameter("date_ajout");
		
		agence = (Agence) session.getAttribute("agence");
							
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
		
		Sms_Terrain sms_Terrain = new Sms_Terrain();
		
		String num_offre = agence.getCode()+"-"+offre_terrainDI.getLastIndex(agence.getCode())+".of";
		
	
							
		if(crud.equals("AJOUTER")) {
			
			if(offre_terrainDI.getOffre_terrainVerifie(num_offre) == false) {
				
				offre_terrain.setNum_offre(num_offre);
				offre_terrain.setNature(nature);
				offre_terrain.setLieu(lieu);
				offre_terrain.setLot(lot);
				offre_terrain.setIlot(ilot);
				offre_terrain.setSuperficie(superficie+" m²");
				offre_terrain.setPrix(Integer.parseInt(prix));
				offre_terrain.setCaracteristique(caracteristique);
				offre_terrain.setDocument(document);		
				if(statut != null) offre_terrain.setStatut(Integer.parseInt(statut));
				else offre_terrain.setStatut(0);
				offre_terrain.setDate_ajout(date_ajout);
				if(offre_terrainDI.creerOffre_terrain(offre_terrain, errorMsg)) {																
					request.setAttribute("message", new Message("offre_terrain enregistré avec succès."+ errorMsg.get(), 0, "green"));
				}else request.setAttribute("message", new Message("Echec enregistrement offre_terrain. " + errorMsg.get(), 0, "red") );
				
			}else request.setAttribute("message", new Message("Cet offre_terrain existe déjà." + errorMsg.get(), 0, "red") );			
	
			sms_Terrain.offre_CL(request, response,num_offre);
			sms_Terrain.offre_DL(request, response,num_offre);
		}
					
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}


}
