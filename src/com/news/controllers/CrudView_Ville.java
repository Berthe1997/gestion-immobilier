package com.news.controllers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.news.beans.Ville;
import com.news.dao.VilleDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_ville")
public class CrudView_Ville extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/view_ville.jsp";
	public static final String VILL = "/WEB-INF/views/ville.jsp";

	
	 VilleDI villeDI = new VilleDI();
	 Ville ville = new Ville();

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
				ville = villeDI.getVille(idU);
				request.setAttribute("ville", ville);
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}
		
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	HttpSession session = request.getSession();
			String id = request.getParameter("id");
			String page = request.getParameter("page");
			String crud = request.getParameter("crud");
			String nom = request.getParameter("nom");	
			String pays = request.getParameter("pays");
			String commentaire = request.getParameter("commentaire");
			
			if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
			
		
			
			if(crud.equals("MODIFIER")) {
				ville = villeDI.getVille(Integer.parseInt(id));
				
				ville.setVille(nom);	 
				ville.setPays(pays);	
				ville.setCommentaire(commentaire);	
				ville.setId(Long.parseLong(id));
				if(villeDI.modifierVille(ville, errorMsg)) {
					request.setAttribute("ville", ville);
					request.setAttribute("message", new Message("Ville modifié avec succès."+ errorMsg.get(), 0, "green"));
				} else request.setAttribute("message", new Message("Echec modification Ville. " + errorMsg.get(), 0, "red") );
			}	
			
				
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}

}
