package com.news.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.news.beans.Agence;
import com.news.beans.Archive_locataire;
import com.news.beans.Calendrier_paiement;
import com.news.beans.Gestionnaire;
import com.news.beans.Locataire;
import com.news.beans.Maison;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.Archive_locataireDI;
import com.news.dao.Calendrier_paiementDI;
import com.news.dao.GestionnaireDI;
import com.news.dao.LocataireDI;
import com.news.dao.MaisonDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/locataire")
public class CrudLocataire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String LOCATAIRE = "/WEB-INF/views/locataire.jsp";
	private static final String FORMAT_DATE = "yyy-MM-dd";

	Agence agence = new Agence();
	 Site site = new Site();
	 Users users	=	new Users();
	 
	 LocataireDI locataireDI = new LocataireDI();
	 Locataire locataire = new Locataire();
	 
	 Archive_locataireDI archive_locataireDI = new Archive_locataireDI();
	 Archive_locataire archive_locataire = new Archive_locataire();
	 
	 GestionnaireDI gestionnaireDI = new GestionnaireDI();
	 Gestionnaire gestionnaire = new Gestionnaire();
	 
	 Calendrier_paiementDI calendrier_paiementDI = new Calendrier_paiementDI();
	 Calendrier_paiement calendrier_paiement = new Calendrier_paiement();
	 
	 MaisonDI maisonDI = new MaisonDI();
	 Maison maison = new Maison();
	 
	 AtomicReference<String> errorMsg = new  AtomicReference<>("");
		VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String crud	=	request.getParameter("crud");
			String page	=	request.getParameter("page");
			String id	=	request.getParameter("id");
		//	String idS = request.getParameter("idS");
			String typ	=	request.getParameter("typ");
			String matri	=	request.getParameter("matri");
		
			site = (Site) session.getAttribute("site");
			agence = (Agence) session.getAttribute("agence");
			
			gestionnaire = gestionnaireDI.getGestionnaire(typ);
			
			
			if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
			
			DateTime dt = new DateTime();
			org.joda.time.format.DateTimeFormatter formattere = DateTimeFormat.forPattern(FORMAT_DATE);
			String dating = dt.toString(formattere);
						
			if(crud.equals("id")) {
				
				locataire.setArchive(1);
				locataire.setMatricule(matri);
				
				Locataire locataireA = locataireDI.getLocataire(Integer.parseInt(id));
				if(locataireA != null) {
				
					archive_locataire.setSite(locataireA.getSite());
					archive_locataire.setMatricule(locataireA.getMatricule());
					archive_locataire.setNom(locataireA.getNom());
					archive_locataire.setPrenom(locataireA.getPrenom());
					archive_locataire.setSexe(locataireA.getSexe());
					archive_locataire.setDate_naiss(locataireA.getDate_naiss());
					archive_locataire.setLieu_naiss(locataireA.getLieu_naiss());
					archive_locataire.setSituation_matr(locataireA.getSituation_matr());
					archive_locataire.setFonction(locataireA.getFonction());
					archive_locataire.setNationalite(locataireA.getNationalite() );
					archive_locataire.setTel(locataireA.getTel());
					archive_locataire.setEmail(locataireA.getEmail());		
					archive_locataire.setNum_porte(locataireA.getNum_porte());
					archive_locataire.setType_contrat(locataireA.getType_contrat());
					archive_locataire.setDate_entree(locataireA.getDate_entree());
					archive_locataire.setDate_sortie(locataireA.getDate_sortie());
					archive_locataire.setDate_sortie(dating);
					archive_locataire.setTel_whatsapp(locataireA.getTel_whatsapp());
					archive_locataire.setPhoto(locataireA.getPhoto());
					archive_locataire.setCode(locataireA.getCode());
					archive_locataire.setAnnee(locataireA.getAnnee());					
					archive_locataire.setP_C(locataireA.getP_C());
					archive_locataire.setP_A(locataireA.getP_A());
					archive_locataire.setP_CO(locataireA.getP_CO());	
					archive_locataire.setCaution( locataireA.getCaution());
					archive_locataire.setAvance(locataireA.getAvance() );
					archive_locataire.setCommission_E( locataireA.getCommission_E() );
					
				}
				archive_locataireDI.creerArchive_locataire(archive_locataire, errorMsg);
				locataireDI.modifierLocataireAR(locataire, errorMsg);
				
				calendrier_paiement.setMatricule(matri);
				calendrier_paiementDI.supprimerCalendrier_paiement(calendrier_paiement, errorMsg);
				
				maison.setNom_prenom(" ");			
				maison.setMatricule(matri);
				maisonDI.modifierMaisons(maison, errorMsg);

			}
							
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(LOCATAIRE).forward(request, response);
		}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	//	String id = request.getParameter("id");
		String page = request.getParameter("page");
		String crud = request.getParameter("crud");	
		String submit = request.getParameter("submit");	
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String sexe = request.getParameter("sexe");		
		String date_naiss= request.getParameter("date_naiss");
		String lieu_naiss = request.getParameter("lieu_naiss");
		String situation_matr = request.getParameter("situation_matr");		
		String fonction = request.getParameter("fonction");
		String nationalite = request.getParameter("nationalite");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String num_porte = request.getParameter("num_porte");
		String type_contrat = request.getParameter("type_contrat");
		String date_entree = request.getParameter("date_entree");
	//	String date_entrees = request.getParameter("date_entrees");
		String date_sortie = request.getParameter("date_sortie");
		
		String telW = request.getParameter("telW");
		String photo = request.getParameter("photo");
		
		String p_C = request.getParameter("p_C");
		String p_A = request.getParameter("p_A");
		String p_CO = request.getParameter("p_CO");
		
				
		agence = (Agence) session.getAttribute("agence");			
		users = (Users) session.getAttribute("users");
		site = (Site) session.getAttribute("site");
		
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
			
						
			DateTime dt = new DateTime();
			org.joda.time.format.DateTimeFormatter formattere = DateTimeFormat.forPattern(FORMAT_DATE);
			String dating = dt.toString(formattere);
			
			LocalDate dates = LocalDate.now();
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("YYYY", Locale.FRENCH); 
			String ans = dates.format(formatters);	
			
		//	LocalDate dat = LocalDate.now(); 
		//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM", Locale.FRENCH); 
		//	String mois = dat.format(formatter);
			
			
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		
			String matricule = agence.getCode()+"-"+locataireDI.getLastIndex(agence.getCode(),site.getSite())+".tech";
			
		//	String matricule = "IM"+"-"+tel;	
			if(submit.equals("AJOUTER")) {
				
				System.out.println(site.getSite());
				JsonObjectBuilder objectBuilder = Json.createObjectBuilder(); 
				if(locataireDI.getVerifierLocataire(site.getSite(),matricule) == false) {	
					
					locataire.setSite(site.getSite());
					locataire.setMatricule(matricule);
					locataire.setNom(nom);
					locataire.setPrenom(prenom);
					locataire.setSexe(sexe);
					locataire.setDate_naiss(date_naiss);
					locataire.setLieu_naiss(lieu_naiss);
					locataire.setSituation_matr(situation_matr);
					locataire.setFonction(fonction);
					locataire.setNationalite(nationalite );
					locataire.setTel(tel);
					locataire.setEmail(email);		
					locataire.setNum_porte(num_porte);
					locataire.setType_contrat(type_contrat);
					locataire.setDate_entree(date_entree);
					locataire.setDate_sortie(date_sortie);
					locataire.setTel_whatsapp(telW);
					locataire.setPhoto(photo);
					locataire.setCode(agence.getCode());
					locataire.setAnnee(ans);
					locataire.setType("physique");
					locataire.setArchive(0);
					
					if(p_C != null) locataire.setP_C(Integer.parseInt(p_C));
					else locataire.setP_C(0);
					if(p_A != null) locataire.setP_A(Integer.parseInt(p_A));
					else locataire.setP_A(0);
					if(p_CO != null) locataire.setP_CO(Integer.parseInt(p_CO));
					else locataire.setP_CO(0);
									
					if(locataireDI.creerLocataire(locataire, errorMsg)) {
						List<Locataire> listL = locataireDI.getAllLocataireM(matricule);
						for(Locataire loc: listL){
							objectBuilder.add("matricule", loc.getMatricule());
							objectBuilder.add("matriculeM", loc.getMatricule());	
						}
						
						//request.setAttribute("block", "block");
						objectBuilder.add("res","Locataire enregistré avec succès !");
					}else objectBuilder.add("res","Echec enregistrement Locataire!");					
				}else objectBuilder.add("res","Cet Locataire existe déjà !"); 
				
				
				
			arrayBuilder.add(objectBuilder);
         	response.setContentType("application/json;charset=utf8");
 			response.getWriter().write(arrayBuilder.build().toString());
 				
 			
 			locataire = locataireDI.getLocataire(matricule);
			request.setAttribute("matricule", locataire);
			
			return;
			}	
			
			
			String date_entrees = request.getParameter("date_entrees");
			String type = request.getParameter("type");
			String dure = request.getParameter("dure");					
			String locataires = request.getParameter("locataire");	
			
			
			
			Locataire Nloc = locataireDI.getLocataire(locataires);	
			
			  if(submit.equals("AJOUTERS")) {	
					JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
														
				  if(type.equals("ANNEE")) {
					  // Date saisie par l'utilisateur
					  DateTimeFormatter formatterP = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.FRENCH);
					 LocalDate dateSaisie = LocalDate.parse(date_entrees,formatterP); // Remplacez par l'entrée utilisateur
				     // Obtenez l'année en cours
				      
				        int annees = dateSaisie.getYear();
				        int dureT = Integer.parseInt(dure);
			
					if(calendrier_paiementDI.getCalendrier_paiementVerifie(matricule,annees) == false) {	      
							       
				        // Liste pour stocker les dates de paiement
				        List<LocalDate> calendrierPaiement = new ArrayList<>();
				        // Parcourez les mois restants de l'année
				        DateTimeFormatter formatterM = DateTimeFormatter.ofPattern("MMMM",Locale.FRENCH);
				        DateTimeFormatter formatterJ = DateTimeFormatter.ofPattern("dd");
				        int J = dateSaisie.getDayOfMonth();
				        for (int annee = 0; annee < dureT; annee++) {
				            for (int mois = 0; mois < 12; mois++) {
				            	calendrierPaiement.add(dateSaisie.plusMonths(mois + annee * 12).withDayOfMonth(J)); // Exemple : paiement le 5 de chaque mois
				            }
				        } 
				        // Affichez les dates de paiement				    
				        for(LocalDate date : calendrierPaiement)  {
				        	 String moisNom = date.format(formatterM);
					        	// Met en majuscule la première lettre du mois
					        	 moisNom = moisNom.substring(0, 1).toUpperCase() + moisNom.substring(1).toLowerCase(); 
					        	    calendrier_paiement.setAnnee(date.getYear());
									calendrier_paiement.setMois(moisNom);
									calendrier_paiement.setSite(site.getSite());
									calendrier_paiement.setCode(agence.getCode());
									 if(Nloc != null) {
									calendrier_paiement.setLocataire(Nloc.getNom()+" "+Nloc.getPrenom());
									 }
									calendrier_paiement.setMatricule(locataires);			
									calendrier_paiement.setMontant_loyer(0);
									calendrier_paiement.setMontant_P(0);
									calendrier_paiement.setMontant_R(0);
									calendrier_paiement.setPenalite(0);				
									calendrier_paiement.setStatut("impaye");
									calendrier_paiement.setDate_paiement(date.format(formatterP));
																		
									
									calendrier_paiementDI.creerCalendrier_paiement(calendrier_paiement, errorMsg);	
				        }
				       
				        Calendrier_paiement Cal = calendrier_paiementDI.getCalendrier_paiementAn(locataires);
				        if(Cal != null) {
				        	locataire.setDate_sortie(Cal.getDate_paiement());
				        }
				        locataireDI.modifierLocataireAn(locataire, errorMsg);			        
					}
					
					arrayBuilder.add(objectBuilder);
		         	response.setContentType("application/json;charset=utf8");
		 			response.getWriter().write(arrayBuilder.build().toString());
		 			return;	
				}	
				  
				  if(type.equals("MOIS")) {
					  // Date saisie par l'utilisateur
					  DateTimeFormatter formatterP = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.FRENCH);
					 LocalDate dateSaisie = LocalDate.parse(date_entrees,formatterP); // Remplacez par l'entrée utilisateur
				     // Obtenez l'année en cours
				      
				        int annees = dateSaisie.getYear();
				        int dureT = Integer.parseInt(dure);
			
					if(calendrier_paiementDI.getCalendrier_paiementVerifie(matricule,annees) == false) {	      
							       
				        // Liste pour stocker les dates de paiement
				        List<LocalDate> calendrierPaiement = new ArrayList<>();
				        // Parcourez les mois restants de l'année
				        DateTimeFormatter formatterM = DateTimeFormatter.ofPattern("MMMM",Locale.FRENCH);
				        DateTimeFormatter formatterJ = DateTimeFormatter.ofPattern("dd");
				        int J = dateSaisie.getDayOfMonth();
				        for (int i = 0; i < dureT; i++) {
				        	calendrierPaiement.add(dateSaisie.plusMonths(i).withDayOfMonth(J)); // Paiement le 5 de chaque mois
				        }
				        
				        // Affichez les dates de paiement				    
				        for(LocalDate date : calendrierPaiement)  {
				        	 String moisNom = date.format(formatterM);
					        	// Met en majuscule la première lettre du mois
					        	 moisNom = moisNom.substring(0, 1).toUpperCase() + moisNom.substring(1).toLowerCase(); 
					        	    calendrier_paiement.setAnnee(date.getYear());
									calendrier_paiement.setMois(moisNom);
									calendrier_paiement.setSite(site.getSite());
									calendrier_paiement.setCode(agence.getCode());
									 if(Nloc != null) {
									  calendrier_paiement.setLocataire(Nloc.getNom()+" "+Nloc.getPrenom());
									  }
									calendrier_paiement.setMatricule(locataires);			
									calendrier_paiement.setMontant_loyer(0);
									calendrier_paiement.setMontant_P(0);
									calendrier_paiement.setMontant_R(0);
									calendrier_paiement.setPenalite(0);				
									calendrier_paiement.setStatut("impaye");
									calendrier_paiement.setDate_paiement(date.format(formatterP));
																		
									calendrier_paiementDI.creerCalendrier_paiement(calendrier_paiement, errorMsg);				        	 					        
				        }
				        
				       
				        Calendrier_paiement Cal = calendrier_paiementDI.getCalendrier_paiementAn(locataires);
				        if(Cal != null) {
				        	locataire.setDate_sortie(Cal.getDate_paiement());
				        }
				        locataireDI.modifierLocataireAn(locataire, errorMsg);
				      
									        
				    	objectBuilder.add("res","le contrat a été enregistre avec succes !");
					}else objectBuilder.add("res","Echec enregistrement contrat !");
					
				}
				 
			  }  
			
			
			
		}
	
}
