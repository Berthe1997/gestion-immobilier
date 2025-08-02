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
import com.news.beans.Entreprise;
import com.news.beans.Site;
import com.news.dao.AgenceDI;
import com.news.dao.EntrepriseDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_entreprise")
public class CRUDView_entreprise extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	private static final String ENTREPRISE = "/WEB-INF/views/view_entreprise.jsp";
	
	Site site = new Site();

	Agence agence = new Agence();
	AgenceDI agenceDI = new AgenceDI();
	
	 EntrepriseDI entrepriseDI = new EntrepriseDI();
	 Entreprise entreprise = new Entreprise();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	HttpSession session = request.getSession();
			//String crud	=	request.getParameter("crud");
			String page	=	request.getParameter("page");
			String id	=	request.getParameter("id");
				
			
				if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
				
				int idU = Integer.parseInt(id);
				entreprise = entrepriseDI.getEntreprise(idU);
				request.setAttribute("entreprise", entreprise);
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(ENTREPRISE).forward(request, response);
		}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
		String entreprises	=	request.getParameter("entreprises");
		String compte_contrib	=	request.getParameter("compte_contrib");
		String cnps	=	request.getParameter("cnps");
		String banque_1	=	request.getParameter("banque_1");
		String banque_2	=	request.getParameter("banque_2");
		String rib_1	=	request.getParameter("rib_1");
		String rib_2	=	request.getParameter("rib_2");
		String email	=	request.getParameter("email");
		String tel	=	request.getParameter("tel");
		String tels	=	request.getParameter("tels");	
		
		String site_web	=	request.getParameter("site_web");
		String siege_social	=	request.getParameter("siege_social");
		String adresse	=	request.getParameter("adresse");
		String logo	=	request.getParameter("logo");
		String fibre	=	request.getParameter("fibre");
		String cie	=	request.getParameter("cie");
		String sodeci	=	request.getParameter("sodeci");
		String type_L	=	request.getParameter("type_L");
		String gps	=	request.getParameter("gps");
		
		site = (Site) session.getAttribute("site");
		
		if(verifieSess.sessions(request, response) == false) {
	response.sendRedirect( request.getContextPath() + INDEX );
	return;
 } 
		
		if(crud.equals("MODIFIER")) {
			entreprise = entrepriseDI.getEntreprise(Integer.parseInt(id));
			
			entreprise.setEntreprise(entreprises);
			entreprise.setCompte_contrib(compte_contrib);
			entreprise.setCnps(cnps);
			entreprise.setBanque_1(banque_1);
			entreprise.setBanque_2(banque_2);
			entreprise.setRib_1(rib_1);
			entreprise.setRib_2(rib_2);
			entreprise.setEmail(email);
			entreprise.setTel(tel);
			entreprise.setTels(tels);
			entreprise.setSite_web	(site_web);
			entreprise.setSiege_social(siege_social);
			entreprise.setAdresse(adresse);
			entreprise.setLogo(logo);
			entreprise.setFibre(fibre);
			entreprise.setCie(cie);
			entreprise.setSodeci(sodeci);
			entreprise.setType_L(type_L);
			entreprise.setGps(gps);
			entreprise.setId(Long.parseLong(id));
			
			if(entrepriseDI.modifierEntreprise(entreprise, errorMsg)) {
				request.setAttribute("entreprise", entreprise);
				request.setAttribute("message", new Message("Entreprise modifié avec succès."+ errorMsg.get(), 0, "green"));
			} else request.setAttribute("message", new Message("Echec modification Entreprise. " + errorMsg.get(), 0, "red") );
		}	
		
			
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(ENTREPRISE).forward(request, response);
	}

}
