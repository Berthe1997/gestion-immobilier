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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.news.beans.Gestionnaire;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.beans_M.Documents;
import com.news.dao.DocumentsDI;
import com.news.dao.GestionnaireDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/view_gestionnaire")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)

public class CrudView_Gestionnaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	public static final String IMMOBILIER = "/WEB-INF/views/view_gestionnaire.jsp";
	
	
	Site site = new Site();
    Users users	=	new Users();
    Agence agence = new Agence();
	 
	 GestionnaireDI gestionnaireDI = new GestionnaireDI();
	 Gestionnaire gestionnaire = new Gestionnaire();
	 
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
				
			 if(crud.equals("id")) {
				int idU = Integer.parseInt(id);
				gestionnaire = gestionnaireDI.getGestionnaire(idU);
				request.setAttribute("gestionnaire", gestionnaire);
				
				
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
				}
			 
			 if(crud.equals("ids")) {
				 documents = documentsDI.getDocuments(Integer.parseInt(id));
				 documents.setId(Long.parseLong(id));
				 documentsDI.supprimerDocuments(documents, errorMsg);
				 
				 String uploadDir = this.getServletContext().getInitParameter("upload_dir");
				  Path path = Paths.get(uploadDir+"Document/"+"Gestionnaire/"+documents.getChemin_F());
				  
			        try {
			            Files.delete(path);
			        }
			        catch (IOException e) {
			  
			            e.printStackTrace();
			        }
				
									
				request.setAttribute("gestionnaire", gestionnaire);	
				request.setAttribute("page", page);
				this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
				}
				
				if(crud.equals("doc")) {
					
					int ids = Integer.parseInt(id);
					documents = documentsDI.getDocuments(ids);
					
					String uploadDir = this.getServletContext().getInitParameter("upload_dir");
					File apaths = new File(uploadDir+"Document/"+"Gestionnaire/"+documents.getChemin_F() );
					
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
			String id	=	request.getParameter("id");
			String page = request.getParameter("page");
			String crud = request.getParameter("crud");			
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String sexe = request.getParameter("sexe");
			String date_naiss= request.getParameter("date_naiss");
			String lieu_naiss= request.getParameter("lieu_naiss");
			String email= request.getParameter("email");
			String situationM= request.getParameter("situationM");
			String nationalite= request.getParameter("nationalite");
			String lieu_residence= request.getParameter("lieu_residence");
			String tel= request.getParameter("tel");
			String num_cnps= request.getParameter("num_cnps");
			String adresseP= request.getParameter("adresseP");
			String date_emb= request.getParameter("date_emb");
			String anc= request.getParameter("anc");
			//String service= request.getParameter("service");
			//==============DEUXIEME===============================//
			String telW = request.getParameter("telW");
			String tels = request.getParameter("tels");
			String cni= request.getParameter("cni");
			String cmu= request.getParameter("cmu");
			String banque_1= request.getParameter("banque_1");		
			String rib_1= request.getParameter("rib_1");			
			String image_cni= request.getParameter("image_cni");
			String photo= request.getParameter("photo");
						
			agence = (Agence) session.getAttribute("agence");
			users = (Users) session.getAttribute("users");
			site = (Site) session.getAttribute("site");
			
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
					File fileToSave = new File(uploadDir+"Document/"+"Gestionnaire/"+filePart.getSubmittedFileName());
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
		    	
		    		 request.setAttribute("gestionnaire", gestionnaire);	
		    	 }
				
				
			
				String matricule = "AGIL"+"-"+tel;
			
			
			if(crud.equals("MODIFIER")) {
				gestionnaire = gestionnaireDI.getGestionnaire(Integer.parseInt(id));
				
				gestionnaire.setMatricule(matricule);
				gestionnaire.setNom(nom);
				gestionnaire.setPrenom(prenom);
				gestionnaire.setSexe(sexe);
				gestionnaire.setDate_naiss(date_naiss);
				gestionnaire.setLieu_naiss(lieu_naiss);
				gestionnaire.setSituation_matrimonial(situationM);			
				gestionnaire.setNationalite(nationalite);
				gestionnaire.setLieu_residence(lieu_residence);
				gestionnaire.setTel(tel);
				gestionnaire.setEmail(email);
				gestionnaire.setNum_cnps(num_cnps);
				gestionnaire.setAdresse_postal(adresseP);			
				gestionnaire.setDate_embauchema(date_emb);
				gestionnaire.setAnciennete(anc);
				//==============DEUXIEME===============================//
				gestionnaire.setTel_whatsapp(telW);	
				gestionnaire.setTels(tels);
				gestionnaire.setCni(cni);
				gestionnaire.setCmu(cmu);
				gestionnaire.setBanque_1(banque_1);			
				gestionnaire.setRib_1(rib_1);			
				gestionnaire.setImage_cni(image_cni);
				gestionnaire.setPhoto(photo);
				gestionnaire.setSite(site.getSite());			
				gestionnaire.setId(Long.parseLong(id));
				
				if(gestionnaireDI.modifierGestionnaire(gestionnaire, errorMsg)) {
					request.setAttribute("gestionnaire", gestionnaire);
					request.setAttribute("message", new Message("Gestionnaire modifié avec succès."+ errorMsg.get(), 0, "green"));
				} else request.setAttribute("message", new Message("Echec modification Gestionnaire. " + errorMsg.get(), 0, "red") );
			}	
			
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher(IMMOBILIER).forward(request, response);
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
