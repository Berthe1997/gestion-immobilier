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

import com.news.beans.Agence;
import com.news.beans.Arrieres;
import com.news.beans.Calendrier_paiement;
import com.news.beans.Compte;
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
import com.news.dao.ArrieresDI;
import com.news.dao.Calendrier_paiementDI;
import com.news.dao.CompteDI;
import com.news.dao.Impot_loyerDI;
import com.news.dao.LocataireDI;
import com.news.dao.MaisonDI;
import com.news.dao.OperationDI;
import com.news.dao.OuvertureFermetureCaisseDI;
import com.news.dao.Paiement_loyerDI;
import com.news.dao.Paiement_serviceDI;
import com.news.dao.ServiceDI;
import com.news.fonctions.MiseAJourSomme;
import com.news.fonctions.Notifications;
import com.news.fonctions.VerifieSession;


@WebServlet("/paiement")
public class CrudPaiement extends HttpServlet {
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
	 
	 ArrieresDI arrieresDI = new ArrieresDI();
	 Arrieres arrieres = new Arrieres();
	 
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
				
				arrieres = arrieresDI.getArrieres(matricule);
				request.setAttribute("arrieres", arrieres);
				
				calendrier_paiement = calendrier_paiementDI.getCalendrier_paiement(matricule);
				request.setAttribute("calendrier_paiement", calendrier_paiement);
				
				List<Calendrier_paiement> listCP = calendrier_paiementDI.getAllCalendrier_paiement(site.getSite(),matricule); 
				request.setAttribute("listCP", listCP);
				
				List<Paiement_service> listPay = paiement_serviceDI.getAllPaiement_service(site.getSite(),matricule); 
				request.setAttribute("listPay", listPay);
								
				List<Arrieres> listAR = arrieresDI.getAllArrieres(site.getSite(),matricule); 
				request.setAttribute("listAR", listAR);
								
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
			String idar = request.getParameter("idar");	
			String page = request.getParameter("page");
			String crud = request.getParameter("crud");	
			String matricule = request.getParameter("matricule");			
			String montantS= request.getParameter("montantS");
			String montanT= request.getParameter("montanT");
			String ans = request.getParameter("ans");
			String an = request.getParameter("an");
			String mois= request.getParameter("mois");
			String mode= request.getParameter("mode");
			String modeAV= request.getParameter("modeAV");
			String modeC= request.getParameter("modeC");
			String modeCO= request.getParameter("modeCO");
			
		//================----------------------------------ARRIERE----------------------------------========//
			String pourcIR	=	request.getParameter("pourcIR");						
			int TIR =0;
			if(pourcIR != null) {TIR = Integer.parseInt(pourcIR);}
			
			String pourcAR	=	request.getParameter("pourcAR");
			int TAR =0;
			if(pourcAR != null) {TAR = Integer.parseInt(pourcAR);}
						
			int	PAR = 100, resultAR=0;
			resultAR= PAR - TAR - TIR;	
									
			String ansAR = request.getParameter("ansAR");
			String moisAR= request.getParameter("moisAR");
			String modeR= request.getParameter("modeR");
			String montantAR= request.getParameter("montantAR");
			
			//================----------------------------------AVANCE----------------------------------========//
			String pourcIAV	=	request.getParameter("pourcIAV");						
			int TIAV =0;
			if(pourcIAV != null) {TIAV = Integer.parseInt(pourcIAV);}
			
			String pourcAV	=	request.getParameter("pourcAV");
			int TAV =0;
			if(pourcAV != null) {TAV = Integer.parseInt(pourcAV);}
						
			int	PAV = 100, resultAV=0;
			resultAV= PAV - TAV - TIAV;	
			
									
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
			String montantNB= request.getParameter("montantNB");
			String montantA = request.getParameter("montantA");			
			String commiss = request.getParameter("commiss");   
			  
			 		
			users = (Users) session.getAttribute("users");
			site = (Site) session.getAttribute("site");
			agence = (Agence) session.getAttribute("agence");
			
			Notifications notifications = new Notifications();
			
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
												
				String type = request.getParameter("type");
											
				List<OuvertureFermetureCaisse> ouvFerCaisses	=	ouvFerCaisseDI.getOuvA(agence.getCode(), users.getEmail(),dating );
				String codeCaisse = "";
				String mailUser = "";
				for(OuvertureFermetureCaisse ouvF : ouvFerCaisses) {  
					codeCaisse = ouvF.getCodeCaisse();
					mailUser = ouvF.getUserName();
				}
				
