package com.news.controllers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.beans.Calendrier_paiement;
import com.news.beans.Locataire;
import com.news.beans.Maison;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.Calendrier_paiementDI;
import com.news.dao.LocataireDI;
import com.news.dao.MaisonDI;
import com.news.fonctions.Notifications;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_maison")
public class CRUDViewMaison extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/view_maison.jsp";
	public static final String SMS = "/WEB-INF/views/sms_demarcheur.jsp";
	public static final String LOCATAIRE = "/WEB-INF/views/locataire.jsp";
	public static final String LOCATAIREM = "/WEB-INF/views/locataire_M.jsp";
	
	 Site site = new Site();
	 Users users	=	new Users();
	 
		MaisonDI maisonDI = new MaisonDI();
		Maison maison = new Maison();
		
		 LocataireDI locataireDI = new LocataireDI();
		 Locataire locataire = new Locataire();
		 
		 Calendrier_paiementDI calendrier_paiementDI = new Calendrier_paiementDI();
		 Calendrier_paiement calendrier_paiement = new Calendrier_paiement();

	
	 AtomicReference<String> errorMsg = new  AtomicReference<>("");
		VerifieSession verifieSess = new VerifieSession();
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
		//	String crud	=	request.getParameter("crud");
			String page	=	request.getParameter("page");
			String id	=	request.getParameter("id");
		
			
			site = (Site) session.getAttribute("site");
			
				if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
				
				int idU = Integer.parseInt(id);
				maison = maisonDI.getMaison(idU);
				request.setAttribute("maison", maison);
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}
		
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();		
			String id	=	request.getParameter("id");
			String page = request.getParameter("page");
			String crud = request.getParameter("crud");			
			String maisons = request.getParameter("maisons");		
			String piece= request.getParameter("piece");
			String chambre= request.getParameter("chambre");
			String type= request.getParameter("type");
			String etage= request.getParameter("etage");
			String ascenceur= request.getParameter("ascenceur");
			String balcon= request.getParameter("balcon");
			String terrasse= request.getParameter("terrasse");
			String prix= request.getParameter("prix");
			String prixA= request.getParameter("prixA");
			String occupe= request.getParameter("occupe");
			String matricule= request.getParameter("matricule");
			String num_compt= request.getParameter("num_compt");
			String num_sodeci= request.getParameter("num_sodeci");
			String salon= request.getParameter("salon");
		
		
			String matri= request.getParameter("matri");
			
			System.out.println(matri);
			
			users = (Users) session.getAttribute("users");
			site = (Site) session.getAttribute("site");
			
				if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
				
				Notifications notifications = new Notifications();
				if(crud.equals("SMS")) {
					notifications.reception_dem(request, response);
					
					request.setAttribute("page", page);
					this.getServletContext().getRequestDispatcher(SMS).forward(request, response);
				}
			
				Locataire Ml = locataireDI.getLocataire(matri);	
				Locataire Nloc = locataireDI.getLocataire(matricule);	
				
				 Calendrier_paiement Cal = calendrier_paiementDI.getCalendrier_paiement(matricule);
			    Calendrier_paiement Cale = calendrier_paiementDI.getCalendrier_paiement(matri);	
			    			    
			if(crud.equals("MODIFIER")) {
				maison = maisonDI.getMaison(Integer.parseInt(id));
				
				maison.setSite(site.getSite());
				maison.setMaison(maisons);
				maison.setPiece(piece);
				maison.setChambre(chambre);
				maison.setType(type);
				maison.setEtage(etage);
				if(ascenceur != null) maison.setAscenceur(Integer.parseInt(ascenceur));
				else maison.setAscenceur(0);
				if(balcon != null) maison.setBalcon(Integer.parseInt(balcon));
				else maison.setBalcon(0);
				if(terrasse != null) maison.setTerrasse(Integer.parseInt(terrasse));
				else maison.setTerrasse(0);
				maison.setPrix(Integer.parseInt(prix));
				maison.setNumero_compteur(num_compt);
				maison.setNumero_sodeci(num_sodeci);
				maison.setSalon(salon);
											
				    if(matricule.equals(" ")) {
				    	maison.setNom_prenom(" ");	
						maison.setOccupe(0);	
						 maison.setMatricule(matricule);
					  }else  {						
						 if(Nloc != null) {
						    maison.setNom_prenom(Nloc.getNom()+" "+Nloc.getPrenom());	
		                     maison.setOccupe(1);
		                     maison.setMatricule(matricule);
						    }
					}
				    
				    if(matricule.equals(" ")&& (Ml != null)) {
				    	 if(Ml != null) {
							 locataire.setNum_porte(" ");
							 locataire.setMatricule(matri);
							 locataireDI.modifierLocataireM(locataire, errorMsg);
						    }				    	
					  }else  {						
						  locataire.setNum_porte(maisons);
						  locataire.setMatricule(matricule);				    
						  locataireDI.modifierLocataireM(locataire, errorMsg);
					}
				    				   				  			    				     
				    if(Cal != null) {
				    	if(Cal.getMontant_loyer() != 0) {
				    		 int montL=0;
				    		 montL=Cal.getMontant_loyer() + Integer.parseInt(prix) - Integer.parseInt(prixA);
				    		calendrier_paiement.setMontant_loyer(montL);
							calendrier_paiement.setMontant_R(montL); 
							
							 calendrier_paiement.setMatricule(matricule);
				    	  } else {
						calendrier_paiement.setMontant_loyer(Integer.parseInt(prix));
						calendrier_paiement.setMontant_R(Integer.parseInt(prix));	
						
						 calendrier_paiement.setMatricule(matricule);
				    }
				    }
				    
				    if(matricule.equals(" ")&& (Cale != null)) {
				    	 if(Cale != null) {
				    	calendrier_paiement.setMontant_loyer(0);
						calendrier_paiement.setMontant_R(0);
						 calendrier_paiement.setMatricule(matri);
				    }
				    }				    					 
				    calendrier_paiementDI.modifierCalendrier_paiement(calendrier_paiement, errorMsg);
				    			    				  			     				   																					
				maison.setId(Long.parseLong(id));
				
				if(maisonDI.modifierMaison(maison, errorMsg)) {
					request.setAttribute("maison", maison);
					request.setAttribute("message", new Message("Maison modifié avec succès."+ errorMsg.get(), 0, "green"));
				} else request.setAttribute("message", new Message("Echec modification Maison. " + errorMsg.get(), 0, "red") );
			
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
			}	
			
		

			  String idM	=	request.getParameter("idM");			
			  String types = request.getParameter("types");
			  			
			  String matriculeM = request.getParameter("matriculeM");	
				
			  
			  Calendrier_paiement CalM = calendrier_paiementDI.getCalendrier_paiement(matriculeM);
				Locataire NlocM = locataireDI.getLocataire(matriculeM);
				if(crud.equals("MODIFIERM")) {
					if(types.equals("supS")) {
					Maison trouve = maisonDI.getMaison(Integer.parseInt(idM));
					
					if(NlocM != null) {
					     maison.setNom_prenom(NlocM.getNom()+" "+NlocM.getPrenom());	
	                     maison.setOccupe(1);
	                     maison.setMatricule(matriculeM);
					    }
					
					if(matriculeM != null) {
						if(trouve != null) {
					  locataire.setNum_porte(trouve.getMaison());
						}
					  locataire.setMatricule(matriculeM);				    
					  locataireDI.modifierLocataireM(locataire, errorMsg);
					}
					
					  if(CalM != null) {
						if(trouve != null) {
						  calendrier_paiement.setMontant_loyer(trouve.getPrix());
							calendrier_paiement.setMontant_R(trouve.getPrix());	
						}	
							 calendrier_paiement.setMatricule(matriculeM);
					  }
					  calendrier_paiementDI.modifierCalendrier_paiement(calendrier_paiement, errorMsg);
							
						maison.setId(Long.parseLong(idM));
						
						if(maisonDI.modifierMaisonM(maison, errorMsg)) {
							request.setAttribute("maison", maison);
							request.setAttribute("message", new Message("Maison modifié avec succès."+ errorMsg.get(), 0, "green"));
						} else request.setAttribute("message", new Message("Echec modification Maison. " + errorMsg.get(), 0, "red") );
				}
			  
					request.setAttribute("page", page);
					this.getServletContext().getRequestDispatcher(LOCATAIRE).forward(request, response);
				}	
				
				if(crud.equals("MODIFIERMM")) {
					if(types.equals("supS")) {
					Maison trouve = maisonDI.getMaison(Integer.parseInt(idM));
					
					if(NlocM != null) {
					     maison.setNom_prenom(NlocM.getNom());	
	                     maison.setOccupe(1);
	                     maison.setMatricule(matriculeM);
					    }
					
					if(matriculeM != null) {
						if(trouve != null) {
					  locataire.setNum_porte(trouve.getMaison());
						}
					  locataire.setMatricule(matriculeM);				    
					  locataireDI.modifierLocataireM(locataire, errorMsg);
					}
					
					  if(CalM != null) {
						if(trouve != null) {
						  calendrier_paiement.setMontant_loyer(trouve.getPrix());
							calendrier_paiement.setMontant_R(trouve.getPrix());	
						}	
							 calendrier_paiement.setMatricule(matriculeM);
					  }
					  calendrier_paiementDI.modifierCalendrier_paiement(calendrier_paiement, errorMsg);
							
						maison.setId(Long.parseLong(idM));
						
						if(maisonDI.modifierMaisonM(maison, errorMsg)) {
							request.setAttribute("maison", maison);
							request.setAttribute("message", new Message("Maison modifié avec succès."+ errorMsg.get(), 0, "green"));
						} else request.setAttribute("message", new Message("Echec modification Maison. " + errorMsg.get(), 0, "red") );
				}
			  
					request.setAttribute("page", page);
					this.getServletContext().getRequestDispatcher(LOCATAIREM).forward(request, response);
				}	
			
			
			
			
		}

}
