package com.news.controllers;


import com.news.beans.*;
import com.news.dao.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Choose")
public class ChangeEcoAn extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	private static final String ACCUEIL = "/WEB-INF/views/home.jsp";
	/* ============== LES MENUS GESTION ECOLE ============== */	
	public static final String SITE = "/WEB-INF/views/site.jsp";
	public static final String AGENCE = "/WEB-INF/views/agence.jsp";
	public static final String MAISON = "/WEB-INF/views/maison.jsp";
	public static final String DOC_LOCATAIRE = "/WEB-INF/views/doc_locataire.jsp";
	/* ============== LES MENUS GESTION NOTIFICATION ============== */
	public static final String SMS = "/WEB-INF/views/sms_demarcheur.jsp";
	public static final String RAPPEL = "/WEB-INF/views/rappel_loyer.jsp";
	/* ============== LES MENUS GESTION BIEN MEUBLEE ============== */
	public static final String BIENM = "/WEB-INF/bien_M/bienM.jsp";
	public static final String RESERVATION = "/WEB-INF/bien_M/reservation.jsp";
	public static final String CLIENTB = "/WEB-INF/bien_M/clientB.jsp";
	public static final String ARCHIVE_CR = "/WEB-INF/bien_M/archive_CR.jsp";
	public static final String FACTURATION = "/WEB-INF/bien_M/facturation.jsp";
	public static final String DECLRATION = "/WEB-INF/bien_M/declaration.jsp";
	public static final String HISTORIQUE = "/WEB-INF/bien_M/historique.jsp";
	/* ============== LES MENUS RESSOURCES HUMAINES ============== */
	public static final String PROPRIETAIRE = "/WEB-INF/views/proprietaire.jsp";
	public static final String PROPRIETAIRE_M = "/WEB-INF/views/proprietaire_M.jsp";
	public static final String LOCATAIRE = "/WEB-INF/views/locataire.jsp";
	public static final String LOCATAIRE_M = "/WEB-INF/views/locataire_M.jsp";
	public static final String GESTIONNAIRE = "/WEB-INF/views/gestionnaire.jsp";
	public static final String ARCHIVE = "/WEB-INF/views/archive_locataire.jsp";
	public static final String DEMARCHEUR = "/WEB-INF/views/demarcheur.jsp";
	public static final String RECEPTION_CLI = "/WEB-INF/views/reception_client.jsp";
	/* ============== LES MENUS ENTREPRISE ============== */ 
	public static final String ENTREPRISE = "/WEB-INF/views/entreprise.jsp";
	public static final String SERVICET = "/WEB-INF/views/service_technique.jsp";
	public static final String VISITEM = "/WEB-INF/views/visite_maison.jsp";
	public static final String PARTENAIRE = "/WEB-INF/views/partenaire.jsp";
	/* ============== LES MENUS COMPTABILITE ============== */
	public static final String COMPTABILITE = "/WEB-INF/views/comptabilite.jsp";
	public static final String DOCUMENT_COMPTA = "/WEB-INF/views/documentCompta.jsp";
	public static final String PAIEMENT_LOYER = "/WEB-INF/views/paiement_loyer.jsp";
	public static final String CHARGE_ENTRETIEN = "/WEB-INF/views/charge_entretien.jsp";
	public static final String GESTION_SERVICE = "/WEB-INF/views/gestion_service.jsp";
	public static final String COMPTE_P = "/WEB-INF/views/compte_P.jsp";
	public static final String COMPTE_G = "/WEB-INF/views/compte_G.jsp";
	private static final String OUV_CAISSE = "/WEB-INF/views/ouvertureCaisse.jsp";
	private static final String FER_CAISSE = "/WEB-INF/views/fermetureCaisse.jsp";
	private static final String ARRIERES = "/WEB-INF/views/gestion_arrieres.jsp";
	
	/* ============== LES MENUS PARAMETRES ============== */
	public static final String UTILISATEURS = "/WEB-INF/views/utilisateur.jsp"; 
	public static final String PROFILS = "/WEB-INF/views/profile.jsp";
	public static final String PAYS = "/WEB-INF/views/pays.jsp";
	public static final String VILLE = "/WEB-INF/views/ville.jsp";
	public static final String COMMUNE = "/WEB-INF/views/commune.jsp";
	public static final String QUARTIER = "/WEB-INF/views/quartier.jsp";
	public static final String SERVICE = "/WEB-INF/views/service.jsp";
	public static final String TAUX = "/WEB-INF/views/taux.jsp";
	public static final String ROLE = "/WEB-INF/views/role.jsp";
	public static final String GESTION_C = "/WEB-INF/views/gestion_caution.jsp";
	
	/* ============== LES MENUS GESTION TERRAIN ============== */
	public static final String CLIENT_A = "/WEB-INF/gestion_T/client_A.jsp"; 
	public static final String TERRAIN = "/WEB-INF/gestion_T/terrain.jsp";
	public static final String DOSSIER_CLI = "/WEB-INF/gestion_T/dossier_cl.jsp";
	public static final String OFFRE_TERRAIN = "/WEB-INF/gestion_T/offre_terrain.jsp";
		
	
	SiteDI siteDI = new SiteDI();
	Site site = new Site();	
	ProfilDI profilDI = new ProfilDI();

	Users users	=	new Users();
	
	Agence agence = new Agence();
	AgenceDI agenceDI = new AgenceDI();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		String sites	=	request.getParameter("sites");
		String page = request.getParameter("page");
		
		Site site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
		supprimerSession(request);
		
		if(!site.getSite().equals(sites)) {
			request.setAttribute("page", "home");
			this.getServletContext().getRequestDispatcher(ACCUEIL).forward(request, response);
			return;
		}
		
		users = (Users) session.getAttribute("users");
		site = (Site) session.getAttribute("site");		
		
		if(page.equals("home")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(ACCUEIL).forward(request, response);
		}
				
		/*======= le menu administration et les sous menus ========*/
		if(page.equals("site")) {
					
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(SITE).forward(request, response);
			}
		if(page.equals("agence")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(AGENCE).forward(request, response);
			}
         if(page.equals("maison")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(MAISON).forward(request, response);
			}
		if(page.equals("doc_locataire")) {
				
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(DOC_LOCATAIRE).forward(request, response);
			}
		
		/*======= le menu gestion notification et les sous menus ========*/
		if(page.equals("sms_demarcheur")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(SMS).forward(request, response);
		}
        if(page.equals("rappele_loyer")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(RAPPEL).forward(request, response);
		}
        
        /*======= le menu gestion Bien Meublée et les sous menus ========*/
        if(page.equals("bienM")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(BIENM).forward(request, response);
		}
        if(page.equals("reservation")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(RESERVATION).forward(request, response);
		}
       if(page.equals("clientB")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(CLIENTB).forward(request, response);
		}
        if(page.equals("archive_CR")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(ARCHIVE_CR).forward(request, response);
		}
        if(page.equals("facturation")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(FACTURATION).forward(request, response);
		}
        if(page.equals("declaration")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(DECLRATION).forward(request, response);
		}
        if(page.equals("historique")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(HISTORIQUE).forward(request, response);
		}
		
		/*======= le menu ressources humaines et les sous menus ========*/
		 if(page.equals("proprietaire")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(PROPRIETAIRE).forward(request, response);
			}
		 if(page.equals("proprietaire_M")) {
				
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(PROPRIETAIRE_M).forward(request, response);
				}
		 if(page.equals("gestionnaire")) {
				
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(GESTIONNAIRE).forward(request, response);
				} 
		 if(page.equals("locataire")) {
				
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(LOCATAIRE).forward(request, response);
			}
		 if(page.equals("locataire_M")) {
				
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(LOCATAIRE_M).forward(request, response);
		}
		if(page.equals("archive_locataire")) {
					
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(ARCHIVE).forward(request, response);
			}	
		if(page.equals("demarcheur")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(DEMARCHEUR).forward(request, response);
			}	
		if(page.equals("reception_client")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(RECEPTION_CLI).forward(request, response);
			}	
		 
		 /*======= le menu entreprise et les sous menus ========*/
			if (page.equals("entreprise")) {

				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(ENTREPRISE).forward(request, response);
			}
			if (page.equals("service_technique")) {

				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(SERVICET).forward(request, response);
			}
			if (page.equals("visite_maison")) {

				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(VISITEM).forward(request, response);
			}
			if (page.equals("partenaire")) {

				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(PARTENAIRE).forward(request, response);
			}
		
		/*======= le menu comptabilite et les sous menus ========*/
		 if(page.equals("comptabilite")) {
				
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(COMPTABILITE).forward(request, response);
			}		 
		 if(page.equals("ouvertureCaisse")) {

				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(OUV_CAISSE).forward(request, response);
			}
		if(page.equals("fermetureCaisse")) {

				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(FER_CAISSE).forward(request, response);
		}		 
		 if(page.equals("documentations")) {
				
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(DOCUMENT_COMPTA).forward(request, response);
			}
		 if(page.equals("paiement_loyer")) {
				
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(PAIEMENT_LOYER).forward(request, response);
			}
		 if(page.equals("gestion_service")) {
				
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(GESTION_SERVICE).forward(request, response);
			}
		 if(page.equals("charge_entretien")) {
				
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(CHARGE_ENTRETIEN).forward(request, response);
			}
		 if(page.equals("compte_P")) {
				
			 List<Profil> listProfil = profilDI.getAllProfil();
	   			request.setAttribute("listProfil", listProfil);
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(COMPTE_P).forward(request, response);
			}
		 if(page.equals("compte_G")) {
				
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(COMPTE_G).forward(request, response);
			}
         if(page.equals("gestion_arrieres")) {
				
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(ARRIERES).forward(request, response);
			}
		/*======= le menu parametres et les sous menus ========*/
           if (page.equals("utilisateurs")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(UTILISATEURS).forward(request, response);
		}
        if(page.equals("profils")) {
   			List<Profil> listProfil = profilDI.getAllProfil();
   			request.setAttribute("listProfil", listProfil);
   			request.setAttribute("page", page);
   			this.getServletContext().getRequestDispatcher(PROFILS).forward(request, response);
   		}
        if (page.equals("pays")) {
   			
   			request.setAttribute("page", page);
   			this.getServletContext().getRequestDispatcher(PAYS).forward(request, response);
   		}
       if (page.equals("ville")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(VILLE).forward(request, response);
		}
       if (page.equals("commune")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(COMMUNE).forward(request, response);
		}
       if (page.equals("quartier")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(QUARTIER).forward(request, response);
		}
       if (page.equals("service")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(SERVICE).forward(request, response);
		}
       if (page.equals("taux")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(TAUX).forward(request, response);
		}
       if (page.equals("role")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(ROLE).forward(request, response);
		}
       if (page.equals("gestion_caution")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(GESTION_C).forward(request, response);
		}
       
       /*======= le menu parametres gestion terrain ========*/ 
       if (page.equals("terrain")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(TERRAIN).forward(request, response);
		}
      if (page.equals("client_A")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(CLIENT_A).forward(request, response);
		}
     if (page.equals("dossier_cl")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(DOSSIER_CLI).forward(request, response);
		}
     if (page.equals("offre_terrain")) {
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(OFFRE_TERRAIN).forward(request, response);
		}
	}
	
	public void supprimerSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		String codeA	=	request.getParameter("codeA");
		String sites	=	request.getParameter("sites");
		
		session.removeAttribute("site");
		session.removeAttribute("agence");
	
			
		agence	=	agenceDI.getAgence(codeA);
		site	=	siteDI.getSite(sites);
		users = (Users) session.getAttribute("users");
		
		
		
		session.setAttribute("agence", agence);
		session.setAttribute("site", site);

	}

	
}