	//================----------------------------------COMMISSION----------------------------------========//			
				if(crud.equals("ARRIERES")) {	
					
					    paiement_loyer.setCode(agence.getCode());
						paiement_loyer.setSite(site.getSite());
						paiement_loyer.setMatricule(matricule);
						paiement_loyer.setType(type);
						paiement_loyer.setMontant(Integer.parseInt(montantAR));
						paiement_loyer.setDate(dating);
						paiement_loyer.setCaisse(codeCaisse);
						paiement_loyer.setCaissier(mailUser);
						paiement_loyer.setAnnee(Integer.parseInt(ansAR));
						paiement_loyer.setMois(moisAR);
						paiement_loyer.setMontantA((Integer.parseInt(montantAR) * TAR) /100);
						paiement_loyer.setMontantI((Integer.parseInt(montantAR) * TIR) /100);
						paiement_loyer.setMontantP((Integer.parseInt(montantAR) * resultAR) /100);
						paiement_loyer.setModeP(modeR);
						paiement_loyer.setHeure(datings);
						 												
				//++++++++++++++++++++++++++++++OPERATION++++++++++++++++++++++++++++++;		
						operation.setCode(agence.getCode());
						operation.setSite(site.getSite() );
						operation.setProprietaire(site.getMatricule());
						operation.setMatricule(matricule);
						operation.setNom_prenom(mailUser );
						operation.setType(type );				
						operation.setApprovisonnement(Integer.parseInt(montantAR) );
						operation.setMontantA((Integer.parseInt(montantAR) * TAR) /100);
						operation.setMontantI((Integer.parseInt(montantAR) * TIR) /100);
						operation.setMontantP((Integer.parseInt(montantAR) * resultAR) /100);
						operation.setDescription("Paiement"+" "+type+" "+site.getSite());
						operation.setDate(dating );
						operation.setCaisse(codeCaisse);
						operation.setAnnee(Integer.parseInt(ansAR));
						operation.setMois(moisAR);
						operation.setHeure(datings);
						operation.setTaux(Integer.parseInt(pourcA));
						 																		
						//++++++++++++++++++++++++++++++OPERATION++++++++++++++++++++++++++++++;					
						compte.setCode(agence.getCode());
						Compte compteG = compteDI.getCompte(site.getCode(),site.getSite());
						if(compteG != null) {
							compte.setCode(agence.getCode());
							compte.setSite(site.getSite() );
							compte.setMatricule(site.getMatricule());
							compte.setSoldeP((Integer.parseInt(montantAR) * resultAR) /100 + compteG.getSoldeP());
							compte.setSoldeA((Integer.parseInt(montantAR) * TAR) /100 + compteG.getSoldeA());	
							compte.setSoldeI((Integer.parseInt(montantAR) * TIR) /100 + compteG.getSoldeI());
						
						 }
					
					MiseAJourSomme miseSomme = new MiseAJourSomme();
					miseSomme.LesSomme(request);
											          	 
					if(paiement_loyerDI.creerPaiement_loyer(paiement_loyer, errorMsg)) {							
						request.setAttribute("message", new Message("Paiement_loyer enregistré avec succès."+ errorMsg.get(), 0, "green"));
						operationDI.creerOperation(operation, errorMsg); 
						compteDI.modifierCompte(compte, errorMsg);
						
						arrieres = arrieresDI.getArrieres(Integer.parseInt(idar));
						arrieres.setId(Long.parseLong(idar));
						arrieresDI.supprimerArrieres(arrieres, errorMsg);
						
					}else request.setAttribute("message", new Message("Echec enregistrement Paiement_loyer. " + errorMsg.get(), 0, "red") );										
				//	notifications.arrieres(request, response);
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
				
						
	 //================----------------------------------CAUTION----------------------------------========//	
				 if(crud.equals("CAUTION")) {
						
					 Locataire locataireG = locataireDI.getLocataire(matricule);
						if(locataireG != null) {
					    locataire.setCaution(Integer.parseInt(montantC));
					    locataire.setAvance(locataireG.getAvance());
					    locataire.setCommission_E(locataireG.getCommission_E());
					    locataire.setP_C(1);
						locataire.setP_A(locataireG.getP_A());
						locataire.setP_CO(locataireG.getP_CO());
						locataire.setMatricule(matricule); 
						}
						locataireDI.modifierLocataireAM(locataire, errorMsg); 
					 
					    paiement_loyer.setCode(agence.getCode());
						paiement_loyer.setSite(site.getSite());
						paiement_loyer.setMatricule(matricule);
						paiement_loyer.setType(type);
						paiement_loyer.setMontant(Integer.parseInt(montantC)*Integer.parseInt(montantNB)); 
						paiement_loyer.setStatut("paye");
						paiement_loyer.setDate(dating);
						paiement_loyer.setCaisse(codeCaisse);
						paiement_loyer.setCaissier(mailUser);
						paiement_loyer.setMontantA(0);
						paiement_loyer.setMontantI(0);
						paiement_loyer.setMontantP(Integer.parseInt(montantC));
						paiement_loyer.setModeP(modeC);
						paiement_loyer.setHeure(datings);
						paiement_loyer.setMois(" ");
						paiement_loyerDI.creerPaiement_loyer(paiement_loyer, errorMsg); 
						
					//++++++++++++++++++++++++++++++OPERATION++++++++++++++++++++++++++++++;
						operation.setCode(agence.getCode());
						operation.setSite(site.getSite() );
						operation.setProprietaire(site.getMatricule());
						operation.setMatricule(matricule);
						operation.setNom_prenom(mailUser );
						operation.setType(type );
						//operation.setDecaissement(ecaissement );
						operation.setApprovisonnement(Integer.parseInt(montantC)*Integer.parseInt(montantNB));
						operation.setMontantP(Integer.parseInt(montantC));
						operation.setMontantA(0);
						operation.setMontantI(0);
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
						compte.setSoldeP(Integer.parseInt(montantC)*Integer.parseInt(montantNB));
						compteDI.creerCompte(compte, errorMsg); 
					}else {
						Compte compteG = compteDI.getCompte(site.getCode(),site.getSite());
						if(compteG != null) {
							compte.setCode(agence.getCode());
							compte.setSite(site.getSite() );
							compte.setMatricule(site.getMatricule());
							compte.setSoldeP((Integer.parseInt(montantC)*Integer.parseInt(montantNB))+compteG.getSoldeP());	
							compte.setSoldeA(compteG.getSoldeA());	
							compte.setSoldeI(compteG.getSoldeI());	
						}
						compteDI.modifierCompte(compte, errorMsg); 
					}
						
						MiseAJourSomme miseSomme = new MiseAJourSomme();
						miseSomme.LesSomme(request);
						//notifications.caution(request, response);	
					
					this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
					request.setAttribute("page", page);
				 }
				 
								
    //================----------------------------------COMMISSION----------------------------------========//			
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
				  paiement_loyer.setModeP(modeCO);
				  paiement_loyer.setHeure(datings);
				  paiement_loyer.setMois(" ");
				  paiement_loyerDI.creerPaiement_loyer(paiement_loyer, errorMsg);  
           	            
