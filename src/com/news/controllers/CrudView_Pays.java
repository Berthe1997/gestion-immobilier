package com.news.controllers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.news.beans.Pays;
import com.news.dao.PaysDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_pays")
public class CrudView_Pays extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/view_pays.jsp";
	public static final String PAY = "/WEB-INF/views/pays.jsp";

	
	 PaysDI paysDI = new PaysDI();
	 Pays pays = new Pays();
	 
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
				pays = paysDI.getPays(idU);
				request.setAttribute("pays", pays);
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}
		
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	HttpSession session = request.getSession();
			String id = request.getParameter("id");
			String page = request.getParameter("page");
			String crud = request.getParameter("crud");
			String nom = request.getParameter("nom");	
			String capital = request.getParameter("capital");
			String prefix = request.getParameter("prefix");
			String continent = request.getParameter("continent");
			
			if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
			
			
			if(crud.equals("MODIFIER")) {
				pays = paysDI.getPays(Integer.parseInt(id));
				
				pays.setPays(nom);	 
				pays.setCapital(capital);
				pays.setPrefix(prefix);			
				pays.setContinent(continent);
				pays.setId(Long.parseLong(id));
				if(paysDI.modifierpays(pays, errorMsg)) {
					request.setAttribute("pays", pays);
					request.setAttribute("message", new Message("Pays modifié avec succès."+ errorMsg.get(), 0, "green"));
				} else request.setAttribute("message", new Message("Echec modification Pays. " + errorMsg.get(), 0, "red") );
			}	
			
				
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}
}

