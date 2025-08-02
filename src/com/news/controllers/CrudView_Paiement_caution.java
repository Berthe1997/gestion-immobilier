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

import com.news.beans.Calendrier_paiement;
import com.news.beans.Compte_gestionnaire;
import com.news.beans.Compte_proprietaire;
import com.news.beans.Impot_loyer;
import com.news.beans.Locataire;
import com.news.beans.Maison;
import com.news.beans.Paiement_service;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.Calendrier_paiementDI;
import com.news.dao.Compte_gestionnaireDI;
import com.news.dao.Compte_proprietaireDI;
import com.news.dao.Impot_loyerDI;
import com.news.dao.LocataireDI;
import com.news.dao.MaisonDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_paiement_caution")
public class CrudView_Paiement_caution extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	//public static final String IMMOBILIER = "/WEB-INF/views/paiement_loyer.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/view_paiement_caution.jsp";
	private static final String FORMAT_DATE = "yyy-MM-dd";

	Site site = new Site();
	 Users users	=	new Users();
	 
	 Paiement_service paiement_service = new Paiement_service();
	 
	 LocataireDI locataireDI = new LocataireDI();
	 Locataire locataire = new Locataire();
	 
	 Compte_gestionnaireDI compte_gestionnaireDI = new Compte_gestionnaireDI();
	 Compte_gestionnaire compte_gestionnaire = new Compte_gestionnaire();
	 
	 Compte_proprietaireDI compte_proprietaireDI = new Compte_proprietaireDI();
	 Compte_proprietaire compte_proprietaire = new Compte_proprietaire();
	 
	 MaisonDI maisonDI = new MaisonDI();
	 Maison maison = new Maison();
	 
	 Calendrier_paiementDI calendrier_paiementDI = new Calendrier_paiementDI();
	 Calendrier_paiement calendrier_paiement = new Calendrier_paiement();
	 
	 Impot_loyerDI impot_loyerDI = new Impot_loyerDI();
	 Impot_loyer impot_loyer = new Impot_loyer();
	 
	 AtomicReference<String> errorMsg = new  AtomicReference<>("");
		VerifieSession verifieSess = new VerifieSession();
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String crud	=	request.getParameter("crud");
			String page	=	request.getParameter("page");
			String id	=	request.getParameter("id");
			//SString idl	=	request.getParameter("idl");	
			String montantS= request.getParameter("montantS");
			String matricule	=	request.getParameter("matricule");
			String ans = request.getParameter("ans");			
			String mois= request.getParameter("mois");
		
					
			site = (Site) session.getAttribute("site");
			
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
					String pourcI	=	request.getParameter("pourcI");														
					String pourcA	=	request.getParameter("pourcA");
					int	PA = 100, resultA=0;
					resultA= PA - Integer.parseInt(pourcA) - Integer.parseInt(pourcI);	
					
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
					
					//---------------------------------------------------COMMISSION GESTIONNAIRE-------------------------------------//		
	                  Compte_gestionnaire CompteG = compte_gestionnaireDI.getCompte_gestionnaire(site.getSite(),ansU,mois);
	                  int  comptA= Integer.parseInt(montantS) * Integer.parseInt(pourcA) /100;
	                  
						compte_gestionnaire.setMontant_commission(CompteG.getMontant_commission()-comptA);
						compte_gestionnaire.setPropriete(site.getSite());
						compte_gestionnaire.setAns(ansU);
						compte_gestionnaire.setMois(mois);
	                      
						compte_gestionnaireDI.modifierCompte_gestionnaire(compte_gestionnaire, errorMsg);		
								
						//-----------------------------------------COMPTE PROPRIETAIRE-----------------------------------------------//		
						Impot_loyer CompteI = impot_loyerDI.getImpot_loyer(site.getSite(),ansU,mois);
			             int  comptPI= Integer.parseInt(montantS) * Integer.parseInt(pourcI) /100;
			                 
			             impot_loyer.setMontant(CompteI.getMontant()- comptPI);
					     impot_loyer.setSite(site.getSite());
						 impot_loyer.setAns(ansU);
						 impot_loyer.setMois(mois);
																									
						impot_loyerDI.modifierImpot_loyer(impot_loyer, errorMsg);	
						
						//-----------------------------------------COMPTE PROPRIETAIRE-----------------------------------------------//		
		                 Compte_proprietaire CompteP = compte_proprietaireDI.getCompte_proprietaire(site.getSite(),ansU,mois);
		                 int  comptPR= Integer.parseInt(montantS) * resultA /100;
		                 
							compte_proprietaire.setMontant_APC(CompteP.getMontant_APC()- comptPR);
							compte_proprietaire.setPropriete(site.getSite());
							compte_proprietaire.setAns(ansU);
							compte_proprietaire.setMois(mois);
							
						
		                   
							compte_proprietaireDI.modifierCompte_proprietaire(compte_proprietaire, errorMsg);
							
				}
								
				List<Calendrier_paiement> listPaiL = calendrier_paiementDI.getAllCalendrier_paiementT(site.getSite(),matricule); 
				request.setAttribute("listPaiL", listPaiL);
											
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}

}
