package com.news.controllers;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.beans.Agence;
import com.news.beans.Caisse;
import com.news.beans.OuvertureFermetureCaisse;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.CaisseDI;
import com.news.dao.OuvertureFermetureCaisseDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/CRUDOuvFer")
public class CRUDOuvertureFermeture extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	private static final String OUV_CAISSE = "/WEB-INF/views/ouvertureCaisse.jsp";
	//private static final String FER_CAISSE = "/WEB-INF/views/fermetureCaisse.jsp";
	public static final String COMPTABILITE = "/WEB-INF/views/comptabilite.jsp";
	
	
	OuvertureFermetureCaisseDI ouvFerCaisseDI	=	new OuvertureFermetureCaisseDI();
	CaisseDI caisseDI = new CaisseDI();
	
	Users users	=	new Users();
	Site site = new Site();
	Agence agence = new Agence();
	OuvertureFermetureCaisse ouvFerCaisse = new OuvertureFermetureCaisse();
	Caisse caisse = new Caisse();
	
	private boolean ValidateByMinMAx(int minLen, int maxlen){
	    return (minLen<=maxlen)?true:false;
	}
	private boolean ValidateUserData(OuvertureFermetureCaisse ouvFer, AtomicReference<String> errorMsg){
		String retString="";
	    boolean b1=ValidateByMinMAx(ouvFer.getCashDispoOSession(),ouvFer.getCashDispoFSessionP());
	    if (!b1) retString+="Cash session précédente supérieur à cash ouverture.";
	    errorMsg.set(retString);
	    return b1;
	}
	VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String page = request.getParameter("page");
		String crud = request.getParameter("cruds");
		String libelleC = request.getParameter("libelleC");
		
		users = (Users) session.getAttribute("users");
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		}
		
		
		if ("ouvertureCaisse".equals(page)) {
			if(crud.equals("tester")) {
				JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
				JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
				
				List<OuvertureFermetureCaisse> ouvFerCaisses	=	ouvFerCaisseDI.getOuv(agence.getCode(), site.getSite(), libelleC);
				int tail = ouvFerCaisses.size();
				if(tail != 0) {
					for(OuvertureFermetureCaisse ouvF : ouvFerCaisses) {
						objectBuilder.add("montant", ouvF.getCashReel());
					}
				} else {
					objectBuilder.add("montant", 0);
				}
				
				arrayBuilder.add(objectBuilder);
				
				response.setContentType("application/json; charset=utf8");
				response.getWriter().write(arrayBuilder.build().toString());
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String page = request.getParameter("page");
		String crud = request.getParameter("cruds");
		String id = request.getParameter("id");
		String nomCaissier = request.getParameter("email");
		String libelleC = request.getParameter("libelleC");
		String cashF = request.getParameter("cashF");
		String cashO = request.getParameter("cashO");
		String difference = request.getParameter("difference");
		String dateO = request.getParameter("dateO");
		String codeCaisse = request.getParameter("codeCaisse");
		String cashReel = request.getParameter("cashReel");
		String differencef = request.getParameter("differencef");
		String dateF = request.getParameter("dateF");
		
		 AtomicReference<String> errorMsg = new  AtomicReference<>("");
		
		 users = (Users) session.getAttribute("users");
	     site = (Site) session.getAttribute("site");
	     agence = (Agence) session.getAttribute("agence");
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		}
		
			
		if (page.equals("ouvertureCaisse")) {
			if(crud.contentEquals("OUVRIR")) {
				Boolean verifie = ouvFerCaisseDI.getOuvFerVerifier(agence.getCode(),site.getSite(), libelleC, dateO);
				if(verifie == false) {
					int cashSessionF	=	Integer.parseInt(cashF);
					int cashSessionO	=	Integer.parseInt(cashO);
					int differenceO	=	Integer.parseInt(difference);
					
					ouvFerCaisse.setCode(agence.getCode());
					ouvFerCaisse.setSite(site.getSite());
					ouvFerCaisse.setUserName(nomCaissier);
					ouvFerCaisse.setCodeCaisse(libelleC);
					ouvFerCaisse.setCashDispoFSessionP(cashSessionF);
					ouvFerCaisse.setCashDispoOSession(cashSessionO);
					ouvFerCaisse.setDifferenceMontantO(differenceO);
					ouvFerCaisse.setCashCalcule(cashSessionO);
					ouvFerCaisse.setDateOuverture(dateO);
					ouvFerCaisse.setOuvert(1);
					
					if (!ValidateUserData(ouvFerCaisse, errorMsg) ) {
						request.setAttribute("message", new Message("La caisse n'a pas été ouverte avec succès. Validation erreur."+ errorMsg.get(), 0, "red")  );
						request.setAttribute("page", page);
						this.getServletContext().getRequestDispatcher(OUV_CAISSE).forward(request, response);
					    return;
					 }
					
					if(ouvFerCaisseDI.creerOuv(ouvFerCaisse, errorMsg)) {
						List<OuvertureFermetureCaisse> ouvFerCaisses	=	ouvFerCaisseDI.getOuvA(agence.getCode(), nomCaissier,dateO );
						int ouvert = 0, fermer = 0;
						for(OuvertureFermetureCaisse ouvF : ouvFerCaisses) {
							if(ouvF.getOuvert() == 1) {ouvert = ouvF.getOuvert();}
							if(ouvF.getFermer() == 1) {fermer = ouvF.getFermer();}
						}
						
						caisse.setOf(Integer.parseInt("1"));
						caisse.setCodeCaisse(libelleC);
						caisseDI.modifierO(caisse, errorMsg);
						
						request.setAttribute("message", new Message("La caisse a été ouverte avec succès."+ errorMsg.get(), 0, "green"));
						request.setAttribute("ouvFerCaisse", ouvFerCaisse);
						request.setAttribute("ouvert", ouvert);
						request.setAttribute("fermer", fermer);
						request.setAttribute("page", "comptabilite");
						this.getServletContext().getRequestDispatcher(COMPTABILITE).forward(request, response);
						return;
					}else request.setAttribute("message", new Message("Echec ouverture caisse. " + errorMsg.get(), 0, "red") );
				} else {
					request.setAttribute("message", new Message("Cette caisse est déjà ouverte.", 0, "red"));
				}
				
			}
			if (crud.equals("tester")) {
				JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
				JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
				
				List<OuvertureFermetureCaisse> ouvFerCaisses	=	ouvFerCaisseDI.getOuv(agence.getCode(),site.getSite(), libelleC);
				
				for(OuvertureFermetureCaisse ouvF : ouvFerCaisses) {
					objectBuilder.add("montant", ouvF.getCashDispoFSessionP());
				}
				
				arrayBuilder.add(objectBuilder);
				
				response.setContentType("application/json; charset=utf8");
				response.getWriter().write(arrayBuilder.build().toString());
			}
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(OUV_CAISSE).forward(request, response);
		}
		if (page.equals("fermetureCaisse")) {
			Long idf = Long.parseLong(id);
			int cashRe	=	Integer.parseInt(cashReel);
			int differenceF	=	Integer.parseInt(differencef);
			
			ouvFerCaisse.setCashReel(cashRe);
			ouvFerCaisse.setDifferenceMontantF(differenceF);
			ouvFerCaisse.setDateFermeture(dateF);
			ouvFerCaisse.setFermer(1);
			ouvFerCaisse.setId(idf);
			if(ouvFerCaisseDI.modifierOuvFer(ouvFerCaisse, errorMsg)) {
				
				caisse.setOf(0);
				caisse.setCodeCaisse(codeCaisse);
				caisseDI.modifierO(caisse, errorMsg);
				
				List<OuvertureFermetureCaisse> ouvFerCaisses	=	ouvFerCaisseDI.getOuvA(agence.getCode(), nomCaissier,dateF );
				
				for(OuvertureFermetureCaisse ouvF : ouvFerCaisses) {
					int ferme = ouvF.getFermer();
					request.setAttribute("ferme", ferme);
				}
				
				 request.setAttribute("message", new Message("La caisse a été fermé avec succès."+ errorMsg.get(), 0, "green"));
				 request.setAttribute("ouvFerCaisse", ouvFerCaisse);
			}else request.setAttribute("message", new Message("Echec fermeture caisse. " + errorMsg.get(), 0, "red") );
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(COMPTABILITE).forward(request, response);
		}
	}

}
