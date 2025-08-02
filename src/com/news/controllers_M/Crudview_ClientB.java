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
import com.news.beans_M.Client_bien;
import com.news.beans_M.Resident_bien;
import com.news.controllers.Message;
import com.news.dao_M.Client_bienDI;
import com.news.dao_M.Resident_bienDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_clientB")
public class Crudview_ClientB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/bien_M/view_clientB.jsp";
	
	Client_bienDI client_bienDI= new Client_bienDI();
	Client_bien client_bien= new Client_bien();
	
    Resident_bienDI resident_bienDI= new Resident_bienDI();
	Resident_bien resident_bien= new Resident_bien();

	
	Agence agence = new Agence();
	Site site = new Site();
	Users users	=	new Users();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
		String crud	=	request.getParameter("crud");
						
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
			 if(crud.equals("id")) {
				int idU = Integer.parseInt(id);
				client_bien = client_bienDI.getClient_bien(idU);
				request.setAttribute("client_bien", client_bien);
			 }
			
			 if(crud.equals("idR")) {
				 resident_bien = resident_bienDI.getResident_bien(Integer.parseInt(id));
				 resident_bien.setId(Long.parseLong(id));
				 resident_bienDI.supprimerResident_bien(resident_bien, errorMsg);
            	 
					request.setAttribute("resident_bien", resident_bien); 
					request.setAttribute("client_bien", client_bien); 
			 }
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		String page = request.getParameter("page");
		String crud = request.getParameter("crud");	
		String id	=	request.getParameter("id");			
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String num_cni = request.getParameter("num_cni");
		String nationalite = request.getParameter("nationalite");	
		String date = request.getParameter("date");
		String matricule = request.getParameter("matricule");
		String document = request.getParameter("document");
		
	  //==============RESIDNT CLIENT===============================//
		String nomC = request.getParameter("nomC");
		String prenomC = request.getParameter("prenomC");
		String matriculeC = request.getParameter("matriculeC");
		String liencli = request.getParameter("liencli");
		String num_cniC = request.getParameter("num_cniC");
		String cni = request.getParameter("cni");
		
		
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
		
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
		if(crud.equals("resident")) {
			Client_bien clientB = client_bienDI.getClient_bien(matriculeC);
			
			resident_bien.setAgence(agence.getCode());
			resident_bien.setCode_reservation(matriculeC);
			resident_bien.setNom(nomC);
			resident_bien.setPrenom(prenomC);						
			resident_bien.setNum_cni(num_cniC);
			resident_bien.setLien_client(liencli);
			resident_bien.setSite(site.getSite());
			resident_bien.setCni(cni);
			
  			if(resident_bienDI.creerResident_bien(resident_bien, errorMsg)) {
  				 request.setAttribute("resident_bien", resident_bien);
				request.setAttribute("message", new Message("Resident enregistré avec succès."+ errorMsg.get(), 0, "green"));
			} else request.setAttribute("message", new Message("Echec enregistrement Resident. " + errorMsg.get(), 0, "red") );
		                                      
             request.setAttribute("client_bien", client_bien);
             request.setAttribute("client_bien", clientB);
          }	
		
		
		if(crud.equals("MODIFIER")) {
			client_bien = client_bienDI.getClient_bien(Integer.parseInt(id));
			
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
			client_bien.setId(Long.parseLong(id));
						
			resident_bien.setCni(num_cni);
			resident_bien.setCode_reservation(matricule);
			
			if(client_bienDI.modifierClient_bien(client_bien, errorMsg)) {
				resident_bienDI.modifierResident_bien(resident_bien, errorMsg);
				
				request.setAttribute("client_bien", client_bien);
				request.setAttribute("message", new Message("Client modifié avec succès."+ errorMsg.get(), 0, "green"));
			} else request.setAttribute("message", new Message("Echec modification Client. " + errorMsg.get(), 0, "red") );
		}	
		
			
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}


}
