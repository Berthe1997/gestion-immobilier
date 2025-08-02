package com.news.controllers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.news.beans.Service;
import com.news.dao.ServiceDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_service")
public class Crudview_Service extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/view_service.jsp";
	public static final String PAY = "/WEB-INF/views/service.jsp";
	
	ServiceDI serviceDI = new ServiceDI();
	Service service = new Service();

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
				service = serviceDI.getService(idU);
				request.setAttribute("service", service);
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}
	
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//	HttpSession session = request.getSession();
				String id = request.getParameter("id");
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
				
				String test = request.getParameter("test");
				System.out.println(test);
				
				if(crud.equals("MODIFIER")) {
					service = serviceDI.getService(Integer.parseInt(id));
									
					service.setService(services);
					service.setType_service(type);
					service.setStatut(statut);
					service.setMontant(Integer.parseInt(montant));
					service.setId(Long.parseLong(id));
					if(serviceDI.modifierService(service, errorMsg)) {
						request.setAttribute("service", service);
						request.setAttribute("message", new Message("service modifié avec succès."+ errorMsg.get(), 0, "green"));
					} else request.setAttribute("message", new Message("Echec modification service. " + errorMsg.get(), 0, "red") );
				}	
				
					
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
			}		
		
}
