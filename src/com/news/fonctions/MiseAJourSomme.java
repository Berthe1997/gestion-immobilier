package com.news.fonctions;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.news.beans.Agence;
import com.news.beans.Operation;
import com.news.beans.OuvertureFermetureCaisse;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.OperationDI;
import com.news.dao.OuvertureFermetureCaisseDI;
import com.news.dao.Paiement_loyerDI;


public class MiseAJourSomme {

	private static final String FORMAT_DATE = "yyy-MM-dd";
	
	public MiseAJourSomme() {}
	
	Paiement_loyerDI paiement_loyerDI = new Paiement_loyerDI();
	OuvertureFermetureCaisseDI ouvFerCaisseDI	=	new OuvertureFermetureCaisseDI();
	OperationDI operationDI = new OperationDI();
	
	Users users	=	new Users();
	Site site = new Site();
	Agence agence = new Agence();
	
	OuvertureFermetureCaisse ouvFerCaisse = new OuvertureFermetureCaisse();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	
	public void LesSomme(HttpServletRequest req) {
		HttpSession session = req.getSession();
		//String id = req.getParameter("id");
		
		DateTime dt = new DateTime();
		DateTimeFormatter formatter = DateTimeFormat.forPattern(FORMAT_DATE);
		String dating = dt.toString(formatter);
		
		users = (Users) session.getAttribute("users");
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
		List<OuvertureFermetureCaisse> ouvFerCaisses	=	ouvFerCaisseDI.getOuvA(agence.getCode(), users.getEmail(),dating );
		String caisse = "";
		int decaisse = 0, cashDispO = 0;
		Long idOuvF = null;
		for(OuvertureFermetureCaisse ouvF : ouvFerCaisses) {
			caisse = ouvF.getCodeCaisse();
			decaisse = ouvF.getMontantDecaisse();
			cashDispO = ouvF.getCashDispoOSession();
			idOuvF = ouvF.getId();
		}
		
		
  // List<Paiement_loyer> listPaieT	=	paiement_loyerDI.getAllPaiement_loyer(agence.getCode(), site.getSite(),dating,caisse);
   
   List<Operation> listPaieT	=	operationDI.getAllOperation(agence.getCode(),dating,caisse);
		
		int encaisse = 0;
		for(Operation paie : listPaieT) {
			encaisse = paie.getApprovisonnement();
		}
		
		
		
		int cashCalcule = (cashDispO + encaisse) - decaisse;
		
		ouvFerCaisse.setMontantEncaisse(encaisse);
		ouvFerCaisse.setMontantDecaisse(decaisse);
		ouvFerCaisse.setCashCalcule(cashCalcule);
		ouvFerCaisse.setId(idOuvF);
		if(idOuvF!=null) {ouvFerCaisseDI.modifierEncaisseDecaisse(ouvFerCaisse, errorMsg);}
	}
	
	public void LesSommes(HttpServletRequest req,Long id) {
		HttpSession session = req.getSession();
		
		DateTime dt = new DateTime();
		DateTimeFormatter formatter = DateTimeFormat.forPattern(FORMAT_DATE);
		String dating = dt.toString(formatter);
		
		users = (Users) session.getAttribute("users");
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
		List<OuvertureFermetureCaisse> ouvFerCaisses	=	ouvFerCaisseDI.getOuvA(agence.getCode(), users.getEmail(),dating );
		String caisse = "";
		int decaisse = 0, cashDispO = 0;
		Long idOuvF = null;
		for(OuvertureFermetureCaisse ouvF : ouvFerCaisses) {
			caisse = ouvF.getCodeCaisse();
			decaisse = ouvF.getMontantDecaisse();
			cashDispO = ouvF.getCashDispoOSession();
			idOuvF = ouvF.getId();
		}
		
		
	//	List<Paiement_loyer> listPaieT	=	paiement_loyerDI.getAllPaiement_loyer(agence.getCode(), site.getSite(),dating,caisse);
		
		 List<Operation> listPaieT	=	operationDI.getAllOperation(agence.getCode(),dating,caisse);
			
			int encaisse = 0;
			for(Operation paie : listPaieT) {
				encaisse = paie.getApprovisonnement();
			}
				
		int cashCalcule = (cashDispO + encaisse) - decaisse;
		
		ouvFerCaisse.setMontantEncaisse(encaisse);
		ouvFerCaisse.setMontantDecaisse(decaisse);
		ouvFerCaisse.setCashCalcule(cashCalcule);
		ouvFerCaisse.setId(idOuvF);
		if(idOuvF!=null) {ouvFerCaisseDI.modifierEncaisseDecaisse(ouvFerCaisse, errorMsg);} 
	}

}
