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
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.beans_M.Ar_client_bien;
import com.news.beans_M.Ar_resident_bien;
import com.news.beans_M.Bien_meuble;
import com.news.beans_M.Client_bien;
import com.news.beans_M.Resident_bien;
import com.news.dao_M.Ar_client_bienDI;
import com.news.dao_M.Ar_resident_bienDI;
import com.news.dao_M.Bien_meubleDI;
import com.news.dao_M.Client_bienDI;
import com.news.dao_M.Resident_bienDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/archive_CR")
public class CrudArchive_CR extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String ARCHIVE_CR = "/WEB-INF/bien_M/archive_CR.jsp";
	public static final String VARCHIVE_CR = "/WEB-INF/bien_M/view_archive_CR.jsp";
	

	 Agence agence = new Agence();
	 Site site = new Site();
	 Users users	=	new Users();
	 
	 Ar_resident_bienDI ar_resident_bienDI= new Ar_resident_bienDI();
	 Ar_resident_bien ar_resident_bien= new Ar_resident_bien();
	 
	 Resident_bienDI resident_bienDI= new Resident_bienDI();
     Resident_bien resident_bien= new Resident_bien();
	 
	 Ar_client_bienDI ar_client_bienDI= new Ar_client_bienDI();
	 Ar_client_bien ar_client_bien= new Ar_client_bien();
	 
	 Client_bienDI client_bienDI= new Client_bienDI();
     Client_bien client_bien= new Client_bien();
     
     Bien_meubleDI bien_meubleDI= new Bien_meubleDI();
	 Bien_meuble bien_meuble= new Bien_meuble();
	 
	 AtomicReference<String> errorMsg = new  AtomicReference<>("");
		VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String crud	=	request.getParameter("crud");
			String page	=	request.getParameter("page");
			String id	=	request.getParameter("id");
			String bien = request.getParameter("bien");		
			String matri	=	request.getParameter("matri");
		
			site = (Site) session.getAttribute("site");
			agence = (Agence) session.getAttribute("agence");
			
			
			if(crud.equals("id")) {
					int idU = Integer.parseInt(id);
					ar_client_bien = ar_client_bienDI.getAr_client_bien(idU);
					request.setAttribute("ar_client_bien", ar_client_bien);
					
					request.setAttribute("page", page);
					this.getServletContext().getRequestDispatcher(VARCHIVE_CR).forward(request, response);
				 }
						
			if(crud.equals("archive")) {
				
				Client_bien ar_client_bienA = client_bienDI.getClient_bien(Integer.parseInt(id));
				if(ar_client_bienA != null) {
				
					ar_client_bien.setAgence(ar_client_bienA.getAgence());
					ar_client_bien.setMatricule(ar_client_bienA.getMatricule());
					ar_client_bien.setNom(ar_client_bienA.getNom());
					ar_client_bien.setPrenom(ar_client_bienA.getPrenom());
					ar_client_bien.setTel(ar_client_bienA.getTel());
					ar_client_bien.setEmail(ar_client_bienA.getEmail());
					ar_client_bien.setNum_cni(ar_client_bienA.getNum_cni());
					ar_client_bien.setNationalite(ar_client_bienA.getNationalite());
					ar_client_bien.setDate_ajout(ar_client_bienA.getDate_ajout());
					ar_client_bien.setDocument(ar_client_bienA.getDocument());
					ar_client_bien.setSite(ar_client_bienA.getSite());
										
			}
				ar_client_bienDI.creerAr_client_bien(ar_client_bien, errorMsg);		
				
				List<Resident_bien> resident_biens	=	resident_bienDI.getAllResident_bien(matri);

				for(Resident_bien resid : resident_biens) {
					
					ar_resident_bien.setAgence(resid.getAgence());
					ar_resident_bien.setCode_reservation(resid.getCode_reservation());
					ar_resident_bien.setNom(resid.getNom());
					ar_resident_bien.setPrenom(resid.getPrenom());			
					ar_resident_bien.setNum_cni(resid.getNum_cni());
					ar_resident_bien.setLien_client(resid.getLien_client());
					ar_resident_bien.setSite(resid.getSite());
					
					ar_resident_bienDI.creerAr_resident_bien(ar_resident_bien, errorMsg);
				}
				
				
				Bien_meuble bien_meubles = bien_meubleDI.getBien_meuble(bien);
				if(bien_meubles != null) {
				  bien_meuble.setStatut("Libre");
				  bien_meuble.setBien(bien);
				}
				bien_meubleDI.modifierBien_meubleS(bien_meuble, errorMsg);
												
				 resident_bien.setCode_reservation(matri);
				 resident_bienDI.supprimerResident_bienM(resident_bien, errorMsg);
																								
				client_bien = client_bienDI.getClient_bien(Integer.parseInt(id));
				client_bien.setId(Long.parseLong(id));
				client_bienDI.supprimerClient_bien(client_bien, errorMsg);
						
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(ARCHIVE_CR).forward(request, response);		
	}
		
}
	
}