				  Locataire locataireG = locataireDI.getLocataire(matricule);
					if(locataireG != null) {
				    locataire.setCaution(locataireG.getCaution());
				    locataire.setAvance(locataireG.getAvance());
				    locataire.setCommission_E(Integer.parseInt(commiss));
				    locataire.setP_C(locataireG.getP_C());
					locataire.setP_A(locataireG.getP_A());
					locataire.setP_CO(1);
					locataire.setMatricule(matricule); 
					}
					locataireDI.modifierLocataireAM(locataire, errorMsg); 
								 
				
	  //++++++++++++++++++++++++++++++OPERATION++++++++++++++++++++++++++++++;		
				operation.setCode(agence.getCode());
				operation.setSite(site.getSite() );
				operation.setProprietaire(site.getMatricule());
				operation.setMatricule(matricule);
				operation.setNom_prenom(mailUser );
				operation.setType(type );
				//operation.setDecaissement(ecaissement );
				operation.setApprovisonnement(Integer.parseInt(commiss) );
				operation.setMontantA(Integer.parseInt(commiss));
				operation.setMontantI(0);
				operation.setMontantP(0);
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
			
	//================----------------------------------COMMISSION----------------------------------========//		
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
	 					calendrier_paiement.setModeP(modeAV);	
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
	 					  paiement_loyer.setMontantA((Integer.parseInt(montantS) * TAV) /100);
	 					  paiement_loyer.setMontantI((Integer.parseInt(montantS) * TIAV) /100);
	 					  paiement_loyer.setMontantP((Integer.parseInt(montantS) * resultAV) /100);
	 					  paiement_loyer.setModeP(mode);
	 					  paiement_loyer.setHeure(datings);
	 					  paiement_loyerDI.creerPaiement_loyer(paiement_loyer, errorMsg);  
	 					
