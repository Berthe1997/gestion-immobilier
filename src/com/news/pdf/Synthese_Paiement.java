package com.news.pdf;

import java.io.File;
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
import org.joda.time.format.DateTimeFormatter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.news.beans.Agence;
import com.news.beans.Calendrier_paiement;
import com.news.beans.Locataire;
import com.news.beans.Maison;
import com.news.beans.Operation;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.AgenceDI;
import com.news.dao.Calendrier_paiementDI;
import com.news.dao.LocataireDI;
import com.news.dao.MaisonDI;
import com.news.dao.OperationDI;
import com.news.dao.SiteDI;
import com.news.fonctions.VerifieSession;
import com.news.pdfs.entete.EnteteBilan;

@WebServlet("/Synthese_Paiement")
public class Synthese_Paiement extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	private static final String FORMAT_DATE 			= "dd/MM/yyyy HH:mm:ss";
	private static final String FORMAT_DATES 			= "yyy-MM-dd";
	private static final String FORMAT_DATING 			= "dd/MM/yyyy";
	
	Users users	=	new Users();
	
	AgenceDI agenceDI = new AgenceDI();
	Agence agence = new Agence();
	
	LocataireDI locataireDI = new LocataireDI();
	Locataire locataire = new Locataire();
	
	SiteDI siteDI = new SiteDI();
	Site site = new Site();
	
	 OperationDI operationDI = new OperationDI();
	 Operation operation = new Operation();
	 
	 MaisonDI maisonDI = new MaisonDI();
	 Maison maison = new Maison();
	 
	 Calendrier_paiementDI calendrier_paiementDI = new Calendrier_paiementDI();
	 Calendrier_paiement calendrier_paiement = new Calendrier_paiement();
	
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = request.getSession();
		
		String type = request.getParameter("type");
		String typ = request.getParameter("typ");
		String debut	=	request.getParameter("debut");
		String fin	=	request.getParameter("fin");
		String sit	=	request.getParameter("sit");
		String proprietaire	=	request.getParameter("matricule");
	

		String uploadDir = this.getServletContext().getInitParameter("upload_dir");
		
	
		
		users = (Users) session.getAttribute("users");
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		}
				
		DateTime dt = new DateTime();
		DateTime dt1 = new DateTime(debut);
		DateTime dt2 = new DateTime(fin);
		DateTimeFormatter formatter = DateTimeFormat.forPattern( FORMAT_DATE );
		DateTimeFormatter formatters = DateTimeFormat.forPattern( FORMAT_DATES );
		DateTimeFormatter format = DateTimeFormat.forPattern( FORMAT_DATING );
		String date1 = dt.toString( formatter );
		String date1s = dt.toString( formatters );
		String dte = dt1.toString(format);
		String dtes = dt2.toString(format);
		String typo = "", dte1 = "", dte2 = "";
		if("Bilan_journalier".equals(type)) {typo="BILAN JOURNALIER";dte1=""+date1;}
		if("Bilan_par_date".equals(type)) {typo="BILAN";dte1="DU "+dte;}
		if("Bilan_customise".equals(type)) {typo="BILAN";dte1="DU "+dte;dte2="AU "+dtes;}
		if("Bilan_général".equals(type)) {typo="BILAN GENERAL";}
		
	
		Document document = new Document();
		try{
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline; filename=Report.pdf");
			@SuppressWarnings("unused")
			PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			writer.setPageEvent(new EnteteBilan());
			document.setMargins(Utilities.millimetersToPoints(10), Utilities.millimetersToPoints(10), Utilities.millimetersToPoints(10),Utilities.millimetersToPoints(10));
			document.open();
						
			Font en = new Font(FontFamily.TIMES_ROMAN,9.0f, Font.NORMAL, BaseColor.BLACK);
			Font enP = new Font(FontFamily.TIMES_ROMAN,7.0f, Font.NORMAL, BaseColor.BLACK);
			Font entete = new Font(FontFamily.HELVETICA,8.0f, Font.BOLD, BaseColor.BLACK);		
			
			Font qui = new Font(FontFamily.HELVETICA,11.0f, Font.BOLD, BaseColor.BLACK);
			Font quis = new Font(FontFamily.HELVETICA,10.0f, Font.BOLD, BaseColor.RED);
			Font ct = new Font(FontFamily.TIMES_ROMAN,8.0f, Font.NORMAL, BaseColor.BLACK);
		//	Font gt = new Font(FontFamily.TIMES_ROMAN,7.0f, Font.NORMAL, BaseColor.BLACK);
			Font e = new Font(FontFamily.TIMES_ROMAN,9.0f, Font.BOLD, BaseColor.RED);
			
			
			
			PdfPTable header = new PdfPTable(4); header.setWidthPercentage(100);
			float[] colonneWidthHead = {0.8f,1.1f,2f,1.6f};header.setWidths(colonneWidthHead);
			
			PdfPCell cellt1 = new PdfPCell(new Paragraph(""+agence.getAgence(),en));cellt1.setIndent(35);
			cellt1.setBorder(0);cellt1.setColspan(4);header.addCell(cellt1);
			
			if(agence.getLogo() != null) {
				Image image1 = Image.getInstance(uploadDir+"erreur.png");
				File file = new File(uploadDir+"logos/"+agence.getLogo()+"");
				if(file.exists()) image1 = Image.getInstance(uploadDir+"logos/"+agence.getLogo()+"");
				image1.scaleAbsolute(40,30);
				PdfPCell cell11 = new PdfPCell(image1);cell11.setRowspan(2);cell11.setIndent(40);
				cell11.setColspan(2);cell11.setBorder(0);cell11.setPadding(1);header.addCell(cell11);
			}else {
				Image image0 = Image.getInstance(uploadDir+"erreur.png");
				image0.scaleAbsolute(40,30);
				PdfPCell cell11 = new PdfPCell(image0);cell11.setRowspan(2);cell11.setIndent(40);
				cell11.setColspan(2);cell11.setBorder(0);cell11.setPadding(1);header.addCell(cell11);
			}
						
			PdfPCell cellt2 = new PdfPCell(new Paragraph(""+typo+" "+dte1 +" "+dte2,en));cellt2.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellt2.setBorder(0);cellt2.setColspan(2);cellt2.setRowspan(2);header.addCell(cellt2);
			
			PdfPCell cellt3 = new PdfPCell(new Paragraph("TEL :"+agence.getTel(),ct));cellt3.setIndent(35);
			cellt3.setBorder(0);cellt3.setColspan(4);header.addCell(cellt3);
			
			PdfPCell cellt4 = new PdfPCell(new Paragraph(" "));cellt4.setColspan(4);
			cellt4.setBorder(0);header.addCell(cellt4);
			
			PdfPCell cellt5 = new PdfPCell(new Paragraph(""+typ,quis));cellt5.setColspan(4);
			cellt5.setBackgroundColor(BaseColor.CYAN);cellt5.setHorizontalAlignment(Element.ALIGN_CENTER);header.addCell(cellt5);
			
			document.add(header);
			
		if(proprietaire.equals("TOUS")) {
			if(typ.equals("SYNTHESE DES OPERATIONS")) {
				
				PdfPTable tableI1 = new PdfPTable(7);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
				tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {2.0f,1.3f,0.8f,0.8f,0.8f,1.3f,0.7f};tableI1.setWidths(colonneWidthI1);
				
						
				PdfPCell cellI13 = new PdfPCell(new Paragraph("Bien Immobilier",en));
				tableI1.addCell(cellI13);
												
				PdfPCell cellI15 = new PdfPCell(new Paragraph("Commission Agence",en));
				tableI1.addCell(cellI15);
				
				PdfPCell cellI16 = new PdfPCell(new Paragraph("Impot Site",en));
				tableI1.addCell(cellI16);
				
				PdfPCell cellI17 = new PdfPCell(new Paragraph("Visite Site",en));
				tableI1.addCell(cellI17);
				
				PdfPCell cellI18 = new PdfPCell(new Paragraph("Autre Service",en));
				tableI1.addCell(cellI18);
				
				PdfPCell cellI14 = new PdfPCell(new Paragraph("Montant Proprietaire",en));
				tableI1.addCell(cellI14);
				
				PdfPCell cellI19 = new PdfPCell(new Paragraph("Total",en));
				tableI1.addCell(cellI19);
				
				
                List<Operation> listoperations	=	null;
				
				if("Bilan_journalier".equals(type)) {
					listoperations	=	operationDI.getAllPaieDateE(agence.getCode(), date1s);
				}
				if("Bilan_par_date".equals(type)) {
					listoperations	=	operationDI.getAllPaieDateE(agence.getCode(), debut);
				}
				if("Bilan_customise".equals(type)) {
					listoperations	=	operationDI.getAllPaieCustomE(agence.getCode(), debut, fin);
				}
				if("Bilan_général".equals(type)) {
					listoperations	=	operationDI.getAllDateE(agence.getCode());
				}
				int Tot = 0,TG = 0, SoldP = 0, SoldA = 0, SoldI = 0, SoldV = 0, SoldAU = 0;
				for(Operation list : listoperations) {
					
				//	int index	=	listoperations.indexOf(list) + 1;
					Tot = list.getMontantA() + list.getMontantAU() + list.getMontantI() + list.getMontantP() + list.getMontantV() ;
					SoldP +=list.getMontantP(); SoldA +=list.getMontantA(); SoldI +=list.getMontantI();
					SoldV +=list.getMontantV(); SoldAU +=list.getMontantAU(); TG = SoldP+SoldA+SoldI+SoldV+SoldAU;
					
					PdfPCell cellI21 = new PdfPCell(new Paragraph(""+list.getSite(),en));
					cellI21.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI21);
															
					PdfPCell cellI23 = new PdfPCell(new Paragraph(""+list.getMontantA(),en));
					cellI23.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI23);
					
					PdfPCell cellI24 = new PdfPCell(new Paragraph(""+list.getMontantI(),en));
					cellI24.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI24);
					
					PdfPCell cellI25 = new PdfPCell(new Paragraph(""+list.getMontantV(),en));
					cellI25.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI25);
					
					PdfPCell cellI26 = new PdfPCell(new Paragraph(""+list.getMontantAU(),en));
					cellI26.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI26);
					
					PdfPCell cellI22 = new PdfPCell(new Paragraph(""+list.getMontantP(),en));
					cellI22.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI22);
					
					PdfPCell cellI27 = new PdfPCell(new Paragraph(""+Tot,en));
					cellI27.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI27);	
				}
				
				PdfPCell cellI28 = new PdfPCell(new Paragraph("TOTAL",en));cellI28.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);
												
				PdfPCell cellI30 = new PdfPCell(new Paragraph(""+SoldA,en));cellI30.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI30.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI30);
				
				PdfPCell cellI31 = new PdfPCell(new Paragraph(""+SoldI,en));cellI31.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI31.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI31);
				
				PdfPCell cellI32 = new PdfPCell(new Paragraph(""+SoldV,en));cellI32.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI32.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI32);
				
				PdfPCell cellI33 = new PdfPCell(new Paragraph(""+SoldAU,en));cellI33.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI33.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI33);
				
				PdfPCell cellI29 = new PdfPCell(new Paragraph(""+SoldP,en));cellI29.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI29.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI29);
				
				PdfPCell cellI34 = new PdfPCell(new Paragraph(""+TG,quis));cellI34.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI34.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI34);	
								
				document.add(tableI1);
												
			}
			
	     if(typ.equals("DETAILS DES PAIEMENTS")) {
				
				PdfPTable tableI1 = new PdfPTable(11);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
				tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {2.0f,1.3f,1.3f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f};tableI1.setWidths(colonneWidthI1);
				
						
				PdfPCell cellI13 = new PdfPCell(new Paragraph("Proprietaire",en));
				tableI1.addCell(cellI13);
				
				PdfPCell cellI14 = new PdfPCell(new Paragraph("Bien Immobilier",en));
				tableI1.addCell(cellI14);
				
				PdfPCell cellI15 = new PdfPCell(new Paragraph("Locataire",en));
				tableI1.addCell(cellI15);
				
				PdfPCell cellI16 = new PdfPCell(new Paragraph("Loyer Mensuel",en));
				tableI1.addCell(cellI16);
				
				PdfPCell cellI17 = new PdfPCell(new Paragraph("Mois",en));
				tableI1.addCell(cellI17);
				
				PdfPCell cellI18 = new PdfPCell(new Paragraph("Paiement Effectué",en));
				tableI1.addCell(cellI18);
								
				PdfPCell cellI20 = new PdfPCell(new Paragraph("(%) Commission Agence FCFA",en));
				tableI1.addCell(cellI20);
				
				PdfPCell cellI21 = new PdfPCell(new Paragraph("Frais Divers FCFA",en));
				tableI1.addCell(cellI21);
				
				PdfPCell cellI22 = new PdfPCell(new Paragraph("Frais de Visite ",en));
				tableI1.addCell(cellI22);
				
				PdfPCell cellI23 = new PdfPCell(new Paragraph("impot Deductibles FCFA",en));
				tableI1.addCell(cellI23);
				
				PdfPCell cellI24 = new PdfPCell(new Paragraph("Total Net Proprietaire",en));
				tableI1.addCell(cellI24);
				
                 List<Operation> listoperations	=	null;
				
				if("Bilan_journalier".equals(type)) {
					listoperations	=	operationDI.getAllPaieDate(agence.getCode(), date1s);
				}
				if("Bilan_par_date".equals(type)) {
					listoperations	=	operationDI.getAllPaieDate(agence.getCode(), debut);
				}
				if("Bilan_customise".equals(type)) {
					listoperations	=	operationDI.getAllPaieCustom(agence.getCode(), debut, fin);
				}
				if("Bilan_général".equals(type)) {
					listoperations	=	operationDI.getAllDate(agence.getCode());
				}
				
				int Tot = 0,TG = 0, SoldP = 0, SoldA = 0, SoldI = 0, SoldV = 0, SoldAU = 0, SoldPr = 0;
				for(Operation list : listoperations) {
					
					SoldPr += list.getApprovisonnement(); SoldP += list.getMontantP(); SoldA += list.getMontantA(); 
					SoldI += list.getMontantI();SoldV += list.getMontantV();SoldAU += list.getMontantAU();
							
					Site sites = siteDI.getSite(list.getSite());
					Locataire locataire = locataireDI.getLocataire(list.getMatricule());
					Maison Maisons = maisonDI.getMaison(list.getMatricule());
					
						PdfPCell cellI25 = new PdfPCell(new Paragraph(""+sites.getNom_prenom(),en));
						cellI25.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI25);
					
						PdfPCell cellI26 = new PdfPCell(new Paragraph(""+list.getSite(),en));
						cellI26.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI26);
						
						if(locataire != null) {
						 PdfPCell cellI27 = new PdfPCell(new Paragraph(""+locataire.getNom()+" "+locataire.getPrenom(),en));
						 cellI27.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI27);
						}else {
							 PdfPCell cellI27 = new PdfPCell(new Paragraph("",en));
							 cellI27.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI27);
						}
						
						if(Maisons != null) {
						PdfPCell cellI28 = new PdfPCell(new Paragraph(""+Maisons.getPrix(),en));
						cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);
						}else {
							PdfPCell cellI28 = new PdfPCell(new Paragraph("",en));
							cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);
						}
						
						PdfPCell cellI29 = new PdfPCell(new Paragraph(""+list.getMois()+" "+list.getAnnee(),en));
						cellI29.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI29);
						
						PdfPCell cellI30 = new PdfPCell(new Paragraph(""+list.getApprovisonnement(),en));
						cellI30.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI30);
																		
						PdfPCell cellI32 = new PdfPCell(new Paragraph(""+list.getMontantA(),en));
						cellI32.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI32);
						
						PdfPCell cellI33 = new PdfPCell(new Paragraph(""+list.getMontantAU(),en));
						cellI33.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI33);
						
						PdfPCell cellI34 = new PdfPCell(new Paragraph(""+list.getMontantV(),en));
						cellI34.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI34);
						
						PdfPCell cellI35 = new PdfPCell(new Paragraph(""+list.getMontantI(),en));
						cellI35.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI35);
						
						PdfPCell cellI36 = new PdfPCell(new Paragraph(""+list.getMontantP(),en));
						cellI36.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI36);
					}
				
				PdfPCell cellI37 = new PdfPCell(new Paragraph("TOTAL",en));cellI37.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI37.setColspan(5);cellI37.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI37);
				
				PdfPCell cellI38 = new PdfPCell(new Paragraph(""+SoldPr,en));cellI38.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI38.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI38);
								
				PdfPCell cellI40 = new PdfPCell(new Paragraph(""+SoldA,en));cellI40.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI40.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI40);
				
				PdfPCell cellI41 = new PdfPCell(new Paragraph(""+SoldAU,en));cellI41.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI41.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI41);
				
				PdfPCell cellI42 = new PdfPCell(new Paragraph(""+SoldV,en));cellI42.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI42.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI42);
				
				PdfPCell cellI43 = new PdfPCell(new Paragraph(""+SoldI,en));cellI43.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI43.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI43);
				
				PdfPCell cellI44 = new PdfPCell(new Paragraph(""+SoldP,en));cellI44.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI44.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI44);
				
				
				document.add(tableI1);	
	      }
	   }else {
  //============ ============ ============ PROPRIETAIRE ============ ============ ===========================// 
		   if(sit.equals("")) {
			  
			   if(typ.equals("SYNTHESE DES OPERATIONS")) {
					
					PdfPTable tableI1 = new PdfPTable(7);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
					tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {2.0f,1.3f,1.3f,0.8f,0.8f,0.8f,0.7f};tableI1.setWidths(colonneWidthI1);
					
							
					PdfPCell cellI13 = new PdfPCell(new Paragraph("Bien Immobilier",en));
					tableI1.addCell(cellI13);
					
					PdfPCell cellI14 = new PdfPCell(new Paragraph("Montant Proprietaire",en));
					tableI1.addCell(cellI14);
					
					PdfPCell cellI15 = new PdfPCell(new Paragraph("Commission Agence",en));
					tableI1.addCell(cellI15);
					
					PdfPCell cellI16 = new PdfPCell(new Paragraph("Impot Site",en));
					tableI1.addCell(cellI16);
					
					PdfPCell cellI17 = new PdfPCell(new Paragraph("Visite Site",en));
					tableI1.addCell(cellI17);
					
					PdfPCell cellI18 = new PdfPCell(new Paragraph("Autre Service",en));
					tableI1.addCell(cellI18);
					
					PdfPCell cellI19 = new PdfPCell(new Paragraph("Total",en));
					tableI1.addCell(cellI19);
					
					
	                List<Operation> listoperations	=	null;
					
					if("Bilan_journalier".equals(type)) {
						listoperations	=	operationDI.getAllPaieDateEP(agence.getCode(), date1s,proprietaire);
					}
					if("Bilan_par_date".equals(type)) {
						listoperations	=	operationDI.getAllPaieDateEP(agence.getCode(), debut,proprietaire);
					}
					if("Bilan_customise".equals(type)) {
						listoperations	=	operationDI.getAllPaieCustomEP(agence.getCode(), debut, fin,proprietaire);
					}
					if("Bilan_général".equals(type)) {
						listoperations	=	operationDI.getAllDateEP(agence.getCode(),proprietaire);
					}
					int Tot = 0,TG = 0, SoldP = 0, SoldA = 0, SoldI = 0, SoldV = 0, SoldAU = 0;
					for(Operation list : listoperations) {
						
					//	int index	=	listoperations.indexOf(list) + 1;
						Tot = list.getMontantA() + list.getMontantAU() + list.getMontantI() + list.getMontantP() + list.getMontantV() ;
						SoldP +=list.getMontantP(); SoldA +=list.getMontantA(); SoldI +=list.getMontantI();
						SoldV +=list.getMontantV(); SoldAU +=list.getMontantAU(); TG = SoldP+SoldA+SoldI+SoldV+SoldAU;
						
						PdfPCell cellI21 = new PdfPCell(new Paragraph(""+list.getSite(),en));
						cellI21.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI21);
						
						PdfPCell cellI22 = new PdfPCell(new Paragraph(""+list.getMontantP(),en));
						cellI22.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI22);
						
						PdfPCell cellI23 = new PdfPCell(new Paragraph(""+list.getMontantA(),en));
						cellI23.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI23);
						
						PdfPCell cellI24 = new PdfPCell(new Paragraph(""+list.getMontantI(),en));
						cellI24.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI24);
						
						PdfPCell cellI25 = new PdfPCell(new Paragraph(""+list.getMontantV(),en));
						cellI25.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI25);
						
						PdfPCell cellI26 = new PdfPCell(new Paragraph(""+list.getMontantAU(),en));
						cellI26.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI26);
						
						PdfPCell cellI27 = new PdfPCell(new Paragraph(""+Tot,en));
						cellI27.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI27);	
					}
					
					PdfPCell cellI28 = new PdfPCell(new Paragraph("TOTAL",en));cellI28.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);
					
					PdfPCell cellI29 = new PdfPCell(new Paragraph(""+SoldP,en));cellI29.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI29.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI29);
					
					PdfPCell cellI30 = new PdfPCell(new Paragraph(""+SoldA,en));cellI30.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI30.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI30);
					
					PdfPCell cellI31 = new PdfPCell(new Paragraph(""+SoldI,en));cellI31.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI31.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI31);
					
					PdfPCell cellI32 = new PdfPCell(new Paragraph(""+SoldV,en));cellI32.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI32.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI32);
					
					PdfPCell cellI33 = new PdfPCell(new Paragraph(""+SoldAU,en));cellI33.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI33.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI33);
					
					PdfPCell cellI34 = new PdfPCell(new Paragraph(""+TG,quis));cellI34.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI34.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI34);	
									
					document.add(tableI1);
													
				}
				
		     if(typ.equals("DETAILS DES PAIEMENTS")) {
					
					PdfPTable tableI1 = new PdfPTable(12);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
					tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {2.0f,1.3f,1.3f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f};tableI1.setWidths(colonneWidthI1);
					
							
					PdfPCell cellI13 = new PdfPCell(new Paragraph("Proprietaire",en));
					tableI1.addCell(cellI13);
					
					PdfPCell cellI14 = new PdfPCell(new Paragraph("Bien Immobilier",en));
					tableI1.addCell(cellI14);
					
					PdfPCell cellI15 = new PdfPCell(new Paragraph("Locataire",en));
					tableI1.addCell(cellI15);
					
					PdfPCell cellI16 = new PdfPCell(new Paragraph("Loyer Mensuel",en));
					tableI1.addCell(cellI16);
					
					PdfPCell cellI17 = new PdfPCell(new Paragraph("Mois",en));
					tableI1.addCell(cellI17);
					
					PdfPCell cellI18 = new PdfPCell(new Paragraph("Paiement Effectué",en));
					tableI1.addCell(cellI18);
					
					PdfPCell cellI19 = new PdfPCell(new Paragraph("Commission Agence (%)",en));
					tableI1.addCell(cellI19);
					
					PdfPCell cellI20 = new PdfPCell(new Paragraph("Commission Agence FCFA",en));
					tableI1.addCell(cellI20);
					
					PdfPCell cellI21 = new PdfPCell(new Paragraph("Frais Divers FCFA",en));
					tableI1.addCell(cellI21);
					
					PdfPCell cellI22 = new PdfPCell(new Paragraph("Frais de Visite ",en));
					tableI1.addCell(cellI22);
					
					PdfPCell cellI23 = new PdfPCell(new Paragraph("impot Deductibles FCFA",en));
					tableI1.addCell(cellI23);
					
					PdfPCell cellI24 = new PdfPCell(new Paragraph("Total Net Proprietaire",en));
					tableI1.addCell(cellI24);
					
	                 List<Operation> listoperations	=	null;
					
					if("Bilan_journalier".equals(type)) {
						listoperations	=	operationDI.getAllPaieDateP(agence.getCode(), date1s,proprietaire);
					}
					if("Bilan_par_date".equals(type)) {
						listoperations	=	operationDI.getAllPaieDateP(agence.getCode(), debut,proprietaire);
					}
					if("Bilan_customise".equals(type)) {
						listoperations	=	operationDI.getAllPaieCustomP(agence.getCode(), debut, fin,proprietaire);
					}
					if("Bilan_général".equals(type)) {
						listoperations	=	operationDI.getAllDateP(agence.getCode(),proprietaire);
					}
					
					int Tot = 0,TG = 0, SoldP = 0, SoldA = 0, SoldI = 0, SoldV = 0, SoldAU = 0, SoldPr = 0;
					for(Operation list : listoperations) {
						
						SoldPr += list.getApprovisonnement(); SoldP += list.getMontantP(); SoldA += list.getMontantA(); 
						SoldI += list.getMontantI();SoldV += list.getMontantV();SoldAU += list.getMontantAU();
								
						Site sites = siteDI.getSite(list.getSite());
						Locataire locataire = locataireDI.getLocataire(list.getMatricule());
						Maison Maisons = maisonDI.getMaison(list.getMatricule());
						
							PdfPCell cellI25 = new PdfPCell(new Paragraph(""+sites.getNom_prenom(),en));
							cellI25.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI25);
						
							PdfPCell cellI26 = new PdfPCell(new Paragraph(""+list.getSite(),en));
							cellI26.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI26);
							
							if(locataire != null) {
							 PdfPCell cellI27 = new PdfPCell(new Paragraph(""+locataire.getNom()+" "+locataire.getPrenom(),en));
							 cellI27.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI27);
							}else {
								 PdfPCell cellI27 = new PdfPCell(new Paragraph("",en));
								 cellI27.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI27);
							}
							
							if(Maisons != null) {
							PdfPCell cellI28 = new PdfPCell(new Paragraph(""+Maisons.getPrix(),en));
							cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);
							}else {
								PdfPCell cellI28 = new PdfPCell(new Paragraph("",en));
								cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);
							}
							
							PdfPCell cellI29 = new PdfPCell(new Paragraph(""+list.getMois()+" "+list.getAnnee(),en));
							cellI29.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI29);
							
							PdfPCell cellI30 = new PdfPCell(new Paragraph(""+list.getApprovisonnement(),en));
							cellI30.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI30);
							
							PdfPCell cellI31 = new PdfPCell(new Paragraph(""+list.getTaux()+"(%)",en));
							cellI31.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI31);
							
							PdfPCell cellI32 = new PdfPCell(new Paragraph(""+list.getMontantA(),en));
							cellI32.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI32);
							
							PdfPCell cellI33 = new PdfPCell(new Paragraph(""+list.getMontantAU(),en));
							cellI33.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI33);
							
							PdfPCell cellI34 = new PdfPCell(new Paragraph(""+list.getMontantV(),en));
							cellI34.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI34);
							
							PdfPCell cellI35 = new PdfPCell(new Paragraph(""+list.getMontantI(),en));
							cellI35.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI35);
							
							PdfPCell cellI36 = new PdfPCell(new Paragraph(""+list.getMontantP(),en));
							cellI36.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI36);
						}
					
					PdfPCell cellI37 = new PdfPCell(new Paragraph("TOTAL",en));cellI37.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI37.setColspan(5);cellI37.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI37);
					
					PdfPCell cellI38 = new PdfPCell(new Paragraph(""+SoldPr,en));cellI38.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI38.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI38);
					
					PdfPCell cellI39 = new PdfPCell(new Paragraph(" ",en));cellI39.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI39.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI39);
					
					PdfPCell cellI40 = new PdfPCell(new Paragraph(""+SoldA,en));cellI40.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI40.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI40);
					
					PdfPCell cellI41 = new PdfPCell(new Paragraph(""+SoldAU,en));cellI41.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI41.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI41);
					
					PdfPCell cellI42 = new PdfPCell(new Paragraph(""+SoldV,en));cellI42.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI42.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI42);
					
					PdfPCell cellI43 = new PdfPCell(new Paragraph(""+SoldI,en));cellI43.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI43.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI43);
					
					PdfPCell cellI44 = new PdfPCell(new Paragraph(""+SoldP,en));cellI44.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI44.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI44);
					
					
					document.add(tableI1);	
		      }  
			   
		   }
		   
		   else
  //============ ============ ============ SITE ============ ============ ===========================//    
		   if(sit!= null) {
			  
			   if(typ.equals("SYNTHESE DES OPERATIONS")) {
					
					PdfPTable tableI1 = new PdfPTable(7);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
					tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {2.0f,1.3f,1.3f,0.8f,0.8f,0.8f,0.7f};tableI1.setWidths(colonneWidthI1);
					
							
					PdfPCell cellI13 = new PdfPCell(new Paragraph("Bien Immobilier",en));
					tableI1.addCell(cellI13);
					
					PdfPCell cellI14 = new PdfPCell(new Paragraph("Montant Proprietaire",en));
					tableI1.addCell(cellI14);
					
					PdfPCell cellI15 = new PdfPCell(new Paragraph("Commission Agence",en));
					tableI1.addCell(cellI15);
					
					PdfPCell cellI16 = new PdfPCell(new Paragraph("Impot Site",en));
					tableI1.addCell(cellI16);
					
					PdfPCell cellI17 = new PdfPCell(new Paragraph("Visite Site",en));
					tableI1.addCell(cellI17);
					
					PdfPCell cellI18 = new PdfPCell(new Paragraph("Autre Service",en));
					tableI1.addCell(cellI18);
					
					PdfPCell cellI19 = new PdfPCell(new Paragraph("Total",en));
					tableI1.addCell(cellI19);
					
					
	                List<Operation> listoperations	=	null;
					
					if("Bilan_journalier".equals(type)) {
						listoperations	=	operationDI.getAllPaieDateES(agence.getCode(), date1s,sit);
					}
					if("Bilan_par_date".equals(type)) {
						listoperations	=	operationDI.getAllPaieDateES(agence.getCode(), debut,sit);
					}
					if("Bilan_customise".equals(type)) {
						listoperations	=	operationDI.getAllPaieCustomES(agence.getCode(), debut, fin,sit);
					}
					if("Bilan_général".equals(type)) {
						listoperations	=	operationDI.getAllDateES(agence.getCode(),sit);
					}
					int Tot = 0,TG = 0, SoldP = 0, SoldA = 0, SoldI = 0, SoldV = 0, SoldAU = 0;
					for(Operation list : listoperations) {
						
					//	int index	=	listoperations.indexOf(list) + 1;
						Tot = list.getMontantA() + list.getMontantAU() + list.getMontantI() + list.getMontantP() + list.getMontantV() ;
						SoldP +=list.getMontantP(); SoldA +=list.getMontantA(); SoldI +=list.getMontantI();
						SoldV +=list.getMontantV(); SoldAU +=list.getMontantAU(); TG = SoldP+SoldA+SoldI+SoldV+SoldAU;
						
						PdfPCell cellI21 = new PdfPCell(new Paragraph(""+list.getSite(),en));
						cellI21.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI21);
						
						PdfPCell cellI22 = new PdfPCell(new Paragraph(""+list.getMontantP(),en));
						cellI22.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI22);
						
						PdfPCell cellI23 = new PdfPCell(new Paragraph(""+list.getMontantA(),en));
						cellI23.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI23);
						
						PdfPCell cellI24 = new PdfPCell(new Paragraph(""+list.getMontantI(),en));
						cellI24.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI24);
						
						PdfPCell cellI25 = new PdfPCell(new Paragraph(""+list.getMontantV(),en));
						cellI25.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI25);
						
						PdfPCell cellI26 = new PdfPCell(new Paragraph(""+list.getMontantAU(),en));
						cellI26.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI26);
						
						PdfPCell cellI27 = new PdfPCell(new Paragraph(""+Tot,en));
						cellI27.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI27);	
					}
					
					PdfPCell cellI28 = new PdfPCell(new Paragraph("TOTAL",en));cellI28.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);
					
					PdfPCell cellI29 = new PdfPCell(new Paragraph(""+SoldP,en));cellI29.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI29.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI29);
					
					PdfPCell cellI30 = new PdfPCell(new Paragraph(""+SoldA,en));cellI30.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI30.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI30);
					
					PdfPCell cellI31 = new PdfPCell(new Paragraph(""+SoldI,en));cellI31.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI31.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI31);
					
					PdfPCell cellI32 = new PdfPCell(new Paragraph(""+SoldV,en));cellI32.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI32.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI32);
					
					PdfPCell cellI33 = new PdfPCell(new Paragraph(""+SoldAU,en));cellI33.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI33.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI33);
					
					PdfPCell cellI34 = new PdfPCell(new Paragraph(""+TG,quis));cellI34.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI34.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI34);	
									
					document.add(tableI1);
													
				}
				
		     if(typ.equals("DETAILS DES PAIEMENTS")) {
					
					PdfPTable tableI1 = new PdfPTable(12);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
					tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {2.0f,1.3f,1.3f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f};tableI1.setWidths(colonneWidthI1);
					
							
					PdfPCell cellI13 = new PdfPCell(new Paragraph("Proprietaire",en));
					tableI1.addCell(cellI13);
					
					PdfPCell cellI14 = new PdfPCell(new Paragraph("Bien Immobilier",en));
					tableI1.addCell(cellI14);
					
					PdfPCell cellI15 = new PdfPCell(new Paragraph("Locataire",en));
					tableI1.addCell(cellI15);
					
					PdfPCell cellI16 = new PdfPCell(new Paragraph("Loyer Mensuel",en));
					tableI1.addCell(cellI16);
					
					PdfPCell cellI17 = new PdfPCell(new Paragraph("Mois",en));
					tableI1.addCell(cellI17);
					
					PdfPCell cellI18 = new PdfPCell(new Paragraph("Paiement Effectué",en));
					tableI1.addCell(cellI18);
					
					PdfPCell cellI19 = new PdfPCell(new Paragraph("Commission Agence (%)",en));
					tableI1.addCell(cellI19);
					
					PdfPCell cellI20 = new PdfPCell(new Paragraph("Commission Agence FCFA",en));
					tableI1.addCell(cellI20);
					
					PdfPCell cellI21 = new PdfPCell(new Paragraph("Frais Divers FCFA",en));
					tableI1.addCell(cellI21);
					
					PdfPCell cellI22 = new PdfPCell(new Paragraph("Frais de Visite ",en));
					tableI1.addCell(cellI22);
					
					PdfPCell cellI23 = new PdfPCell(new Paragraph("impot Deductibles FCFA",en));
					tableI1.addCell(cellI23);
					
					PdfPCell cellI24 = new PdfPCell(new Paragraph("Total Net Proprietaire",en));
					tableI1.addCell(cellI24);
					
	                 List<Operation> listoperations	=	null;
					
					if("Bilan_journalier".equals(type)) {
						listoperations	=	operationDI.getAllPaieDateS(agence.getCode(), date1s,sit);
					}
					if("Bilan_par_date".equals(type)) {
						listoperations	=	operationDI.getAllPaieDateS(agence.getCode(), debut,sit);
					}
					if("Bilan_customise".equals(type)) {
						listoperations	=	operationDI.getAllPaieCustomS(agence.getCode(), debut, fin,sit);
					}
					if("Bilan_général".equals(type)) {
						listoperations	=	operationDI.getAllDateS(agence.getCode(),sit);
					}
					
					int Tot = 0,TG = 0, SoldP = 0, SoldA = 0, SoldI = 0, SoldV = 0, SoldAU = 0, SoldPr = 0;
					for(Operation list : listoperations) {
						
						SoldPr += list.getApprovisonnement(); SoldP += list.getMontantP(); SoldA += list.getMontantA(); 
						SoldI += list.getMontantI();SoldV += list.getMontantV();SoldAU += list.getMontantAU();
								
						Site sites = siteDI.getSite(list.getSite());
						Locataire locataire = locataireDI.getLocataire(list.getMatricule());
						Maison Maisons = maisonDI.getMaison(list.getMatricule());
						
							PdfPCell cellI25 = new PdfPCell(new Paragraph(""+sites.getNom_prenom(),en));
							cellI25.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI25);
						
							PdfPCell cellI26 = new PdfPCell(new Paragraph(""+list.getSite(),en));
							cellI26.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI26);
							
							if(locataire != null) {
							 PdfPCell cellI27 = new PdfPCell(new Paragraph(""+locataire.getNom()+" "+locataire.getPrenom(),en));
							 cellI27.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI27);
							}else {
								 PdfPCell cellI27 = new PdfPCell(new Paragraph("",en));
								 cellI27.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI27);
							}
							
							if(Maisons != null) {
							PdfPCell cellI28 = new PdfPCell(new Paragraph(""+Maisons.getPrix(),en));
							cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);
							}else {
								PdfPCell cellI28 = new PdfPCell(new Paragraph("",en));
								cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);
							}
							
							PdfPCell cellI29 = new PdfPCell(new Paragraph(""+list.getMois()+" "+list.getAnnee(),en));
							cellI29.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI29);
							
							PdfPCell cellI30 = new PdfPCell(new Paragraph(""+list.getApprovisonnement(),en));
							cellI30.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI30);
							
							PdfPCell cellI31 = new PdfPCell(new Paragraph(""+list.getTaux()+"(%)",en));
							cellI31.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI31);
							
							PdfPCell cellI32 = new PdfPCell(new Paragraph(""+list.getMontantA(),en));
							cellI32.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI32);
							
							PdfPCell cellI33 = new PdfPCell(new Paragraph(""+list.getMontantAU(),en));
							cellI33.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI33);
							
							PdfPCell cellI34 = new PdfPCell(new Paragraph(""+list.getMontantV(),en));
							cellI34.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI34);
							
							PdfPCell cellI35 = new PdfPCell(new Paragraph(""+list.getMontantI(),en));
							cellI35.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI35);
							
							PdfPCell cellI36 = new PdfPCell(new Paragraph(""+list.getMontantP(),en));
							cellI36.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI36);
						}
					
					PdfPCell cellI37 = new PdfPCell(new Paragraph("TOTAL",en));cellI37.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI37.setColspan(5);cellI37.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI37);
					
					PdfPCell cellI38 = new PdfPCell(new Paragraph(""+SoldPr,en));cellI38.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI38.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI38);
					
					PdfPCell cellI39 = new PdfPCell(new Paragraph(" ",en));cellI39.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI39.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI39);
					
					PdfPCell cellI40 = new PdfPCell(new Paragraph(""+SoldA,en));cellI40.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI40.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI40);
					
					PdfPCell cellI41 = new PdfPCell(new Paragraph(""+SoldAU,en));cellI41.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI41.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI41);
					
					PdfPCell cellI42 = new PdfPCell(new Paragraph(""+SoldV,en));cellI42.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI42.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI42);
					
					PdfPCell cellI43 = new PdfPCell(new Paragraph(""+SoldI,en));cellI43.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI43.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI43);
					
					PdfPCell cellI44 = new PdfPCell(new Paragraph(""+SoldP,en));cellI44.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI44.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI44);
					
					
					document.add(tableI1);	
		      }   
			   
		   }
     
		   
	   }
		
		
	     	     
				 
			document.close();	
		}catch(DocumentException de) {
			de.printStackTrace();
		} catch(IOException ie) {
			ie.printStackTrace();
		}
	}	
}
