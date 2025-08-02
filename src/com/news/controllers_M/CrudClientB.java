package com.news.controllers_M;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.news.beans.Agence;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.beans_M.Client_bien;
import com.news.beans_M.Reservation;
import com.news.dao_M.Client_bienDI;
import com.news.dao_M.ReservationDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/clientB")
public class CrudClientB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/bien_M/view_clientB.jsp";
	public static final String IMMOBILIERB = "/WEB-INF/bien_M/clientB.jsp";
	//private static final String FORMAT_DATE = "yyy-MM-dd";

	Client_bienDI client_bienDI= new Client_bienDI();
	Client_bien client_bien= new Client_bien();
	
	 ReservationDI reservationDI= new ReservationDI();
	 Reservation reservation= new Reservation();
	
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
				client_bien = client_bienDI.getClient_bien(Integer.parseInt(id));
				client_bien.setId(Long.parseLong(id));
				client_bienDI.supprimerClient_bien(client_bien, errorMsg);
				
				request.setAttribute("client_bien", client_bien);
			}
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIERB).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		String page = request.getParameter("page");	
		String submit = request.getParameter("submit");	
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String num_cni = request.getParameter("num_cni");
		String nationalite = request.getParameter("nationalite");
		String document = request.getParameter("document");
		String date = request.getParameter("date");
		
		
		
		
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
		
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
		
		
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		
		String matricule = agence.getCode()+"-"+client_bienDI.getLastIndex(agence.getCode())+".bm";
							
		if(submit.equals("AJOUTER")) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			if(client_bienDI.getClient_bienVerifie(matricule) == false) {
				
				client_bien.setAgence(agence.getCode());
				client_bien.setMatricule(matricule);
				client_bien.setNom(nom);
				client_bien.setPrenom(prenom);
				client_bien.setTel(tel);
				client_bien.setEmail(email);
				client_bien.setNum_cni(num_cni);
				client_bien.setNationalite(nationalite);
				client_bien.setDate_ajout(date);
				client_bien.setDocument(document);
				client_bien.setSite(site.getSite());
		
				if(client_bienDI.creerClient_bien(client_bien, errorMsg)) {
					List<Client_bien> listL = client_bienDI.getAllClient_bien(matricule);
					for(Client_bien loc: listL){
						objectBuilder.add("matricule", loc.getMatricule());							
					}
					
					objectBuilder.add("res","Client enregistré avec succès !");
				}else objectBuilder.add("res","Echec enregistrement Client!");					
			}else objectBuilder.add("res","Cet Client existe déjà !"); 
			
			
			
		arrayBuilder.add(objectBuilder);
     	response.setContentType("application/json;charset=utf8");
			response.getWriter().write(arrayBuilder.build().toString());
									
		return;
		}	
							
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}

}
