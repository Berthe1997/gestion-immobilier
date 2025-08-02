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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
import com.news.beans.Archive_locataire;
import com.news.beans.Calendrier_paiement;
import com.news.beans.Locataire;
import com.news.beans.Maison;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.beans_M.Documents;
import com.news.dao.Archive_locataireDI;
import com.news.dao.Calendrier_paiementDI;
import com.news.dao.DocumentsDI;
import com.news.dao.LocataireDI;
import com.news.dao.MaisonDI;
import com.news.fonctions.VerifieSession;


@WebServlet("/view_locataire")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)

public class CrudView_Locataire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String LOCATAIRE = "/WEB-INF/views/view_locataire.jsp";
	public static final String LOCATAIRE_M = "/WEB-INF/views/view_locataire_M.jsp";
	public static final String ARCHIVE = "/WEB-INF/views/view_archive.jsp";
	
	 Site site = new Site();
	 Users users	=	new Users();
	 Agence agence = new Agence();
	 
	 LocataireDI locataireDI = new LocataireDI();
	 Locataire locataire = new Locataire();
	 
	 Archive_locataireDI archive_locataireDI = new Archive_locataireDI();
	 Archive_locataire archive_locataire = new Archive_locataire();
	 
	 Calendrier_paiementDI calendrier_paiementDI = new Calendrier_paiementDI();
	 Calendrier_paiement calendrier_paiement = new Calendrier_paiement();
	 
	 MaisonDI maisonDI = new MaisonDI();
	 Maison maison = new Maison();
	 
	 DocumentsDI documentsDI= new DocumentsDI();
	 Documents documents= new Documents();
	 	
	 
	 AtomicReference<String> errorMsg = new  AtomicReference<>("");
		VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String crud	=	request.getParameter("crud");
			String page	=	request.getParameter("page");
			String id	=	request.getParameter("id");
		
			site = (Site) session.getAttribute("site");
			
			
			
			if(verifieSess.sessions(request, response) == false) {
				response.sendRedirect( request.getContextPath() + INDEX );
				return;
			} 
			
			if(crud.equals("idA")) {
			int idU = Integer.parseInt(id);
			archive_locataire = archive_locataireDI.getArchive_locataire(idU);
			request.setAttribute("archive_locataire", archive_locataire);
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(ARCHIVE).forward(request, response);
			}
			
			if(crud.equals("id")) {
				int idU = Integer.parseInt(id);
				locataire = locataireDI.getLocataire(idU);
				request.setAttribute("locataire", locataire);
				
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(LOCATAIRE).forward(request, response);					
		
			}
			
			if(crud.equals("idM")) {
				int idU = Integer.parseInt(id);
				locataire = locataireDI.getLocataire(idU);
				request.setAttribute("locataire", locataire);
				
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(LOCATAIRE_M).forward(request, response);					
		
			}
			
			if(crud.equals("idsP")) {
				 documents = documentsDI.getDocuments(Integer.parseInt(id));
				 documents.setId(Long.parseLong(id));
				 documentsDI.supprimerDocuments(documents, errorMsg);
				
				 String uploadDir = this.getServletContext().getInitParameter("upload_dir");
				  Path path = Paths.get(uploadDir+"Document/"+"Locataire/"+documents.getChemin_F());
				  
			        try {
			            Files.delete(path);
			        }
			        catch (IOException e) {
			  
			            e.printStackTrace();
			        }
									
				request.setAttribute("locataire", locataire);	
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(LOCATAIRE).forward(request, response);
				}
			
			if(crud.equals("idsM")) {
				 documents = documentsDI.getDocuments(Integer.parseInt(id));
				 documents.setId(Long.parseLong(id));
				 documentsDI.supprimerDocuments(documents, errorMsg);
				
				 String uploadDir = this.getServletContext().getInitParameter("upload_dir");
				  Path path = Paths.get(uploadDir+"Document/"+"Locataire/"+documents.getChemin_F());
				  
			        try {
			            Files.delete(path);
			        }
			        catch (IOException e) {
			  
			            e.printStackTrace();
			        }
									
				request.setAttribute("locataire", locataire);	
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(LOCATAIRE_M).forward(request, response);
				}
			
			 if(crud.equals("doc")) {
					
					int ids = Integer.parseInt(id);
					documents = documentsDI.getDocuments(ids);
					
					String uploadDir = this.getServletContext().getInitParameter("upload_dir");
					File apaths = new File(uploadDir+"Document/"+"Locataire/"+documents.getChemin_F() );
					
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
		String id = request.getParameter("id");
		String page = request.getParameter("page");
		String crud = request.getParameter("crud");	
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String sexe = request.getParameter("sexe");		
		String date_naiss= request.getParameter("date_naiss");
		String lieu_naiss = request.getParameter("lieu_naiss");
		String situation_matr = request.getParameter("situation_matr");		
		String fonction = request.getParameter("fonction");
		String nationalite = request.getParameter("nationalite");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String num_porte = request.getParameter("num_porte");
		String type_contrat = request.getParameter("type_contrat");
		String date_entree = request.getParameter("date_entree");
		String date_sortie = request.getParameter("date_sortie");
		String telW = request.getParameter("telW");
		String photo = request.getParameter("photo");
		
		String p_C = request.getParameter("p_C");
		String p_A = request.getParameter("p_A");
		String p_CO = request.getParameter("p_CO");
							
		users = (Users) session.getAttribute("users");
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
			if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		} 
			
			String uploadDir = this.getServletContext().getInitParameter("upload_dir");
			
			String dats = request.getParameter("dats");
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
				File fileToSave = new File(uploadDir+"Document/"+"Locataire/"+filePart.getSubmittedFileName());
				Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
								 	
			}	
	    	 	    	
	    		 if(documentsDI.getDocumentsVerifie(agence.getCode(),client,type_doc) == false) {
	    			 
	    			    documents.setAgence(agence.getCode());
	    				documents.setClient(client);
	    				documents.setType_doc(type_doc);
	    				documents.setChemin_F(nomFichier);
	    				documents.setDate_ajout(dats);
	    				
	    				if(documentsDI.creerDocuments(documents, errorMsg)) {																
							request.setAttribute("message", new Message("Document enregistré avec succès."+ errorMsg.get(), 0, "green"));
						}else request.setAttribute("message", new Message("Echec enregistrement Document. " + errorMsg.get(), 0, "red") );
						
					}else request.setAttribute("message", new Message("Cet Document existe déjà." + errorMsg.get(), 0, "red") );
	    	
	    		 request.setAttribute("locataire", locataire);	
	    		 
	    		request.setAttribute("page", page);
	 			this.getServletContext().getRequestDispatcher(LOCATAIRE).forward(request, response);
	    	 }
	    	 
	    	 if(crud.equals("AJOUTERM")) {
	    		 
	    		 String nomFichier = null;
	 			Part filePart = request.getPart("file");
	 			nomFichier = getNomFichier(filePart);
	 			//String uploadDir = getServletContext().getInitParameter("upload_dir");
	 			
	 	    	 if ( nomFichier != null && !nomFichier.isEmpty()) {
	 				InputStream fileInputStream = filePart.getInputStream();
	 				//File fileToSave = new File(uploadDir+MATRICULE+"/"+filePart.getSubmittedFileName());
	 				File fileToSave = new File(uploadDir+"Document/"+"Locataire/"+filePart.getSubmittedFileName());
	 				Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
	 								 	
	 			}	
	    		 
	    		 if(documentsDI.getDocumentsVerifie(agence.getCode(),client,type_doc) == false) {
	    			 
	    			    documents.setAgence(agence.getCode());
	    				documents.setClient(client);
	    				documents.setType_doc(type_doc);
	    				documents.setChemin_F(nomFichier);
	    				documents.setDate_ajout(dats);
	    				
	    				if(documentsDI.creerDocuments(documents, errorMsg)) {																
							request.setAttribute("message", new Message("Document enregistré avec succès."+ errorMsg.get(), 0, "green"));
						}else request.setAttribute("message", new Message("Echec enregistrement Document. " + errorMsg.get(), 0, "red") );
						
					}else request.setAttribute("message", new Message("Cet Document existe déjà." + errorMsg.get(), 0, "red") );
	    	
	    		 request.setAttribute("locataire", locataire);	
	    		 
	    		 request.setAttribute("page", page);
	 			this.getServletContext().getRequestDispatcher(LOCATAIRE_M).forward(request, response);
	    	 }
			
			
			LocalDate dates = LocalDate.now();
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("YYYY", Locale.FRENCH); 
			String ans = dates.format(formatters);	
			
			
			String matricule = request.getParameter("matricule");							
			if(crud.equals("MODIFIER")) {
				locataire = locataireDI.getLocataire(Integer.parseInt(id));
				
				locataire.setSite(site.getSite());
				locataire.setMatricule(matricule);
				locataire.setNom(nom);
				locataire.setPrenom(prenom);
				locataire.setSexe(sexe);
				locataire.setDate_naiss(date_naiss);
				locataire.setLieu_naiss(lieu_naiss);
				locataire.setSituation_matr(situation_matr);
				locataire.setFonction(fonction);
				locataire.setNationalite(nationalite );
				locataire.setTel(tel);
				locataire.setEmail(email);		
				locataire.setNum_porte(num_porte);
				locataire.setType_contrat(type_contrat);
				locataire.setDate_entree(date_entree);
				locataire.setDate_sortie(date_sortie);
				locataire.setTel_whatsapp(telW);
				locataire.setPhoto(photo);
				
				if(p_C != null) locataire.setP_C(Integer.parseInt(p_C));
				else locataire.setP_C(0);
				if(p_A != null) locataire.setP_A(Integer.parseInt(p_A));
				else locataire.setP_A(0);
				if(p_CO != null) locataire.setP_CO(Integer.parseInt(p_CO));
				else locataire.setP_CO(0);
			
				locataire.setId(Long.parseLong(id));
				
																
				if(locataireDI.modifierLocataire(locataire, errorMsg)) {					
					calendrier_paiement.setLocataire(nom+" "+prenom);
					calendrier_paiement.setMatricule(matricule);
					calendrier_paiementDI.modifierCalendrier_paiementL(calendrier_paiement, errorMsg);
					
					maison.setNom_prenom(nom+" "+prenom);
					maison.setMatricule(matricule);
					maisonDI.modifierMaisonL(maison, errorMsg);
										
					request.setAttribute("locataire", locataire);
					request.setAttribute("message", new Message("Locataire modifié avec succès."+ errorMsg.get(), 0, "green"));
				} else request.setAttribute("message", new Message("Echec modification Locataire. " + errorMsg.get(), 0, "red") );
				
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(LOCATAIRE).forward(request, response);
			}
		
	  if(crud.equals("ECHEANCE")) {		
			
			String date_entrees = request.getParameter("date_entrees");
			String type = request.getParameter("type");
			String dure = request.getParameter("dure");
			String nomp = request.getParameter("nomp");
			String mat = request.getParameter("mat");	
			
			
		  if(type.equals("ANNEE")) {
			  // Date saisie par l'utilisateur
			  DateTimeFormatter formatterP = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.FRENCH);
			 LocalDate dateSaisie = LocalDate.parse(date_entrees,formatterP); // Remplacez par l'entrée utilisateur
		     // Obtenez l'année en cours
		      
		        int annees = dateSaisie.getYear();
		        int dureT = Integer.parseInt(dure);
	
			if(calendrier_paiementDI.getCalendrier_paiementVerifie(mat,annees) == false) {	      
					       
		        // Liste pour stocker les dates de paiement
		        List<LocalDate> calendrierPaiement = new ArrayList<>();
		        // Parcourez les mois restants de l'année
		        DateTimeFormatter formatterM = DateTimeFormatter.ofPattern("MMMM",Locale.FRENCH);
		        DateTimeFormatter formatterJ = DateTimeFormatter.ofPattern("dd");
		        int J = dateSaisie.getDayOfMonth();
		        for (int annee = 0; annee < dureT; annee++) {
		            for (int mois = 0; mois < 12; mois++) {
		            	calendrierPaiement.add(dateSaisie.plusMonths(mois + annee * 12).withDayOfMonth(J)); // Exemple : paiement le 5 de chaque mois
		            }
		        } 
		        // Affichez les dates de paiement				    
		        for(LocalDate date : calendrierPaiement)  {
		        	 String moisNom = date.format(formatterM);
			        	// Met en majuscule la première lettre du mois
			        	 moisNom = moisNom.substring(0, 1).toUpperCase() + moisNom.substring(1).toLowerCase(); 
			        	    calendrier_paiement.setAnnee(date.getYear());
							calendrier_paiement.setMois(moisNom);
							calendrier_paiement.setSite(site.getSite());
							calendrier_paiement.setLocataire(nomp);
							calendrier_paiement.setMatricule(mat);			
							calendrier_paiement.setMontant_loyer(0);
							calendrier_paiement.setMontant_P(0);
							calendrier_paiement.setMontant_R(0);
							calendrier_paiement.setPenalite(0);				
							calendrier_paiement.setStatut("impaye");
							calendrier_paiement.setDate_paiement(date.format(formatterP));
																
							calendrier_paiementDI.creerCalendrier_paiement(calendrier_paiement, errorMsg);					        	 					        
		        }
							        
			}
			 request.setAttribute("locataire", locataire);
			  request.setAttribute("block", "block");
		}	
		  
		  if(type.equals("MOIS")) {
			  // Date saisie par l'utilisateur
			  DateTimeFormatter formatterP = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.FRENCH);
			 LocalDate dateSaisie = LocalDate.parse(date_entrees,formatterP); // Remplacez par l'entrée utilisateur
		     // Obtenez l'année en cours
		      
		        int annees = dateSaisie.getYear();
		        int dureT = Integer.parseInt(dure);
	
			if(calendrier_paiementDI.getCalendrier_paiementVerifie(mat,annees) == false) {	      
					       
		        // Liste pour stocker les dates de paiement
		        List<LocalDate> calendrierPaiement = new ArrayList<>();
		        // Parcourez les mois restants de l'année
		        DateTimeFormatter formatterM = DateTimeFormatter.ofPattern("MMMM",Locale.FRENCH);
		        DateTimeFormatter formatterJ = DateTimeFormatter.ofPattern("dd");
		        int J = dateSaisie.getDayOfMonth();
		        for (int i = 0; i < dureT; i++) {
		        	calendrierPaiement.add(dateSaisie.plusMonths(i).withDayOfMonth(J)); // Paiement le 5 de chaque mois
		        }
		        
		        // Affichez les dates de paiement				    
		        for(LocalDate date : calendrierPaiement)  {
		        	 String moisNom = date.format(formatterM);
			        	// Met en majuscule la première lettre du mois
			        	 moisNom = moisNom.substring(0, 1).toUpperCase() + moisNom.substring(1).toLowerCase(); 
			        	    calendrier_paiement.setAnnee(date.getYear());
							calendrier_paiement.setMois(moisNom);
							calendrier_paiement.setSite(site.getSite());
							calendrier_paiement.setLocataire(nomp);
							calendrier_paiement.setMatricule(mat);			
							calendrier_paiement.setMontant_loyer(0);
							calendrier_paiement.setMontant_P(0);
							calendrier_paiement.setMontant_R(0);
							calendrier_paiement.setPenalite(0);				
							calendrier_paiement.setStatut("impaye");
							calendrier_paiement.setDate_paiement(date.format(formatterP));
																
							calendrier_paiementDI.creerCalendrier_paiement(calendrier_paiement, errorMsg);					        	 					        
		        }
							        
			}
			  request.setAttribute("locataire", locataire);
			  request.setAttribute("block", "block");
		}
		 
	  }  
	 					
			
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
