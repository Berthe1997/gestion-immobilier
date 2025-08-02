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
import com.news.beans.Gestionnaire;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.GestionnaireDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/gestionnaire")
public class CrudGestionnaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/gestionnaire.jsp";
	
	Agence agence = new Agence();
	Site site = new Site();
	 Users users	=	new Users();
	 
	 GestionnaireDI gestionnaireDI = new GestionnaireDI();
	 Gestionnaire gestionnaire = new Gestionnaire();
	
	 AtomicReference<String> errorMsg = new  AtomicReference<>("");
		VerifieSession verifieSess = new VerifieSession();
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String crud	=	request.getParameter("crud");
			String page	=	request.getParameter("page");
			String id	=	request.getParameter("id");
		
			agence = (Agence) session.getAttribute("agence");
			site = (Site) session.getAttribute("site");
			
				if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
				
				if(crud.equals("id")) {
					
					gestionnaire = gestionnaireDI.getGestionnaire(Integer.parseInt(id));
					gestionnaire.setId(Long.parseLong(id));
					gestionnaireDI.supprimerGestionnaire(gestionnaire, errorMsg);
				}
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}
		
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();		
			String page = request.getParameter("page");
			String crud = request.getParameter("crud");			
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String sexe = request.getParameter("sexe");
			String date_naiss= request.getParameter("date_naiss");
			String lieu_naiss= request.getParameter("lieu_naiss");
			String email= request.getParameter("email");
			String situationM= request.getParameter("situationM");
			String nationalite= request.getParameter("nationalite");
			String lieu_residence= request.getParameter("lieu_residence");
			String tel= request.getParameter("tel");
			String num_cnps= request.getParameter("num_cnps");
			String adresseP= request.getParameter("adresseP");
			String date_emb= request.getParameter("date_emb");
			String anc= request.getParameter("anc");
			String service= request.getParameter("service");		
			//==============DEUXIEME===============================//
			String telW = request.getParameter("telW");
			String tels = request.getParameter("tels");
			String cni= request.getParameter("cni");
			String cmu= request.getParameter("cmu");
			String banque_1= request.getParameter("banque_1");		
			String rib_1= request.getParameter("rib_1");			
			String image_cni= request.getParameter("image_cni");
			String photo= request.getParameter("photo");
			
			agence = (Agence) session.getAttribute("agence");
			users = (Users) session.getAttribute("users");
			site = (Site) session.getAttribute("site");
			
				if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
			
				String matricule = "AGIL"+"-"+tel;
			if(crud.equals("AJOUTER")) {
				
				if(gestionnaireDI.getVerifierGestionnaire(site.getSite(),matricule) == false) {
					
					gestionnaire.setMatricule(matricule);
					gestionnaire.setNom(nom);
					gestionnaire.setPrenom(prenom);
					gestionnaire.setSexe(sexe);
					gestionnaire.setDate_naiss(date_naiss);
					gestionnaire.setLieu_naiss(lieu_naiss);
					gestionnaire.setSituation_matrimonial(situationM);			
					gestionnaire.setNationalite(nationalite);
					gestionnaire.setLieu_residence(lieu_residence);
					gestionnaire.setTel(tel);
					gestionnaire.setEmail(email);
					gestionnaire.setNum_cnps(num_cnps);
					gestionnaire.setAdresse_postal(adresseP);			
					gestionnaire.setDate_embauchema(date_emb);
					gestionnaire.setAnciennete(anc);
					gestionnaire.setService(service);
					//==============DEUXIEME===============================//
					gestionnaire.setTel_whatsapp(telW);	
					gestionnaire.setTels(tels);
					gestionnaire.setCni(cni);
					gestionnaire.setCmu(cmu);
					gestionnaire.setBanque_1(banque_1);			
					gestionnaire.setRib_1(rib_1);			
					gestionnaire.setImage_cni(image_cni);
					gestionnaire.setPhoto(photo);
					gestionnaire.setSite(site.getSite());
					gestionnaire.setCode(agence.getCode());
										
					if(gestionnaireDI.creerGestionnaire(gestionnaire, errorMsg)) {																
						request.setAttribute("message", new Message("Gestionnaire enregistré avec succès."+ errorMsg.get(), 0, "green"));
					}else request.setAttribute("message", new Message("Echec enregistrement Gestionnaire. " + errorMsg.get(), 0, "red") );
					
				}else request.setAttribute("message", new Message("Cet Gestionnaire existe déjà." + errorMsg.get(), 0, "red") );			
		}
			
		
			
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}
	
}
