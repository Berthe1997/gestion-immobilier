package com.news.controllers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.news.beans.Gestionnaire;
import com.news.beans.Proprietaire;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.GestionnaireDI;
import com.news.dao.ProprietaireDI;
import com.news.dao.SiteDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_site")
public class CrudView_Site extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/view_site.jsp";
	public static final String IMMOBIL = "/WEB-INF/views/site.jsp";
	
		
	SiteDI siteDI = new SiteDI();
	Site site = new Site();
	
	 Users users	=	new Users();
	
	 GestionnaireDI gestionnaireDI = new GestionnaireDI();
	 Gestionnaire gestionnaire = new Gestionnaire();
	 
	 ProprietaireDI proprietaireDI = new ProprietaireDI();
	 Proprietaire proprietaire = new Proprietaire();
	 
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
									
		site = (Site) session.getAttribute("site");
		
	//	gestionnaire = gestionnaireDI.getGestionnaire(typ);
		
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
		
			
				int idU = Integer.parseInt(id);
				site = siteDI.getSite(idU);
				request.setAttribute("site", site);
			
								
	
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String page = request.getParameter("page");
		String crud = request.getParameter("crud");	
		String type = request.getParameter("type");
		String nombre = request.getParameter("nombre");
		String ville = request.getParameter("ville");
		String commune = request.getParameter("commune");
		String quartier = request.getParameter("quartier");		
		String cite= request.getParameter("cite");
		String num_acd = request.getParameter("num_acd");
		String permis_c = request.getParameter("permis_c");		
		String date_ob= request.getParameter("date_ob");
		String num_cnps = request.getParameter("num_cnps");
		String num_lot = request.getParameter("num_lot");
		String num_illot = request.getParameter("num_illot");
		String superficie = request.getParameter("superficie");
		String num_impot = request.getParameter("num_impot");
		String situation_geo = request.getParameter("situation_geo");
		String description = request.getParameter("description");
		String matricule = request.getParameter("matricule");		
		String date_ajout = request.getParameter("date_ajout");
		
		
		users = (Users) session.getAttribute("users");
		site = (Site) session.getAttribute("site");
		
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
			Proprietaire NP = proprietaireDI.getProprietaire(matricule);	
		
		if(crud.equals("MODIFIER")) {
			site = siteDI.getSite(Integer.parseInt(id));
			
			site.setType_site(type);
			site.setNombre_porte(nombre);
			site.setVille(ville);
			site.setCommune(commune);
			site.setQuartier(quartier);
			site.setCite(cite);
			site.setNum_acd(num_acd);
			site.setPermis_contruction(permis_c);
			site.setDate_obtention(date_ob);
			site.setNum_cnps(num_cnps);
			site.setNum_lot(num_lot);
			site.setNum_illot(num_illot);
			site.setSuperficie(superficie);
			site.setNum_impot(num_impot);
			site.setSituation_geo(situation_geo);
			site.setDescription(description);
			site.setMatricule(matricule);
			if(NP != null) {
				site.setNom_prenom(NP.getNom()+" "+NP.getPrenom());
			}
			site.setDate_ajout(date_ajout);
			site.setId(Long.parseLong(id));
			
			if(siteDI.modifierSite(site, errorMsg)) {
				request.setAttribute("site", site);
				request.setAttribute("message", new Message("Site modifié avec succès."+ errorMsg.get(), 0, "green"));
			} else request.setAttribute("message", new Message("Echec modification Site. " + errorMsg.get(), 0, "red") );
		}
		
				
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}
		

}
