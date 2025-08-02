package com.news.controllers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.news.beans.Commune;
import com.news.dao.CommuneDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_commune")
public class CrudView_Commune extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String com = "/WEB-INF/views/commune.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/view_commune.jsp";

	CommuneDI communeDI = new CommuneDI();
	Commune commune = new Commune();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	HttpSession session = request.getSession();
		String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
			
		
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
			
			int idU = Integer.parseInt(id);
			commune = communeDI.getCommune(idU);
			request.setAttribute("commune", commune);
		
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
			String ville = request.getParameter("ville");
			String commentaire = request.getParameter("commentaire");
			
			if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
			
		

			if(crud.equals("MODIFIER")) {
				commune = communeDI.getCommune(Integer.parseInt(id));
				
				commune.setCommune(nom);	 
				commune.setPays(pays);
				commune.setVille(ville);			
				commune.setCommentaire(commentaire);
				commune.setId(Long.parseLong(id));
				if(communeDI.modifierCommune(commune, errorMsg)) {
					request.setAttribute("commune", commune);
					request.setAttribute("message", new Message("Commune modifié avec succès."+ errorMsg.get(), 0, "green"));
				} else request.setAttribute("message", new Message("Echec modification Commune. " + errorMsg.get(), 0, "red") );
			}	
			
			
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}
	

}
