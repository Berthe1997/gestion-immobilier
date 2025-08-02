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
import com.news.beans.Service_technique;
import com.news.beans.Site;
import com.news.dao.Service_techniqueDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_serviceT")
public class CRUDView_Service_technique extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	private static final String SERVICET = "/WEB-INF/views/view_service_technique.jsp";
	
	Site site = new Site();

	Agence agence = new Agence();
	
	Service_techniqueDI service_techniqueDI = new Service_techniqueDI();
	Service_technique service_technique = new Service_technique();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	//	String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
		
		
		site = (Site) session.getAttribute("site");
		
			if(verifieSess.sessions(request, response) == false) {
		response.sendRedirect( request.getContextPath() + INDEX );
		return;
	} 
			
			int idU = Integer.parseInt(id);
			service_technique = service_techniqueDI.getService_technique(idU);
			request.setAttribute("service_technique", service_technique);
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(SERVICET).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
		String service	=	request.getParameter("service");
		String designation	=	request.getParameter("designation");
		String quantite	=	request.getParameter("quantite");
		String prixU	=	request.getParameter("prixU");
		String total	=	request.getParameter("total");
		String date	=	request.getParameter("date");
		
				
		
		 agence = (Agence) session.getAttribute("agence");
		  site = (Site) session.getAttribute("site");
			
			if(verifieSess.sessions(request, response) == false) {
		response.sendRedirect( request.getContextPath() + INDEX );
		return;
			}
			
			if(crud.equals("MODIFIER")) {
				service_technique = service_techniqueDI.getService_technique(Integer.parseInt(id));
				
				service_technique.setCode(agence.getCode());
				service_technique.setSite(site.getSite());
				service_technique.setService(service);
				service_technique.setDesignation(designation);
				service_technique.setQuantite(Integer.parseInt(quantite));
				service_technique.setPrixU(Integer.parseInt(prixU));
				service_technique.setTotal(Integer.parseInt(quantite)*Integer.parseInt(prixU));
				service_technique.setDate(date);
				service_technique.setId(Long.parseLong(id));
				
				if(service_techniqueDI.modifierService_technique(service_technique, errorMsg)) {
					request.setAttribute("service_technique", service_technique);
					request.setAttribute("message", new Message("Service_technique modifié avec succès."+ errorMsg.get(), 0, "green"));
				} else request.setAttribute("message", new Message("Echec modification Service_technique. " + errorMsg.get(), 0, "red") );
			}	
			
				
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(SERVICET).forward(request, response);
		}

}
