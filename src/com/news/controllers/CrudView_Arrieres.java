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
import com.news.beans.Arrieres;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.ArrieresDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_arrieres")
public class CrudView_Arrieres extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/view_arrieres.jsp";
	
	
	ArrieresDI arrieresDI = new ArrieresDI();
	Arrieres arrieres = new Arrieres();
	
	Agence agence = new Agence();
	Site site = new Site();
	Users users	=	new Users();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
			
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
			int idU = Integer.parseInt(id);
			arrieres = arrieresDI.getArrieres(idU);
			request.setAttribute("arrieres", arrieres);
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		String id = request.getParameter("id");
		String page = request.getParameter("page");
		String crud = request.getParameter("crud");
		String matricule = request.getParameter("matricule");	
		String montant = request.getParameter("montant");
		String ans = request.getParameter("ans");
		String mois = request.getParameter("mois");
		String date = request.getParameter("date");
			
			if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
			
			site = (Site) session.getAttribute("site");
			agence = (Agence) session.getAttribute("agence");
		

			if(crud.equals("MODIFIER")) {
				arrieres = arrieresDI.getArrieres(Integer.parseInt(id));
				
				 arrieres.setCode(agence.getCode());
				  arrieres.setSite(site.getSite());
				  arrieres.setMatricule(matricule);
				  arrieres.setMontant(Integer.parseInt(montant));
				  arrieres.setAns(ans);
				  arrieres.setMois(mois);
				  arrieres.setDate(date);
				  arrieres.setId(Long.parseLong(id));
				if(arrieresDI.modifierArrieres(arrieres, errorMsg)) {
					request.setAttribute("arrieres", arrieres);
					request.setAttribute("message", new Message("arrieres modifié avec succès."+ errorMsg.get(), 0, "green"));
				} else request.setAttribute("message", new Message("Echec modification arrieres. " + errorMsg.get(), 0, "red") );
			}	
			
			
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}

}
