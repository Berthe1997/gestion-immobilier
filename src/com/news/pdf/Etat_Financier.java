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
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.news.beans.Agence;
import com.news.beans.Calendrier_paiement;
import com.news.beans.Locataire;
import com.news.beans.Maison;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.AgenceDI;
import com.news.dao.Calendrier_paiementDI;
import com.news.dao.LocataireDI;
import com.news.dao.MaisonDI;
import com.news.dao.SiteDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/Etat_Financier")
public class Etat_Financier extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	private static final String FORMAT_DATE 			= "dd/MM/yyyy";
	
	Users users	=	new Users();
	
	AgenceDI agenceDI = new AgenceDI();
	Agence agence = new Agence();
	
	LocataireDI locataireDI = new LocataireDI();
	Locataire locataire = new Locataire();
	
	SiteDI siteDI = new SiteDI();
	Site site = new Site();
	
	 MaisonDI maisonDI = new MaisonDI();
	 Maison maison = new Maison();
	
	 Calendrier_paiementDI calendrier_paiementDI = new Calendrier_paiementDI();
	 Calendrier_paiement calendrier_paiement = new Calendrier_paiement();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = request.getSession();
		
	//	String id		=	request.getParameter("id");
		String matE		=	request.getParameter("matE");
		String code		=	request.getParameter("code");
		String type	=	request.getParameter("type");		
		String ans	=	request.getParameter("ans");
		String mois	=	request.getParameter("mois");	
			
		String uploadDir = this.getServletContext().getInitParameter("upload_dir");
		
		users = (Users) session.getAttribute("users");
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		}
		
		
		
		agence = agenceDI.getAgence(code);
		
		maison = maisonDI.getMaison(matE);
		
		locataire = locataireDI.getLocataire(matE);
		
	//	int ansU = Integer.parseInt(ans);
	//	calendrier_paiement = calendrier_paiementDI.getCalendrier_paiement(ansU,mois,matE);
		
		DateTime dt = new DateTime();		
		DateTimeFormatter formatter = DateTimeFormat.forPattern( FORMAT_DATE );		
		String date = dt.toString( formatter );
		
		
		Document document = new Document();
		try{
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline; filename=QUITANCE DE LOYER.pdf");
			@SuppressWarnings("unused")
			PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
						
			Font en = new Font(FontFamily.TIMES_ROMAN,9.0f, Font.NORMAL, BaseColor.BLACK);			
			Font entete = new Font(FontFamily.HELVETICA,8.0f, Font.BOLD, BaseColor.BLACK);		
			
			Font qui = new Font(FontFamily.HELVETICA,11.0f, Font.BOLD, BaseColor.BLACK);
			Font ct = new Font(FontFamily.TIMES_ROMAN,8.0f, Font.NORMAL, BaseColor.BLACK);
		//	Font gt = new Font(FontFamily.TIMES_ROMAN,7.0f, Font.NORMAL, BaseColor.BLACK);
			Font e = new Font(FontFamily.TIMES_ROMAN,9.0f, Font.BOLD, BaseColor.RED);
			
		//	Image image12 = Image.getInstance(uploadDir+"logos/logo.jpg");
		//	image12.scaleAbsolute(40,30);		
			
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
						
			PdfPCell cellt2 = new PdfPCell(new Paragraph("imprimé le "+date,en));cellt2.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellt2.setBorder(0);cellt2.setColspan(2);cellt2.setRowspan(2);header.addCell(cellt2);
			
			PdfPCell cellt3 = new PdfPCell(new Paragraph("TEL :"+agence.getTel(),ct));cellt3.setIndent(35);
			cellt3.setBorder(0);cellt3.setColspan(4);header.addCell(cellt3);
			
			PdfPCell cellt4 = new PdfPCell(new Paragraph(" "));cellt4.setColspan(4);
			cellt4.setBorder(0);header.addCell(cellt4);
			
			PdfPCell cellt5 = new PdfPCell(new Paragraph("ETAT FINANCIER LOCATAIRE",qui));cellt5.setColspan(4);
			cellt5.setHorizontalAlignment(Element.ALIGN_CENTER);header.addCell(cellt5);
			
		//	document.add(header);
		
			if(type.equals("locataire")) {
			
			PdfPTable headers = new PdfPTable(4);headers.setWidthPercentage(100);headers.setSpacingBefore(10f);			
			headers.setSpacingAfter(10f);float[] colonneWidthHeadrs = {1.1f,1.1f,1.1f,1.1f};headers.setWidths(colonneWidthHeadrs);
			
			PdfPCell celltIH = new PdfPCell(header);celltIH.setColspan(4);
			celltIH.setBorder(0);headers.addCell(celltIH);
			
			PdfPCell celltIH1 = new PdfPCell(new Paragraph(" "));celltIH1.setColspan(4);
			celltIH1.setBorder(0);headers.addCell(celltIH1);
			
			PdfPCell cellI1 = new PdfPCell(new Paragraph("LOCATAIRE ",en));
			headers.addCell(cellI1);
			
			PdfPCell cellI2 = new PdfPCell(new Paragraph(""+" "+locataire.getNom()+" "+locataire.getPrenom(),entete));
			headers.addCell(cellI2);
			
			PdfPCell celltII = new PdfPCell(new Paragraph(" "));celltII.setColspan(2);
			celltII.setRowspan(2);celltII.setBorder(0);headers.addCell(celltII);
			
			PdfPCell cellI3 = new PdfPCell(new Paragraph("CONTACT ",en));
			headers.addCell(cellI3);
			
			PdfPCell cellI4 = new PdfPCell(new Paragraph(""+locataire.getTel(),entete));
			headers.addCell(cellI4);
									
			PdfPCell cellI5 = new PdfPCell(new Paragraph("APPARTEMENT ",en));
			headers.addCell(cellI5);
			
			PdfPCell cellI6 = new PdfPCell(new Paragraph(""+locataire.getNum_porte(),entete));
			headers.addCell(cellI6);
			
			PdfPCell celltII1 = new PdfPCell(new Paragraph(" "));celltII1.setColspan(2);
			celltII1.setRowspan(2);celltII1.setBorder(0);headers.addCell(celltII1);
			
			PdfPCell cellI7 = new PdfPCell(new Paragraph("DATE D'ENTREE ",en));
			headers.addCell(cellI7);
			
			PdfPCell cellI8 = new PdfPCell(new Paragraph(""+locataire.getDate_entree(),entete));
			headers.addCell(cellI8);
									
			PdfPCell cellI9 = new PdfPCell(new Paragraph("MONTANT CAUTION",en));
			headers.addCell(cellI9);
			
			PdfPCell cellI10 = new PdfPCell(new Paragraph(""+locataire.getCaution()+" FCFA",entete));
			headers.addCell(cellI10);
			
			PdfPCell celltII2 = new PdfPCell(new Paragraph(" "));celltII2.setColspan(2);
			celltII2.setRowspan(2);celltII2.setBorder(0);headers.addCell(celltII2);
			
			PdfPCell cellI11 = new PdfPCell(new Paragraph("LOYER MENSUEL",en));
			headers.addCell(cellI11);
			
			PdfPCell cellI12 = new PdfPCell(new Paragraph(""+maison.getPrix()+" FCFA",entete));
			headers.addCell(cellI12);
			
			PdfPCell celltII3 = new PdfPCell(new Paragraph(" "));celltII3.setColspan(2);
			celltII3.setRowspan(2);celltII3.setBorder(0);headers.addCell(celltII3);
			
			document.add(headers);
			
			
			PdfPTable tableI1 = new PdfPTable(8);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
			tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {0.3f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,1.0f};tableI1.setWidths(colonneWidthI1);
			
					
			PdfPCell cellI13 = new PdfPCell(new Paragraph("Num",en));
			tableI1.addCell(cellI13);
			
			PdfPCell cellI14 = new PdfPCell(new Paragraph("Periode",en));
			tableI1.addCell(cellI14);
			
			PdfPCell cellI15 = new PdfPCell(new Paragraph("Date d'Echéance",en));
			tableI1.addCell(cellI15);
			
			PdfPCell cellI16 = new PdfPCell(new Paragraph("Montant Payé",en));
			tableI1.addCell(cellI16);
			
			PdfPCell cellI17 = new PdfPCell(new Paragraph("Date de Paiement",en));
			tableI1.addCell(cellI17);
			
			PdfPCell cellI18 = new PdfPCell(new Paragraph("Statut",en));
			tableI1.addCell(cellI18);
			
			PdfPCell cellI19 = new PdfPCell(new Paragraph("Solde",en));
			tableI1.addCell(cellI19);
			
			PdfPCell cellI20 = new PdfPCell(new Paragraph("Observation",en));
			tableI1.addCell(cellI20);
			
			List<Calendrier_paiement> listPI = calendrier_paiementDI.getAllCalendrier_paiementPDF(site.getSite(),matE);
			int Tp = 0, Sold = 0;
			for(Calendrier_paiement clas : listPI) {
				
				int index	=	listPI.indexOf(clas) + 1;
				
				PdfPCell cellI21 = new PdfPCell(new Paragraph(""+index,en));
				cellI21.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI21);
				
				PdfPCell cellI22 = new PdfPCell(new Paragraph(""+clas.getMois()+" "+clas.getAnnee(),en));
				cellI22.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI22);
				
				PdfPCell cellI23 = new PdfPCell(new Paragraph(""+clas.getDate_paiement(),en));
				cellI23.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI23);
				
				PdfPCell cellI24 = new PdfPCell(new Paragraph(""+clas.getMontant_P(),en));
				cellI24.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI24);
				
				PdfPCell cellI25 = new PdfPCell(new Paragraph(""+clas.getDate_paiement(),en));
				cellI25.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI25);
				
				PdfPCell cellI26 = new PdfPCell(new Paragraph(""+clas.getStatut(),en));
				cellI26.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI26);
				
				PdfPCell cellI27 = new PdfPCell(new Paragraph(""+clas.getMontant_R(),en));
				cellI27.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI27);
				
				if(clas.getMontant_R()== 0) {
					PdfPCell cellI28 = new PdfPCell(new Paragraph("paiement effectuer",en));
					cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);
				} else {
					PdfPCell cellI28 = new PdfPCell(new Paragraph("retard de paiement",en));
					cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);
				}
				
				Tp += clas.getMontant_P(); Sold += clas.getMontant_R();
			}
			
			document.add(tableI1);
			
			PdfPTable headerS = new PdfPTable(4);headerS.setWidthPercentage(100);headerS.setSpacingBefore(10f);			
			headerS.setSpacingAfter(10f);float[] colonneWidthHeadrS = {1.1f,1.1f,1.1f,1.1f};headerS.setWidths(colonneWidthHeadrS);
			
			PdfPCell celltII_1 = new PdfPCell(new Paragraph(" "));celltII_1.setColspan(2);
			celltII_1.setRowspan(2);celltII_1.setBorder(0);headerS.addCell(celltII_1);
			
			PdfPCell cellI_1 = new PdfPCell(new Paragraph("TOTAL PAYE ",en));
			headerS.addCell(cellI_1);
			
			PdfPCell cellI_2 = new PdfPCell(new Paragraph(""+Tp+" FCFA",e));
			headerS.addCell(cellI_2);
									
			PdfPCell cellI_3 = new PdfPCell(new Paragraph("SOLDE ",en));
			headerS.addCell(cellI_3);
			
			PdfPCell cellI_4 = new PdfPCell(new Paragraph(""+Sold+" FCFA",e));
			headerS.addCell(cellI_4);
			
			PdfPCell celltII_2 = new PdfPCell(new Paragraph(" "));celltII_2.setColspan(2);
			celltII_2.setRowspan(2);celltII_2.setBorder(0);headerS.addCell(celltII_2);
			
			document.add(headerS);
			
			}
			
  //++++++++++++++++++++++++++++++++++++++ETAT FINANCIER PAR SITE++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//			
			if(type.equals("site")) {
				
				List<Locataire> listS = locataireDI.getAllLocataire(site.getSite());
				for(Locataire sites : listS) {
					maison = maisonDI.getMaison(sites.getMatricule());
					List<Calendrier_paiement> listPI = calendrier_paiementDI.getAllCalendrier_paiementPDF(sites.getSite(),sites.getMatricule());
				
				PdfPTable headers = new PdfPTable(4);headers.setWidthPercentage(100);headers.setSpacingBefore(10f);			
				headers.setSpacingAfter(10f);float[] colonneWidthHeadrs = {1.1f,1.1f,1.1f,1.1f};headers.setWidths(colonneWidthHeadrs);
												
				PdfPCell cellI1 = new PdfPCell(new Paragraph("LOCATAIRE ",en));
				headers.addCell(cellI1);
				
				PdfPCell cellI2 = new PdfPCell(new Paragraph(""+" "+sites.getNom()+" "+sites.getPrenom(),entete));
				headers.addCell(cellI2);
				
				PdfPCell celltII = new PdfPCell(new Paragraph(" "));celltII.setColspan(2);
				celltII.setRowspan(2);celltII.setBorder(0);headers.addCell(celltII);
				
				PdfPCell cellI3 = new PdfPCell(new Paragraph("CONTACT ",en));
				headers.addCell(cellI3);
				
				PdfPCell cellI4 = new PdfPCell(new Paragraph(""+sites.getTel(),entete));
				headers.addCell(cellI4);
										
				PdfPCell cellI5 = new PdfPCell(new Paragraph("APPARTEMENT ",en));
				headers.addCell(cellI5);
				
				PdfPCell cellI6 = new PdfPCell(new Paragraph(""+sites.getNum_porte(),entete));
				headers.addCell(cellI6);
				
				PdfPCell celltII1 = new PdfPCell(new Paragraph(" "));celltII1.setColspan(2);
				celltII1.setRowspan(2);celltII1.setBorder(0);headers.addCell(celltII1);
				
				PdfPCell cellI7 = new PdfPCell(new Paragraph("DATE D'ENTREE ",en));
				headers.addCell(cellI7);
				
				PdfPCell cellI8 = new PdfPCell(new Paragraph(""+sites.getDate_entree(),entete));
				headers.addCell(cellI8);
										
				PdfPCell cellI9 = new PdfPCell(new Paragraph("MONTANT CAUTION",en));
				headers.addCell(cellI9);
				
				PdfPCell cellI10 = new PdfPCell(new Paragraph(""+sites.getCaution()+" FCFA",entete));
				headers.addCell(cellI10);
				
				PdfPCell celltII2 = new PdfPCell(new Paragraph(" "));celltII2.setColspan(2);
				celltII2.setRowspan(2);celltII2.setBorder(0);headers.addCell(celltII2);
				
				PdfPCell cellI11 = new PdfPCell(new Paragraph("LOYER MENSUEL",en));
				headers.addCell(cellI11);
				
				PdfPCell cellI12 = new PdfPCell(new Paragraph(""+maison.getPrix()+" FCFA",entete));
				headers.addCell(cellI12);
				
				PdfPCell celltII3 = new PdfPCell(new Paragraph(" "));celltII3.setColspan(2);
				celltII3.setRowspan(2);celltII3.setBorder(0);headers.addCell(celltII3);
				
				//document.add(headers);
								
				PdfPTable tableI1 = new PdfPTable(8);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
				tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {0.3f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,1.0f};tableI1.setWidths(colonneWidthI1);
				
		//+++++++++++++++++++++IMPORT ENTÊTE++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
				PdfPCell celltIH = new PdfPCell(header);celltIH.setColspan(8);
				celltIH.setBorder(0);tableI1.addCell(celltIH);
				
				PdfPCell celltIH1 = new PdfPCell(new Paragraph(" "));celltIH1.setColspan(8);
				celltIH1.setBorder(0);tableI1.addCell(celltIH1);
				
				PdfPCell celltIH2 = new PdfPCell(headers);celltIH2.setColspan(8);
				celltIH2.setBorder(0);tableI1.addCell(celltIH2);
				
				PdfPCell celltIH3 = new PdfPCell(new Paragraph(" "));celltIH3.setColspan(8);
				celltIH3.setBorder(0);tableI1.addCell(celltIH3);
	  //+++++++++++++++++++++FIN IMPORT ENTÊTE++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
						
				PdfPCell cellI13 = new PdfPCell(new Paragraph("Num",en));
				tableI1.addCell(cellI13);
				
				PdfPCell cellI14 = new PdfPCell(new Paragraph("Periode",en));
				tableI1.addCell(cellI14);
				
				PdfPCell cellI15 = new PdfPCell(new Paragraph("Date d'Echéance",en));
				tableI1.addCell(cellI15);
				
				PdfPCell cellI16 = new PdfPCell(new Paragraph("Montant Payé",en));
				tableI1.addCell(cellI16);
				
				PdfPCell cellI17 = new PdfPCell(new Paragraph("Date de Paiement",en));
				tableI1.addCell(cellI17);
				
				PdfPCell cellI18 = new PdfPCell(new Paragraph("Statut",en));
				tableI1.addCell(cellI18);
				
				PdfPCell cellI19 = new PdfPCell(new Paragraph("Solde",en));
				tableI1.addCell(cellI19);
				
				PdfPCell cellI20 = new PdfPCell(new Paragraph("Observation",en));
				tableI1.addCell(cellI20);
				
				
				int Tp = 0, Sold = 0;
				for(Calendrier_paiement clas : listPI) {
					
					int index	=	listPI.indexOf(clas) + 1;
					
					PdfPCell cellI21 = new PdfPCell(new Paragraph(""+index,en));
					cellI21.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI21);
					
					PdfPCell cellI22 = new PdfPCell(new Paragraph(""+clas.getMois()+" "+clas.getAnnee(),en));
					cellI22.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI22);
					
					PdfPCell cellI23 = new PdfPCell(new Paragraph(""+clas.getDate_paiement(),en));
					cellI23.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI23);
					
					PdfPCell cellI24 = new PdfPCell(new Paragraph(""+clas.getMontant_P(),en));
					cellI24.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI24);
					
					PdfPCell cellI25 = new PdfPCell(new Paragraph(""+clas.getDate_paiement(),en));
					cellI25.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI25);
					
					PdfPCell cellI26 = new PdfPCell(new Paragraph(""+clas.getStatut(),en));
					cellI26.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI26);
					
					PdfPCell cellI27 = new PdfPCell(new Paragraph(""+clas.getMontant_R(),en));
					cellI27.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI27);
					
					if(clas.getMontant_R()== 0) {
						PdfPCell cellI28 = new PdfPCell(new Paragraph("paiement effectuer",en));
						cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);
					} else {
						PdfPCell cellI28 = new PdfPCell(new Paragraph("retard de paiement",en));
						cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);
					}
					
					Tp += clas.getMontant_P(); Sold += clas.getMontant_R();
				}
				if(listPI.size() > 0) {
					tableI1.setKeepTogether(true);
					document.add(tableI1);
				}
				
				
				PdfPTable headerS = new PdfPTable(4);headerS.setWidthPercentage(100);headerS.setSpacingBefore(10f);			
				headerS.setSpacingAfter(10f);float[] colonneWidthHeadrS = {1.1f,1.1f,1.1f,1.1f};headerS.setWidths(colonneWidthHeadrS);
				
				PdfPCell celltII_1 = new PdfPCell(new Paragraph(" "));celltII_1.setColspan(2);
				celltII_1.setRowspan(2);celltII_1.setBorder(0);headerS.addCell(celltII_1);
				
				PdfPCell cellI_1 = new PdfPCell(new Paragraph("TOTAL PAYE ",en));
				headerS.addCell(cellI_1);
				
				PdfPCell cellI_2 = new PdfPCell(new Paragraph(""+Tp+" FCFA",e));
				headerS.addCell(cellI_2);
										
				PdfPCell cellI_3 = new PdfPCell(new Paragraph("SOLDE ",en));
				headerS.addCell(cellI_3);
				
				PdfPCell cellI_4 = new PdfPCell(new Paragraph(""+Sold+" FCFA",e));
				headerS.addCell(cellI_4);
				
				PdfPCell celltII_2 = new PdfPCell(new Paragraph(" "));celltII_2.setColspan(2);
				celltII_2.setRowspan(2);celltII_2.setBorder(0);headerS.addCell(celltII_2);
				
				document.add(headerS);								
				
				
				}
			}
			
	//++++++++++++++++++++++++++++++++++++++ETAT FINANCIER PAR ANNEE++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//		
			if(type.equals("annee")) {
				
				List<Locataire> listS = locataireDI.getAllLocataire(site.getSite());
				for(Locataire sites : listS) {
					maison = maisonDI.getMaison(sites.getMatricule());
					int ansU = Integer.parseInt(ans);
					List<Calendrier_paiement> listPI = calendrier_paiementDI.getAllCalendrier_paiementPDF(sites.getSite(),sites.getMatricule(),ansU);
				
				PdfPTable headers = new PdfPTable(4);headers.setWidthPercentage(100);headers.setSpacingBefore(10f);			
				headers.setSpacingAfter(10f);float[] colonneWidthHeadrs = {1.1f,1.1f,1.1f,1.1f};headers.setWidths(colonneWidthHeadrs);
												
				PdfPCell cellI1 = new PdfPCell(new Paragraph("LOCATAIRE ",en));
				headers.addCell(cellI1);
				
				PdfPCell cellI2 = new PdfPCell(new Paragraph(""+" "+sites.getNom()+" "+sites.getPrenom(),entete));
				headers.addCell(cellI2);
				
				PdfPCell celltII = new PdfPCell(new Paragraph(" "));celltII.setColspan(2);
				celltII.setRowspan(2);celltII.setBorder(0);headers.addCell(celltII);
				
				PdfPCell cellI3 = new PdfPCell(new Paragraph("CONTACT ",en));
				headers.addCell(cellI3);
				
				PdfPCell cellI4 = new PdfPCell(new Paragraph(""+sites.getTel(),entete));
				headers.addCell(cellI4);
										
				PdfPCell cellI5 = new PdfPCell(new Paragraph("APPARTEMENT ",en));
				headers.addCell(cellI5);
				
				PdfPCell cellI6 = new PdfPCell(new Paragraph(""+sites.getNum_porte(),entete));
				headers.addCell(cellI6);
				
				PdfPCell celltII1 = new PdfPCell(new Paragraph(" "));celltII1.setColspan(2);
				celltII1.setRowspan(2);celltII1.setBorder(0);headers.addCell(celltII1);
				
				PdfPCell cellI7 = new PdfPCell(new Paragraph("DATE D'ENTREE ",en));
				headers.addCell(cellI7);
				
				PdfPCell cellI8 = new PdfPCell(new Paragraph(""+sites.getDate_entree(),entete));
				headers.addCell(cellI8);
										
				PdfPCell cellI9 = new PdfPCell(new Paragraph("MONTANT CAUTION",en));
				headers.addCell(cellI9);
				
				PdfPCell cellI10 = new PdfPCell(new Paragraph(""+sites.getCaution()+" FCFA",entete));
				headers.addCell(cellI10);
				
				PdfPCell celltII2 = new PdfPCell(new Paragraph(" "));celltII2.setColspan(2);
				celltII2.setRowspan(2);celltII2.setBorder(0);headers.addCell(celltII2);
				
				PdfPCell cellI11 = new PdfPCell(new Paragraph("LOYER MENSUEL",en));
				headers.addCell(cellI11);
				
				PdfPCell cellI12 = new PdfPCell(new Paragraph(""+maison.getPrix()+" FCFA",entete));
				headers.addCell(cellI12);
				
				PdfPCell celltII3 = new PdfPCell(new Paragraph(" "));celltII3.setColspan(2);
				celltII3.setRowspan(2);celltII3.setBorder(0);headers.addCell(celltII3);
				
				//document.add(headers);
								
				PdfPTable tableI1 = new PdfPTable(8);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
				tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {0.3f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,1.0f};tableI1.setWidths(colonneWidthI1);
				
		//+++++++++++++++++++++IMPORT ENTÊTE++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
				PdfPCell celltIH = new PdfPCell(header);celltIH.setColspan(8);
				celltIH.setBorder(0);tableI1.addCell(celltIH);
				
				PdfPCell celltIH1 = new PdfPCell(new Paragraph(" "));celltIH1.setColspan(8);
				celltIH1.setBorder(0);tableI1.addCell(celltIH1);
				
				PdfPCell celltIH2 = new PdfPCell(headers);celltIH2.setColspan(8);
				celltIH2.setBorder(0);tableI1.addCell(celltIH2);
				
				PdfPCell celltIH3 = new PdfPCell(new Paragraph(" "));celltIH3.setColspan(8);
				celltIH3.setBorder(0);tableI1.addCell(celltIH3);
	  //+++++++++++++++++++++FIN IMPORT ENTÊTE++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
						
				PdfPCell cellI13 = new PdfPCell(new Paragraph("Num",en));
				tableI1.addCell(cellI13);
				
				PdfPCell cellI14 = new PdfPCell(new Paragraph("Periode",en));
				tableI1.addCell(cellI14);
				
				PdfPCell cellI15 = new PdfPCell(new Paragraph("Date d'Echéance",en));
				tableI1.addCell(cellI15);
				
				PdfPCell cellI16 = new PdfPCell(new Paragraph("Montant Payé",en));
				tableI1.addCell(cellI16);
				
				PdfPCell cellI17 = new PdfPCell(new Paragraph("Date de Paiement",en));
				tableI1.addCell(cellI17);
				
				PdfPCell cellI18 = new PdfPCell(new Paragraph("Statut",en));
				tableI1.addCell(cellI18);
				
				PdfPCell cellI19 = new PdfPCell(new Paragraph("Solde",en));
				tableI1.addCell(cellI19);
				
				PdfPCell cellI20 = new PdfPCell(new Paragraph("Observation",en));
				tableI1.addCell(cellI20);
				
				
				int Tp = 0, Sold = 0;
				for(Calendrier_paiement clas : listPI) {
					
					int index	=	listPI.indexOf(clas) + 1;
					
					PdfPCell cellI21 = new PdfPCell(new Paragraph(""+index,en));
					cellI21.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI21);
					
					PdfPCell cellI22 = new PdfPCell(new Paragraph(""+clas.getMois()+" "+clas.getAnnee(),en));
					cellI22.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI22);
					
					PdfPCell cellI23 = new PdfPCell(new Paragraph(""+clas.getDate_paiement(),en));
					cellI23.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI23);
					
					PdfPCell cellI24 = new PdfPCell(new Paragraph(""+clas.getMontant_P(),en));
					cellI24.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI24);
					
					PdfPCell cellI25 = new PdfPCell(new Paragraph(""+clas.getDate_paiement(),en));
					cellI25.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI25);
					
					PdfPCell cellI26 = new PdfPCell(new Paragraph(""+clas.getStatut(),en));
					cellI26.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI26);
					
					PdfPCell cellI27 = new PdfPCell(new Paragraph(""+clas.getMontant_R(),en));
					cellI27.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI27);
					
					if(clas.getMontant_R()== 0) {
						PdfPCell cellI28 = new PdfPCell(new Paragraph("paiement effectuer",en));
						cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);
					} else {
						PdfPCell cellI28 = new PdfPCell(new Paragraph("retard de paiement",en));
						cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);
					}
					
					Tp += clas.getMontant_P(); Sold += clas.getMontant_R();
				}
				if(listPI.size() > 0) {
					tableI1.setKeepTogether(true);
					document.add(tableI1);
				}
				
				
				PdfPTable headerS = new PdfPTable(4);headerS.setWidthPercentage(100);headerS.setSpacingBefore(10f);			
				headerS.setSpacingAfter(10f);float[] colonneWidthHeadrS = {1.1f,1.1f,1.1f,1.1f};headerS.setWidths(colonneWidthHeadrS);
				
				PdfPCell celltII_1 = new PdfPCell(new Paragraph(" "));celltII_1.setColspan(2);
				celltII_1.setRowspan(2);celltII_1.setBorder(0);headerS.addCell(celltII_1);
				
				PdfPCell cellI_1 = new PdfPCell(new Paragraph("TOTAL PAYE ",en));
				headerS.addCell(cellI_1);
				
				PdfPCell cellI_2 = new PdfPCell(new Paragraph(""+Tp+" FCFA",e));
				headerS.addCell(cellI_2);
										
				PdfPCell cellI_3 = new PdfPCell(new Paragraph("SOLDE ",en));
				headerS.addCell(cellI_3);
				
				PdfPCell cellI_4 = new PdfPCell(new Paragraph(""+Sold+" FCFA",e));
				headerS.addCell(cellI_4);
				
				PdfPCell celltII_2 = new PdfPCell(new Paragraph(" "));celltII_2.setColspan(2);
				celltII_2.setRowspan(2);celltII_2.setBorder(0);headerS.addCell(celltII_2);
				
				document.add(headerS);								
				
				
				}
			}
			
  //++++++++++++++++++++++++++++++++++++++ETAT FINANCIER PAR MOIS++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//		
			if(type.equals("moisC")) {
				
				List<Locataire> listS = locataireDI.getAllLocataire(site.getSite());
				for(Locataire sites : listS) {
					maison = maisonDI.getMaison(sites.getMatricule());
					int ansU = Integer.parseInt(ans);
					List<Calendrier_paiement> listPI = calendrier_paiementDI.getAllCalendrier_paiementPDF(sites.getSite(),sites.getMatricule(),ansU,mois);
				
				PdfPTable headers = new PdfPTable(4);headers.setWidthPercentage(100);headers.setSpacingBefore(10f);			
				headers.setSpacingAfter(10f);float[] colonneWidthHeadrs = {1.1f,1.1f,1.1f,1.1f};headers.setWidths(colonneWidthHeadrs);
												
				PdfPCell cellI1 = new PdfPCell(new Paragraph("LOCATAIRE ",en));
				headers.addCell(cellI1);
				
				PdfPCell cellI2 = new PdfPCell(new Paragraph(""+" "+sites.getNom()+" "+sites.getPrenom(),entete));
				headers.addCell(cellI2);
				
				PdfPCell celltII = new PdfPCell(new Paragraph(" "));celltII.setColspan(2);
				celltII.setRowspan(2);celltII.setBorder(0);headers.addCell(celltII);
				
				PdfPCell cellI3 = new PdfPCell(new Paragraph("CONTACT ",en));
				headers.addCell(cellI3);
				
				PdfPCell cellI4 = new PdfPCell(new Paragraph(""+sites.getTel(),entete));
				headers.addCell(cellI4);
										
				PdfPCell cellI5 = new PdfPCell(new Paragraph("APPARTEMENT ",en));
				headers.addCell(cellI5);
				
				PdfPCell cellI6 = new PdfPCell(new Paragraph(""+sites.getNum_porte(),entete));
				headers.addCell(cellI6);
				
				PdfPCell celltII1 = new PdfPCell(new Paragraph(" "));celltII1.setColspan(2);
				celltII1.setRowspan(2);celltII1.setBorder(0);headers.addCell(celltII1);
				
				PdfPCell cellI7 = new PdfPCell(new Paragraph("DATE D'ENTREE ",en));
				headers.addCell(cellI7);
				
				PdfPCell cellI8 = new PdfPCell(new Paragraph(""+sites.getDate_entree(),entete));
				headers.addCell(cellI8);
										
				PdfPCell cellI9 = new PdfPCell(new Paragraph("MONTANT CAUTION",en));
				headers.addCell(cellI9);
				
				PdfPCell cellI10 = new PdfPCell(new Paragraph(""+sites.getCaution()+" FCFA",entete));
				headers.addCell(cellI10);
				
				PdfPCell celltII2 = new PdfPCell(new Paragraph(" "));celltII2.setColspan(2);
				celltII2.setRowspan(2);celltII2.setBorder(0);headers.addCell(celltII2);
				
				PdfPCell cellI11 = new PdfPCell(new Paragraph("LOYER MENSUEL",en));
				headers.addCell(cellI11);
				
				PdfPCell cellI12 = new PdfPCell(new Paragraph(""+maison.getPrix()+" FCFA",entete));
				headers.addCell(cellI12);
				
				PdfPCell celltII3 = new PdfPCell(new Paragraph(" "));celltII3.setColspan(2);
				celltII3.setRowspan(2);celltII3.setBorder(0);headers.addCell(celltII3);
				
				//document.add(headers);
								
				PdfPTable tableI1 = new PdfPTable(8);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
				tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {0.3f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,1.0f};tableI1.setWidths(colonneWidthI1);
				
		//+++++++++++++++++++++IMPORT ENTÊTE++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
				PdfPCell celltIH = new PdfPCell(header);celltIH.setColspan(8);
				celltIH.setBorder(0);tableI1.addCell(celltIH);
				
				PdfPCell celltIH1 = new PdfPCell(new Paragraph(" "));celltIH1.setColspan(8);
				celltIH1.setBorder(0);tableI1.addCell(celltIH1);
				
				PdfPCell celltIH2 = new PdfPCell(headers);celltIH2.setColspan(8);
				celltIH2.setBorder(0);tableI1.addCell(celltIH2);
				
				PdfPCell celltIH3 = new PdfPCell(new Paragraph(" "));celltIH3.setColspan(8);
				celltIH3.setBorder(0);tableI1.addCell(celltIH3);
	  //+++++++++++++++++++++FIN IMPORT ENTÊTE++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
						
				PdfPCell cellI13 = new PdfPCell(new Paragraph("Num",en));
				tableI1.addCell(cellI13);
				
				PdfPCell cellI14 = new PdfPCell(new Paragraph("Periode",en));
				tableI1.addCell(cellI14);
				
				PdfPCell cellI15 = new PdfPCell(new Paragraph("Date d'Echéance",en));
				tableI1.addCell(cellI15);
				
				PdfPCell cellI16 = new PdfPCell(new Paragraph("Montant Payé",en));
				tableI1.addCell(cellI16);
				
				PdfPCell cellI17 = new PdfPCell(new Paragraph("Date de Paiement",en));
				tableI1.addCell(cellI17);
				
				PdfPCell cellI18 = new PdfPCell(new Paragraph("Statut",en));
				tableI1.addCell(cellI18);
				
				PdfPCell cellI19 = new PdfPCell(new Paragraph("Solde",en));
				tableI1.addCell(cellI19);
				
				PdfPCell cellI20 = new PdfPCell(new Paragraph("Observation",en));
				tableI1.addCell(cellI20);
				
				
				int Tp = 0, Sold = 0;
				for(Calendrier_paiement clas : listPI) {
					
					int index	=	listPI.indexOf(clas) + 1;
					
					PdfPCell cellI21 = new PdfPCell(new Paragraph(""+index,en));
					cellI21.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI21);
					
					PdfPCell cellI22 = new PdfPCell(new Paragraph(""+clas.getMois()+" "+clas.getAnnee(),en));
					cellI22.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI22);
					
					PdfPCell cellI23 = new PdfPCell(new Paragraph(""+clas.getDate_paiement(),en));
					cellI23.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI23);
					
					PdfPCell cellI24 = new PdfPCell(new Paragraph(""+clas.getMontant_P(),en));
					cellI24.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI24);
					
					PdfPCell cellI25 = new PdfPCell(new Paragraph(""+clas.getDate_paiement(),en));
					cellI25.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI25);
					
					PdfPCell cellI26 = new PdfPCell(new Paragraph(""+clas.getStatut(),en));
					cellI26.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI26);
					
					PdfPCell cellI27 = new PdfPCell(new Paragraph(""+clas.getMontant_R(),en));
					cellI27.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI27);
					
					if(clas.getMontant_R()== 0) {
						PdfPCell cellI28 = new PdfPCell(new Paragraph("paiement effectuer",en));
						cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);
					} else {
						PdfPCell cellI28 = new PdfPCell(new Paragraph("retard de paiement",en));
						cellI28.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI28);
					}
					
					Tp += clas.getMontant_P(); Sold += clas.getMontant_R();
				}
				if(listPI.size() > 0) {
					tableI1.setKeepTogether(true);
					document.add(tableI1);
				}
				
				
				PdfPTable headerS = new PdfPTable(4);headerS.setWidthPercentage(100);headerS.setSpacingBefore(10f);			
				headerS.setSpacingAfter(10f);float[] colonneWidthHeadrS = {1.1f,1.1f,1.1f,1.1f};headerS.setWidths(colonneWidthHeadrS);
				
				PdfPCell celltII_1 = new PdfPCell(new Paragraph(" "));celltII_1.setColspan(2);
				celltII_1.setRowspan(2);celltII_1.setBorder(0);headerS.addCell(celltII_1);
				
				PdfPCell cellI_1 = new PdfPCell(new Paragraph("TOTAL PAYE ",en));
				headerS.addCell(cellI_1);
				
				PdfPCell cellI_2 = new PdfPCell(new Paragraph(""+Tp+" FCFA",e));
				headerS.addCell(cellI_2);
										
				PdfPCell cellI_3 = new PdfPCell(new Paragraph("SOLDE ",en));
				headerS.addCell(cellI_3);
				
				PdfPCell cellI_4 = new PdfPCell(new Paragraph(""+Sold+" FCFA",e));
				headerS.addCell(cellI_4);
				
				PdfPCell celltII_2 = new PdfPCell(new Paragraph(" "));celltII_2.setColspan(2);
				celltII_2.setRowspan(2);celltII_2.setBorder(0);headerS.addCell(celltII_2);
				
				document.add(headerS);								
				
				
				}
			}	
			
			
			document.close();
		}  catch (DocumentException de) {
			throw new ServletException(de);
		}
	}
}
