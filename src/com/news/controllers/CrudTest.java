package com.news.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.news.beans.Agence;
import com.news.beans.Calendrier_paiement;
import com.news.beans.Compte;
import com.news.beans.Compte_gestionnaire;
import com.news.beans.Compte_proprietaire;
import com.news.beans.Impot_loyer;
import com.news.beans.Locataire;
import com.news.beans.Maison;
import com.news.beans.Operation;
import com.news.beans.OuvertureFermetureCaisse;
import com.news.beans.Paiement_loyer;
import com.news.beans.Paiement_service;
import com.news.beans.Service;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.Calendrier_paiementDI;
import com.news.dao.CompteDI;
import com.news.dao.Compte_gestionnaireDI;
import com.news.dao.Compte_proprietaireDI;
import com.news.dao.Impot_loyerDI;
import com.news.dao.LocataireDI;
import com.news.dao.MaisonDI;
import com.news.dao.OperationDI;
import com.news.dao.OuvertureFermetureCaisseDI;
import com.news.dao.Paiement_loyerDI;
import com.news.dao.Paiement_serviceDI;
import com.news.dao.ServiceDI;
import com.news.fonctions.MiseAJourSomme;
import com.news.fonctions.VerifieSession;



@WebServlet("/paiement_loyer")
public class CrudTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/paiement_loyer.jsp";
	public static final String LOYER = "/WEB-INF/views/view_paiement_loyer.jsp";
	private static final String COMPTABILITE = "/WEB-INF/views/comptabilite.jsp";
	
	private static final String FORMAT_DATE = "yyy-MM-dd";
	private static final String FORMAT_DATES = "HH:mm:ss";

	Site site = new Site();
	 Users users	=	new Users();
	 Agence agence = new Agence();
	 
	 Paiement_serviceDI paiement_serviceDI = new Paiement_serviceDI();
	 Paiement_service paiement_service = new Paiement_service();
	 
	 ServiceDI serviceDI = new ServiceDI();
	 Service service = new Service();
	 
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
	 
	 Paiement_loyerDI paiement_loyerDI = new Paiement_loyerDI();
	 Paiement_loyer paiement_loyer = new Paiement_loyer();
	 
	 OuvertureFermetureCaisseDI ouvFerCaisseDI	=	new OuvertureFermetureCaisseDI();
	 OuvertureFermetureCaisse ouvFerCaisse = new OuvertureFermetureCaisse();
	 
	 OperationDI operationDI = new OperationDI();
	 Operation operation = new Operation();
	 
	 CompteDI compteDI = new CompteDI();
	 Compte compte = new Compte();
	 
	 AtomicReference<String> errorMsg = new  AtomicReference<>("");
		VerifieSession verifieSess = new VerifieSession();
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String crud	=	request.getParameter("crud");
			//String page	=	request.getParameter("page");
			String id	=	request.getParameter("id");
			String page = request.getParameter("menu");
			String choix = request.getParameter("ch");	
			String matricule	=	request.getParameter("matricule");
			
			
			DateTime dt = new DateTime();
			org.joda.time.format.DateTimeFormatter formattere = DateTimeFormat.forPattern(FORMAT_DATE);
			String dating = dt.toString(formattere);
			
			site = (Site) session.getAttribute("site");
			
				if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
				
				
				
				List<OuvertureFermetureCaisse> ouvFerCaisses	=	ouvFerCaisseDI.getOuvA(agence.getCode(), users.getEmail(),dating );
				int ouvert = 0;
				for(OuvertureFermetureCaisse ouvF : ouvFerCaisses) {
					if(ouvF.getOuvert() == 1) {ouvert = ouvF.getOuvert();}
				}
				
				if("pE".equals(choix)) {
					if(ouvert==1) {
						request.setAttribute("page", page);
						this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
					}
					if(ouvert==0) {
						request.setAttribute("ouvert", ouvert);
						request.setAttribute("page", "comptabilite");
						this.getServletContext().getRequestDispatcher(COMPTABILITE).forward(request, response);
					}
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
				
				List<Paiement_service> listPay = paiement_serviceDI.getAllPaiement_service(site.getSite(),matricule); 
				request.setAttribute("listPay", listPay);
				
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
																	
				 if(crud.equals("supp")) {
				    	paiement_service = paiement_serviceDI.getPaiement_service(Integer.parseInt(id));
				    	paiement_service.setId(Long.parseLong(id));
				    	paiement_serviceDI.supprimerPaiement_service(paiement_service, errorMsg);
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
			else TI = 0;
			
			String pourcA	=	request.getParameter("pourcA");
			int TA =0;
			if(pourcA != null) {TA = Integer.parseInt(pourcA);}
			else TA = 0;
						
			System.out.println(TI);
			System.out.println(TA);
			
			int	PA = 100, resultA=0;
			resultA= PA - TA - TI;				    
			    
            String nbr = request.getParameter("nbr");
			
			String montantC= request.getParameter("montantC");
			String montantA = request.getParameter("montantA");			
			String commiss = request.getParameter("commiss");   
			  
			 		
			users = (Users) session.getAttribute("users");
			site = (Site) session.getAttribute("site");
			agence = (Agence) session.getAttribute("agence");
			
				if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
				DateTime dt = new DateTime();
				org.joda.time.format.DateTimeFormatter formattere = DateTimeFormat.forPattern(FORMAT_DATE);
				String dating = dt.toString(formattere);
				
				DateTime dts = new DateTime();
				org.joda.time.format.DateTimeFormatter formatterss = DateTimeFormat.forPattern(FORMAT_DATES);
				String datings = dts.toString(formatterss);
				
				LocalDate dat = LocalDate.now(); 
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM", Locale.FRENCH); 
				String moisS = dat.format(formatter);
				
				LocalDate dates = LocalDate.now(); 
				DateTimeFormatter formatters = DateTimeFormatter.ofPattern("YYYY", Locale.FRENCH); 
				String ansS = dates.format(formatters);
				
				String nom= request.getParameter("nom");					
				String type = request.getParameter("type"); 
				
				Service service_P = serviceDI.getService(type);
							
		
				List<OuvertureFermetureCaisse> ouvFerCaisses	=	ouvFerCaisseDI.getOuvA(agence.getCode(), users.getEmail(),dating );
				String codeCaisse = "";
				String mailUser = "";
				for(OuvertureFermetureCaisse ouvF : ouvFerCaisses) {  
					codeCaisse = ouvF.getCodeCaisse();
					mailUser = ouvF.getUserName();
				}
				
				
		    if(crud.equals("AJOUTERS")) {
		    	if(paiement_serviceDI.getVerifierPaiement_service(site.getSite(),matricule,ansS,moisS) == false) {
		    	paiement_service.setLocataire(nom);
				paiement_service.setMatricule_locataire(matricule);
				paiement_service.setPropriete(site.getSite());
				if(service_P != null) {
				paiement_service.setMontant_loyer(service_P.getMontant());
				}
				paiement_service.setAns(ansS);
				paiement_service.setMois(moisS);
				paiement_service.setType(type);
				
				    paiement_loyer.setCode(agence.getCode());
					paiement_loyer.setSite(site.getSite());
					paiement_loyer.setMatricule(matricule);
					paiement_loyer.setType("service");
					if(service_P != null) {
					paiement_loyer.setMontant(service_P.getMontant());
					}
					paiement_loyer.setStatut("paye");
					paiement_loyer.setDate(dating);
					paiement_loyer.setCaisse(codeCaisse);
					paiement_loyer.setCaissier(mailUser);
					if(service_P != null) {
					paiement_loyer.setMontantA(service_P.getMontant());
					}
					paiement_loyer.setMontantI(0);
					paiement_loyer.setMontantP(0);
					paiement_loyer.setModeP(mode);
					paiement_loyerDI.creerPaiement_loyer(paiement_loyer, errorMsg); 
					
					//++++++++++++++++++++++++++++++OPERATION++++++++++++++++++++++++++++++;
					operation.setCode(agence.getCode());
					operation.setSite(site.getSite() );
					operation.setMatricule(matricule);
					operation.setNom_prenom(mailUser );
					operation.setType("service" );
					//operation.setDecaissement(ecaissement );
					if(service_P != null) {
					operation.setApprovisonnement(service_P.getMontant());
					operation.setMontantA(service_P.getMontant());
					}
					operation.setMontantI(0);
					operation.setMontantP(0);
					operation.setDescription("Paiement"+" "+type+" "+site.getSite());
					operation.setDate(dating );
					operation.setCaisse(codeCaisse);
					operation.setHeure(datings);
					operationDI.creerOperation(operation, errorMsg); 
					
					Compte compteG = compteDI.getCompte(site.getCode(),site.getSite());
					if(compteG != null) {
						compte.setCode(agence.getCode());
						compte.setSite(site.getSite() );
						compte.setMatricule(site.getMatricule());
						if(service_P != null) {
						compte.setSoldeA(compteG.getSoldeA()+service_P.getMontant() );	
						}
						compte.setSoldeI(compteG.getSoldeI());
						compte.setSoldeP(compteG.getSoldeP());
					}
					compteDI.modifierCompte(compte, errorMsg);
				
				if(paiement_serviceDI.creerPaiement_service(paiement_service, errorMsg)) {																
					request.setAttribute("message", new Message("Paiement_service enregistré avec succès."+ errorMsg.get(), 0, "green"));
				}else request.setAttribute("message", new Message("Echec enregistrement Paiement_service. " + errorMsg.get(), 0, "red") );
				
			}else request.setAttribute("message", new Message("Cet service existe déjà." + errorMsg.get(), 0, "red") );
		   
		    	MiseAJourSomme miseSomme = new MiseAJourSomme();
				miseSomme.LesSomme(request);
		    	
		    	this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
				request.setAttribute("page", page);
		    }	
		    		   				
			 if(crud.equals("CAUTION")) {
				
				    locataire.setCaution(Integer.parseInt(montantC));		           
					locataire.setMatricule(matricule);  
					locataireDI.modifierLocataireAM(locataire, errorMsg); 
				 
				    paiement_loyer.setCode(agence.getCode());
					paiement_loyer.setSite(site.getSite());
					paiement_loyer.setMatricule(matricule);
					paiement_loyer.setType(type);
					paiement_loyer.setMontant(Integer.parseInt(montantC));
					paiement_loyer.setStatut("paye");
					paiement_loyer.setDate(dating);
					paiement_loyer.setCaisse(codeCaisse);
					paiement_loyer.setCaissier(mailUser);
					paiement_loyer.setMontantA(0);
					paiement_loyer.setMontantI(0);
					paiement_loyer.setMontantP(Integer.parseInt(montantC));
					paiement_loyer.setModeP(mode);
					paiement_loyer.setHeure(datings);
					paiement_loyer.setMois(" ");
					paiement_loyerDI.creerPaiement_loyer(paiement_loyer, errorMsg); 
					
				//++++++++++++++++++++++++++++++OPERATION++++++++++++++++++++++++++++++;
					operation.setCode(agence.getCode());
					operation.setSite(site.getSite() );
					operation.setMatricule(matricule);
					operation.setNom_prenom(mailUser );
					operation.setType(type );
					//operation.setDecaissement(ecaissement );
					operation.setApprovisonnement(Integer.parseInt(montantC) );
					operation.setMontantP(Integer.parseInt(montantC));
					operation.setDescription("Paiement"+" "+type+" "+site.getSite());
					operation.setDate(dating );
					operation.setCaisse(codeCaisse);
					operation.setHeure(datings);
					operation.setMois(" ");
					operationDI.creerOperation(operation, errorMsg); 
				
					
					
			//++++++++++++++++++++++++++++++OPERATION++++++++++++++++++++++++++++++;
				if(compteDI.getVerifierCompte(site.getCode(),site.getSite()) == false) {
					compte.setCode(agence.getCode());
					compte.setSite(site.getSite() );
					compte.setMatricule(site.getMatricule());
					compte.setSoldeP(Integer.parseInt(montantC) );
					compteDI.creerCompte(compte, errorMsg); 
				}else {
					Compte compteG = compteDI.getCompte(site.getCode(),site.getSite());
					if(compteG != null) {
						compte.setCode(agence.getCode());
						compte.setSite(site.getSite() );
						compte.setMatricule(site.getMatricule());
						compte.setSoldeP(compteG.getSoldeP()+Integer.parseInt(montantC) );	
						compte.setSoldeA(compteG.getSoldeA());	
						compte.setSoldeI(compteG.getSoldeI());	
					}
					compteDI.modifierCompte(compte, errorMsg); 
				}
					
					
					MiseAJourSomme miseSomme = new MiseAJourSomme();
					miseSomme.LesSomme(request);
				
				this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
				request.setAttribute("page", page);
			 }
			 
            if(crud.equals("COMMISSION")) {
            	
            	  paiement_loyer.setCode(agence.getCode());
				  paiement_loyer.setSite(site.getSite());
				  paiement_loyer.setMatricule(matricule);
				  paiement_loyer.setType(type);
				  paiement_loyer.setMontant(Integer.parseInt(commiss));
				  paiement_loyer.setDate(dating);
				  paiement_loyer.setStatut("paye");
				  paiement_loyer.setCaisse(codeCaisse);
				  paiement_loyer.setCaissier(mailUser);
				  paiement_loyer.setMontantA(Integer.parseInt(commiss));
				  paiement_loyer.setMontantI(0);
				  paiement_loyer.setMontantP(0);
				  paiement_loyer.setModeP(mode);
				  paiement_loyer.setHeure(datings);
				  paiement_loyer.setMois(" ");
				  paiement_loyerDI.creerPaiement_loyer(paiement_loyer, errorMsg);  
            	                         	
				locataire.setCommission_E(Integer.parseInt(commiss));
				locataire.setMatricule(matricule);  
				locataireDI.modifierLocataireAM(locataire, errorMsg); 
				
	  //++++++++++++++++++++++++++++++OPERATION++++++++++++++++++++++++++++++;		
				operation.setCode(agence.getCode());
				operation.setSite(site.getSite() );
				operation.setMatricule(matricule);
				operation.setNom_prenom(mailUser );
				operation.setType(type );
				//operation.setDecaissement(ecaissement );
				operation.setApprovisonnement(Integer.parseInt(commiss) );
				operation.setMontantA(Integer.parseInt(commiss));
				operation.setDescription("Paiement"+" "+type+" "+site.getSite());
				operation.setDate(dating );
				operation.setCaisse(codeCaisse);
				operation.setHeure(datings);
				operation.setMois(" ");
				operationDI.creerOperation(operation, errorMsg); 
				
				//++++++++++++++++++++++++++++++COMPTE++++++++++++++++++++++++++++++;
				if(compteDI.getVerifierCompte(site.getCode(),site.getSite()) == false) {
					compte.setCode(agence.getCode());
					compte.setSite(site.getSite() );
					compte.setMatricule(site.getMatricule());
					compte.setSoldeA(Integer.parseInt(commiss) );
					compteDI.creerCompte(compte, errorMsg); 
				}else {
					Compte compteG = compteDI.getCompte(site.getCode(),site.getSite());
					if(compteG != null) {
						compte.setCode(agence.getCode());
						compte.setSite(site.getSite() );
						compte.setMatricule(site.getMatricule());
						compte.setSoldeA(compteG.getSoldeA()+Integer.parseInt(commiss) );	
						compte.setSoldeP(compteG.getSoldeP());	
						compte.setSoldeI(compteG.getSoldeI());
					}
					compteDI.modifierCompte(compte, errorMsg); 
				}			
				
				MiseAJourSomme miseSomme = new MiseAJourSomme();
				miseSomme.LesSomme(request);
			
				this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
				request.setAttribute("page", page);
            }
				
            if(crud.equals("AVANCE")) {
            
          	  int ansU = Integer.parseInt(ans);  
        	  List<Calendrier_paiement> paiementC = null;
        	int montantAV = 0;
          
          	if(nbr.equals("2")) {
          	 paiementC = calendrier_paiementDI.getAllCalendrier_paiement1(site.getSite(),ansU,matricule);  
          	  montantAV = Integer.parseInt(montantS) * 2;
          	}             	
          	if(nbr.equals("3")) {
          		 paiementC = calendrier_paiementDI.getAllCalendrier_paiement2(site.getSite(),ansU,matricule);
          		 montantAV = Integer.parseInt(montantS) *3;
             }
          	if(nbr.equals("4")) {
         		 paiementC = calendrier_paiementDI.getAllCalendrier_paiement3(site.getSite(),ansU,matricule);
         		 montantAV = Integer.parseInt(montantS) * 4;
            }
          	if(nbr.equals("5")) {
         		 paiementC = calendrier_paiementDI.getAllCalendrier_paiement4(site.getSite(),ansU,matricule);
         		 montantAV = Integer.parseInt(montantS) * 5;
            }
          	if(nbr.equals("6")) {
         		 paiementC = calendrier_paiementDI.getAllCalendrier_paiement5(site.getSite(),ansU,matricule);
         		 montantAV = Integer.parseInt(montantS) * 6;
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
 					
 					  paiement_loyer.setCode(agence.getCode());
 					  paiement_loyer.setSite(site.getSite());
 					  paiement_loyer.setMatricule(paieC.getMatricule());
 					  paiement_loyer.setType(type);
 					  paiement_loyer.setMontant(Integer.parseInt(montantS));
 					  paiement_loyer.setStatut("paye");
 					  paiement_loyer.setDate(dating);
 					  paiement_loyer.setCaisse(codeCaisse);
 					  paiement_loyer.setCaissier(mailUser);
 					  paiement_loyer.setAnnee(paieC.getAnnee());
 					  paiement_loyer.setMois(paieC.getMois());
 					  paiement_loyer.setMontantA((Integer.parseInt(montantS) * TA) /100);
 					  paiement_loyer.setMontantI((Integer.parseInt(montantS) * TI) /100);
 					  paiement_loyer.setMontantP((Integer.parseInt(montantS) * resultA) /100);
 					  paiement_loyer.setModeP(mode);
 					  paiement_loyer.setHeure(datings);
 					  paiement_loyerDI.creerPaiement_loyer(paiement_loyer, errorMsg);  
 					
 					
 					//locataire.setCaution(Integer.parseInt(montantC));
					locataire.setAvance(Integer.parseInt(montantA) * Integer.parseInt(nbr));
				//	locataire.setCommission_E(Integer.parseInt(commiss));
					locataire.setMatricule(matricule);  
					locataireDI.modifierLocataireAM(locataire, errorMsg);
					
					
			//++++++++++++++++++++++++++++++OPERATION++++++++++++++++++++++++++++++;		
					operation.setCode(agence.getCode());
					operation.setSite(site.getSite() );
					operation.setMatricule(paieC.getMatricule());
					operation.setNom_prenom(mailUser );
					operation.setType(type );					
					operation.setApprovisonnement(Integer.parseInt(montantS) );
					operation.setMontantA((Integer.parseInt(montantS) * TA) /100);
					operation.setMontantI((Integer.parseInt(montantS) * TI) /100);
					operation.setMontantP((Integer.parseInt(montantS) * resultA) /100);
					operation.setDescription("Paiement"+" "+type+" loyer"+" "+site.getSite());
					operation.setDate(dating );
					operation.setCaisse(codeCaisse);
					operation.setAnnee(paieC.getAnnee());
					operation.setMois(paieC.getMois());
					operation.setHeure(datings);					
					operationDI.creerOperation(operation, errorMsg); 
					
																								
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
            	
            	
            
				//++++++++++++++++++++++++++++++COMPTE++++++++++++++++++++++++++++++;
				if(compteDI.getVerifierCompte(site.getCode(),site.getSite()) == false) {
					compte.setCode(agence.getCode());
					compte.setSite(site.getSite() );
					compte.setMatricule(site.getMatricule());
					compte.setSoldeP((montantAV * resultA) /100 );
					compte.setSoldeA((montantAV * TA) /100 );
					compte.setSoldeI((montantAV * TI) /100);
					compteDI.creerCompte(compte, errorMsg); 
				}else {
					Compte compteG = compteDI.getCompte(site.getCode(),site.getSite());
					if(compteG != null) {
						compte.setCode(agence.getCode());
						compte.setSite(site.getSite() );
						compte.setMatricule(site.getMatricule());
						compte.setSoldeP((montantAV * resultA) /100 + compteG.getSoldeP());
						compte.setSoldeA((montantAV * TA) /100 + compteG.getSoldeA());	
						compte.setSoldeI((montantAV * TI) /100 + compteG.getSoldeI());
					}
					compteDI.modifierCompte(compte, errorMsg); 
				}	

            	int idU = Integer.parseInt(id);				
				locataire = locataireDI.getLocataire(matricule);
				request.setAttribute("locataire", locataire);
								
				maison = maisonDI.getMaison(idU);
				request.setAttribute("maison", maison);
				
				List<Paiement_loyer> listPaiL = paiement_loyerDI.getAllPaiement_loyer(site.getSite(),matricule); 
				request.setAttribute("listPaiL", listPaiL);
            	
            	this.getServletContext().getRequestDispatcher(LOYER).forward(request, response);
			
				request.setAttribute("page", page);
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
            	  
            	    paiement_loyer.setCode(agence.getCode());
					paiement_loyer.setSite(site.getSite());
					paiement_loyer.setMatricule(matricule);
					paiement_loyer.setType(type);
					paiement_loyer.setMontant(Integer.parseInt(montanT));
					paiement_loyer.setDate(dating);
					paiement_loyer.setCaisse(codeCaisse);
					paiement_loyer.setCaissier(mailUser);
					paiement_loyer.setAnnee(Integer.parseInt(ans));
					paiement_loyer.setMois(mois);
					paiement_loyer.setMontantA((Integer.parseInt(montanT) * TA) /100);
					paiement_loyer.setMontantI((Integer.parseInt(montanT) * TI) /100);
					paiement_loyer.setMontantP((Integer.parseInt(montanT) * resultA) /100);
					paiement_loyer.setModeP(mode);
					paiement_loyer.setHeure(datings);
					paiement_loyerDI.creerPaiement_loyer(paiement_loyer, errorMsg);  
					
					
			//++++++++++++++++++++++++++++++OPERATION++++++++++++++++++++++++++++++;		
					operation.setCode(agence.getCode());
					operation.setSite(site.getSite() );
					operation.setMatricule(matricule);
					operation.setNom_prenom(mailUser );
					operation.setType(type );				
					operation.setApprovisonnement(Integer.parseInt(montanT) );
					operation.setMontantA((Integer.parseInt(montanT) * TA) /100);
					operation.setMontantI((Integer.parseInt(montanT) * TI) /100);
					operation.setMontantP((Integer.parseInt(montanT) * resultA) /100);
					operation.setDescription("Paiement"+" "+type+" "+site.getSite());
					operation.setDate(dating );
					operation.setCaisse(codeCaisse);
					operation.setAnnee(Integer.parseInt(ans));
					operation.setMois(mois);
					operation.setHeure(datings);
					operationDI.creerOperation(operation, errorMsg); 
					
					
					
					//++++++++++++++++++++++++++++++OPERATION++++++++++++++++++++++++++++++;					
					compte.setCode(agence.getCode());
					Compte compteG = compteDI.getCompte(site.getCode(),site.getSite());
					if(compteG != null) {
						compte.setCode(agence.getCode());
						compte.setSite(site.getSite() );
						compte.setMatricule(site.getMatricule());
						compte.setSoldeP((Integer.parseInt(montanT) * resultA) /100 + compteG.getSoldeP());
						compte.setSoldeA((Integer.parseInt(montanT) * TA) /100 + compteG.getSoldeA());	
						compte.setSoldeI((Integer.parseInt(montanT) * TI) /100 + compteG.getSoldeI());
					}
					compteDI.modifierCompte(compte, errorMsg); 
					
					MiseAJourSomme miseSomme = new MiseAJourSomme();
					miseSomme.LesSomme(request);
					
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
									
										int idU = Integer.parseInt(id);				
										locataire = locataireDI.getLocataire(matricule);
										request.setAttribute("locataire", locataire);
														
										maison = maisonDI.getMaison(idU);
										request.setAttribute("maison", maison);
										
										List<Paiement_loyer> listPaiL = paiement_loyerDI.getAllPaiement_loyer(site.getSite(),matricule); 
										request.setAttribute("listPaiL", listPaiL);
										
										this.getServletContext().getRequestDispatcher(LOYER).forward(request, response);
										
										  request.setAttribute("page", page);
										
              
              }
																	   								
		
		}
}
