package com.news.controllers_GT;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.beans.Agence;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.beans_GT.Dossier_client;
import com.news.controllers.Message;
import com.news.dao_GT.Dossier_clientDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/dossier_cl")
public class CrudDossier_cl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/gestion_T/dossier_cl.jsp";

	//private static final String FORMAT_DATE = "yyy-MM-dd";
	Dossier_clientDI dossier_clientDI= new Dossier_clientDI();
	Dossier_client dossier_client= new Dossier_client();
	
	Agence agence = new Agence();
	Site site = new Site();
	Users users	=	new Users();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
		
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
			
						
			if(crud.equals("id")) {
				dossier_client = dossier_clientDI.getDossier_client(Integer.parseInt(id));
				dossier_client.setId(Long.parseLong(id));
				dossier_clientDI.supprimerDossier_client(dossier_client, errorMsg);
							        
			        request.setAttribute("page", page);
					this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
				}
			
			
				
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		String page = request.getParameter("page");	
		String crud = request.getParameter("crud");	
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");	
		String adresse = request.getParameter("adresse");
		String date = request.getParameter("date");
		String contact = request.getParameter("contact");
		String sexe = request.getParameter("sexe");
		//---------NEW CHAMPS----------------------//
		String date_naissance = request.getParameter("date_naissance");	
		String nationnalite = request.getParameter("nationnalite");
		String profession = request.getParameter("profession");		
		String depot_DATTC = request.getParameter("depot_DATTC");
		String dossier_techn_CT = request.getParameter("dossier_techn_CT");
		String dossier_techn_D = request.getParameter("dossier_techn_D");
		String attes_CD = request.getParameter("attes_CD");
		String intro_dem_ACD = request.getParameter("intro_dem_ACD");
		String date_BM = request.getParameter("date_BM");
		String n_DM = request.getParameter("n_DM");
		String trans_DM = request.getParameter("trans_DM");
		String bornage_cont = request.getParameter("bornage_cont");
		String transp_pv_bc = request.getParameter("transp_pv_bc");
		String creat_ACD = request.getParameter("creat_ACD");
		String notif_ACD = request.getParameter("notif_ACD");
		String pf_ACD = request.getParameter("pf_ACD");
		String ref_ACD = request.getParameter("ref_ACD");
		String situation_matr = request.getParameter("situation_matr");
		String creat_TF = request.getParameter("creat_TF");
		String trans_TF = request.getParameter("trans_TF");
		
		String lot = request.getParameter("lot");
		String ilot = request.getParameter("ilot");
		String lotissement = request.getParameter("lotissement");
		String date_achat = request.getParameter("date_achat");
		
		
											
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
		
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
							
		String matricule = agence.getCode()+"-"+dossier_clientDI.getLastIndex(agence.getCode())+".dcl";
		
		 if(crud.equals("AJOUTER")) {	
				
		    		
					if(dossier_clientDI.getDossier_clientVerifie(matricule) == false) {
						
						dossier_client.setNom(nom);
						dossier_client.setPrenom(prenom);
						dossier_client.setMatricule(matricule);
						dossier_client.setAdresse(adresse);						
						dossier_client.setDate(date);						
						dossier_client.setTel(contact);	
						dossier_client.setSexe(sexe);
						//-----------------------------NEW CHAMPS-----------------------------------//
						dossier_client.setDate_naissance(date_naissance);
						dossier_client.setNationnalite(nationnalite);
						dossier_client.setProfession(profession);					
						if(depot_DATTC != null) dossier_client.setDepot_DATTC(Integer.parseInt(depot_DATTC));
						else dossier_client.setDepot_DATTC(0);
						if(dossier_techn_CT != null) dossier_client.setDossier_techn_CT(Integer.parseInt(dossier_techn_CT));
						else dossier_client.setDossier_techn_CT(0);		
						if(dossier_techn_D != null) dossier_client.setDossier_techn_D(Integer.parseInt(dossier_techn_D));
						else dossier_client.setDossier_techn_D(0);				
						if(attes_CD != null) dossier_client.setAttes_CD(Integer.parseInt(attes_CD));
						else dossier_client.setAttes_CD(0);		
						if(intro_dem_ACD != null) dossier_client.setIntro_dem_ACD(Integer.parseInt(intro_dem_ACD));
						else dossier_client.setIntro_dem_ACD(0);		
						if(date_BM != null) dossier_client.setDate_BM(Integer.parseInt(date_BM));
						else dossier_client.setDate_BM(0);		
						if(n_DM != null) dossier_client.setN_DM(Integer.parseInt(n_DM));
						else dossier_client.setN_DM(0);		
						if(trans_DM != null) dossier_client.setTrans_DM(Integer.parseInt(trans_DM));
						else dossier_client.setTrans_DM(0);	
						if(bornage_cont != null) dossier_client.setBornage_cont(Integer.parseInt(bornage_cont));
						else dossier_client.setBornage_cont(0);		
						if(transp_pv_bc != null) dossier_client.setTransp_pv_bc(Integer.parseInt(transp_pv_bc));
						else dossier_client.setTransp_pv_bc(0);		
						if(creat_ACD != null) dossier_client.setCreat_ACD(Integer.parseInt(creat_ACD));
						else dossier_client.setCreat_ACD(0);		
						if(notif_ACD != null) dossier_client.setNotif_ACD(Integer.parseInt(notif_ACD));
						else dossier_client.setNotif_ACD(0);		
						if(pf_ACD != null) dossier_client.setPf_ACD(Integer.parseInt(pf_ACD));
						else dossier_client.setPf_ACD(0);
						if(ref_ACD != null) dossier_client.setRef_ACD(Integer.parseInt(ref_ACD));
						else dossier_client.setRef_ACD(0);
						dossier_client.setSituation_matr(situation_matr);
						if(creat_TF != null) dossier_client.setCreat_TF(Integer.parseInt(creat_TF));
						else dossier_client.setCreat_TF(0);				
						if(trans_TF != null) dossier_client.setTrans_TF(Integer.parseInt(trans_TF));
						else dossier_client.setTrans_TF(0);
						
						dossier_client.setTel(lot);
						dossier_client.setAdresse(ilot);
						dossier_client.setLotissement(lotissement);
						dossier_client.setDate_achat(date_achat);

						if(dossier_clientDI.creerDossier_client(dossier_client, errorMsg)) {								
							request.setAttribute("message", new Message("dossier_client enregistré avec succès."+ errorMsg.get(), 0, "green"));
						}else request.setAttribute("message", new Message("Echec enregistrement dossier_client. " + errorMsg.get(), 0, "red") );
						
					}else request.setAttribute("message", new Message("Cet dossier_client existe déjà." + errorMsg.get(), 0, "red") );			
			}
							
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
			}
	
}
