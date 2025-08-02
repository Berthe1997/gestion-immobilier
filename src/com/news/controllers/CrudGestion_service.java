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
import org.joda.time.format.DateTimeFormatter;

import com.news.beans.Agence;
import com.news.beans.Compte;
import com.news.beans.Gestion_service;
import com.news.beans.Gestionnaire;
import com.news.beans.Locataire;
import com.news.beans.Operation;
import com.news.beans.OuvertureFermetureCaisse;
import com.news.beans.Reception_client;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.CompteDI;
import com.news.dao.Gestion_serviceDI;
import com.news.dao.GestionnaireDI;
import com.news.dao.LocataireDI;
import com.news.dao.OperationDI;
import com.news.dao.OuvertureFermetureCaisseDI;
import com.news.dao.Reception_clientDI;
import com.news.fonctions.MiseAJourSomme;
import com.news.fonctions.Notifications;
import com.news.fonctions.VerifieSession;

@WebServlet("/Gestion_S")
public class CrudGestion_service extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	private static final String VISITE = "/WEB-INF/views/gestion_service.jsp";
	private static final String FORMAT_DATE = "yyy-MM-dd";
	private static final String FORMAT_DATES = "HH:mm:ss";
	
	Site site = new Site();
	Agence agence = new Agence();
	Users users	=	new Users();

	OperationDI operationDI = new OperationDI();
	Operation operation = new Operation();
	
	Gestion_service gestion_service = new Gestion_service();
	Gestion_serviceDI gestion_serviceDI = new Gestion_serviceDI();
	
	 OuvertureFermetureCaisseDI ouvFerCaisseDI	=	new OuvertureFermetureCaisseDI();
	 OuvertureFermetureCaisse ouvFerCaisse = new OuvertureFermetureCaisse();
	 
	 CompteDI compteDI = new CompteDI();
	 Compte compte = new Compte();
	 
	 GestionnaireDI gestionnaireDI = new GestionnaireDI();
	 Gestionnaire gestionnaire = new Gestionnaire();
	 
	 LocataireDI locataireDI = new LocataireDI();
	 Locataire locataire = new Locataire();
	 
	 Reception_clientDI reception_clientDI = new Reception_clientDI();
	 Reception_client reception_client = new Reception_client();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
		String type	=	request.getParameter("type");		
		String date	=	request.getParameter("date");
		String heure	=	request.getParameter("heure");
		String matricule	=	request.getParameter("matricule");
		String montant	=	request.getParameter("montant");
		
		agence = (Agence) session.getAttribute("agence");
		site = (Site) session.getAttribute("site");
		
			if(verifieSess.sessions(request, response) == false) {
		response.sendRedirect( request.getContextPath() + INDEX );
		return;
	} 
			
			if(crud.equals("id")) {
				
				gestion_service = gestion_serviceDI.getGestion_service(Integer.parseInt(id));
				gestion_service.setId(Long.parseLong(id));
				gestion_serviceDI.supprimerGestion_service(gestion_service, errorMsg);
				
				operation.setCode(agence.getCode());
				operation.setSite(site.getSite() );
				operation.setType(type);	
				operation.setDate(date);
				operation.setHeure(heure);
				operationDI.supprimerOperationVM(operation, errorMsg);
				
				Compte compteG = compteDI.getCompte(site.getCode(),site.getSite());
				if(compteG != null) {
					compte.setCode(agence.getCode());
					compte.setSite(site.getSite() );
					compte.setMatricule(site.getMatricule());
					compte.setSoldeA(compteG.getSoldeA()-Integer.parseInt(montant) );	
					compte.setSoldeI(compteG.getSoldeI());	
					compte.setSoldeP(compteG.getSoldeP());	
				}
				compteDI.modifierCompte(compte, errorMsg);
				
				reception_client.setStatutP(0);
				reception_client.setMatricule(matricule);
				reception_clientDI.modifierReception_clientS(reception_client, errorMsg);
			}
		
		
		
		request.setAttribute("page", page);

		this.getServletContext().getRequestDispatcher(VISITE).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
	//	String id	=	request.getParameter("id");
		String agent	=	request.getParameter("agent");			
		String nom	=	request.getParameter("nom");
		String service	=	request.getParameter("service");
		String type	=	request.getParameter("type");		
		String montant	=	request.getParameter("montant");		
		String montantS	=	request.getParameter("montantS");
		String date	=	request.getParameter("date");
		
		
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
		DateTimeFormatter formatters = DateTimeFormat.forPattern(FORMAT_DATES);
		String datings = dts.toString(formatters);
		
		List<OuvertureFermetureCaisse> ouvFerCaisses	=	ouvFerCaisseDI.getOuvA(agence.getCode(), users.getEmail(),dating );
		String codeCaisse = "";
		String mailUser = "";
		for(OuvertureFermetureCaisse ouvF : ouvFerCaisses) {  
			codeCaisse = ouvF.getCodeCaisse();
			mailUser = ouvF.getUserName();
		}
		
		Notifications notifications = new Notifications();
		
		Locataire NPL = locataireDI.getLocataire(nom);		
	if(crud.equals("SERVICE")) {
				
		gestion_service.setCode(agence.getCode());
		gestion_service.setSite(site.getSite());
		if(NPL != null) {
			gestion_service.setAgent(NPL.getNom()+" "+NPL.getPrenom());
		}			
		gestion_service.setMatricule(nom);
		gestion_service.setService(service);
		gestion_service.setType(service);
		gestion_service.setMontant(Integer.parseInt(montantS));
		gestion_service.setDate(dating);
		gestion_service.setHeure(datings);
		

		//++++++++++++++++++++++++++++++OPERATION++++++++++++++++++++++++++++++;
		operation.setCode(agence.getCode());
		operation.setSite(site.getSite() );
		operation.setProprietaire(site.getMatricule());
		operation.setMatricule(nom);
		operation.setNom_prenom(mailUser );
		operation.setType(service);		
		operation.setApprovisonnement(Integer.parseInt(montantS));
		operation.setMontantAU(Integer.parseInt(montantS));
		operation.setMontantV(0);
		operation.setDescription("Paiement"+" visite "+type+" "+site.getSite());
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
			compte.setSoldeA(Integer.parseInt(montantS) );
			compteDI.creerCompte(compte, errorMsg); 
		}else {
			Compte compteG = compteDI.getCompte(site.getCode(),site.getSite());
			if(compteG != null) {
				compte.setCode(agence.getCode());
				compte.setSite(site.getSite() );
				compte.setMatricule(site.getMatricule());
				compte.setSoldeA(compteG.getSoldeA()+Integer.parseInt(montantS) );	
				compte.setSoldeI(compteG.getSoldeI());	
				compte.setSoldeP(compteG.getSoldeP());	
			}
			compteDI.modifierCompte(compte, errorMsg); 
		}
			
			if(gestion_serviceDI.creerGestion_service(gestion_service, errorMsg)) {			
				request.setAttribute("message", new Message("gestion_service enregistré avec succès."+ errorMsg.get(), 0, "green"));
			} else request.setAttribute("message", new Message("Echec enregistrement gestion_service. " + errorMsg.get(), 0, "red") );
		
			
			MiseAJourSomme miseSomme = new MiseAJourSomme();
			miseSomme.LesSomme(request);
	}
	
			
	Reception_client NP = reception_clientDI.getReception_client(agent);	
	if(crud.equals("VISITE")) {
	
		
		gestion_service.setCode(agence.getCode());
		gestion_service.setSite(site.getSite());
		if(NP != null) {
		gestion_service.setAgent(NP.getNom());
		}		
		gestion_service.setMatricule(agent);
		gestion_service.setService(service);
		gestion_service.setType(type);
		gestion_service.setMontant(Integer.parseInt(montant));
		gestion_service.setDate(date);
		gestion_service.setHeure(datings);
		

		//++++++++++++++++++++++++++++++OPERATION++++++++++++++++++++++++++++++;
		operation.setCode(agence.getCode());
		operation.setSite(site.getSite() );
		operation.setProprietaire(site.getMatricule());
		operation.setMatricule(agent);
		operation.setNom_prenom(mailUser);
		operation.setType(service);		
		operation.setApprovisonnement(Integer.parseInt(montant));
		operation.setMontantV(Integer.parseInt(montant));
		operation.setMontantAU(0);
		operation.setDescription("Paiement"+" visite "+type+" "+site.getSite());
		operation.setDate(date);
		operation.setCaisse(codeCaisse);
		operation.setHeure(datings);
		operation.setMois(" ");
		operationDI.creerOperation(operation, errorMsg); 
		
 //++++++++++++++++++++++++++++++COMPTE++++++++++++++++++++++++++++++;	
		if(compteDI.getVerifierCompte(site.getCode(),site.getSite()) == false) {
			compte.setCode(agence.getCode());
			compte.setSite(site.getSite() );
			compte.setMatricule(site.getMatricule());
			compte.setSoldeA(Integer.parseInt(montant) );
			compteDI.creerCompte(compte, errorMsg); 
		}else {
			Compte compteG = compteDI.getCompte(site.getCode(),site.getSite());
			if(compteG != null) {
				compte.setCode(agence.getCode());
				compte.setSite(site.getSite() );
				compte.setMatricule(site.getMatricule());
				compte.setSoldeA(compteG.getSoldeA()+Integer.parseInt(montant) );
				compte.setSoldeI(compteG.getSoldeI());	
				compte.setSoldeP(compteG.getSoldeP());	
			}
			compteDI.modifierCompte(compte, errorMsg); 
		}
			
			if(gestion_serviceDI.creerGestion_service(gestion_service, errorMsg)) {														
				request.setAttribute("message", new Message("gestion_service enregistré avec succès."+ errorMsg.get(), 0, "green"));
			} else request.setAttribute("message", new Message("Echec enregistrement gestion_service. " + errorMsg.get(), 0, "red") );
		
			reception_client.setStatutP(1);
			reception_client.setMatricule(agent);
			reception_clientDI.modifierReception_clientS(reception_client, errorMsg);
			
			MiseAJourSomme miseSomme = new MiseAJourSomme();
			miseSomme.LesSomme(request);
			
			if(NP.getStatut().equals("Externe")) {
				//notifications.reception_cliEX(request, response);
				//notifications.reception_cli(request, response);
			}
			
          if(NP.getStatut().equals("Interne")) {
				//notifications.reception_cli(request, response);
			}
	}
	
		
	request.setAttribute("page", page);
	this.getServletContext().getRequestDispatcher(VISITE).forward(request, response);
 } 
}
