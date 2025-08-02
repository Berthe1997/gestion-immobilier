package com.news.controllers_GT;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.news.beans_GT.Terrain;
import com.news.controllers.Message;
import com.news.dao_GT.TerrainDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_terrain")
public class CrudView_Terrain extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";	
	public static final String IMMOBILIER = "/WEB-INF/gestion_T/view_terrain.jsp";

	TerrainDI terrainDI= new TerrainDI();
	Terrain terrain= new Terrain();
	
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
			terrain = terrainDI.getTerrain(idU);
			request.setAttribute("terrain", terrain);
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	HttpSession session = request.getSession();
			String id = request.getParameter("id");
			String page = request.getParameter("page");	
			String crud = request.getParameter("crud");	
			String libelle = request.getParameter("libelle");
			String code = request.getParameter("code");
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
			
		

			if(crud.equals("MODIFIER")) {
				terrain = terrainDI.getTerrain(Integer.parseInt(id));
				
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
				terrain.setId(Long.parseLong(id));
				if(terrainDI.modifierTerrain(terrain, errorMsg)) {
					request.setAttribute("terrain", terrain);
					request.setAttribute("message", new Message("terrain modifié avec succès."+ errorMsg.get(), 0, "green"));
				} else request.setAttribute("message", new Message("Echec modification terrain. " + errorMsg.get(), 0, "red") );
			}	
			
			
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}
	

}
