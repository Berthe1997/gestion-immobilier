package com.news.controllers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.beans.Proprietaire;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.ProprietaireDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/proprietaire")
public class CRUDProprietaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/proprietaire.jsp";
	
	
	 Site site = new Site();
	 Users users	=	new Users();
	 
	 ProprietaireDI proprietaireDI = new ProprietaireDI();
	 Proprietaire proprietaire = new Proprietaire();
	 
	
	 AtomicReference<String> errorMsg = new  AtomicReference<>("");
		VerifieSession verifieSess = new VerifieSession();
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String crud	=	request.getParameter("crud");
			String page	=	request.getParameter("page");
			String id	=	request.getParameter("id");
		
			
			site = (Site) session.getAttribute("site");
			
				if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
				
				if(crud.equals("id")) {
					
					proprietaire = proprietaireDI.getProprietaire(Integer.parseInt(id));
					proprietaire.setId(Long.parseLong(id));
					proprietaireDI.supprimerProprietaire(proprietaire, errorMsg);
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
			String adresseP= request.getParameter("adresseP");
			String date_ajout= request.getParameter("date_ajout");
			String telW = request.getParameter("telW");
			//==============DEUXIEME===============================//
			String tels = request.getParameter("tels");
			String cni= request.getParameter("cni");
			String cmu= request.getParameter("cmu");
			String banque_1= request.getParameter("banque_1");
			String banque_2= request.getParameter("banque_2");
			String rib_1= request.getParameter("rib_1");
			String rib_2= request.getParameter("rib_2");
			String profession= request.getParameter("profession");
			String image_cni= request.getParameter("image_cni");
			String photo= request.getParameter("photo");
			
			String taux= request.getParameter("taux");
			String dure_contrat= request.getParameter("dure_contrat");
			String type= request.getParameter("type");
			String image= request.getParameter("image");
			
			
		
			
			users = (Users) session.getAttribute("users");
			site = (Site) session.getAttribute("site");
			
				if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
			
				String matricule = "AGIL"+"-"+tel;
			if(crud.equals("AJOUTER")) {
				
				if(proprietaireDI.getVerifierProprietaire(matricule) == false) {
					
					proprietaire.setMatricule(matricule);
					proprietaire.setNom(nom);
					proprietaire.setPrenom(prenom);
					proprietaire.setSexe(sexe);
					proprietaire.setDate_naiss(date_naiss);
					proprietaire.setLieu_naiss(lieu_naiss);
					proprietaire.setLieu_residence(lieu_residence);
					proprietaire.setTel(tel);
					proprietaire.setEmail(email);
					proprietaire.setAdresse_postal(adresseP);
					proprietaire.setNationalite(nationalite);			
					proprietaire.setSituation_matri(situationM);			
					proprietaire.setDate_ajout(date_ajout);
					proprietaire.setTel_whatsapp(telW);
					//==============DEUXIEME===============================//	
					proprietaire.setTels(tels);
					proprietaire.setCni(cni);
					proprietaire.setCmu(cmu);
					proprietaire.setBanque_1(banque_1);
					proprietaire.setBanque_2(banque_2);
					proprietaire.setRib_1(rib_1);			
					proprietaire.setRib_2(rib_2);			
					proprietaire.setProfession(profession);
					proprietaire.setImage_cni(image_cni);
					proprietaire.setPhoto(photo);
					
					proprietaire.setTaux(Integer.parseInt(taux));			
					proprietaire.setDure_contrat(Integer.parseInt(dure_contrat));
					proprietaire.setType_contrat(type);
					proprietaire.setImage_contrat(image);
										
					if(proprietaireDI.creerProprietaire(proprietaire, errorMsg)) {																
						request.setAttribute("message", new Message("Proprietaire enregistré avec succès."+ errorMsg.get(), 0, "green"));
					}else request.setAttribute("message", new Message("Echec enregistrement Proprietaire. " + errorMsg.get(), 0, "red") );
					
				}else request.setAttribute("message", new Message("Cet Proprietaire existe déjà." + errorMsg.get(), 0, "red") );			
		}
			
		
			
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}

}
