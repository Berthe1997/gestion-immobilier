package com.news.controllers_M;

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
import com.news.beans.Compte;
import com.news.beans.Operation;
import com.news.beans.OuvertureFermetureCaisse;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.beans_M.Bien_meuble;
import com.news.beans_M.Client_bien;
import com.news.beans_M.Reservation;
import com.news.controllers.Message;
import com.news.dao.CompteDI;
import com.news.dao.OperationDI;
import com.news.dao.OuvertureFermetureCaisseDI;
import com.news.dao_M.Bien_meubleDI;
import com.news.dao_M.Client_bienDI;
import com.news.dao_M.ReservationDI;
import com.news.fonctions.MiseAJourSomme;
import com.news.fonctions.Notifications;
import com.news.fonctions.VerifieSession;

@WebServlet("/reserve")
public class CrudReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/bien_M/view_clientB.jsp";
	public static final String IMMOBILIER_R = "/WEB-INF/bien_M/reservation.jsp";
	private static final String COMPTABILITE = "/WEB-INF/views/comptabilite.jsp";
	private static final String IMMOBILIER_D = "/WEB-INF/bien_M/view_reservation.jsp";
	
	private static final String FORMAT_DATES = "HH:mm:ss";
	private static final String FORMAT_DATE = "yyy-MM-dd";

	Client_bienDI client_bienDI= new Client_bienDI();
	Client_bien client_bien= new Client_bien();
	
	 ReservationDI reservationDI= new ReservationDI();
	 Reservation reservation= new Reservation();
	 
	 Bien_meubleDI bien_meubleDI= new Bien_meubleDI();
	 Bien_meuble bien_meuble= new Bien_meuble();
	 
	 OuvertureFermetureCaisseDI ouvFerCaisseDI	=	new OuvertureFermetureCaisseDI();
	 OuvertureFermetureCaisse ouvFerCaisse = new OuvertureFermetureCaisse();
	 
	 OperationDI operationDI = new OperationDI();
     Operation operation = new Operation();
	 
	 CompteDI compteDI = new CompteDI();
	 Compte compte = new Compte();
	
	Agence agence = new Agence();
	Site site = new Site();
	Users users	=	new Users();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String crud	=	request.getParameter("crud");
		String page = request.getParameter("menu");		
		String choix = request.getParameter("ch");
		String id	=	request.getParameter("id");
		String bien	=	request.getParameter("bien");
		String code	=	request.getParameter("code");
			
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
			
			DateTime dt = new DateTime();
			org.joda.time.format.DateTimeFormatter formattere = DateTimeFormat.forPattern(FORMAT_DATE);
			String dating = dt.toString(formattere);
			
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
				reservation = reservationDI.getReservation(code);
				request.setAttribute("reservation", reservation);
				
				bien_meuble = bien_meubleDI.getBien_meuble(bien);
				request.setAttribute("bien_meuble", bien_meuble);				
			}
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER_R).forward(request, response);
		}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		String page = request.getParameter("page");
		String crud = request.getParameter("crud");
		String bien = request.getParameter("bien");
		String client = request.getParameter("client");
		String dateE = request.getParameter("dateE");
		String dateS = request.getParameter("dateS");
		String nombre = request.getParameter("nombre");
		String loyerN = request.getParameter("loyerN");
		String montant_net = request.getParameter("montant_net");
		String reduction = request.getParameter("reduction");
		String mode= request.getParameter("mode");
		String code_R= request.getParameter("code_R");
		
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
		
		//Notifications notifications = new Notifications();
		
		users = (Users) session.getAttribute("users");
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
		DateTime dt = new DateTime();
		org.joda.time.format.DateTimeFormatter formattere = DateTimeFormat.forPattern(FORMAT_DATE);
		String dating = dt.toString(formattere);
		
		DateTime dts = new DateTime();
		org.joda.time.format.DateTimeFormatter formatterss = DateTimeFormat.forPattern(FORMAT_DATES);
		String datings = dts.toString(formatterss);
		
																							
		List<OuvertureFermetureCaisse> ouvFerCaisses	=	ouvFerCaisseDI.getOuvA(agence.getCode(), users.getEmail(),dating );
		String codeCaisse = "";
		String mailUser = "";
		for(OuvertureFermetureCaisse ouvF : ouvFerCaisses) {  
			codeCaisse = ouvF.getCodeCaisse();
			mailUser = ouvF.getUserName();
		}
						
		if(crud.equals("ENREGISTRER")) {
			
			String taux	=	request.getParameter("taux");	
			int T =0;
			T= Integer.parseInt(taux);
			int	PAR = 100, resultAR=0;
			resultAR= PAR - T;
			
			reservation.setReduction(Integer.parseInt(reduction));
			reservation.setCommissionA((Integer.parseInt(montant_net) * T) /100);
			reservation.setMontant_P((Integer.parseInt(montant_net) * resultAR) /100);
			reservation.setMontant_net(Integer.parseInt(montant_net));
			reservation.setMode(mode);
			reservation.setDate_A(dating);
			reservation.setHeure(datings);
			reservation.setCode_reservation(code_R);
			
			
			//++++++++++++++++++++++++++++++OPERATION++++++++++++++++++++++++++++++;		
			operation.setCode(agence.getCode());
			operation.setSite(site.getSite() );
			operation.setProprietaire(site.getMatricule());
			operation.setMatricule(client);
			operation.setNom_prenom(mailUser );
			operation.setType("reservation" );				
			operation.setApprovisonnement(Integer.parseInt(montant_net) );
			operation.setMontantA((Integer.parseInt(montant_net) * T) /100);
			//operation.setMontantI((Integer.parseInt(montantAR) * TIR) /100);
			operation.setMontantP((Integer.parseInt(montant_net) * resultAR) /100);
			operation.setDescription("Paiement"+" "+"bien Meublée"+" "+site.getSite());
			operation.setDate(dating );
			operation.setHeure(datings);
			operation.setCaisse(codeCaisse);						
			operation.setTaux(Integer.parseInt(taux));
									
			//++++++++++++++++++++++++++++++OPERATION++++++++++++++++++++++++++++++;					
			compte.setCode(agence.getCode());
			Compte compteG = compteDI.getCompte(site.getCode(),site.getSite());
			if(compteG != null) {
				compte.setCode(agence.getCode());
				compte.setSite(site.getSite() );
				compte.setMatricule(site.getMatricule());
				compte.setSoldeP((Integer.parseInt(montant_net) * resultAR) /100 + compteG.getSoldeP());
				compte.setSoldeA((Integer.parseInt(montant_net) * T) /100 + compteG.getSoldeA());	
				compte.setSoldeI(compteG.getSoldeI());
			
			 }
		
		MiseAJourSomme miseSomme = new MiseAJourSomme();
		miseSomme.LesSomme(request);
								          	 
		if(reservationDI.modifierReservationR(reservation, errorMsg)) {							
			request.setAttribute("message", new Message("Paiement Reservation enregistré avec succès."+ errorMsg.get(), 0, "green"));
			operationDI.creerOperation(operation, errorMsg); 
			compteDI.modifierCompte(compte, errorMsg);
								
		}else request.setAttribute("message", new Message("Echec enregistrement Paiement Reservation. " + errorMsg.get(), 0, "red") );										
		//notifications.reservation(request, response);
		//notifications.reservationP(request, response);
				
		this.getServletContext().getRequestDispatcher(IMMOBILIER_D).forward(request, response);
		
		request.setAttribute("page", page);
			
		}
		
		
		String codeR = bien+"_R";
		if(crud.equals("RESERVER")) {
			client_bien = client_bienDI.getClient_bien(client);
			
			reservation.setAgence(agence.getCode());
			reservation.setCode_bien(bien);
			reservation.setCode_reservation(codeR);
			reservation.setClient(client);
			reservation.setDate_entree(dateE);
			reservation.setDate_sortie(dateS);			
			reservation.setMontant_T(Integer.parseInt(loyerN)* Integer.parseInt(nombre));						
			reservation.setDate_A(dateE);
			reservation.setSite(site.getSite());
			reservation.setNombre(Integer.parseInt(nombre));
			
			bien_meuble.setStatut("occupe");
			bien_meuble.setBien(bien);
			
			client_bien.setBien(bien);
			client_bien.setMatricule(client);
			
			if(reservationDI.creerReservation(reservation, errorMsg)) {					
				bien_meubleDI.modifierBien_meubleS(bien_meuble, errorMsg);	
				client_bienDI.modifierClient_bienM(client_bien, errorMsg);
				request.setAttribute("message", new Message("Reservation enregistré avec succès."+ errorMsg.get(), 0, "green"));
			} else request.setAttribute("message", new Message("Echec enregistrement Reservation. " + errorMsg.get(), 0, "red") );
		                                      
            request.setAttribute("client_bien", client_bien);
			
            
            request.setAttribute("page", page);
    		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}
						
	}

}
