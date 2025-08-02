package com.news.controllers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.beans.Service;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.ServiceDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/service")
public class CrudService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/service.jsp";

	 Site site = new Site();
	 Users users	=	new Users();
	
	ServiceDI serviceDI = new ServiceDI();
	Service service = new Service();
	
	 AtomicReference<String> errorMsg = new  AtomicReference<>("");
		VerifieSession verifieSess = new VerifieSession();
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String crud	=	request.getParameter("crud");
			String page	=	request.getParameter("page");
			String id	=	request.getParameter("id");
			     			
			users = (Users) session.getAttribute("users");
			site = (Site) session.getAttribute("site");
			
				if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
				
				if(crud.equals("id")) {
					service = serviceDI.getService(Integer.parseInt(id));
					service.setId(Long.parseLong(id));
					serviceDI.supprimerService(service, errorMsg);
				}
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}				
	
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				HttpSession session = request.getSession();
				String page = request.getParameter("page");
				String crud = request.getParameter("crud");
				String services = request.getParameter("services");	
				String type = request.getParameter("type");
				String statut = request.getParameter("statut");
				String montant = request.getParameter("montant");
				
				if(verifieSess.sessions(request, response) == false) {
					response.sendRedirect( request.getContextPath() + INDEX );
					return;
				} 
				
				users = (Users) session.getAttribute("users");
				site = (Site) session.getAttribute("site");
				
				if(crud.equals("AJOUTER")) {
					
					if(serviceDI.getVerifierService(site.getSite(),services) == false) {
						
						service.setSite(site.getSite());
						service.setService(services);
						service.setType_service(type);
						service.setStatut(statut);
						service.setMontant(Integer.parseInt(montant));
				
						if(serviceDI.creerService(service, errorMsg)) {																
							request.setAttribute("message", new Message("service enregistré avec succès."+ errorMsg.get(), 0, "green"));
						}else request.setAttribute("message", new Message("Echec enregistrement service. " + errorMsg.get(), 0, "red") );
						
					}else request.setAttribute("message", new Message("Cet service existe déjà." + errorMsg.get(), 0, "red") );
				
			}
							
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
			}		
		
}
