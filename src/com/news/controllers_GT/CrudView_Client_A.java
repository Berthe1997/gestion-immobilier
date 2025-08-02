package com.news.controllers_GT;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.news.beans_GT.Client_acheteur;
import com.news.beans_GT.Paiement_terrain;
import com.news.beans_GT.Terrain;
import com.news.controllers.Message;
import com.news.dao_GT.Client_acheteurDI;
import com.news.dao_GT.Paiement_terrainDI;
import com.news.dao_GT.TerrainDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_client_A")
public class CrudView_Client_A extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";	
	public static final String IMMOBILIER = "/WEB-INF/gestion_T/view_client_A.jsp";

	Client_acheteurDI client_acheteurDI= new Client_acheteurDI();
	Client_acheteur client_acheteur= new Client_acheteur();
	
	TerrainDI terrainDI= new TerrainDI();
	Terrain terrain= new Terrain();
	
	Paiement_terrainDI paiement_terrainDI= new Paiement_terrainDI();
	Paiement_terrain paiement_terrain= new Paiement_terrain();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	HttpSession session = request.getSession();
		String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
		String client	=	request.getParameter("client");
		String terrains = request.getParameter("terrain");
		
			
		
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
			if(crud.equals("ids")) {
				paiement_terrain = paiement_terrainDI.getPaiement_terrain(Integer.parseInt(id));
				paiement_terrain.setId(Long.parseLong(id));
				paiement_terrainDI.supprimerPaiement_terrain(paiement_terrain, errorMsg);
				
				request.setAttribute("paiement_terrain", paiement_terrain);
				
				client_acheteur = client_acheteurDI.getClient_acheteur(client);
				request.setAttribute("client_acheteur", client_acheteur);
				
				terrain = terrainDI.getTerrain(terrains);
				request.setAttribute("terrain", terrain);
			}
			
			if(crud.equals("id")) {
			int idU = Integer.parseInt(id);
			client_acheteur = client_acheteurDI.getClient_acheteur(idU);
			request.setAttribute("client_acheteur", client_acheteur);
			
			paiement_terrain = paiement_terrainDI.getPaiement_terrain(client);
			request.setAttribute("paiement_terrain", paiement_terrain);
			
			terrain = terrainDI.getTerrain(terrains);
			request.setAttribute("terrain", terrain);
			}
		
		request.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	HttpSession session = request.getSession();
			String id = request.getParameter("id");
			String page = request.getParameter("page");	
			String crud = request.getParameter("crud");	
			String submit = request.getParameter("submit");	
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");	
			String cni = request.getParameter("cni");
			String Email = request.getParameter("Email");
			String terrains = request.getParameter("terrain");
			String type = request.getParameter("type");
			String date = request.getParameter("date");
			String acanal_ac = request.getParameter("acanal_ac");
			String sexe = request.getParameter("sexe");
			String prix = request.getParameter("prix");
			String contact = request.getParameter("contact");
			String matricule = request.getParameter("matricule");
			String honoraire = request.getParameter("honoraire");
			//---------NEW CHAMPS----------------------//
			String date_naissance = request.getParameter("date_naissance");	
			String nationnalite = request.getParameter("nationnalite");
			String profession = request.getParameter("profession");
			String adresse = request.getParameter("adresse");
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
			
			String montantR = request.getParameter("montantR");
			String terre = request.getParameter("terre");
			String client = request.getParameter("client");
			String modep = request.getParameter("modep");
			String datep = request.getParameter("datep");
			
			if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
			
		
			if(submit.equals("AJOUTER")) {
				
				paiement_terrain.setTerrain(terre);
				paiement_terrain.setClient(client);
				paiement_terrain.setMontantP(Integer.parseInt(montantR));
				paiement_terrain.setMontantR(0);					
				paiement_terrain.setModeP(modep);
				paiement_terrain.setDateP(datep);
				
				if(paiement_terrainDI.creerPaiement_terrain(paiement_terrain, errorMsg)) {
					request.setAttribute("paiement_terrain", paiement_terrain);
					
					int idU = Integer.parseInt(id);
					client_acheteur = client_acheteurDI.getClient_acheteur(idU);
					request.setAttribute("client_acheteur", client_acheteur);
					
					request.setAttribute("message", new Message("paiement_terrain enregistré avec succès."+ errorMsg.get(), 0, "green"));
				} else request.setAttribute("message", new Message("Echec enregistrement paiement_terrain. " + errorMsg.get(), 0, "red") );
			}	
									

			if(submit.equals("MODIFIER")) {
				client_acheteur = client_acheteurDI.getClient_acheteur(Integer.parseInt(id));
				
				client_acheteur.setNom(nom);
				client_acheteur.setPrenom(prenom);
				client_acheteur.setMatricule(matricule);
				client_acheteur.setCni(cni);
				client_acheteur.setEmail(Email);
				client_acheteur.setTerrain(terrains);				
				client_acheteur.setType(type);
				client_acheteur.setDate(date);
				client_acheteur.setAcanal_ac(acanal_ac);
				client_acheteur.setSexe(sexe);
				client_acheteur.setContact(contact);
				client_acheteur.setHonoraire(Integer.parseInt(honoraire));
				//-----------------------------------NEW CHAMPS------------------------------------------//
				client_acheteur.setDate_naissance(date_naissance);
				client_acheteur.setNationnalite(nationnalite);
				client_acheteur.setProfession(profession);
				client_acheteur.setAdresse(adresse);					
				if(depot_DATTC != null) client_acheteur.setDepot_DATTC(Integer.parseInt(depot_DATTC));
				else client_acheteur.setDepot_DATTC(0);
				if(dossier_techn_CT != null) client_acheteur.setDossier_techn_CT(Integer.parseInt(dossier_techn_CT));
				else client_acheteur.setDossier_techn_CT(0);		
				if(dossier_techn_D != null) client_acheteur.setDossier_techn_D(Integer.parseInt(dossier_techn_D));
				else client_acheteur.setDossier_techn_D(0);				
				if(attes_CD != null) client_acheteur.setAttes_CD(Integer.parseInt(attes_CD));
				else client_acheteur.setAttes_CD(0);		
				if(intro_dem_ACD != null) client_acheteur.setIntro_dem_ACD(Integer.parseInt(intro_dem_ACD));
				else client_acheteur.setIntro_dem_ACD(0);		
				if(date_BM != null) client_acheteur.setDate_BM(Integer.parseInt(date_BM));
				else client_acheteur.setDate_BM(0);		
				if(n_DM != null) client_acheteur.setN_DM(Integer.parseInt(n_DM));
				else client_acheteur.setN_DM(0);		
				if(trans_DM != null) client_acheteur.setTrans_DM(Integer.parseInt(trans_DM));
				else client_acheteur.setTrans_DM(0);	
				if(bornage_cont != null) client_acheteur.setBornage_cont(Integer.parseInt(bornage_cont));
				else client_acheteur.setBornage_cont(0);		
				if(transp_pv_bc != null) client_acheteur.setTransp_pv_bc(Integer.parseInt(transp_pv_bc));
				else client_acheteur.setTransp_pv_bc(0);		
				if(creat_ACD != null) client_acheteur.setCreat_ACD(Integer.parseInt(creat_ACD));
				else client_acheteur.setCreat_ACD(0);		
				if(notif_ACD != null) client_acheteur.setNotif_ACD(Integer.parseInt(notif_ACD));
				else client_acheteur.setNotif_ACD(0);		
				if(pf_ACD != null) client_acheteur.setPf_ACD(Integer.parseInt(pf_ACD));
				else client_acheteur.setPf_ACD(0);
				if(ref_ACD != null) client_acheteur.setRef_ACD(Integer.parseInt(ref_ACD));
				else client_acheteur.setRef_ACD(0);
				client_acheteur.setSituation_matr(situation_matr);
				if(creat_TF != null) client_acheteur.setCreat_TF(Integer.parseInt(creat_TF));
				else client_acheteur.setCreat_TF(0);				
				if(trans_TF != null) client_acheteur.setTrans_TF(Integer.parseInt(trans_TF));
				else client_acheteur.setTrans_TF(0);
				client_acheteur.setId(Long.parseLong(id));
				
				if(client_acheteurDI.modifierClient_acheteur(client_acheteur, errorMsg)) {
					request.setAttribute("client_acheteur", client_acheteur);
					request.setAttribute("terrain", terrain);

					request.setAttribute("message", new Message("client_acheteur modifié avec succès."+ errorMsg.get(), 0, "green"));
				} else request.setAttribute("message", new Message("Echec modification client_acheteur. " + errorMsg.get(), 0, "red") );
			}	
			
			
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
		}
	


}
