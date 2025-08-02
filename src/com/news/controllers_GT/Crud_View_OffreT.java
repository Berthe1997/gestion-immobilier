package com.news.controllers_GT;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.news.beans.Agence;
import com.news.beans_GT.Offre_terrain;
import com.news.controllers.Message;
import com.news.dao_GT.Offre_terrainDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_offreT")
public class Crud_View_OffreT extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";	
	public static final String IMMOBILIER = "/WEB-INF/gestion_T/view_offreT.jsp";
	
	Offre_terrainDI offre_terrainDI= new Offre_terrainDI();
	Offre_terrain offre_terrain= new Offre_terrain();
	
	Agence agence = new Agence();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	HttpSession session = request.getSession();
	//	String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
			
		
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
			
			int idU = Integer.parseInt(id);
			offre_terrain = offre_terrainDI.getOffre_terrain(idU);
			request.setAttribute("offre_terrain", offre_terrain);
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
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
		String num_offre = request.getParameter("num_offre");
		
		
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
		
	
		if(crud.equals("MODIFIER")) {
			offre_terrain = offre_terrainDI.getOffre_terrain(Integer.parseInt(id));
			
			offre_terrain.setNum_offre(num_offre);
			offre_terrain.setNature(nature);
			offre_terrain.setLieu(lieu);
			offre_terrain.setLot(lot);
			offre_terrain.setIlot(ilot);
			offre_terrain.setSuperficie(superficie);
			offre_terrain.setPrix(Integer.parseInt(prix));
			offre_terrain.setCaracteristique(caracteristique);
			offre_terrain.setDocument(document);		
			if(statut != null) offre_terrain.setStatut(Integer.parseInt(statut));
			else offre_terrain.setStatut(0);
			offre_terrain.setDate_ajout(date_ajout);
			offre_terrain.setId(Long.parseLong(id));
			if(offre_terrainDI.modifierOffre_terrain(offre_terrain, errorMsg)) {
				request.setAttribute("offre_terrain", offre_terrain);
				request.setAttribute("message", new Message("offre_terrain modifié avec succès."+ errorMsg.get(), 0, "green"));
			} else request.setAttribute("message", new Message("Echec modification offre_terrain. " + errorMsg.get(), 0, "red") );
		}	
		
		
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}



}