package com.news.controllers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.beans.Agence;
import com.news.beans.Roles;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.RoleDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/roles")
public class CRUDRoles extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String PROFIL = "/WEB-INF/views/role.jsp";

	RoleDI roleDI = new RoleDI();
	Roles role = new Roles();
	
	 Site site = new Site();
	 Users users	=	new Users();
	 Agence agences = new Agence();
	 
	 AtomicReference<String> errorMsg = new  AtomicReference<>("");
		VerifieSession verifieSess = new VerifieSession();
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}

		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String id = request.getParameter("id");
			String page = request.getParameter("page");
			String crud = request.getParameter("crud");
			String profil = request.getParameter("profil");
			
			/* ==== LES ACCES ==== */
			String accueil 					= request.getParameter("accueil");
			String agence 				= request.getParameter("agence");
			String code_site 			= request.getParameter("code_site");
			String porte 					= request.getParameter("porte");
			String rapport 					= request.getParameter("rapport");
			
			/* ==== LES ACCES ==== */
			String ressourceH 				= request.getParameter("ressourceH");
			String gestionnaire 					= request.getParameter("gestionnaire");
			String locataire 				= request.getParameter("locataire");
			String proprietaire 					= request.getParameter("proprietaire");

			/* ==== LES ACCES ==== */
			String comptabilite 				= request.getParameter("comptabilite");
			String paiementL 			= request.getParameter("paiementL");
			String chargeE 					= request.getParameter("chargeE");
			
			/* ==== LES ACCES ==== */
			String parametre 				= request.getParameter("parametre");
			String utilisateur 				= request.getParameter("utilisateur");
			String profile 				= request.getParameter("profile");
			String roleS 				= request.getParameter("roleS");					
			String ville 					= request.getParameter("ville");
			String commune 					= request.getParameter("commune");
			String quartier 				= request.getParameter("quartier");
		    String pays 					= request.getParameter("pays");
			String service 					= request.getParameter("service");			
			String pourcentage 					= request.getParameter("pourcentage");
			
			/* ==== LES ACCES DES BOUTONS ==== */
			String  ajout_AG = request.getParameter("ajout_AG");	
			String  modif_AG= request.getParameter("modif_AG");
			String  supp_AG= request.getParameter("supp_AG");
			String  ajout_SI= request.getParameter("ajout_SI");
			String  modif_SI= request.getParameter("modif_SI");
			String  supp_SI= request.getParameter("supp_SI");
			String  ajout_AP= request.getParameter("ajout_AP");	
			String  modif_AP= request.getParameter("modif_AP");
			String  supp_AP= request.getParameter("supp_AP");
			String  ajout_GE= request.getParameter("ajout_GE");
			String  modif_GE= request.getParameter("modif_GE");
			String  supp_GE= request.getParameter("supp_GE");
			String  ajout_LO= request.getParameter("ajout_LO");
			String  modif_LO= request.getParameter("modif_LO");	
			String  supp_LO= request.getParameter("supp_LO");
			String  ajout_PR= request.getParameter("ajout_PR");
			String  modif_PR= request.getParameter("modif_PR");
			String  supp_PR= request.getParameter("supp_PR");
			String  ajout_PL= request.getParameter("ajout_PL");
			String  supp_PL= request.getParameter("supp_PL");
			
			/* ==== LES BOUTONS PARAMETRE ==== */
			String ajout_UT= request.getParameter("ajout_UT");
			String modif_UT= request.getParameter("modif_UT");	
			String supp_UT= request.getParameter("supp_UT");
			String ajout_SV= request.getParameter("ajout_SV");
			String modif_SV= request.getParameter("modif_SV");	
			String supp_SV= request.getParameter("supp_SV");
			String ajout_PO= request.getParameter("ajout_PO");
			String modif_PO= request.getParameter("modif_PO");	
			String supp_PO= request.getParameter("supp_PO");
						
			/* ==== LES BOUTONS RAPPORT ==== */
			String coutM= request.getParameter("coutM");
			String etat_FL= request.getParameter("etat_FL");	
			String etat_FS= request.getParameter("etat_FS");
			String etat_FA= request.getParameter("etat_FA");
			String etat_FM= request.getParameter("etat_FM");	
			String fiche_ID= request.getParameter("fiche_ID");
			String tableau_SU= request.getParameter("tableau_SU");
			String etat_LI= request.getParameter("etat_LI");	
			String bilan_CH= request.getParameter("bilan_CH");
			String etat_FAP= request.getParameter("etat_FAP");
			String etat_FMP= request.getParameter("etat_FMP");	
			String etat_FAG= request.getParameter("etat_FAG");
			String etat_FMG= request.getParameter("etat_FMG");
			String etat_FIL= request.getParameter("etat_FIL");	
			String commissionE= request.getParameter("commissionE");
			
			/* ==== LES BOUTONS NEW ==== */
			String partenaire= request.getParameter("partenaire");
			String service_tech= request.getParameter("service_tech");	
			String g_caution= request.getParameter("g_caution");
			String archive= request.getParameter("archive");
			
			/* ==== LES BOUTONS NEW ==== */
			String archive_cli= request.getParameter("archive_cli");
			String client= request.getParameter("client");	
			String ajout_cli= request.getParameter("ajout_cli");
			String modif_cli= request.getParameter("modif_cli");
			String supp_cli	= request.getParameter("supp_cli");	
			String demarcheur= request.getParameter("demarcheur");
			String ajout_dem= request.getParameter("ajout_dem");
			String modif_dem= request.getParameter("modif_dem");	
			String supp_dem= request.getParameter("supp_dem");
			String reception_cli= request.getParameter("reception_cli");
			String ajout_recli= request.getParameter("ajout_recli");	
			String modif_recli= request.getParameter("modif_recli");
			String supp_recli= request.getParameter("supp_recli");
			String notification	= request.getParameter("notification");	
			String arriere= request.getParameter("arriere");
			String ajout_ari= request.getParameter("ajout_ari");	
			String modif_ari= request.getParameter("modif_ari");
			String supp_ari= request.getParameter("supp_ari");
			String bien_meuble= request.getParameter("bien_meuble");	
			String bien	= request.getParameter("bien");
			String ajout_b= request.getParameter("ajout_b");
			String modif_b= request.getParameter("modif_b");	
			String supp_b= request.getParameter("supp_b");
			String paiement_bm= request.getParameter("paiement_bm");
			String gestion_service= request.getParameter("gestion_service");	
			String rapport_c= request.getParameter("rapport_c");
		    
		    users = (Users) session.getAttribute("users");
			site = (Site) session.getAttribute("site");
			agences = (Agence) session.getAttribute("agence");
			if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			}
			if(crud.equals("ENREGISTRER")) {
				Roles roles = roleDI.getRole(site.getSite(),profil);
				if(roles == null) {
					role.setSite(agences.getCode());
					role.setProfil(profil);
					roleDI.creerRole(role, errorMsg);
				}
			}
			if(crud.equals("get")) {
				int idR = Integer.parseInt(id);
				role = roleDI.getRole(idR);
				request.setAttribute("role", role);
			}
			
			if(crud.equals("AJOUTER")) {
				Long idR = Long.parseLong(id);
				int idRo = Integer.parseInt(id);
																
				role.setAgence((agence != null?1:0));		
				role.setCode_site((code_site != null?1:0));
				role.setPorte((porte != null?1:0));
				role.setRapport((rapport != null?1:0));
				role.setRessourceH((ressourceH != null?1:0));
				role.setGestionnaire((gestionnaire != null?1:0));
				role.setLocataire((locataire != null?1:0));
				role.setProprietaire((proprietaire != null?1:0));
				role.setComptabilite((comptabilite != null?1:0));
				role.setPaiementL((paiementL != null?1:0));				
				role.setChargeE((chargeE != null?1:0));	
				
				role.setParametre((parametre != null?1:0));
				role.setUtilisateur((utilisateur != null?1:0));
				role.setProfile((profile != null?1:0));									
				role.setVille((ville != null?1:0));
				role.setCommune((commune != null?1:0));
				role.setQuartier((quartier != null?1:0));		
				role.setPays((pays != null?1:0));
				role.setPourcentage((pourcentage != null?1:0));
				role.setService((service != null?1:0));
				role.setRole((roleS != null?1:0));			
				role.setAccueil((accueil != null?1:0));
			
				/* ==== LES ACCES ==== */
				role.setAjout_AG((ajout_AG != null?1:0));
				role.setModif_AG((modif_AG != null?1:0));
				role.setSupp_AG((supp_AG != null?1:0));				
				role.setAjout_SI((ajout_SI != null?1:0));		
				role.setModif_SI((modif_SI != null?1:0));
				role.setSupp_SI((supp_SI != null?1:0));
				role.setAjout_AP((ajout_AP != null?1:0));
				role.setModif_AP((modif_AP != null?1:0));
				role.setSupp_AP((supp_AP != null?1:0));
				role.setAjout_GE((ajout_GE != null?1:0));
				role.setModif_GE((modif_GE != null?1:0));
				role.setSupp_GE((supp_GE != null?1:0));
				role.setAjout_LO((ajout_LO != null?1:0));
				role.setModif_LO((modif_LO != null?1:0));		
				role.setSupp_LO((supp_LO != null?1:0));
				role.setAjout_PR((ajout_PR != null?1:0));
				role.setModif_PR((modif_PR != null?1:0));
				role.setSupp_PR((supp_PR != null?1:0));
				role.setAjout_PL((ajout_PL != null?1:0));
				role.setSupp_PL((supp_PL != null?1:0));
				
				/* ==== LES PARAMETRES ==== */
				role.setAjout_UT((ajout_UT  != null?1:0));
				role.setModif_UT((modif_UT  != null?1:0));
				role.setSupp_UT((supp_UT  != null?1:0));				
				role.setAjout_SV((ajout_SV  != null?1:0));		
				role.setModif_SV((modif_SV  != null?1:0));
				role.setSupp_SV((supp_SV  != null?1:0));
				role.setAjout_PO((ajout_PO  != null?1:0));
				role.setModif_PO((modif_PO  != null?1:0));
				role.setSupp_PO((supp_PO  != null?1:0));
						
				/* ==== LES RAPPORTS ==== */
				role.setCoutM((coutM != null?1:0));
				role.setEtat_FL((etat_FL	 != null?1:0));
				role.setEtat_FS((etat_FS != null?1:0));
				role.setEtat_FA((etat_FA != null?1:0));
				role.setEtat_FM((etat_FM	 != null?1:0));
				role.setFiche_ID((fiche_ID != null?1:0));
				role.setTableau_SU((tableau_SU != null?1:0));
				role.setEtat_LI((etat_LI	 != null?1:0));
				role.setBilan_CH((bilan_CH != null?1:0));
				role.setEtat_FAP((etat_FAP != null?1:0));
				role.setEtat_FMP((etat_FMP != null?1:0));		
				role.setEtat_FAG((etat_FAG	 != null?1:0));
				role.setEtat_FMG((etat_FMG != null?1:0));
				role.setEtat_FIL((etat_FIL	 != null?1:0));
				role.setCommissionE((commissionE != null?1:0));
				
				/* ==== LES NEW ==== */
				role.setPartenaire((partenaire != null?1:0));
				role.setService_tech((service_tech != null?1:0));
				role.setG_caution((g_caution != null?1:0));
				role.setArchive((archive != null?1:0));
				
				/* ==== LES NEW MENU ==== */
				role.setArchive_cli((archive_cli!= null?1:0));
				role.setClient((client!= null?1:0));
				role.setAjout_cli((ajout_cli!= null?1:0));
				role.setModif_cli((modif_cli!= null?1:0));
				role.setSupp_cli((supp_cli!= null?1:0));
				role.setDemarcheur((demarcheur != null?1:0));
				role.setAjout_dem((ajout_dem!= null?1:0));
				role.setModif_dem((modif_dem	!= null?1:0));
				role.setSupp_dem((supp_dem!= null?1:0));
				role.setReception_cli((reception_cli!= null?1:0));		
				role.setAjout_recli((ajout_recli!= null?1:0));
				role.setModif_recli((modif_recli!= null?1:0));
				role.setSupp_recli((supp_recli!= null?1:0));
				role.setNotification((notification!= null?1:0));
				role.setArriere((arriere!= null?1:0));
				role.setAjout_ari((ajout_ari != null?1:0));
				role.setModif_ari((modif_ari!= null?1:0));
				role.setSupp_ari((supp_ari!= null?1:0));
				role.setBien_meuble((bien_meuble!= null?1:0));
				role.setBien((bien!= null?1:0));		
				role.setAjout_b((ajout_b!= null?1:0));
				role.setModif_b((modif_b!= null?1:0));
				role.setSupp_b((supp_b!= null?1:0));
				role.setPaiement_bm((paiement_bm!= null?1:0));
				role.setGestion_service((gestion_service!= null?1:0));
				role.setRapport_c((rapport_c!= null?1:0));
				
				
				role.setId(idR);
				
				roleDI.modifierRole(role, errorMsg);
				
				role = roleDI.getRole(idRo);
				request.setAttribute("role", role);
			}
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(PROFIL).forward(request, response);
		}
}
