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

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

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
import com.news.dao.Paiement_serviceDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/paiement_caution")
public class CrudPaiement_caution extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/paiement_loyer.jsp";
	public static final String LOYER = "/WEB-INF/views/view_paiement_loyer.jsp";
	private static final String FORMAT_DATE = "yyy-MM-dd";

	Site site = new Site();
	 Users users	=	new Users();
	 
	 Paiement_serviceDI paiement_serviceDI = new Paiement_serviceDI();
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
		//	String idl	=	request.getParameter("idl");		
			String matricule	=	request.getParameter("matricule");
			
			
		
			
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
				
				calendrier_paiement = calendrier_paiementDI.getCalendrier_paiement(matricule);
				request.setAttribute("calendrier_paiement", calendrier_paiement);
				
				List<Calendrier_paiement> listCP = calendrier_paiementDI.getAllCalendrier_paiement(site.getSite(),matricule); 
				request.setAttribute("listCP", listCP);
				
				}
				
				if("mois".equals(crud)) {
					int idU = Integer.parseInt(id);
					calendrier_paiement = calendrier_paiementDI.getCalendrier_paiement(idU);
					locataire = locataireDI.getLocataire(matricule);
									
					List<Calendrier_paiement> listCPM = calendrier_paiementDI.getAllCalendrier_paiementM(idU); 
					request.setAttribute("listCPM", listCPM);
					request.setAttribute ("taille",listCPM.size());
					
					List<Calendrier_paiement> listCP = calendrier_paiementDI.getAllCalendrier_paiement(site.getSite(),matricule); 
					request.setAttribute("listCP", listCP);
					request.setAttribute("locataire", locataire);
					request.setAttribute("calendrier_paiement",calendrier_paiement);
					
					request.setAttribute("block", "block");
				}
																	
															
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}
		
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String id = request.getParameter("id");
			String page = request.getParameter("page");
			String crud = request.getParameter("crud");	
			String matricule = request.getParameter("matricule");			
			String montantS= request.getParameter("montantS");
			String montanT= request.getParameter("montanT");
			String ans = request.getParameter("ans");			
			String mois= request.getParameter("mois");
			String mode= request.getParameter("mode");
							

			String pourcI	=	request.getParameter("pourcI");						
			int TI =0;
			if(pourcI != null) {TI = Integer.parseInt(pourcI);}
			
			String pourcA	=	request.getParameter("pourcA");
			int TA =0;
			if(pourcA != null) {TA = Integer.parseInt(pourcA);}
			
			
			int	PA = 100, resultA=0;
			resultA= PA - TA - TI;				    
			    
            String nbr = request.getParameter("nbr");
			
			String montantC= request.getParameter("montantC");
			String montantA = request.getParameter("montantA");			
			String commiss = request.getParameter("commiss");   
			  
			 		
			users = (Users) session.getAttribute("users");
			site = (Site) session.getAttribute("site");
			
				if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
				DateTime dt = new DateTime();
				org.joda.time.format.DateTimeFormatter formattere = DateTimeFormat.forPattern(FORMAT_DATE);
				String dating = dt.toString(formattere);
								
				
			 if(crud.equals("CAUTION")) {
				
				 locataire.setCaution(Integer.parseInt(montantC));
	            //locataire.setAvance(Integer.parseInt(montantA));
				 //locataire.setCommission_E(Integer.parseInt(commiss));
				locataire.setMatricule(matricule);  
				locataireDI.modifierLocataireAM(locataire, errorMsg);  
			 }
			 
            if(crud.equals("COMMISSION")) {
            	
            	//locataire.setCaution(Integer.parseInt(montantC));
            	//locataire.setAvance(Integer.parseInt(montantA));
				locataire.setCommission_E(Integer.parseInt(commiss));
				locataire.setMatricule(matricule);  
				locataireDI.modifierLocataireAM(locataire, errorMsg); 
			 }
				
            if(crud.equals("AVANCE")) {
            
          	  int ansU = Integer.parseInt(ans);  
        	  List<Calendrier_paiement> paiementC = null;
          
          	if(nbr.equals("2")) {
          	 paiementC = calendrier_paiementDI.getAllCalendrier_paiement1(site.getSite(),ansU,matricule);             
          	}             	
          	if(nbr.equals("3")) {
          		 paiementC = calendrier_paiementDI.getAllCalendrier_paiement2(site.getSite(),ansU,matricule);
             }
          	if(nbr.equals("4")) {
         		 paiementC = calendrier_paiementDI.getAllCalendrier_paiement3(site.getSite(),ansU,matricule);
            }
          	if(nbr.equals("5")) {
         		 paiementC = calendrier_paiementDI.getAllCalendrier_paiement4(site.getSite(),ansU,matricule);
            }
          	if(nbr.equals("6")) {
         		 paiementC = calendrier_paiementDI.getAllCalendrier_paiement5(site.getSite(),ansU,matricule);
            }
            	for(Calendrier_paiement paieC : paiementC) {
            		
            		calendrier_paiement.setMontant_P(paieC.getMontant_P() + Integer.parseInt(montantS));
             		calendrier_paiement.setMontant_R(paieC.getMontant_R() - Integer.parseInt(montantS));
             		calendrier_paiement.setMontant_S(Integer.parseInt(montantS));					
 					calendrier_paiement.setStatut("paye");						
 					calendrier_paiement.setTaux_I(TI);
 					calendrier_paiement.setTaux_A(TA);	
 					calendrier_paiement.setDateP(dating);
 					calendrier_paiement.setModeP(mode);	
 					calendrier_paiement.setAnnee(paieC.getAnnee());
 					calendrier_paiement.setMois(paieC.getMois());
 					calendrier_paiement.setMatricule(paieC.getMatricule());
 					
 					
 					//locataire.setCaution(Integer.parseInt(montantC));
					locataire.setAvance(Integer.parseInt(montantA) * Integer.parseInt(nbr));
				//	locataire.setCommission_E(Integer.parseInt(commiss));
					locataire.setMatricule(matricule);  
					locataireDI.modifierLocataireAM(locataire, errorMsg);
 					
 					//----------------------------------COMMISSION GESTIONNAIRE----------------------------------//		        						
					if(compte_gestionnaireDI.getVerifierCompte_gestionnaire(site.getSite(),paieC.getAnnee(),paieC.getMois()) == false) {	
						compte_gestionnaire.setGestionnaire(users.getNom());
						compte_gestionnaire.setPropriete(site.getSite());
						compte_gestionnaire.setMontant_commission((Integer.parseInt(montantS) * TA) /100);
						compte_gestionnaire.setAns(paieC.getAnnee());
						compte_gestionnaire.setMois(paieC.getMois());
						
						compte_gestionnaireDI.creerCompte_gestionnaire(compte_gestionnaire, errorMsg);
				}else {
					Compte_gestionnaire CompteG = compte_gestionnaireDI.getCompte_gestionnaire(site.getSite(),paieC.getAnnee(),paieC.getMois());
					 if(CompteG != null) {					
					compte_gestionnaire.setMontant_commission((Integer.parseInt(montantS) * TA) /100 + CompteG.getMontant_commission());
					compte_gestionnaire.setPropriete(site.getSite());
					compte_gestionnaire.setAns(paieC.getAnnee());
					compte_gestionnaire.setMois(paieC.getMois());
					 }
					compte_gestionnaireDI.modifierCompte_gestionnaire(compte_gestionnaire, errorMsg);
				}
										
			 //----------------------------------COMPTE IMPOT LOYER----------------------------------//		        	 		     					 				
					 if(impot_loyerDI.getVerifierImpot_loyer(site.getSite(),paieC.getAnnee(),paieC.getMois()) == false) {	
						  impot_loyer.setProprietaire(site.getNom_prenom());
						  impot_loyer.setMatricule(site.getMatricule());
						  impot_loyer.setSite(site.getSite());
						  impot_loyer.setMontant((Integer.parseInt(montantS) * TI) /100);
						  impot_loyer.setAns(paieC.getAnnee());
						  impot_loyer.setMois(paieC.getMois());
							
							impot_loyerDI.creerImpot_loyer(impot_loyer, errorMsg);
					}else {
						Impot_loyer CompteI = impot_loyerDI.getImpot_loyer(site.getSite(),paieC.getAnnee(),paieC.getMois());
						  if(CompteI != null) {															
						impot_loyer.setMontant((Integer.parseInt(montantS) * TI) /100 + CompteI.getMontant());
						impot_loyer.setSite(site.getSite());
						impot_loyer.setAns(paieC.getAnnee());
						impot_loyer.setMois(paieC.getMois());
						  }
						impot_loyerDI.modifierImpot_loyer(impot_loyer, errorMsg);	
					}
		
			 //----------------------------------COMPTE PROPRIETAIRE----------------------------------//	        					 				  
				  if(compte_proprietaireDI.getVerifierCompte_proprietaire(site.getSite(),paieC.getAnnee(),paieC.getMois()) == false) {	
					  compte_proprietaire.setProprietaire(site.getNom_prenom());
					  compte_proprietaire.setMatricule(site.getMatricule());
					  compte_proprietaire.setPropriete(site.getSite());
					  compte_proprietaire.setMontant_APC((Integer.parseInt(montantS) * resultA) /100);
					  compte_proprietaire.setAns(paieC.getAnnee());
					  compte_proprietaire.setMois(paieC.getMois());
							
					  compte_proprietaireDI.creerCompte_proprietaire(compte_proprietaire, errorMsg);
					}else {
						Compte_proprietaire CompteP = compte_proprietaireDI.getCompte_proprietaire(site.getSite(),paieC.getAnnee(),paieC.getMois());
						  if(CompteP != null) {				
						compte_proprietaire.setMontant_APC((Integer.parseInt(montantS) * resultA) /100 + CompteP.getMontant_APC());
						compte_proprietaire.setPropriete(site.getSite());
						compte_proprietaire.setAns(paieC.getAnnee());
						compte_proprietaire.setMois(paieC.getMois());
						  }
						compte_proprietaireDI.modifierCompte_proprietaire(compte_proprietaire, errorMsg);
					}
							
 					
 					  if(calendrier_paiementDI.modifierCalendrier_paiementP(calendrier_paiement, errorMsg)) {							
							request.setAttribute("message", new Message("Paiement_Avance effectué avec succès."+ errorMsg.get(), 0, "green"));
						}else request.setAttribute("message", new Message("Echec enregistrement Paiement_loyer. " + errorMsg.get(), 0, "red") );					 																				
					}
				 
			 }
				
              if(crud.equals("ENREGISTRER")) {
            		int ansU = Integer.parseInt(ans);
            	  Calendrier_paiement CalP = calendrier_paiementDI.getCalendrier_paiement(ansU,mois,matricule);
            	  if(CalP != null) {
					calendrier_paiement.setMontant_P(CalP.getMontant_P() + Integer.parseInt(montanT));
					calendrier_paiement.setMontant_R(CalP.getMontant_R() - Integer.parseInt(montanT));
					calendrier_paiement.setMontant_S(Integer.parseInt(montanT));	
					if(calendrier_paiement.getMontant_R() == 0) {
						calendrier_paiement.setStatut("paye");
					}else {
						calendrier_paiement.setStatut("impaye");
					}	
					calendrier_paiement.setTaux_I(TI);
					calendrier_paiement.setTaux_A(TA);	
					calendrier_paiement.setDateP(dating);	
					calendrier_paiement.setModeP(mode);	
					calendrier_paiement.setAnnee(Integer.parseInt(ans));
					calendrier_paiement.setMois(mois);
					calendrier_paiement.setMatricule(matricule);
            	  }
					
							//----------------------------------COMMISSION GESTIONNAIRE----------------------------------//
							if(compte_gestionnaireDI.getVerifierCompte_gestionnaire(site.getSite(),ansU,mois) == false) {	
									compte_gestionnaire.setGestionnaire(users.getNom());
									compte_gestionnaire.setPropriete(site.getSite());
									compte_gestionnaire.setMontant_commission((Integer.parseInt(montanT) * TA) /100);
									compte_gestionnaire.setAns(ansU);
									compte_gestionnaire.setMois(mois);
									
									compte_gestionnaireDI.creerCompte_gestionnaire(compte_gestionnaire, errorMsg);
							}else {
								Compte_gestionnaire CompteG = compte_gestionnaireDI.getCompte_gestionnaire(site.getSite(),ansU,mois);
								 if(CompteG != null) {					
								compte_gestionnaire.setMontant_commission((Integer.parseInt(montanT) * TA) /100 + CompteG.getMontant_commission());
								compte_gestionnaire.setPropriete(site.getSite());
								compte_gestionnaire.setAns(ansU);
								compte_gestionnaire.setMois(mois);
								 }
								compte_gestionnaireDI.modifierCompte_gestionnaire(compte_gestionnaire, errorMsg);
							}
							
							 //----------------------------------COMPTE IMPOT LOYER----------------------------------//
							  if(impot_loyerDI.getVerifierImpot_loyer(site.getSite(),ansU,mois) == false) {	
								  impot_loyer.setProprietaire(site.getNom_prenom());
								  impot_loyer.setMatricule(site.getMatricule());
								  impot_loyer.setSite(site.getSite());
								  impot_loyer.setMontant((Integer.parseInt(montanT) * TI) /100);
								  impot_loyer.setAns(ansU);
								  impot_loyer.setMois(mois);
									
									impot_loyerDI.creerImpot_loyer(impot_loyer, errorMsg);
							}else {
								Impot_loyer CompteI = impot_loyerDI.getImpot_loyer(site.getSite(),ansU,mois);
								  if(CompteI != null) {															
								impot_loyer.setMontant((Integer.parseInt(montanT) * TI) /100 + CompteI.getMontant());
								impot_loyer.setSite(site.getSite());
								impot_loyer.setAns(ansU);
								impot_loyer.setMois(mois);
								  }
								impot_loyerDI.modifierImpot_loyer(impot_loyer, errorMsg);	
							}
							
							 //----------------------------------COMPTE PROPRIETAIRE----------------------------------//
							  if(compte_proprietaireDI.getVerifierCompte_proprietaire(site.getSite(),ansU,mois) == false) {	
								  compte_proprietaire.setProprietaire(site.getNom_prenom());
								  compte_proprietaire.setMatricule(site.getMatricule());
								  compte_proprietaire.setPropriete(site.getSite());
								  compte_proprietaire.setMontant_APC((Integer.parseInt(montanT) * resultA) /100);
								  compte_proprietaire.setAns(ansU);
								  compte_proprietaire.setMois(mois);
										
								  compte_proprietaireDI.creerCompte_proprietaire(compte_proprietaire, errorMsg);
								}else {
									Compte_proprietaire CompteP = compte_proprietaireDI.getCompte_proprietaire(site.getSite(),ansU,mois);
									  if(CompteP != null) {				
									compte_proprietaire.setMontant_APC((Integer.parseInt(montanT) * resultA) /100 + CompteP.getMontant_APC());
									compte_proprietaire.setPropriete(site.getSite());
									compte_proprietaire.setAns(ansU);
									compte_proprietaire.setMois(mois);
									  }
									compte_proprietaireDI.modifierCompte_proprietaire(compte_proprietaire, errorMsg);
								}
							  						 
							  							 
										if(calendrier_paiementDI.modifierCalendrier_paiementP(calendrier_paiement, errorMsg)) {							
											request.setAttribute("message", new Message("Paiement_loyer enregistré avec succès."+ errorMsg.get(), 0, "green"));
										}else request.setAttribute("message", new Message("Echec enregistrement Paiement_loyer. " + errorMsg.get(), 0, "red") );										
									}
						
						int idU = Integer.parseInt(id);				
						locataire = locataireDI.getLocataire(matricule);
						request.setAttribute("locataire", locataire);
										
						maison = maisonDI.getMaison(idU);
						request.setAttribute("maison", maison);
						
						List<Calendrier_paiement> listPaiL = calendrier_paiementDI.getAllCalendrier_paiementT(site.getSite(),matricule); 
						request.setAttribute("listPaiL", listPaiL);
						
						     request.setAttribute("page", page);
							this.getServletContext().getRequestDispatcher(LOYER).forward(request, response);
			
		
		}

}
