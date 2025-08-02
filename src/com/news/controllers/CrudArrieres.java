package com.news.controllers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.news.beans.Agence;
import com.news.beans.Arrieres;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.ArrieresDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/arrieres")
public class CrudArrieres extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/gestion_arrieres.jsp";
	private static final String FORMAT_DATE = "yyy-MM-dd";

	ArrieresDI arrieresDI = new ArrieresDI();
	Arrieres arrieres = new Arrieres();
	
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
				arrieres = arrieresDI.getArrieres(Integer.parseInt(id));
				arrieres.setId(Long.parseLong(id));
				arrieresDI.supprimerArrieres(arrieres, errorMsg);
			}
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();		
			String page = request.getParameter("page");
			String crud = request.getParameter("crud");
			String matricule = request.getParameter("matricule");	
			String montant = request.getParameter("montant");
			String ans = request.getParameter("ans");
			String mois = request.getParameter("mois");
			
			if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
			
			site = (Site) session.getAttribute("site");
			agence = (Agence) session.getAttribute("agence");
			
			DateTime dt = new DateTime();
			org.joda.time.format.DateTimeFormatter formattere = DateTimeFormat.forPattern(FORMAT_DATE);
			String dating = dt.toString(formattere);
						
			
			if(crud.equals("AJOUTER")) {
				
				if(arrieresDI.getVerifierArrieres(agence.getCode(),site.getSite(),matricule,ans,mois) == false) {
					
					  arrieres.setCode(agence.getCode());
					  arrieres.setSite(site.getSite());
					  arrieres.setMatricule(matricule);
					  arrieres.setMontant(Integer.parseInt(montant));
					  arrieres.setAns(ans);
					  arrieres.setMois(mois);
					  arrieres.setDate(dating);
			
					if(arrieresDI.creerArrieres(arrieres, errorMsg)) {																
						request.setAttribute("message", new Message("arrieres enregistré avec succès."+ errorMsg.get(), 0, "green"));
					}else request.setAttribute("message", new Message("Echec enregistrement arrieres. " + errorMsg.get(), 0, "red") );
					
				}else request.setAttribute("message", new Message("Cette arrieres existe déjà." + errorMsg.get(), 0, "red") );			
		}
						
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}

}
