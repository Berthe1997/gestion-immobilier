package com.news.controllers_M;

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
import com.news.beans_M.Bien_meuble;
import com.news.controllers.Message;
import com.news.dao_M.Bien_meubleDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_bien_M")
public class CrudView_Bien_M extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/bien_M/view_bienM.jsp";
	
	 Bien_meubleDI bien_meubleDI= new Bien_meubleDI();
	 Bien_meuble bien_meuble= new Bien_meuble();
	
	Agence agence = new Agence();
	Site site = new Site();
	Users users	=	new Users();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			String page	=	request.getParameter("page");
			String id	=	request.getParameter("id");
							
				if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
				
				int idU = Integer.parseInt(id);
				bien_meuble = bien_meubleDI.getBien_meuble(idU);
				request.setAttribute("bien_meuble", bien_meuble);
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		String page = request.getParameter("page");
		String crud = request.getParameter("crud");	
		String id	=	request.getParameter("id");
		String bien = request.getParameter("bien");
		String type = request.getParameter("type");
		String adresse = request.getParameter("adresse");
		String statut = request.getParameter("statut");
		String loyerN = request.getParameter("loyerN");
		String caution = request.getParameter("caution");
		String description = request.getParameter("description");
		String date = request.getParameter("date");
		
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
		
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
		if(crud.equals("MODIFIER")) {
			bien_meuble = bien_meubleDI.getBien_meuble(Integer.parseInt(id));
			
			bien_meuble.setAgence(agence.getCode());
			bien_meuble.setBien(bien);
			bien_meuble.setType(type);
			bien_meuble.setAdresse(adresse);
			bien_meuble.setStatut(statut);
			bien_meuble.setLoyer_nuit(Integer.parseInt(loyerN));
			bien_meuble.setCaution(Integer.parseInt(caution));
			bien_meuble.setDescription(description);
			bien_meuble.setDate_ajout(date);
			bien_meuble.setSite(site.getSite());
			bien_meuble.setId(Long.parseLong(id));
			
			if(bien_meubleDI.modifierBien_meuble(bien_meuble, errorMsg)) {
				request.setAttribute("bien_meuble", bien_meuble);
				request.setAttribute("message", new Message("Bien meublée modifié avec succès."+ errorMsg.get(), 0, "green"));
			} else request.setAttribute("message", new Message("Echec modification Bien meublée. " + errorMsg.get(), 0, "red") );
		}	
		
			
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}

}
