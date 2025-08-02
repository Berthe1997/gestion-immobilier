package com.news.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.news.beans.Agence;
import com.news.beans.Droit_p;
import com.news.beans.Proprietaire_m;
import com.news.beans.Site;
import com.news.beans.Urgence_p;
import com.news.beans.Users;
import com.news.beans_M.Documents;
import com.news.dao.DocumentsDI;
import com.news.dao.Droit_pDI;
import com.news.dao.Proprietaire_mDI;
import com.news.dao.Urgence_pDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_proprietaire_m")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)

public class CrudView_Proprietaire_M extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String PROPRIETAIRE = "/WEB-INF/views/view_proprietaire_M.jsp";

	
	 Site site = new Site();
	 Users users	=	new Users();
	 Agence agence = new Agence();
	 
	 Proprietaire_mDI proprietaire_mDI = new Proprietaire_mDI();
	 Proprietaire_m proprietaire_m = new Proprietaire_m();
	 
	 Droit_pDI droit_pDI = new Droit_pDI();
	 Droit_p droit_p = new Droit_p();
	 
	 Urgence_pDI urgence_pDI = new Urgence_pDI();
	 Urgence_p urgence_p = new Urgence_p();
	 
	 DocumentsDI documentsDI= new DocumentsDI();
	 Documents documents= new Documents();
	 
	 AtomicReference<String> errorMsg = new  AtomicReference<>("");
		VerifieSession verifieSess = new VerifieSession();
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
			String page	=	request.getParameter("page");
			String crud	=	request.getParameter("crud");
			String id	=	request.getParameter("id");
			String matriD	=	request.getParameter("matriD");
			String matriU	=	request.getParameter("matriU");
			
			if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
			 if(crud.equals("id")) {
				int idU = Integer.parseInt(id);
				proprietaire_m = proprietaire_mDI.getProprietaire_m(idU);
				request.setAttribute("proprietaire_m", proprietaire_m);
				
				 droit_p = droit_pDI.getDroit_p(matriD);
	 			request.setAttribute("droit_p", droit_p);
	 				
	 				urgence_p = urgence_pDI.getUrgence_p(matriU);
	 				request.setAttribute("urgence_p", urgence_p);
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(PROPRIETAIRE).forward(request, response);
		}
			 
			 if(crud.equals("idDR")) {
					
            	 droit_p = droit_pDI.getDroit_p(Integer.parseInt(id));
            	 droit_p.setId(Long.parseLong(id));
            	 droit_pDI.supprimerDroit_p(droit_p, errorMsg);            	            		   	             
    	         request.setAttribute("proprietaire_m", proprietaire_m);  
    	            	             
    	             request.setAttribute("page", page);
    				this.getServletContext().getRequestDispatcher(PROPRIETAIRE).forward(request, response);
				}
             
             if(crud.equals("idUr")) {
					
            	 urgence_p = urgence_pDI.getUrgence_p(Integer.parseInt(id));
            	 urgence_p.setId(Long.parseLong(id));
            	 urgence_pDI.supprimerUrgence_p(urgence_p, errorMsg);
            	 
            		request.setAttribute("proprietaire_m", proprietaire_m);           		   	           
    	           
    	             request.setAttribute("page", page);
    				this.getServletContext().getRequestDispatcher(PROPRIETAIRE).forward(request, response);
				}	 
			 
			 
			 if(crud.equals("ids")) {
				 documents = documentsDI.getDocuments(Integer.parseInt(id));
				 documents.setId(Long.parseLong(id));
				 documentsDI.supprimerDocuments(documents, errorMsg);
				
				 String uploadDir = this.getServletContext().getInitParameter("upload_dir");
				  Path path = Paths.get(uploadDir+"Document/"+"Proprietaire/"+documents.getChemin_F());
				  
			        try {
			            Files.delete(path);
			        }
			        catch (IOException e) {
			  
			            e.printStackTrace();
			        }
									
				request.setAttribute("proprietaire_m", proprietaire_m);	
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(PROPRIETAIRE).forward(request, response);
				}
			 
			 if(crud.equals("doc")) {
					
					int ids = Integer.parseInt(id);
					documents = documentsDI.getDocuments(ids);
					
					String uploadDir = this.getServletContext().getInitParameter("upload_dir");
					File apaths = new File(uploadDir+"Document/"+"Proprietaire/"+documents.getChemin_F() );
					
					// DÃ©finir des en-tÃªtes de rÃ©ponse
					
			        response.setHeader("Expires", "0");
			        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			        response.setHeader("Pragma", "public");
			        response.setContentType("application/pdf");

			        InputStream in = new FileInputStream(apaths);
			        OutputStream out = response.getOutputStream();

			        // Copy the bits from instream to outstream
			        byte[] buf = new byte[1024];
			        int len;
			        while ((len = in.read(buf)) > 0) {
			           out.write(buf, 0, len);
			        }
			        in.close();
					
				}
			 
			 
		}

		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String crud	=	request.getParameter("crud");
			String page	=	request.getParameter("page");
         	String id	=	request.getParameter("id");
			String agenc	=	request.getParameter("agenc");
			String adresse	=	request.getParameter("adresse");
			String email	=	request.getParameter("email");
			String tel	=	request.getParameter("tel");
			String tel_fax	=	request.getParameter("tel_fax");
			String site_web	=	request.getParameter("site_web");
			String directeur	=	request.getParameter("directeur");
			String date_ajout	=	request.getParameter("date_ajout");
			String logo	=	request.getParameter("logo");		
			//===================NEW CHAMP=======================================//	
			String localisation	=	request.getParameter("localisation");
			String adresse_postal	=	request.getParameter("adresse_postal");
			String tels	=	request.getParameter("tels");
			String gps	=	request.getParameter("gps");
			String taux= request.getParameter("taux");
			String dure_contrat= request.getParameter("dure_contrat");
			String type= request.getParameter("type");
			
			//==============AYANT DROIT===============================//
			String proprietaireA = request.getParameter("proprietaireA");
			String nomA = request.getParameter("nomA");
			String prenomA = request.getParameter("prenomA");
			String sexeA = request.getParameter("sexeA");
			String emailA= request.getParameter("emailA");
			String telA= request.getParameter("telA");
			String telsA= request.getParameter("telsA");
			String adresseA= request.getParameter("adresseA");
			String fonctionA= request.getParameter("fonctionA");
			//==============CAS URGENCE===============================//
			String proprietaireC = request.getParameter("proprietaireC");
			String nomC = request.getParameter("nomC");
			String prenomC = request.getParameter("prenomC");
			String sexeC = request.getParameter("sexeC");
			String emailC= request.getParameter("emailC");
			String telC= request.getParameter("telC");
			String telsC= request.getParameter("telsC");
			String adresseC= request.getParameter("adresseC");
			String fonctionC= request.getParameter("fonctionC");
			
			site = (Site) session.getAttribute("site");
			agence = (Agence) session.getAttribute("agence");
			
				if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		 } 
				
		
				String uploadDir = this.getServletContext().getInitParameter("upload_dir");
				
				String date = request.getParameter("date");
				String type_doc = request.getParameter("type_doc");
				String client = request.getParameter("client");
			
		  
			if(crud.equals("AJOUTER")) {
		    		 
				String nomFichier = null;
				Part filePart = request.getPart("file");
				nomFichier = getNomFichier(filePart);
				//String uploadDir = getServletContext().getInitParameter("upload_dir");
				
		    	 if ( nomFichier != null && !nomFichier.isEmpty()) {
					InputStream fileInputStream = filePart.getInputStream();
					//File fileToSave = new File(uploadDir+MATRICULE+"/"+filePart.getSubmittedFileName());
					File fileToSave = new File(uploadDir+"Document/"+"Proprietaire/"+filePart.getSubmittedFileName());
					Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
									 	
				}	
					
		    		 if(documentsDI.getDocumentsVerifie(agence.getCode(),client,type_doc) == false) {
		    			 
		    			   documents.setAgence(agence.getCode());
		    				documents.setClient(client);
		    				documents.setType_doc(type_doc);
		    				documents.setChemin_F(nomFichier);
		    				documents.setDate_ajout(date);
		    				
		    				if(documentsDI.creerDocuments(documents, errorMsg)) {																
								request.setAttribute("message", new Message("Document enregistré avec succès."+ errorMsg.get(), 0, "green"));
							}else request.setAttribute("message", new Message("Echec enregistrement Document. " + errorMsg.get(), 0, "red") );
							
						}else request.setAttribute("message", new Message("Cet Document existe déjà." + errorMsg.get(), 0, "red") );
		    	
		    		 request.setAttribute("proprietaire_m", proprietaire_m);
		    		 
		    		 request.setAttribute("urgence_p", urgence_p);
					 request.setAttribute("droit_p", droit_p);
		    	 }
				
				
				if(crud.equals("ayantD")) {
					droit_p.setProprietaire(proprietaireA);
					droit_p.setNom(nomA);
					droit_p.setPrenom(prenomA);
					droit_p.setSexe(sexeA);
					droit_p.setEmail(emailA);
					droit_p.setFonction(fonctionA);
					droit_p.setAdresse(adresseA);
					droit_p.setTel(telA);
					droit_p.setTels(telsA);
					droit_p.setMatricule(proprietaireA+"D");
					
					if(droit_pDI.creerDroit_p(droit_p, errorMsg)) {	
						
						request.setAttribute("message", new Message("Ayant Droit  enregistré avec succès."+ errorMsg.get(), 0, "green"));
					} else request.setAttribute("message", new Message("Echec enregistrement Ayant Droit. " + errorMsg.get(), 0, "red") );
				
					 request.setAttribute("urgence_p", urgence_p);
					 request.setAttribute("droit_p", droit_p);
    	            
    	             request.setAttribute("proprietaire_m", proprietaire_m);     
				}	
				
              if(crud.equals("cas")) {
            	  
            	urgence_p.setProprietaire(proprietaireC);
      			urgence_p.setNom(nomC);
      			urgence_p.setPrenom(prenomC);
      			urgence_p.setSexe(sexeC);
      			urgence_p.setEmail(emailC);
      			urgence_p.setFonction(fonctionC);
      			urgence_p.setAdresse(adresseC);
      			urgence_p.setTel(telC);
      			urgence_p.setTels(telsC);
      			urgence_p.setMatricule(proprietaireC+"U");
      			
      			if(urgence_pDI.creerUrgence_p(urgence_p, errorMsg)) {
      				
					request.setAttribute("message", new Message("Contact Urgent enregistré avec succès."+ errorMsg.get(), 0, "green"));
				} else request.setAttribute("message", new Message("Echec enregistrement Contact Urgent. " + errorMsg.get(), 0, "red") );
			
      			 request.setAttribute("urgence_p", urgence_p);
				 request.setAttribute("droit_p", droit_p);
	             
	             request.setAttribute("proprietaire_m", proprietaire_m);
              }	
				
              String matricule = "AGIL"+"-"+tel;
				if(crud.equals("MODIFIER")) {
					proprietaire_m = proprietaire_mDI.getProprietaire_m(Integer.parseInt(id));
					
					proprietaire_m.setMatricule(matricule);
					proprietaire_m.setNom(agenc);
					proprietaire_m.setCode(" ");
					proprietaire_m.setAdresse(adresse);
					proprietaire_m.setTel(tel);
					proprietaire_m.setTel_fax(tel_fax);
					proprietaire_m.setEmail(email);
					proprietaire_m.setSite_web(site_web);
					proprietaire_m.setDirecteur(directeur);
					proprietaire_m.setDate_ajout(date_ajout);
					proprietaire_m.setLogo(logo);				
					
				//===================NEW CHAMP=======================================//				
					proprietaire_m.setLocalisation(localisation);
					proprietaire_m.setAdresse_postal(adresse_postal);
					proprietaire_m.setTels(tels);
					proprietaire_m.setGps(gps);
					proprietaire_m.setTaux(Integer.parseInt(taux));			
					proprietaire_m.setDure_contrat(Integer.parseInt(dure_contrat));
					proprietaire_m.setType_contrat(type);
					proprietaire_m.setId(Long.parseLong(id));
					
					if(proprietaire_mDI.modifierProprietaire_m(proprietaire_m, errorMsg)) {
						request.setAttribute("proprietaire_m", proprietaire_m);
						
						request.setAttribute("urgence_p", urgence_p);
						request.setAttribute("droit_p", droit_p);
						
						request.setAttribute("message", new Message("proprietaire_moral modifié avec succès."+ errorMsg.get(), 0, "green"));
					} else request.setAttribute("message", new Message("Echec modification proprietaire_moral. " + errorMsg.get(), 0, "red") );
				}	
				
					
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(PROPRIETAIRE).forward(request, response);
			}
		
		
		private static String getNomFichier( Part part ) {
		     for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
		         if ( contentDisposition.trim().startsWith( "filename" ) ) {
		             return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
		         }
		     }
		     return null;
		 }
		
}
