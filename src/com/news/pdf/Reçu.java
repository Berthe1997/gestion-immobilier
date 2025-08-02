package com.news.pdf;

import java.io.File;
import java.io.IOException;
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

@WebServlet("/Recu_paiement")
public class Reçu extends HttpServlet {
	
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = request.getSession();
		
	//	String id		=	request.getParameter("id");
		String matE		=	request.getParameter("matE");
		String code		=	request.getParameter("code");
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
		
		int ansU = Integer.parseInt(ans);
		calendrier_paiement = calendrier_paiementDI.getCalendrier_paiement(ansU,mois,matE);
		
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
			
			Font qui = new Font(FontFamily.HELVETICA,13.0f, Font.BOLD, BaseColor.BLACK);
			Font ct = new Font(FontFamily.TIMES_ROMAN,8.0f, Font.NORMAL, BaseColor.BLACK);
			Font gt = new Font(FontFamily.TIMES_ROMAN,7.0f, Font.NORMAL, BaseColor.BLACK);
			
		//	Image image12 = Image.getInstance(uploadDir+"logos/logo.jpg");
		//	image12.scaleAbsolute(40,30);
			for(int i = 1; i < 3; i++) {
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
			
			PdfPCell cellt4 = new PdfPCell(new Paragraph(" "));cellt4.setColspan(2);
			cellt4.setBorder(0);header.addCell(cellt4);
			
			PdfPCell cellt5 = new PdfPCell(new Paragraph("QUITTANCE DE LOYER ",qui));
			cellt5.setHorizontalAlignment(Element.ALIGN_CENTER);header.addCell(cellt5);
			
			PdfPCell cellt6 = new PdfPCell(new Paragraph(" "));
			cellt6.setBorder(0);header.addCell(cellt6);
			
			PdfPCell cellt7 = new PdfPCell(new Paragraph(" "));cellt7.setColspan(2);
			cellt7.setBorder(0);header.addCell(cellt7);
			
			PdfPCell cellt8 = new PdfPCell(new Paragraph("Reçu N° "+calendrier_paiement.getId()+" "+"Date "+calendrier_paiement.getDate_paiement(),ct));cellt8.setBorder(0);
			cellt8.setHorizontalAlignment(Element.ALIGN_CENTER);header.addCell(cellt8);
			
			PdfPCell cellt9 = new PdfPCell(new Paragraph(" "));
			cellt9.setBorder(0);header.addCell(cellt9);
			
			document.add(header);
			
			PdfPTable headers = new PdfPTable(1);
			headers.setWidthPercentage(100);
			
			PdfPTable table1 = new PdfPTable(1);table1.setWidthPercentage(100);table1.setSpacingBefore(22f);	
			//---------LES TABLEAUX INTERIEURS----------------
				//-----tableau I1
			PdfPTable tableI1 = new PdfPTable(4);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
			tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {0.6f,1.0f,0.6f,1.0f};tableI1.setWidths(colonneWidthI1);
			
			int Tenc = 0;
			Tenc = calendrier_paiement.getMontant_loyer() - calendrier_paiement.getPenalite();
			 
			PdfPCell cellI11 = new PdfPCell(new Paragraph("LOCATAIRE ",en));
			cellI11.setBorder(0);tableI1.addCell(cellI11);
			
			PdfPCell cellI12 = new PdfPCell(new Paragraph(" : Mr"+" "+locataire.getNom()+" "+locataire.getPrenom(),entete));
			cellI12.setBorder(0);tableI1.addCell(cellI12);
			
			PdfPCell cellI13 = new PdfPCell(new Paragraph("SITE ",en));
			cellI13.setBorder(0);tableI1.addCell(cellI13);
			
			PdfPCell cellI14 = new PdfPCell(new Paragraph(" : "+site.getSite(),entete));
			cellI14.setBorder(0);tableI1.addCell(cellI14);
			
			PdfPCell cellI15 = new PdfPCell(new Paragraph("CONTACT ",en));
			cellI15.setBorder(0);tableI1.addCell(cellI15);
			
			PdfPCell cellI16 = new PdfPCell(new Paragraph(" : "+locataire.getTel(),entete));
			cellI16.setBorder(0);tableI1.addCell(cellI16);
			
			PdfPCell cellI19 = new PdfPCell(new Paragraph("LOCAL ",en)); 
			cellI19.setBorder(0);tableI1.addCell(cellI19);
			
			PdfPCell cellI20 = new PdfPCell(new Paragraph(" : "+locataire.getNum_porte(),entete));
			cellI20.setBorder(0);tableI1.addCell(cellI20);
			
			
			PdfPCell cellI17 = new PdfPCell(new Paragraph("LOYER ",en));
			cellI17.setBorder(0);tableI1.addCell(cellI17);
			
			PdfPCell cellI18 = new PdfPCell(new Paragraph(" : "+maison.getPrix(),entete));
			cellI18.setBorder(0);tableI1.addCell(cellI18);
			
			PdfPCell cellI21 = new PdfPCell(new Paragraph("LOYER ENCAISSER",en));
			cellI21.setBorder(0);tableI1.addCell(cellI21);
			
			PdfPCell cellI23 = new PdfPCell(new Paragraph(" : "+Tenc,entete));
			cellI23.setBorder(0);tableI1.addCell(cellI23);
			
			PdfPCell cellI24 = new PdfPCell(new Paragraph("PENALITE",en));
			cellI24.setBorder(0);tableI1.addCell(cellI24);
			
			PdfPCell cellI25 = new PdfPCell(new Paragraph(" : "+calendrier_paiement.getPenalite(),entete));
			cellI25.setBorder(0);tableI1.addCell(cellI25);
			
			PdfPCell cellI26 = new PdfPCell(new Paragraph("TOTAL ENCAISSER",en));
			cellI26.setBorder(0);tableI1.addCell(cellI26);
			
			PdfPCell cellI27 = new PdfPCell(new Paragraph(" : "+calendrier_paiement.getMontant_loyer(),entete));
			cellI27.setBorder(0);tableI1.addCell(cellI27);
					
			PdfPCell cellI28 = new PdfPCell(new Paragraph("MOIS CONCERNES ",en)); 
			cellI28.setBorder(0);tableI1.addCell(cellI28);
			
			PdfPCell cellI29 = new PdfPCell(new Paragraph(" : "+calendrier_paiement.getMois()+" "+calendrier_paiement.getAnnee(),entete));
			cellI29.setBorder(0);tableI1.addCell(cellI29);
			
			PdfPCell cellI30 = new PdfPCell(new Paragraph("LOYER IMPAYER",en));
			cellI30.setBorder(0);tableI1.addCell(cellI30);
			
			PdfPCell cellI31 = new PdfPCell(new Paragraph(" : "+calendrier_paiement.getMontant_R(),entete));
			cellI31.setBorder(0);tableI1.addCell(cellI31);
			
			PdfPCell cellI32 = new PdfPCell(new Paragraph("MODE REGLEMENT ",en)); 
			cellI32.setBorder(0);tableI1.addCell(cellI32);
			
			PdfPCell cellI33 = new PdfPCell(new Paragraph(" : "+calendrier_paiement.getModeP(),entete));
			cellI33.setBorder(0);tableI1.addCell(cellI33);
			
			PdfPCell cellI34 = new PdfPCell(new Paragraph("",entete));
			cellI34.setBorder(0);cellI34.setColspan(2);tableI1.addCell(cellI34);
			
			
			  
			PdfPCell cellt12 = new PdfPCell(tableI1);cellt12.setBorderWidth(1);table1.addCell(cellt12);
			
			PdfPCell cellt14 = new PdfPCell(new Paragraph("Visa Gestionnaire",ct));
			cellt14.setIndent(450);cellt14.setBorder(0);table1.addCell(cellt14);
			PdfPCell cellt15 = new PdfPCell(new Paragraph(""+users.getNom(),gt));cellt15.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellt15.setBorder(0);table1.addCell(cellt15);
			
	//-----------------------------------------NOTA----------------------------------------------------------//
			PdfPCell cellt16 = new PdfPCell(new Paragraph(""));
			cellt16.setBorder(0);table1.addCell(cellt16);	
			
			PdfPCell cellt17 = new PdfPCell(new Paragraph("NOTA : Un locataire ne peut déménager :",ct));
			cellt17.setBorder(0);table1.addCell(cellt17);
			
			PdfPCell cellt18 = new PdfPCell(new Paragraph("1-Qu'il n'ait justifié au propriétaire par une quittance de receveur\n"
					+ "  qu'il a acquitté toutes ses contributions personnelles et mobillièrede l'année courante.",ct));
			cellt18.setIndent(25);cellt18.setBorder(0);table1.addCell(cellt18);
			
			PdfPCell cellt19 = new PdfPCell(new Paragraph("2-Qu'il n'ait donné ou reçu congé ecrite dans les délais prescrits.",ct));
			cellt19.setIndent(25);cellt19.setBorder(0);table1.addCell(cellt19);
			

			PdfPCell cellt20 = new PdfPCell(new Paragraph("2-Qu'il n'ait fait faire les réparations locatives à sa charge suivant l'usage "
					+ "ou d'après l'état\n  des lieux s'il en existe un.",ct));
			cellt20.setIndent(25);cellt20.setBorder(0);table1.addCell(cellt20);
			
			
			if(i != 2) {								
				PdfPCell cellt13 = new PdfPCell(new Paragraph("------------------------------------------------------------------------------------\n\n"));
				cellt13.setHorizontalAlignment(Element.ALIGN_CENTER);cellt13.setBorderWidth(0);table1.addCell(cellt13);
			}
			

			document.add(table1);
			}
			document.close();
		}  catch (DocumentException de) {
			throw new ServletException(de);
		}
	}
}