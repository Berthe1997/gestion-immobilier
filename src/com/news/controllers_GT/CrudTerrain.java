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
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.beans_GT.Terrain;
import com.news.controllers.Message;
import com.news.dao_GT.TerrainDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/terrain")
public class CrudTerrain extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/gestion_T/terrain.jsp";

	//private static final String FORMAT_DATE = "yyy-MM-dd";

	TerrainDI terrainDI= new TerrainDI();
	Terrain terrain= new Terrain();
	
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
				terrain = terrainDI.getTerrain(Integer.parseInt(id));
				terrain.setId(Long.parseLong(id));
				terrainDI.supprimerTerrain(terrain, errorMsg);
				
				request.setAttribute("terrain", terrain);
			}
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		String page = request.getParameter("page");	
		String crud = request.getParameter("crud");	
		String libelle = request.getParameter("libelle");
		//String code = request.getParameter("code");
		String adresse = request.getParameter("adresse");
		String superficie = request.getParameter("superficie");
		String type = request.getParameter("type");
		String proprietaire = request.getParameter("proprietaire");
		String contact = request.getParameter("contact");
		String statut_ju = request.getParameter("statut_ju");
		String ilot = request.getParameter("ilot");
		String lot = request.getParameter("lot");
		String prix = request.getParameter("prix");
		String prixG = request.getParameter("prixG");
		String lotissement = request.getParameter("lotissement");
		String date_achat = request.getParameter("date_achat");
		
					
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
		
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
		
		
		
		
		String code = agence.getCode()+"-"+terrainDI.getLastIndex(agence.getCode())+".T";
							
		if(crud.equals("AJOUTER")) {
			
			if(terrainDI.getTerrainVerifie(code) == false) {
				
				terrain.setLibelle(libelle);
				terrain.setCode(code);
				terrain.setAdresse(adresse);
				terrain.setSuperficie(superficie);
				terrain.setType(type);
				terrain.setProprietaire(proprietaire);
				terrain.setContact(contact);
				terrain.setStatut_ju(statut_ju);
				terrain.setLot(lot);
				terrain.setIlot(ilot);
				terrain.setPrix(Integer.parseInt(prix));
				terrain.setPrixG(Integer.parseInt(prixG));
				terrain.setLotissement(lotissement);
				terrain.setDate_achat(date_achat);
		
				if(terrainDI.creerTerrain(terrain, errorMsg)) {																
					request.setAttribute("message", new Message("terrain enregistré avec succès."+ errorMsg.get(), 0, "green"));
				}else request.setAttribute("message", new Message("Echec enregistrement terrain. " + errorMsg.get(), 0, "red") );
				
			}else request.setAttribute("message", new Message("Cet terrain existe déjà." + errorMsg.get(), 0, "red") );			
	}
					
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}

}