	 					 Locataire locataireG = locataireDI.getLocataire(matricule);
	 					if(locataireG != null) {
	 				    locataire.setCaution(locataireG.getCaution());
	 				    locataire.setAvance(Integer.parseInt(montantA) * Integer.parseInt(nbr));
	 				    locataire.setCommission_E(locataireG.getCommission_E());
	 				    locataire.setP_C(locataireG.getP_C());
						locataire.setP_A(1);
						locataire.setP_CO(locataireG.getP_CO());
	 					locataire.setMatricule(matricule); 
	 					}
	 					locataireDI.modifierLocataireAM(locataire, errorMsg); 
	 					
						
				//++++++++++++++++++++++++++++++OPERATION++++++++++++++++++++++++++++++;		
						operation.setCode(agence.getCode());
						operation.setSite(site.getSite() );
						operation.setProprietaire(site.getMatricule());
						operation.setMatricule(paieC.getMatricule());
						operation.setNom_prenom(mailUser );
						operation.setType(type );					
						operation.setApprovisonnement(Integer.parseInt(montantS) );
						operation.setMontantA((Integer.parseInt(montantS) * TAV) /100);
						operation.setMontantI((Integer.parseInt(montantS) * TIAV) /100);
						operation.setMontantP((Integer.parseInt(montantS) * resultAV) /100);
						operation.setDescription("Paiement"+" "+type+" loyer"+" "+site.getSite());
						operation.setDate(dating );
						operation.setCaisse(codeCaisse);
						operation.setAnnee(paieC.getAnnee());
						operation.setMois(paieC.getMois());
						operation.setHeure(datings);
						operation.setTaux(Integer.parseInt(pourcA));
						operationDI.creerOperation(operation, errorMsg); 
						
						
						if(calendrier_paiementDI.modifierCalendrier_paiementP(calendrier_paiement, errorMsg)) {							
							request.setAttribute("message", new Message("Paiement_Avance effectué avec succès."+ errorMsg.get(), 0, "green"));
						}else request.setAttribute("message", new Message("Echec enregistrement Paiement_loyer. " + errorMsg.get(), 0, "red") );					 																				
					}
	            	
	            	           	

	            	//++++++++++++++++++++++++++++++COMPTE++++++++++++++++++++++++++++++;
					if(compteDI.getVerifierCompte(site.getCode(),site.getSite()) == false) {
						compte.setCode(agence.getCode());
						compte.setSite(site.getSite() );
						compte.setMatricule(site.getMatricule());
						compte.setSoldeP((montantAV * resultAV) /100 );
						compte.setSoldeA((montantAV * TAV) /100 );
						compte.setSoldeI((montantAV * TIAV) /100);
						compteDI.creerCompte(compte, errorMsg); 
					}else {
						Compte compteG = compteDI.getCompte(site.getCode(),site.getSite());
						if(compteG != null) {
							compte.setCode(agence.getCode());
							compte.setSite(site.getSite() );
							compte.setMatricule(site.getMatricule());
							compte.setSoldeP((montantAV * resultAV) /100 + compteG.getSoldeP());
							compte.setSoldeA((montantAV * TAV) /100 + compteG.getSoldeA());	
							compte.setSoldeI((montantAV * TIAV) /100 + compteG.getSoldeI());
						}
						compteDI.modifierCompte(compte, errorMsg); 
					}	
					
					MiseAJourSomme miseSomme = new MiseAJourSomme();
					miseSomme.LesSomme(request);
					
					//notifications.avance(request, response);

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
		    	int anU = Integer.parseInt(an);
          	  Calendrier_paiement CalP = calendrier_paiementDI.getCalendrier_paiement(anU,mois,matricule);
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
					calendrier_paiement.setAnnee(Integer.parseInt(an));
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
					paiement_loyer.setAnnee(Integer.parseInt(an));
					paiement_loyer.setMois(mois);
					paiement_loyer.setMontantA((Integer.parseInt(montanT) * TA) /100);
					paiement_loyer.setMontantI((Integer.parseInt(montanT) * TI) /100);
					paiement_loyer.setMontantP((Integer.parseInt(montanT) * resultA) /100);
					paiement_loyer.setModeP(mode);
					paiement_loyer.setHeure(datings);
					 
					
					
			//++++++++++++++++++++++++++++++OPERATION++++++++++++++++++++++++++++++;		
					operation.setCode(agence.getCode());
					operation.setSite(site.getSite() );
					operation.setProprietaire(site.getMatricule());
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
					operation.setAnnee(Integer.parseInt(an));
					operation.setMois(mois);
					operation.setHeure(datings);
					operation.setTaux(Integer.parseInt(pourcA));
					 
															
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
				
				MiseAJourSomme miseSomme = new MiseAJourSomme();
				miseSomme.LesSomme(request);
				
				
          	 
				if(calendrier_paiementDI.modifierCalendrier_paiementP(calendrier_paiement, errorMsg)) {							
					request.setAttribute("message", new Message("Paiement_loyer enregistré avec succès."+ errorMsg.get(), 0, "green"));
					paiement_loyerDI.creerPaiement_loyer(paiement_loyer, errorMsg); 
					operationDI.creerOperation(operation, errorMsg);
					compteDI.modifierCompte(compte, errorMsg);
					
				}else request.setAttribute("message", new Message("Echec enregistrement Paiement_loyer. " + errorMsg.get(), 0, "red") );										
				notifications.bienvenue(request, response);
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
