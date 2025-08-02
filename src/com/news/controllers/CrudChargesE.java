package com.news.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.news.beans.Charges_entretiens;
import com.news.beans.Compte_proprietaire;
import com.news.beans.Depot;
import com.news.beans.Site;
import com.news.dao.Charges_entretiensDI;
import com.news.dao.Compte_proprietaireDI;
import com.news.dao.DepotDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/ChargesE")
public class CrudChargesE extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/charge_entretien.jsp";
	
	private static final String FORMAT_DATE = "yyy-MM-dd";
	private static final String FORMAT_DATES = "HH:mm:ss";
	
	Site site = new Site();
	Agence agence = new Agence();

	Charges_entretiensDI charges_entretiensDI = new Charges_entretiensDI();
	Charges_entretiens charges_entretiens = new Charges_entretiens();
	
	 Compte_proprietaireDI compte_proprietaireDI = new Compte_proprietaireDI();
	 Compte_proprietaire compte_proprietaire = new Compte_proprietaire();
	 
	 DepotDI depotDI = new DepotDI();
	 Depot depot = new Depot();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");		
		//String idD = request.getParameter("idD");
				
		String montantDe = request.getParameter("montantDe");
	
			
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
			
			System.out.println(site.getSite());
			
			Depot Dep = depotDI.getDepotS(site.getSite());
			 Long idD = Dep.getId();
			
			if("id".equals(crud)) {
								
				depot.setMontantR(Dep.getMontantR() + Integer.parseInt(montantDe));
				depot.setId(idD);
				depotDI.modifierDepot(depot, errorMsg);
				
				charges_entretiens = charges_entretiensDI.getCharges_entretiens(Integer.parseInt(id));
				
				charges_entretiens.setId(Long.parseLong(id));	
				
				if(charges_entretiensDI.supprimerCharges_entretiens(charges_entretiens, errorMsg)) {
					request.setAttribute("message", new Message("decaissement supprimé avec succès."+ errorMsg.get(), 0, "green"));
				} else request.setAttribute("message", new Message("Echec suppression decaissement. " + errorMsg.get(), 0, "red") );
							
				
		}
			
			if("DEC".equals(crud)) {
				
				charges_entretiens = charges_entretiensDI.getCharges_entretiens(Integer.parseInt(id));
								
				charges_entretiens.setId(Long.parseLong(id));
				charges_entretiensDI.supprimerCharges_entretiens(charges_entretiens, errorMsg);	
			}
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}
		
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				HttpSession session = request.getSession();
				String id = request.getParameter("id");
				String idD = request.getParameter("idD");
				String idDe = request.getParameter("idDe");
				String page = request.getParameter("page");
				String crud = request.getParameter("crud");
				
				/* ======== DEPOT ======== */
				String montantD = request.getParameter("montantD");
				String montantR = request.getParameter("montantR");
				/* ======== charges_entretiens ======== */
				String charge = request.getParameter("charge");	
				String montantDe = request.getParameter("montantDe");
				String montantRD = request.getParameter("montantRD");
				
				String porte = request.getParameter("porte");
				
				
				if(verifieSess.sessions(request, response) == false) {
					response.sendRedirect( request.getContextPath() + INDEX );
					return;
				} 
				
				site = (Site) session.getAttribute("site");
				agence = (Agence) session.getAttribute("agence");
				
				DateTime dt = new DateTime();
				org.joda.time.format.DateTimeFormatter formattere = DateTimeFormat.forPattern(FORMAT_DATE);
				String dating = dt.toString(formattere);
				
				LocalDate dat = LocalDate.now(); 
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM", Locale.FRENCH); 
				String mois = dat.format(formatter);
				
				LocalDate dates = LocalDate.now(); 
				DateTimeFormatter formatters = DateTimeFormatter.ofPattern("YYYY", Locale.FRENCH); 
				String ans = dates.format(formatters);	
				
				DateTime dtH = new DateTime();
				org.joda.time.format.DateTimeFormatter formatterH = DateTimeFormat.forPattern(FORMAT_DATES);
				String datings = dtH.toString(formatterH);
				
				 int ansU = Integer.parseInt(ans); 
				
				if("DEPOT".equals(crud)) {
					
					depot.setSite(site.getSite());
					depot.setAns(ans);			
					depot.setMontantD(Integer.parseInt(montantD));
					if(montantR != null) {
						depot.setMontantR(Integer.parseInt(montantR) + Integer.parseInt(montantD));
					} else {
						depot.setMontantR(0 + Integer.parseInt(montantD));
					}
					depot.setDate(dating);					
					depot.setMois(mois);
					depot.setHeur(datings);
					depot.setCode(agence.getCode());
										
					
					if(depotDI.creerDepot(depot, errorMsg)) {
						request.setAttribute("message", new Message("Depot  enregistré avec succès."+ errorMsg.get(), 0, "green"));
					} else request.setAttribute("message", new Message("Echec enregistrement Depot. " + errorMsg.get(), 0, "red") );
					
					Charges_entretiens charE = charges_entretiensDI.getCharges_entretiens(site.getSite(),ans,mois);
					Charges_entretiens charED = charges_entretiensDI.getCharges_entretiens(site.getSite(),ans,mois);
					if(charE != null) {
						
						charges_entretiens.setMontantDM(charED.getMontantDM() + Integer.parseInt(montantD));
						charges_entretiens.setPropriete(site.getSite());
						charges_entretiens.setAns(ans);
						charges_entretiens.setMois(mois);
						charges_entretiensDI.modifierCharges_entretiens(charges_entretiens, errorMsg);
					} 	
					
					
					if(compte_proprietaireDI.getVerifierCompte_proprietaire(site.getSite(),ansU,mois) == false) {
						
						compte_proprietaire.setProprietaire(site.getNom_prenom());
						compte_proprietaire.setMatricule(site.getMatricule());
						compte_proprietaire.setPropriete(site.getSite());
						compte_proprietaire.setAns(ansU);
						compte_proprietaire.setMois(mois);
						compte_proprietaire.setRetraitM(Integer.parseInt(montantD));
								
						compte_proprietaireDI.creerCompte_proprietaire(compte_proprietaire, errorMsg);
					} else {
						Compte_proprietaire CompteP = compte_proprietaireDI.getCompte_proprietaire(site.getSite(),ansU,mois);
						
						compte_proprietaire.setRetraitM(Integer.parseInt(montantD) + CompteP.getRetraitM());
						compte_proprietaire.setPropriete(site.getSite());
						compte_proprietaire.setAns(ansU);
						compte_proprietaire.setMois(mois);
						
						compte_proprietaireDI.modifierCompte_proprietaireR(compte_proprietaire, errorMsg);
					}
																					
				}
				
				if("DECAISSEMENT".equals(crud)) {
								
					charges_entretiens.setPropriete(site.getSite());
					charges_entretiens.setType_charge(charge);					
					charges_entretiens.setAns(ans);
					charges_entretiens.setMois(mois);
					charges_entretiens.setDate(dating);	
					
					charges_entretiens.setMontantD(Integer.parseInt(montantDe));
					charges_entretiens.setMontantDM(Integer.parseInt(montantR) - Integer.parseInt(montantDe));
					charges_entretiens.setPorte(porte);	
					charges_entretiens.setCode(agence.getCode());	
				
					if(charges_entretiensDI.creerCharges_entretiens(charges_entretiens, errorMsg)) {
						request.setAttribute("message", new Message("Montant Decaisser  avec succès."+ errorMsg.get(), 0, "green"));
					} else request.setAttribute("message", new Message("Echec Montant Decaisser. " + errorMsg.get(), 0, "red") );
					
					depot = depotDI.getDepot(Integer.parseInt(idD));
					depot.setMontantR(Integer.parseInt(montantR) - Integer.parseInt(montantDe));
					depot.setId(Long.parseLong(idD));
					depotDI.modifierDepot(depot, errorMsg);
			    }
										
				
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
			}
	
}
