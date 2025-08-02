package com.news.controllers;

import java.io.IOException;
import java.util.ArrayList;
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
import org.joda.time.format.DateTimeFormatter;

import com.news.beans.Agence;
import com.news.beans.Caisse;
import com.news.beans.OuvertureFermetureCaisse;
import com.news.beans.Profil;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.CaisseDI;
import com.news.dao.OuvertureFermetureCaisseDI;
import com.news.dao.ProfilDI;
import com.news.dao.SiteDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/menus")
public class GetMenus extends HttpServlet {

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
	public static final String OUVERT_CAISSE = "/WEB-INF/views/ouvertureCaisse.jsp";
	public static final String FERME_CAISSE = "/WEB-INF/views/fermetureCaisse.jsp";
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
	public static final String FICHIER_T = "/WEB-INF/gestion_T/fichier_T.jsp";

	public static final String ATT_SESSION_SITE = "site";
	
	private static final String FORMAT_DATE = "yyy-MM-dd";
	
	
	Users users	=	new Users();
	Site site = new Site();
	Agence agence = new Agence();
	Caisse caisse = new Caisse();
	OuvertureFermetureCaisse ouvFerCaisse = new OuvertureFermetureCaisse();
	
	ProfilDI profilDI = new ProfilDI();
	SiteDI siteDI = new SiteDI();
	CaisseDI caisseDI = new CaisseDI();
	OuvertureFermetureCaisseDI ouvFerCaisseDI	=	new OuvertureFermetureCaisseDI();
	
			
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String menu = request.getParameter("menu");	
		//String submit	=	request.getParameter("submit");
	
		
			
		users = (Users) session.getAttribute("users");		
		agence = (Agence) session.getAttribute("agence");
		site = (Site) session.getAttribute("site");
		
