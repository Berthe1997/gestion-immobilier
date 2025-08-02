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

@WebServlet("/Etat_Paiement")
public class Etat_Paiement extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	
	
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
		String sit	=	request.getParameter("sit");
		String ans	=	request.getParameter("ans");

		String uploadDir = this.getServletContext().getInitParameter("upload_dir");
		
		users = (Users) session.getAttribute("users");
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		}
		
				
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
						
			PdfPCell cellt2 = new PdfPCell(new Paragraph(""+sit,en));cellt2.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellt2.setBorder(0);cellt2.setColspan(2);cellt2.setRowspan(2);header.addCell(cellt2);
			
			PdfPCell cellt3 = new PdfPCell(new Paragraph("TEL :"+agence.getTel(),ct));cellt3.setIndent(35);
			cellt3.setBorder(0);cellt3.setColspan(4);header.addCell(cellt3);
			
			PdfPCell cellt4 = new PdfPCell(new Paragraph(" "));cellt4.setColspan(4);
			cellt4.setBorder(0);header.addCell(cellt4);
			
			PdfPCell cellt5 = new PdfPCell(new Paragraph(""+typ,quis));cellt5.setColspan(4);
			cellt5.setBackgroundColor(BaseColor.CYAN);cellt5.setHorizontalAlignment(Element.ALIGN_CENTER);header.addCell(cellt5);
			
			document.add(header);
			
				     
	     if(type.equals("EtatP")) {   
	    	 
	    	 PdfPTable tableI1 = new PdfPTable(15);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
				tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {0.6f,2.2f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,1.0f};tableI1.setWidths(colonneWidthI1);
				
						
				PdfPCell cellI13 = new PdfPCell(new Paragraph("Num",en));
				tableI1.addCell(cellI13);
				
				PdfPCell cellI14 = new PdfPCell(new Paragraph("Nom et Prénoms",en));
				tableI1.addCell(cellI14);
				
				PdfPCell cellI15 = new PdfPCell(new Paragraph("janv",en));
				tableI1.addCell(cellI15);
				
				PdfPCell cellI16 = new PdfPCell(new Paragraph("févr",en));
				tableI1.addCell(cellI16);
				
				PdfPCell cellI17 = new PdfPCell(new Paragraph("mars",en));
				tableI1.addCell(cellI17);
				
				PdfPCell cellI18 = new PdfPCell(new Paragraph("avril",en));
				tableI1.addCell(cellI18);
				
				PdfPCell cellI19 = new PdfPCell(new Paragraph("mai",en));
				tableI1.addCell(cellI19);
				
				PdfPCell cellI20 = new PdfPCell(new Paragraph("juin",en));
				tableI1.addCell(cellI20);
				
				PdfPCell cellI21 = new PdfPCell(new Paragraph("juil",en));
				tableI1.addCell(cellI21);
				
				PdfPCell cellI22 = new PdfPCell(new Paragraph("août",en));
				tableI1.addCell(cellI22);
				
				PdfPCell cellI23 = new PdfPCell(new Paragraph("sept",en));
				tableI1.addCell(cellI23);
				
				PdfPCell cellI24 = new PdfPCell(new Paragraph("oct",en));
				tableI1.addCell(cellI24);
				
				PdfPCell cellI25 = new PdfPCell(new Paragraph("nov",en));
				tableI1.addCell(cellI25);
				
				PdfPCell cellI26 = new PdfPCell(new Paragraph("déc",en));
				tableI1.addCell(cellI26);
				
				PdfPCell cellI27 = new PdfPCell(new Paragraph("Total",en));
				tableI1.addCell(cellI27);
				
				int ansU = Integer.parseInt(ans);
				List<Calendrier_paiement> listEt = calendrier_paiementDI.getAllCalendrier_paiementPDFL(sit,ansU);
				
				 int Tots =0,TotJs =0,TotFs =0,TotMs	=0,TotAs =0,TotMAs =0,TotJUs =0,TotJLs =0,TotAUs =0,TotSs =0,TotOs =0,TotNs =0,TotDs =0;
             for(Calendrier_paiement clas : listEt) {
					
					int index	=	listEt.indexOf(clas) + 1;					
					
				PdfPCell cellI28 = new PdfPCell(new Paragraph(""+index,en));
				cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);		
									 				
				PdfPCell cellI29 = new PdfPCell(new Paragraph(""+clas.getLocataire(),en));
				cellI29.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI29);
																
				Calendrier_paiement calendJ = calendrier_paiementDI.getCalendrier_paiement(ansU,"Janvier",clas.getMatricule());
				Calendrier_paiement calendF = calendrier_paiementDI.getCalendrier_paiement(ansU,"Février",clas.getMatricule());
				Calendrier_paiement calendM = calendrier_paiementDI.getCalendrier_paiement(ansU,"Mars",clas.getMatricule());
				Calendrier_paiement calendA = calendrier_paiementDI.getCalendrier_paiement(ansU,"Avril",clas.getMatricule());
				Calendrier_paiement calendMA = calendrier_paiementDI.getCalendrier_paiement(ansU,"Mai",clas.getMatricule());
				Calendrier_paiement calendJU = calendrier_paiementDI.getCalendrier_paiement(ansU,"Juin",clas.getMatricule());
				Calendrier_paiement calendJL = calendrier_paiementDI.getCalendrier_paiement(ansU,"Juillet",clas.getMatricule());
				Calendrier_paiement calendAU = calendrier_paiementDI.getCalendrier_paiement(ansU,"Août",clas.getMatricule());
				Calendrier_paiement calendS = calendrier_paiementDI.getCalendrier_paiement(ansU,"Septembre",clas.getMatricule());
				Calendrier_paiement calendO = calendrier_paiementDI.getCalendrier_paiement(ansU,"Octobre",clas.getMatricule());
				Calendrier_paiement calendN = calendrier_paiementDI.getCalendrier_paiement(ansU,"Novembre",clas.getMatricule());
				Calendrier_paiement calendD = calendrier_paiementDI.getCalendrier_paiement(ansU,"Décembre",clas.getMatricule());
				
				int Tot =0,TotJ =0,TotF =0,TotM	=0,TotA =0,TotMA =0,TotJU =0,TotJL =0,TotAU =0,TotS =0,TotO =0,TotN =0,TotD	=0;
										
				if(calendJ != null) {	
					TotJ +=calendJ.getMontant_P();
				PdfPCell cellI30 = new PdfPCell(new Paragraph(""+calendJ.getMontant_P(),en));
				cellI30.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI30);
				}else {
					PdfPCell cellI30 = new PdfPCell(new Paragraph("",en));
					cellI30.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI30);
				}
								
				if(calendF != null) {
					TotF +=calendF.getMontant_P();
				PdfPCell cellI31 = new PdfPCell(new Paragraph(""+calendF.getMontant_P(),en));
				cellI31.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI31);
				}else {
					PdfPCell cellI31 = new PdfPCell(new Paragraph("",en));
					cellI31.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI31);
				}
								
				if(calendM != null) {
					TotM +=calendM.getMontant_P();
				PdfPCell cellI32 = new PdfPCell(new Paragraph(""+calendM.getMontant_P(),en));
				cellI32.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI32);
				}else {
					PdfPCell cellI32 = new PdfPCell(new Paragraph("",en));
					cellI32.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI32);
				}
								
				if(calendA != null) {
					TotA +=calendA.getMontant_P();
				PdfPCell cellI33 = new PdfPCell(new Paragraph(""+calendA.getMontant_P(),en));
				cellI33.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI33);
				}else {
					PdfPCell cellI33 = new PdfPCell(new Paragraph("",en));
					cellI33.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI33);
				}
								
				if(calendMA != null) {
					TotMA +=calendMA.getMontant_P();
				PdfPCell cellI34 = new PdfPCell(new Paragraph(""+calendMA.getMontant_P(),en));
				cellI34.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI34);
				}else {
					PdfPCell cellI34 = new PdfPCell(new Paragraph("",en));
					cellI34.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI34);
				}
								
				if(calendJU != null) {
					TotJU +=calendJU.getMontant_P();
				PdfPCell cellI35 = new PdfPCell(new Paragraph(""+calendJU.getMontant_P(),en));
				cellI35.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI35);
				}else {
					PdfPCell cellI35 = new PdfPCell(new Paragraph("",en));
					cellI35.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI35);
				}
								
				if(calendJL != null) {
					TotJL +=calendJL.getMontant_P();
				PdfPCell cellI36 = new PdfPCell(new Paragraph(""+calendJL.getMontant_P(),en));
				cellI36.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI36);
				}else {
					PdfPCell cellI36 = new PdfPCell(new Paragraph("",en));
					cellI36.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI36);	
				}				
				
				if(calendAU != null) {
					TotAU +=calendAU.getMontant_P();
				PdfPCell cellI37 = new PdfPCell(new Paragraph(""+calendAU.getMontant_P(),en));
				cellI37.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI37);
				}else {
					PdfPCell cellI37 = new PdfPCell(new Paragraph("",en));
					cellI37.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI37);
				}
				
				if(calendS != null) {
					TotS +=calendS.getMontant_P();
				PdfPCell cellI38 = new PdfPCell(new Paragraph(""+calendS.getMontant_P(),en));
				cellI38.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI38);
				}else {
					PdfPCell cellI38 = new PdfPCell(new Paragraph("",en));
					cellI38.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI38);
				}
								
				if(calendO != null) {
					TotO +=calendO.getMontant_P();
				PdfPCell cellI39 = new PdfPCell(new Paragraph(""+calendO.getMontant_P(),en));
				cellI39.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI39);
				}else {
					PdfPCell cellI39 = new PdfPCell(new Paragraph(" ",en));
					cellI39.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI39);
				}
								
				if(calendN != null) {
					TotN +=calendN.getMontant_P();
				PdfPCell cellI40 = new PdfPCell(new Paragraph(""+calendN.getMontant_P(),en));
				cellI40.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI40);
				}else {
					PdfPCell cellI40 = new PdfPCell(new Paragraph("",en));
					cellI40.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI40);	
				}
								
				if(calendD != null) {
					TotD +=calendD.getMontant_P();
				PdfPCell cellI41 = new PdfPCell(new Paragraph(""+calendD.getMontant_P(),en));
				cellI41.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI41);
				}else {
					PdfPCell cellI41 = new PdfPCell(new Paragraph("",en));
					cellI41.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI41);
				}
								
				Tot = TotJ+TotF+TotM+TotA+TotMA+TotJU+TotJL+TotAU+TotS+TotO+TotN+TotD;
				PdfPCell cellI42 = new PdfPCell(new Paragraph(""+Tot,en));
				cellI42.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI42);
				
				
	             Tots +=Tot; TotJs +=TotJ; TotFs +=TotF; TotMs +=TotM; TotAs +=TotA; TotMAs +=TotMA; TotJUs +=TotJU; 
	             TotJLs +=TotJL; TotAUs +=TotAU; TotSs +=TotS; TotOs +=TotO; TotNs +=TotN; TotDs +=TotD;
				 }
                        
                PdfPCell cellI43 = new PdfPCell(new Paragraph("TOTAL",en));cellI43.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cellI43.setColspan(2);cellI43.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI43);
				
				PdfPCell cellI44 = new PdfPCell(new Paragraph(""+TotJs,en));cellI44.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI44.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI44);
				
				PdfPCell cellI45 = new PdfPCell(new Paragraph(""+TotFs,en));cellI45.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI45.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI45);
				
				PdfPCell cellI46 = new PdfPCell(new Paragraph(""+TotMs,en));cellI46.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI46.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI46);
				
				PdfPCell cellI47 = new PdfPCell(new Paragraph(""+TotAs,en));cellI47.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI47.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI47);
				
				PdfPCell cellI48 = new PdfPCell(new Paragraph(""+TotMAs,en));cellI48.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI48.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI48);
				
				PdfPCell cellI49 = new PdfPCell(new Paragraph(""+TotJUs,en));cellI49.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI49.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI49);
				
				PdfPCell cellI50 = new PdfPCell(new Paragraph(""+TotJLs,en));cellI50.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI50.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI50);
				
				PdfPCell cellI51 = new PdfPCell(new Paragraph(""+TotAUs,en));cellI51.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI51.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI51);
					
				PdfPCell cellI52 = new PdfPCell(new Paragraph(""+TotSs,en));cellI52.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI52.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI52);
					
				PdfPCell cellI53 = new PdfPCell(new Paragraph(""+TotOs,en));cellI53.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI53.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI53);
					
				PdfPCell cellI54 = new PdfPCell(new Paragraph(""+TotNs,en));cellI54.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI54.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI54);
					
				PdfPCell cellI55 = new PdfPCell(new Paragraph(""+TotDs,en));cellI55.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI55.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI55);
					
				PdfPCell cellI56 = new PdfPCell(new Paragraph(""+Tots,en));cellI56.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellI56.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI56);
             					            						
				document.add(tableI1);		    	 
	     }
	     
	     
         if(type.equals("EtatA")) {   
	    	 
	    	 PdfPTable tableI1 = new PdfPTable(16);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
				tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {0.5f,2.0f,0.9f,0.9f,0.9f,0.9f,0.9f,0.9f,0.9f,0.9f,0.9f,0.9f,0.9f,0.9f,0.9f,1.1f};tableI1.setWidths(colonneWidthI1);
				
						
				PdfPCell cellI13 = new PdfPCell(new Paragraph("Nu",en));
				tableI1.addCell(cellI13);
				
				PdfPCell cellI14 = new PdfPCell(new Paragraph("Nom et Prénoms",en));
				tableI1.addCell(cellI14);
				
				PdfPCell cellA15 = new PdfPCell(new Paragraph("Arriéré",en));
				tableI1.addCell(cellA15);
				
				PdfPCell cellI15 = new PdfPCell(new Paragraph("janv",en));
				tableI1.addCell(cellI15);
				
				PdfPCell cellI16 = new PdfPCell(new Paragraph("févr",en));
				tableI1.addCell(cellI16);
				
				PdfPCell cellI17 = new PdfPCell(new Paragraph("mars",en));
				tableI1.addCell(cellI17);
				
				PdfPCell cellI18 = new PdfPCell(new Paragraph("avril",en));
				tableI1.addCell(cellI18);
				
				PdfPCell cellI19 = new PdfPCell(new Paragraph("mai",en));
				tableI1.addCell(cellI19);
				
				PdfPCell cellI20 = new PdfPCell(new Paragraph("juin",en));
				tableI1.addCell(cellI20);
				
				PdfPCell cellI21 = new PdfPCell(new Paragraph("juil",en));
				tableI1.addCell(cellI21);
				
				PdfPCell cellI22 = new PdfPCell(new Paragraph("août",en));
				tableI1.addCell(cellI22);
				
				PdfPCell cellI23 = new PdfPCell(new Paragraph("sept",en));
				tableI1.addCell(cellI23);
				
				PdfPCell cellI24 = new PdfPCell(new Paragraph("oct",en));
				tableI1.addCell(cellI24);
				
				PdfPCell cellI25 = new PdfPCell(new Paragraph("nov",en));
				tableI1.addCell(cellI25);
				
				PdfPCell cellI26 = new PdfPCell(new Paragraph("déc",en));
				tableI1.addCell(cellI26);
				
				PdfPCell cellI27 = new PdfPCell(new Paragraph("Total",en));
				tableI1.addCell(cellI27);
				
				int ansU = Integer.parseInt(ans);
				List<Calendrier_paiement> listEt = calendrier_paiementDI.getAllCalendrier_paiementPDFA(sit,ansU);
				
				 int TotR =0;
				 int Tots =0,TotJs =0,TotFs =0,TotMs	=0,TotAs =0,TotMAs =0,TotJUs =0,TotJLs =0,TotAUs =0,TotSs =0,TotOs =0,TotNs =0,TotDs =0;
	             for(Calendrier_paiement clas : listEt) {
						
						int index	=	listEt.indexOf(clas) + 1;					
						
					PdfPCell cellI28 = new PdfPCell(new Paragraph(""+index,en));
					cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);		
										 				
					PdfPCell cellI29 = new PdfPCell(new Paragraph(""+clas.getLocataire(),enP));
					cellI29.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI29);
					
					Calendrier_paiement calendMR = calendrier_paiementDI.getCalendrier_paiement(ansU,clas.getMatricule());
																				
					Calendrier_paiement calendJ = calendrier_paiementDI.getCalendrier_paiement(ansU,"Janvier",clas.getMatricule());
					Calendrier_paiement calendF = calendrier_paiementDI.getCalendrier_paiement(ansU,"Février",clas.getMatricule());
					Calendrier_paiement calendM = calendrier_paiementDI.getCalendrier_paiement(ansU,"Mars",clas.getMatricule());
					Calendrier_paiement calendA = calendrier_paiementDI.getCalendrier_paiement(ansU,"Avril",clas.getMatricule());
					Calendrier_paiement calendMA = calendrier_paiementDI.getCalendrier_paiement(ansU,"Mai",clas.getMatricule());
					Calendrier_paiement calendJU = calendrier_paiementDI.getCalendrier_paiement(ansU,"Juin",clas.getMatricule());
					Calendrier_paiement calendJL = calendrier_paiementDI.getCalendrier_paiement(ansU,"Juillet",clas.getMatricule());
					Calendrier_paiement calendAU = calendrier_paiementDI.getCalendrier_paiement(ansU,"Août",clas.getMatricule());
					Calendrier_paiement calendS = calendrier_paiementDI.getCalendrier_paiement(ansU,"Septembre",clas.getMatricule());
					Calendrier_paiement calendO = calendrier_paiementDI.getCalendrier_paiement(ansU,"Octobre",clas.getMatricule());
					Calendrier_paiement calendN = calendrier_paiementDI.getCalendrier_paiement(ansU,"Novembre",clas.getMatricule());
					Calendrier_paiement calendD = calendrier_paiementDI.getCalendrier_paiement(ansU,"Décembre",clas.getMatricule());
					
					int Tot =0,TotJ =0,TotF =0,TotM	=0,TotA =0,TotMA =0,TotJU =0,TotJL =0,TotAU =0,TotS =0,TotO =0,TotN =0,TotD	=0;					
					//int Totr =0;
					if(calendMR != null) {
						TotR +=calendMR.getMontant_R();
					PdfPCell cellA29 = new PdfPCell(new Paragraph(""+calendMR.getMontant_R(),en));
					cellA29.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellA29);
					}else {
						PdfPCell cellA29 = new PdfPCell(new Paragraph("0",en));
						cellA29.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellA29);
					}
					
					if(calendJ != null) {	
						TotJ +=calendJ.getMontant_R();
					PdfPCell cellI30 = new PdfPCell(new Paragraph(""+calendJ.getMontant_R(),en));
					cellI30.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI30);
					}else {
						PdfPCell cellI30 = new PdfPCell(new Paragraph("",en));
						cellI30.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI30);
					}
									
					if(calendF != null) {
						TotF +=calendF.getMontant_R();
					PdfPCell cellI31 = new PdfPCell(new Paragraph(""+calendF.getMontant_R(),en));
					cellI31.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI31);
					}else {
						PdfPCell cellI31 = new PdfPCell(new Paragraph("",en));
						cellI31.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI31);
					}
									
					if(calendM != null) {
						TotM +=calendM.getMontant_R();
					PdfPCell cellI32 = new PdfPCell(new Paragraph(""+calendM.getMontant_R(),en));
					cellI32.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI32);
					}else {
						PdfPCell cellI32 = new PdfPCell(new Paragraph("",en));
						cellI32.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI32);
					}
									
					if(calendA != null) {
						TotA +=calendA.getMontant_R();
					PdfPCell cellI33 = new PdfPCell(new Paragraph(""+calendA.getMontant_R(),en));
					cellI33.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI33);
					}else {
						PdfPCell cellI33 = new PdfPCell(new Paragraph("",en));
						cellI33.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI33);
					}
									
					if(calendMA != null) {
						TotMA +=calendMA.getMontant_R();
					PdfPCell cellI34 = new PdfPCell(new Paragraph(""+calendMA.getMontant_R(),en));
					cellI34.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI34);
					}else {
						PdfPCell cellI34 = new PdfPCell(new Paragraph("",en));
						cellI34.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI34);
					}
									
					if(calendJU != null) {
						TotJU +=calendJU.getMontant_R();
					PdfPCell cellI35 = new PdfPCell(new Paragraph(""+calendJU.getMontant_R(),en));
					cellI35.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI35);
					}else {
						PdfPCell cellI35 = new PdfPCell(new Paragraph("",en));
						cellI35.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI35);
					}
									
					if(calendJL != null) {
						TotJL +=calendJL.getMontant_R();
					PdfPCell cellI36 = new PdfPCell(new Paragraph(""+calendJL.getMontant_R(),en));
					cellI36.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI36);
					}else {
						PdfPCell cellI36 = new PdfPCell(new Paragraph("",en));
						cellI36.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI36);	
					}				
					
					if(calendAU != null) {
						TotAU +=calendAU.getMontant_R();
					PdfPCell cellI37 = new PdfPCell(new Paragraph(""+calendAU.getMontant_R(),en));
					cellI37.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI37);
					}else {
						PdfPCell cellI37 = new PdfPCell(new Paragraph("",en));
						cellI37.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI37);
					}
					
					if(calendS != null) {
						TotS +=calendS.getMontant_R();
					PdfPCell cellI38 = new PdfPCell(new Paragraph(""+calendS.getMontant_R(),en));
					cellI38.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI38);
					}else {
						PdfPCell cellI38 = new PdfPCell(new Paragraph("",en));
						cellI38.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI38);
					}
									
					if(calendO != null) {
						TotO +=calendO.getMontant_R();
					PdfPCell cellI39 = new PdfPCell(new Paragraph(""+calendO.getMontant_R(),en));
					cellI39.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI39);
					}else {
						PdfPCell cellI39 = new PdfPCell(new Paragraph(" ",en));
						cellI39.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI39);
					}
									
					if(calendN != null) {
						TotN +=calendN.getMontant_R();
					PdfPCell cellI40 = new PdfPCell(new Paragraph(""+calendN.getMontant_R(),en));
					cellI40.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI40);
					}else {
						PdfPCell cellI40 = new PdfPCell(new Paragraph("",en));
						cellI40.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI40);	
					}
									
					if(calendD != null) {
						TotD +=calendD.getMontant_R();
					PdfPCell cellI41 = new PdfPCell(new Paragraph(""+calendD.getMontant_R(),en));
					cellI41.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI41);
					}else {
						PdfPCell cellI41 = new PdfPCell(new Paragraph("",en));
						cellI41.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI41);
					}
									
					Tot = TotJ+TotF+TotM+TotA+TotMA+TotJU+TotJL+TotAU+TotS+TotO+TotN+TotD;
					PdfPCell cellI42 = new PdfPCell(new Paragraph(""+Tot,en));
					cellI42.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI42);
					
					 //TotR +=Totr;
		             Tots +=Tot; TotJs +=TotJ; TotFs +=TotF; TotMs +=TotM; TotAs +=TotA; TotMAs +=TotMA; TotJUs +=TotJU; 
		             TotJLs +=TotJL; TotAUs +=TotAU; TotSs +=TotS; TotOs +=TotO; TotNs +=TotN; TotDs +=TotD;
					 }
	                        
	                PdfPCell cellI43 = new PdfPCell(new Paragraph("TOTAL",en));cellI43.setBackgroundColor(BaseColor.LIGHT_GRAY);
	                cellI43.setColspan(2);cellI43.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI43);
					
					PdfPCell cellA44 = new PdfPCell(new Paragraph(""+TotR,en));cellA44.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellA44.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellA44);
					
					PdfPCell cellI44 = new PdfPCell(new Paragraph(""+TotJs,en));cellI44.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI44.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI44);
					
					PdfPCell cellI45 = new PdfPCell(new Paragraph(""+TotFs,en));cellI45.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI45.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI45);
					
					PdfPCell cellI46 = new PdfPCell(new Paragraph(""+TotMs,en));cellI46.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI46.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI46);
					
					PdfPCell cellI47 = new PdfPCell(new Paragraph(""+TotAs,en));cellI47.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI47.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI47);
					
					PdfPCell cellI48 = new PdfPCell(new Paragraph(""+TotMAs,en));cellI48.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI48.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI48);
					
					PdfPCell cellI49 = new PdfPCell(new Paragraph(""+TotJUs,en));cellI49.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI49.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI49);
					
					PdfPCell cellI50 = new PdfPCell(new Paragraph(""+TotJLs,en));cellI50.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI50.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI50);
					
					PdfPCell cellI51 = new PdfPCell(new Paragraph(""+TotAUs,en));cellI51.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI51.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI51);
						
					PdfPCell cellI52 = new PdfPCell(new Paragraph(""+TotSs,en));cellI52.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI52.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI52);
						
					PdfPCell cellI53 = new PdfPCell(new Paragraph(""+TotOs,en));cellI53.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI53.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI53);
						
					PdfPCell cellI54 = new PdfPCell(new Paragraph(""+TotNs,en));cellI54.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI54.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI54);
						
					PdfPCell cellI55 = new PdfPCell(new Paragraph(""+TotDs,en));cellI55.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI55.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI55);
						
					PdfPCell cellI56 = new PdfPCell(new Paragraph(""+Tots,en));cellI56.setBackgroundColor(BaseColor.LIGHT_GRAY);
					cellI56.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI56);
				
				document.add(tableI1);		    	 
	     }
	     
				 
			document.close();	
		}catch(DocumentException de) {
			de.printStackTrace();
		} catch(IOException ie) {
			ie.printStackTrace();
		}
	}	
}

