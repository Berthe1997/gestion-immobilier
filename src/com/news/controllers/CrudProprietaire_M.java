package com.news.controllers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.beans.Proprietaire_m;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.Proprietaire_mDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/proprietaire_m")
public class CrudProprietaire_M extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String PROPRIETAIRE = "/WEB-INF/views/proprietaire_M.jsp";

	
	Site site = new Site();
	 Users users	=	new Users();
	 
	 Proprietaire_mDI proprietaire_mDI = new Proprietaire_mDI();
	 Proprietaire_m proprietaire_m = new Proprietaire_m();
	 
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
				
					proprietaire_m = proprietaire_mDI.getProprietaire_m(Integer.parseInt(id));
					proprietaire_m.setId(Long.parseLong(id));
					proprietaire_mDI.supprimerProprietaire_m(proprietaire_m, errorMsg);
				}
			
			
			
			request.setAttribute("page", page);

			request.setAttribute("proprietaire_m", proprietaire_m);
			this.getServletContext().getRequestDispatcher(PROPRIETAIRE).forward(request, response);
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
			//===================NEW CHAMP=======================================//	
			String localisation	=	request.getParameter("localisation");
			String adresse_postal	=	request.getParameter("adresse_postal");
			String tels	=	request.getParameter("tels");
			String gps	=	request.getParameter("gps");
			String taux= request.getParameter("taux");
			String dure_contrat= request.getParameter("dure_contrat");
			String type= request.getParameter("type");
			
			site = (Site) session.getAttribute("site");
			
				if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		 } 
				String matricule = "AGIL"+"-"+tel;
			if(crud.equals("AJOUTER")) {
				if(proprietaire_mDI.getVerifierProprietaire_m(matricule) == false) {			
				proprietaire_m.setMatricule(matricule);
				proprietaire_m.setNom(agenc);
				proprietaire_m.setCode(" ");
				proprietaire_m.setAdresse(adresse);
				proprietaire_m.setTel(tel);
				proprietaire_m.setTel_fax(tel_fax);
				proprietaire_m.setEmail(email);
				proprietaire_m.setSite_web(site_web);
				proprietaire_m.setDirecteur(directeur);
				proprietaire_m.setDate_ajout(date_ajout);
				proprietaire_m.setLogo(logo);				
				
			//===================NEW CHAMP=======================================//				
				proprietaire_m.setLocalisation(localisation);
				proprietaire_m.setAdresse_postal(adresse_postal);
				proprietaire_m.setTels(tels);
				proprietaire_m.setGps(gps);
				proprietaire_m.setTaux(Integer.parseInt(taux));			
				proprietaire_m.setDure_contrat(Integer.parseInt(dure_contrat));
				proprietaire_m.setType_contrat(type);
				
					if(proprietaire_mDI.creerProprietaire_m(proprietaire_m, errorMsg)) {
						request.setAttribute("message", new Message("proprietaire_moral enregistré avec succès."+ errorMsg.get(), 0, "green"));
					}else request.setAttribute("message", new Message("Echec enregistrement proprietaire_moral. " + errorMsg.get(), 0, "red") );
				}else request.setAttribute("message", new Message("Cet proprietaire_moral existe déjà. " + errorMsg.get(), 0, "red") );
			}
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(PROPRIETAIRE).forward(request, response);
		}

}