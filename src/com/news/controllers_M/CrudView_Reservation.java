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
import com.news.fonctions.VerifieSession;

@WebServlet("/reserve_R")
public class CrudView_Reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/bien_M/view_clientB.jsp";
	public static final String IMMOBILIER_R = "/WEB-INF/bien_M/reservation.jsp";	
	private static final String IMMOBILIER_D = "/WEB-INF/bien_M/view_reservation.jsp";
	
	
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
		String code	=	request.getParameter("code");
		String matricule	=	request.getParameter("matricule");
		
		String type= "reservation";
		String date= request.getParameter("date");
		String heure= request.getParameter("heure");
		
		String soldeP= request.getParameter("soldeP");
		String soldeA= request.getParameter("soldeA");
		
		
		users = (Users) session.getAttribute("users");
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
			
			if("PaiL".equals(crud)) {
				
				reservation.setReduction(0);
				reservation.setCommissionA(0);
				reservation.setMontant_P(0);
				reservation.setMontant_net(0);
				reservation.setMode("");
				reservation.setCode_reservation(code);
				
				
				//================OPERATION=================
				operation.setCode(agence.getCode());
				operation.setSite(site.getSite() );					
				operation.setMatricule(matricule);
				operation.setType(type);				
				operation.setDate(date);
				operation.setHeure(heure);
				
				
				
				Compte compteG = compteDI.getCompte(site.getCode(),site.getSite());
				if(compteG != null) {
					compte.setCode(agence.getCode());
					compte.setSite(site.getSite() );
					compte.setMatricule(site.getMatricule());
					compte.setSoldeP(compteG.getSoldeP()-Integer.parseInt(soldeP) );	
					compte.setSoldeA(compteG.getSoldeA()-Integer.parseInt(soldeA));	
					compte.setSoldeI(compteG.getSoldeI());
				}
				
				
				if(reservationDI.modifierReservationR(reservation, errorMsg)) {							
					request.setAttribute("message", new Message("Paiement Reservation Suppression avec succès."+ errorMsg.get(), 0, "green"));
					operationDI.supprimerOperationR(operation, errorMsg);
					compteDI.modifierCompte(compte, errorMsg);										
				}else request.setAttribute("message", new Message("Echec Suppression Paiement Reservation. " + errorMsg.get(), 0, "red") );	
				
				
				MiseAJourSomme miseSomme = new MiseAJourSomme();
				miseSomme.LesSomme(request);
				
			}
			
			List<Operation> listPO = operationDI.getAllOperation(site.getSite(),matricule); 
			request.setAttribute("listPO", listPO);
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER_D).forward(request, response);
		}
}