		VerifieSession verifieSess = new VerifieSession();
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		}
		
		DateTime dt = new DateTime();
		DateTimeFormatter formatter = DateTimeFormat.forPattern(FORMAT_DATE);
		String dating = dt.toString(formatter);

		if ("home".equals(menu)) {

			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(ACCUEIL).forward(request, response);
		}
		
		if ("site".equals(menu)) {

			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(SITE).forward(request, response);
		}
		
		if ("agence".equals(menu)) {

			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(AGENCE).forward(request, response);
		} 
		if ("maison".equals(menu)) {

			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(MAISON).forward(request, response);
		}
		if ("doc_locataire".equals(menu)) {

			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(DOC_LOCATAIRE).forward(request, response);
		}
		
		/*======= le menu gestion notification et les sous menus ========*/
          if("sms_demarcheur".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(SMS).forward(request, response);
		}
        if("rappele_loyer".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(RAPPEL).forward(request, response);
		}
        
        /*======= le menu gestion Bien Meublée et les sous menus ========*/
        if("bienM".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(BIENM).forward(request, response);
		}
        if("reservation".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(RESERVATION).forward(request, response);
		}
       if("clientB".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(CLIENTB).forward(request, response);
		}
        if("archive_CR".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(ARCHIVE_CR).forward(request, response);
		}
        if("facturation".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(FACTURATION).forward(request, response);
		}
        if("declaration".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(DECLRATION).forward(request, response);
		}
        if("historique".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(HISTORIQUE).forward(request, response);
		}
		
		/*======= le menu ressources humaines et les sous menus ========*/
         if("proprietaire".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(PROPRIETAIRE).forward(request, response);
			}
         if("proprietaire_M".equals(menu)) {
 			
 			request.setAttribute("page", menu);
 			this.getServletContext().getRequestDispatcher(PROPRIETAIRE_M).forward(request, response);
 			}
		 if("gestionnaire".equals(menu)) {
				
				request.setAttribute("page", menu);
				this.getServletContext().getRequestDispatcher(GESTIONNAIRE).forward(request, response);
		}
		if ("locataire".equals(menu)) {

			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(LOCATAIRE).forward(request, response);
		}
		if ("locataire_M".equals(menu)) {

			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(LOCATAIRE_M).forward(request, response);
		}
		if ("archive_locataire".equals(menu)) {

			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(ARCHIVE).forward(request, response);
		}
		if ("demarcheur".equals(menu)) {

			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(DEMARCHEUR).forward(request, response);
		}
		if ("reception_client".equals(menu)) {

			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(RECEPTION_CLI).forward(request, response);
		}
		/*======= le menu entreprise et les sous menus ========*/
		if ("entreprise".equals(menu)) {

			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(ENTREPRISE).forward(request, response);
		}
		if ("service_technique".equals(menu)) {

			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(SERVICET).forward(request, response);
		}
		if ("visite_maison".equals(menu)) {

			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(VISITEM).forward(request, response);
		}
		if ("partenaire".equals(menu)) {

			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(PARTENAIRE).forward(request, response);
		}
		/*======= le menu comptabilite et les sous menus ========*/
        if ("comptabilite".equals(menu)) {
        	
        	List<OuvertureFermetureCaisse> ouvFerCaisses	=	ouvFerCaisseDI.getOuvA(agence.getCode(), users.getEmail(),dating );
			int ouvert = 0, fermer = 0;
			for(OuvertureFermetureCaisse ouvF : ouvFerCaisses) {
				if(ouvF.getOuvert() == 1) {ouvert = ouvF.getOuvert();}
				if(ouvF.getFermer() == 1) {fermer = ouvF.getFermer();}
				
			}
						
			request.setAttribute("ouvert", ouvert);
			request.setAttribute("fermer", fermer);
				       				
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(COMPTABILITE).forward(request, response);
		}
    /*============================================= le menu comptabilite OUVERTURE DE CAISSES ======================================*/        
		if ("ouvertureCaisse".equals(menu)) {
			OuvertureFermetureCaisse  ouvFermer = new OuvertureFermetureCaisse ();
			List<OuvertureFermetureCaisse> listCaisseNonFermees = ouvFerCaisseDI.getOuv(agence.getCode(), site.getSite(), 0);
			
			DateTime dte = new DateTime(dating);
			Long aujourdhui = dte.getMillis();
	
			List<String> OA = new ArrayList<String>();
			for(OuvertureFermetureCaisse ouvF : listCaisseNonFermees) {
				DateTime dateF = new DateTime(ouvF.getDateOuverture());
				Long fer = dateF.getMillis();
				System.out.println(fer+" < "+aujourdhui);
				if(fer < aujourdhui) {
					ouvFermer.setCashReel(ouvF.getCashCalcule());
					ouvFermer.setDifferenceMontantF(0);
					ouvFermer.setDateFermeture(ouvF.getDateOuverture());
					ouvFermer.setFermer(1);
					ouvFermer.setId(ouvF.getId());
					
					ouvFerCaisseDI.modifierOuvFer(ouvFermer, errorMsg);
				} else OA.add(ouvF.getCodeCaisse());
			}
			
			List<Caisse> listCaisse = caisseDI.getAllCaisseO(agence.getCode());
			
			for(Caisse caisser : listCaisse) {
				int size = 0;
				for(String bb : OA) {
					if(caisser.getCodeCaisse().equals(bb)) size = 1;
				}
				
				if(size == 0) {
					caisse.setOf(0);
					caisse.setCode(agence.getCode());
					caisse.setCodeCaisse(caisser.getCodeCaisse());
					
					caisseDI.modifierO(caisse, errorMsg);
				}
				
			}
			List<OuvertureFermetureCaisse> ouvFerCaisses	=	ouvFerCaisseDI.getOuvA(agence.getCode(), users.getEmail(),dating );
			for(OuvertureFermetureCaisse ouvF : ouvFerCaisses) {
				int ouvert = ouvF.getOuvert();
				request.setAttribute("ouvert", ouvert);
			}
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(OUVERT_CAISSE).forward(request, response);
		}
	/*============================================= le menu comptabilite FERMERTURE DE CAISSES ======================================*/	
		if ("fermetureCaisse".equals(menu)) {
			List<OuvertureFermetureCaisse> ouvFerCaisses	=	ouvFerCaisseDI.getOuvA(agence.getCode(),users.getEmail(),dating );
			String codeCa = "";
			for(OuvertureFermetureCaisse ouvF : ouvFerCaisses) {
				ouvFerCaisse = ouvF;
				codeCa = ouvF.getCodeCaisse();
				
			}
			caisse = caisseDI.getCaisse(codeCa);
			
			request.setAttribute("ouvFerCaisse", ouvFerCaisse);
			request.setAttribute("caisse", caisse);
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(FERME_CAISSE).forward(request, response);
		}
		
        if ("documentations".equals(menu)) {
        	
        	List<OuvertureFermetureCaisse> ouvFerCaisses	=	ouvFerCaisseDI.getOuvA(agence.getCode(), users.getEmail(),dating );
			int ouvert = 0, fermer = 0;
			for(OuvertureFermetureCaisse ouvF : ouvFerCaisses) {
				if(ouvF.getOuvert() == 1) {ouvert = ouvF.getOuvert();}
				if(ouvF.getFermer() == 1) {fermer = ouvF.getFermer();}
				
			}
						
			request.setAttribute("ouvert", ouvert);
			request.setAttribute("fermer", fermer);
        				
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(DOCUMENT_COMPTA).forward(request, response);
		}
        
        if("paiement_loyer".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(PAIEMENT_LOYER).forward(request, response);
		}
       if("gestion_service".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(GESTION_SERVICE).forward(request, response);
		}
	 if("charge_entretien".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(CHARGE_ENTRETIEN).forward(request, response);
		}
	 if("compte_P".equals(menu)) {
		
		 List<Profil> listProfil = profilDI.getAllProfil();
			request.setAttribute("listProfil", listProfil);
		 
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(COMPTE_P).forward(request, response);
		}
	 if("compte_G".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(COMPTE_G).forward(request, response);
		}
     if("gestion_arrieres".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(ARRIERES).forward(request, response);
		}
		/*======= le menu parametres et les sous menus ========*/
        if ("utilisateurs".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(UTILISATEURS).forward(request, response);
		}
        if ("profils".equals(menu)) {
			List<Profil> listProfil = profilDI.getAllProfil();
			request.setAttribute("listProfil", listProfil);
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(PROFILS).forward(request, response);
		}
       if ("pays".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(PAYS).forward(request, response);
		}
       if ("ville".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(VILLE).forward(request, response);
		}
       if ("commune".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(COMMUNE).forward(request, response);
		}
       if ("quartier".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(QUARTIER).forward(request, response);
		}
       if ("service".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(SERVICE).forward(request, response);
		}
       if ("taux".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(TAUX).forward(request, response);
		}
       if ("role".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(ROLE).forward(request, response);
		}
       if ("gestion_caution".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(GESTION_C).forward(request, response);
		}
       
       
       /*======= le menu parametres gestion terrain ========*/ 
       if ("terrain".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(TERRAIN).forward(request, response);
		}
      if ("client_A".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(CLIENT_A).forward(request, response);
		}
      if ("dossier_cl".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(DOSSIER_CLI).forward(request, response);
		}
      if ("offre_terrain".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(OFFRE_TERRAIN).forward(request, response);
		}
      if ("fichier_T".equals(menu)) {
			
			request.setAttribute("page", menu);
			this.getServletContext().getRequestDispatcher(FICHIER_T).forward(request, response);
		}
       
	}

}
