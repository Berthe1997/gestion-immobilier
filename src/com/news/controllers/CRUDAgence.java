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
import com.news.beans.Site;
import com.news.dao.AgenceDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/agence")
public class CRUDAgence extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	private static final String AGENCE = "/WEB-INF/views/agence.jsp";

	
	Site site = new Site();

	Agence agence = new Agence();
	AgenceDI agenceDI = new AgenceDI();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
		
		
		site = (Site) session.getAttribute("site");
		
			if(verifieSess.sessions(request, response) == false) {
		response.sendRedirect( request.getContextPath() + INDEX );
		return;
	} 
			
			if(crud.equals("id")) {
			
				agence = agenceDI.getAgence(Integer.parseInt(id));
				agence.setId(Long.parseLong(id));
				agenceDI.supprimerAgence(agence, errorMsg);
			}
		
		
		
		request.setAttribute("page", page);

		request.setAttribute("agence", agence);
		this.getServletContext().getRequestDispatcher(AGENCE).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
	//	String id	=	request.getParameter("id");
		String agenc	=	request.getParameter("agenc");
		String adresse	=	request.getParameter("adresse");
		String email	=	request.getParameter("email");
		String tel	=	request.getParameter("tel");
		String tel_fax	=	request.getParameter("tel_fax");
		String site_web	=	request.getParameter("site_web");
		String directeur	=	request.getParameter("directeur");
		String date_ajout	=	request.getParameter("date_ajout");
		String logo	=	request.getParameter("logo");
		String registre_C	=	request.getParameter("registre_C");	
		//===================NEW CHAMP=======================================//	
		String localisation	=	request.getParameter("localisation");
		String adresse_postal	=	request.getParameter("adresse_postal");
		String tels	=	request.getParameter("tels");
		String gps	=	request.getParameter("gps");
		String fibre	=	request.getParameter("fibre");
		String montant_L	=	request.getParameter("montant_L");	
		String type_L	=	request.getParameter("type_L");
		String cie	=	request.getParameter("cie");
		String sodeci	=	request.getParameter("sodeci");
		String code	=	request.getParameter("code");
		
		site = (Site) session.getAttribute("site");
		
			if(verifieSess.sessions(request, response) == false) {
		response.sendRedirect( request.getContextPath() + INDEX );
		return;
	 } 
	
		if(crud.equals("AJOUTER")) {
			if(agenceDI.getAgenceVerifie(agenc) == false) {			
			agence.setAgence(agenc);
			agence.setAdresse(adresse);
			agence.setTel(tel);
			agence.setTel_fax(tel_fax);
			agence.setEmail(email);
			agence.setSite_web(site_web);
			agence.setDirecteur(directeur);
			agence.setDate_ajout(date_ajout);
			agence.setLogo(logo);
			agence.setRegistre_C(registre_C);
			agence.setCode(code);
		//===================NEW CHAMP=======================================//				
			agence.setLocalisation(localisation);
			agence.setAdresse_postal(adresse_postal);
			agence.setTels(tels);
			agence.setGps(gps);
			agence.setFibre(fibre);
			agence.setMontant_L(montant_L);
			agence.setType_L(type_L);
			agence.setCie(cie);
			agence.setSodeci(sodeci);
			
				if(agenceDI.creerAgence(agence, errorMsg)) {
					request.setAttribute("message", new Message("Agence enregistr� avec succ�s."+ errorMsg.get(), 0, "green"));
				}else request.setAttribute("message", new Message("Echec enregistrement Agence. " + errorMsg.get(), 0, "red") );
			}else request.setAttribute("message", new Message("Cet Agence existe d�j�. " + errorMsg.get(), 0, "red") );
		}
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(AGENCE).forward(request, response);
	}

}
