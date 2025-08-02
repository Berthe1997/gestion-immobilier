package com.news.controllers;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.beans.Agence;
import com.news.beans.Calendrier_paiement;
import com.news.beans.Compte;
import com.news.beans.Locataire;
import com.news.beans.Maison;
import com.news.beans.Operation;
import com.news.beans.Paiement_loyer;
import com.news.beans.Paiement_service;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.Calendrier_paiementDI;
import com.news.dao.CompteDI;
import com.news.dao.LocataireDI;
import com.news.dao.MaisonDI;
import com.news.dao.OperationDI;
import com.news.dao.Paiement_loyerDI;
import com.news.dao.Paiement_serviceDI;
import com.news.fonctions.MiseAJourSomme;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_paiement_loyer")
public class CrudView_Paiement_loyer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	//public static final String IMMOBILIER = "/WEB-INF/views/paiement_loyer.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/view_paiement_loyer.jsp";
	

	 Site site = new Site();
	 Users users	=	new Users();
	 Agence agence = new Agence();
	 
	 Paiement_serviceDI paiement_serviceDI = new Paiement_serviceDI();
	 Paiement_service paiement_service = new Paiement_service();
	 
	 LocataireDI locataireDI = new LocataireDI();
	 Locataire locataire = new Locataire();
	 	 
	 MaisonDI maisonDI = new MaisonDI();
	 Maison maison = new Maison();
	 
	 Calendrier_paiementDI calendrier_paiementDI = new Calendrier_paiementDI();
	 Calendrier_paiement calendrier_paiement = new Calendrier_paiement();
	 		 
	 Paiement_loyerDI paiement_loyerDI = new Paiement_loyerDI();
	 Paiement_loyer paiement_loyer = new Paiement_loyer();	
	 
	 OperationDI operationDI = new OperationDI();
	 Operation operation = new Operation();
	 
	 CompteDI compteDI = new CompteDI();
	 Compte compte = new Compte();
	 
	 AtomicReference<String> errorMsg = new  AtomicReference<>("");
		VerifieSession verifieSess = new VerifieSession();
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String crud	=	request.getParameter("crud");
			String page	=	request.getParameter("page");
			String id	=	request.getParameter("id");
			String idl	=	request.getParameter("idl");	
			String montantS= request.getParameter("montantS");
			String matricule	=	request.getParameter("matricule");
			String ans = request.getParameter("ans");			
			String mois= request.getParameter("mois");
			String type= request.getParameter("type");
			String date= request.getParameter("date");
			String heure= request.getParameter("heure");
			
			String soldeP= request.getParameter("soldeP");
			String soldeA= request.getParameter("soldeA");
			String soldeI= request.getParameter("soldeI");
		
					
			users = (Users) session.getAttribute("users");
			site = (Site) session.getAttribute("site");
			agence = (Agence) session.getAttribute("agence");
			
				if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
										
				if("Loct".equals(crud)) {
												
				locataire = locataireDI.getLocataire(matricule);
				request.setAttribute("locataire", locataire);
								
				maison = maisonDI.getMaison(matricule);
				request.setAttribute("maison", maison);
								
				}
				
				if("PaiL".equals(crud)) {
										
					int idU = Integer.parseInt(id);
					locataire = locataireDI.getLocataire(idU);
					request.setAttribute("locataire", locataire);
					
					int ansU = Integer.parseInt(ans);
					  Calendrier_paiement CalP = calendrier_paiementDI.getCalendrier_paiement(ansU,mois,matricule);
					  if(CalP != null) {
							calendrier_paiement.setMontant_P(CalP.getMontant_P() - Integer.parseInt(montantS));
							calendrier_paiement.setMontant_R(CalP.getMontant_R() + Integer.parseInt(montantS));
							calendrier_paiement.setMontant_S(CalP.getMontant_P() - Integer.parseInt(montantS));
							calendrier_paiement.setStatut("impaye");
							calendrier_paiement.setAnnee(Integer.parseInt(ans));
							calendrier_paiement.setMois(mois);
							calendrier_paiement.setMatricule(matricule);
					  }			
				
					calendrier_paiementDI.modifierCalendrier_paiementS(calendrier_paiement, errorMsg);
					
					paiement_loyer = paiement_loyerDI.getPaiement_loyer(Integer.parseInt(idl));
					paiement_loyer.setId(Long.parseLong(idl));
					paiement_loyerDI.supprimerPaiement_loyer(paiement_loyer, errorMsg);
					
				//================OPERATION=================
					operation.setCode(agence.getCode());
					operation.setSite(site.getSite() );					
					operation.setMatricule(matricule);
					operation.setType(type);
					operation.setAnnee(Integer.parseInt(ans));
					operation.setMois(mois);
					operation.setDate(date);
					operation.setHeure(heure);
					operationDI.supprimerOperation(operation, errorMsg);
					
					
					Compte compteG = compteDI.getCompte(site.getCode(),site.getSite());
					if(compteG != null) {
						compte.setCode(agence.getCode());
						compte.setSite(site.getSite() );
						compte.setMatricule(site.getMatricule());
						compte.setSoldeP(compteG.getSoldeP()-Integer.parseInt(soldeP) );	
						compte.setSoldeA(compteG.getSoldeA()-Integer.parseInt(soldeA));	
						compte.setSoldeI(compteG.getSoldeI()-Integer.parseInt(soldeI));
					}
					compteDI.modifierCompte(compte, errorMsg);
					
					
					if(type.equals("caution")) {						
					    locataire.setCaution(0);		           
						locataire.setMatricule(matricule);  
						locataireDI.modifierLocataireAM(locataire, errorMsg); 	
					}
					if(type.equals("avance")) {						
					    locataire.setAvance(0);		           
						locataire.setMatricule(matricule);  
						locataireDI.modifierLocataireAM(locataire, errorMsg); 	
					}
					if(type.equals("commission")) {						
					    locataire.setCommission_E(0);		           
						locataire.setMatricule(matricule);  
						locataireDI.modifierLocataireAM(locataire, errorMsg); 	
					}
						
					MiseAJourSomme miseSomme = new MiseAJourSomme();
					miseSomme.LesSomme(request);
				}
								
				List<Paiement_loyer> listPaiL = paiement_loyerDI.getAllPaiement_loyer(site.getSite(),matricule); 
				request.setAttribute("listPaiL", listPaiL);
				
				List<Operation> listPO = operationDI.getAllOperation(site.getSite(),matricule); 
				request.setAttribute("listPO", listPO);
											
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}
		
		/*
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String id = request.getParameter("id");
			String page = request.getParameter("page");
			String crud = request.getParameter("crud");	
			String nom = request.getParameter("nom");
			String matricule = request.getParameter("matricule");			
			String montant= request.getParameter("montant");
			String montantS= request.getParameter("montantS");
			String montantM= request.getParameter("montantM");
			String ans = request.getParameter("ans");			
			String mois= request.getParameter("mois");
			String type = request.getParameter("type");
							
			
			String penalite = request.getParameter("penalite");
			String pourc	=	request.getParameter("pourc");
			int	P1 = 100, result=0;
			result= P1 - Integer.parseInt(pourc);	
			    
			    
			   
			  
								
			users = (Users) session.getAttribute("users");
			site = (Site) session.getAttribute("site");
			
				if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
				DateTime dt = new DateTime();
				org.joda.time.format.DateTimeFormatter formattere = DateTimeFormat.forPattern(FORMAT_DATE);
				String dating = dt.toString(formattere);
				/*	
				if(crud.equals("MODIFIER")) {
					paiement_loyer = paiement_loyerDI.getPaiement_loyer(Integer.parseInt(id));
					paiement_loyer.setLocataire(nom);	 
					paiement_loyer.setMatricule_locataire(matricule);
					paiement_loyer.setMatricule_locataire(matricule);
					paiement_loyer.setPropriete(site.getSite());			
					paiement_loyer.setMontant_loyer(Integer.parseInt(montantS));	
					paiement_loyer.setAns(ans);
					paiement_loyer.setMois(mois);
					paiement_loyer.setDate(dating);
					paiement_loyer.setPenalite(Integer.parseInt(penalite));
					paiement_loyer.setPourcentage(Integer.parseInt(pourc));
					paiement_loyer.setId(Long.parseLong(id));
					
					if(paiement_loyerDI.modifierPaiement_loyer(paiement_loyer, errorMsg)) {
						request.setAttribute("paiement_loyer", paiement_loyer);
						request.setAttribute("message", new Message("Paiement_loyer modifié avec succès."+ errorMsg.get(), 0, "green"));
					} else request.setAttribute("message", new Message("Echec modification Paiement_loyer. " + errorMsg.get(), 0, "red") );					
				}
				
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
			}
*/
}
