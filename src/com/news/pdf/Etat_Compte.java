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
import com.news.beans.Charges_entretiens;
import com.news.beans.Compte_gestionnaire;
import com.news.beans.Compte_proprietaire;
import com.news.beans.Gestionnaire;
import com.news.beans.Impot_loyer;
import com.news.beans.Locataire;
import com.news.beans.Maison;
import com.news.beans.Proprietaire;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.dao.AgenceDI;
import com.news.dao.Calendrier_paiementDI;
import com.news.dao.Charges_entretiensDI;
import com.news.dao.Compte_gestionnaireDI;
import com.news.dao.Compte_proprietaireDI;
import com.news.dao.GestionnaireDI;
import com.news.dao.Impot_loyerDI;
import com.news.dao.LocataireDI;
import com.news.dao.MaisonDI;
import com.news.dao.ProprietaireDI;
import com.news.dao.SiteDI;
import com.news.fonctions.VerifieSession;

@WebServlet("/Etat_Compte")
public class Etat_Compte extends HttpServlet {
	
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
	 
	 Charges_entretiensDI charges_entretiensDI = new Charges_entretiensDI();
	 Charges_entretiens charges_entretiens = new Charges_entretiens();
	 
	 Compte_gestionnaireDI compte_gestionnaireDI = new Compte_gestionnaireDI();
	 Compte_gestionnaire compte_gestionnaire = new Compte_gestionnaire();
	 
	 Compte_proprietaireDI compte_proprietaireDI = new Compte_proprietaireDI();
	 Compte_proprietaire compte_proprietaire = new Compte_proprietaire();
	 
	 Impot_loyerDI impot_loyerDI = new Impot_loyerDI();
	 Impot_loyer impot_loyer = new Impot_loyer();
	 
	 ProprietaireDI proprietaireDI = new ProprietaireDI();
	 Proprietaire proprietaire = new Proprietaire();
	 
	 GestionnaireDI gestionnaireDI = new GestionnaireDI();
	 Gestionnaire gestionnaire = new Gestionnaire();
	
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
		String statut	=	request.getParameter("statut");
		
		String uploadDir = this.getServletContext().getInitParameter("upload_dir");
		
		users = (Users) session.getAttribute("users");
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		}
		
		site = siteDI.getSite(site.getSite());
		
		agence = agenceDI.getAgence(code);
		
		maison = maisonDI.getMaison(matE);
		
		locataire = locataireDI.getLocataire(matE);
		
		gestionnaire = gestionnaireDI.getGestionnaire(matE);
		proprietaire = proprietaireDI.getProprietaire(matE);
		
		
		String listeur = "";
		if(type.equals("compteP")) {
			listeur = "ETAT FINANCIER MENSUELLE DU PROPRIETAIRE ";
		}
		if(type.equals("compteG")) {
			listeur = "ETAT FINANCIER MENSUELLE DU GESTIONNAIRE";
		}
		if(type.equals("compteAG")) {
			listeur = "ETAT FINANCIER ANNUELLE DU GESTIONNAIRE";
		}
		if(type.equals("compteI")) {
			listeur = "ETAT FINANCIER IMPÔT LOYER";
		}
		if(type.equals("compteAP")) {
			listeur = "ETAT FINANCIER ANNUELLE DU PROPRIETAIRE";
		}
		if(type.equals("commissionE")) {
			listeur = "COMMISSION D'ENTREE";
		}
		
		
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
			
			PdfPCell cellt5 = new PdfPCell(new Paragraph(""+listeur,qui));cellt5.setColspan(4);
			cellt5.setHorizontalAlignment(Element.ALIGN_CENTER);header.addCell(cellt5);
				
			//+++++++++++++++++++++++++++++++++++++++++++++++++ETAT COMPTE GESTIONNAIRE+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//	
			if(type.equals("commissionE")) {
				
				PdfPTable headers = new PdfPTable(4);headers.setWidthPercentage(100);headers.setSpacingBefore(10f);			
				headers.setSpacingAfter(10f);float[] colonneWidthHeadrs = {1.1f,1.1f,1.1f,1.1f};headers.setWidths(colonneWidthHeadrs);
				
				PdfPCell celltIH = new PdfPCell(header);celltIH.setColspan(4);
				celltIH.setBorder(0);headers.addCell(celltIH);
				
				PdfPCell celltIH1 = new PdfPCell(new Paragraph(" "));celltIH1.setColspan(4);
				celltIH1.setBorder(0);headers.addCell(celltIH1);
				
				PdfPCell cellI1 = new PdfPCell(new Paragraph("GESTIONNAIRE",en));
				headers.addCell(cellI1);
				
				PdfPCell cellI2 = new PdfPCell(new Paragraph(""+gestionnaire.getNom()+" "+gestionnaire.getPrenom(),entete));
				headers.addCell(cellI2);
				
				PdfPCell celltII = new PdfPCell(new Paragraph(" "));celltII.setColspan(2);
				celltII.setRowspan(2);celltII.setBorder(0);headers.addCell(celltII);
				
				PdfPCell cellI3 = new PdfPCell(new Paragraph("SEXE",en));
				headers.addCell(cellI3);
				
				PdfPCell cellI4 = new PdfPCell(new Paragraph(""+gestionnaire.getSexe(),entete));
				headers.addCell(cellI4);
										
				PdfPCell cellI5 = new PdfPCell(new Paragraph("SITE ",en));
				headers.addCell(cellI5);
				
				PdfPCell cellI6 = new PdfPCell(new Paragraph(""+site.getSite(),entete));
				headers.addCell(cellI6);
				
				PdfPCell celltII1 = new PdfPCell(new Paragraph(" "));celltII1.setColspan(2);
				celltII1.setRowspan(2);celltII1.setBorder(0);headers.addCell(celltII1);
				
				PdfPCell cellI7 = new PdfPCell(new Paragraph("TOTAL APPARTEMENT ",en));
				headers.addCell(cellI7);
				
				PdfPCell cellI8 = new PdfPCell(new Paragraph(""+site.getNombre_porte(),entete));
				headers.addCell(cellI8);
										
				PdfPCell cellI9 = new PdfPCell(new Paragraph("TELEPHONE",en));
				headers.addCell(cellI9);
				
				PdfPCell cellI10 = new PdfPCell(new Paragraph(""+gestionnaire.getTel(),entete));
				headers.addCell(cellI10);
				
				PdfPCell celltII2 = new PdfPCell(new Paragraph(" "));celltII2.setColspan(2);
				celltII2.setRowspan(2);celltII2.setBorder(0);headers.addCell(celltII2);
				
				PdfPCell cellI11 = new PdfPCell(new Paragraph("EMAIL",en));
				headers.addCell(cellI11);
				
				PdfPCell cellI12 = new PdfPCell(new Paragraph(""+gestionnaire.getEmail(),entete));
				headers.addCell(cellI12);
												
				PdfPCell cellI13 = new PdfPCell(new Paragraph("LIEU RESIDENCE",en));
				headers.addCell(cellI13);
				
				PdfPCell cellI14 = new PdfPCell(new Paragraph(""+gestionnaire.getLieu_residence(),entete));
				headers.addCell(cellI14);
				
				PdfPCell celltII3 = new PdfPCell(new Paragraph(" "));celltII3.setColspan(2);
				celltII3.setRowspan(2);celltII3.setBorder(0);headers.addCell(celltII3);
				
				document.add(headers);
								
				locataire = locataireDI.getLocataire(site.getSite(),ans);
							
				PdfPTable tableI1 = new PdfPTable(4);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
				tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {0.3f,1.8f,0.8f,0.8f};tableI1.setWidths(colonneWidthI1);
														
				PdfPCell cellI15 = new PdfPCell(new Paragraph("Num",en));
				cellI15.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI15);
				
				PdfPCell cellI16 = new PdfPCell(new Paragraph("PERIODE",en));
				cellI16.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI16);				

				PdfPCell cellI24 = new PdfPCell(new Paragraph("LOCATAIRE",en));
				cellI24.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI24);
				
				PdfPCell cellI17 = new PdfPCell(new Paragraph("SOLDE COMMISSION",en));
				cellI17.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI17);
				
				List<Locataire> listCG = locataireDI.getAllLocataire(site.getSite(),ans);
				int Sold = 0;
				for(Locataire clas : listCG) {
					
					int index	=	listCG.indexOf(clas) + 1;
				
				PdfPCell cellI21 = new PdfPCell(new Paragraph(""+index,en));
				cellI21.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI21);
				
				PdfPCell cellI25 = new PdfPCell(new Paragraph(""+clas.getNom()+" "+clas.getPrenom(),en));
				cellI25.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI25);
				
				PdfPCell cellI22 = new PdfPCell(new Paragraph(""+clas.getAnnee(),en));
				cellI22.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI22);
				
				PdfPCell cellI23 = new PdfPCell(new Paragraph(""+clas.getCommission_E(),en));
				cellI23.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI23);
				
				Sold += +clas.getCommission_E(); 
				}
				
				document.add(tableI1);
				
				PdfPTable headerS = new PdfPTable(4);headerS.setWidthPercentage(100);headerS.setSpacingBefore(10f);			
				headerS.setSpacingAfter(10f);float[] colonneWidthHeadrS = {1.1f,1.1f,1.1f,1.1f};headerS.setWidths(colonneWidthHeadrS);
				
				PdfPCell celltII_1 = new PdfPCell(new Paragraph(" "));celltII_1.setColspan(2);
				celltII_1.setRowspan(2);celltII_1.setBorder(0);headerS.addCell(celltII_1);
				
				PdfPCell cellI_1 = new PdfPCell(new Paragraph("SOLDE ",en));
				headerS.addCell(cellI_1);
				
				PdfPCell cellI_2 = new PdfPCell(new Paragraph(""+Sold+" FCFA",e));
				headerS.addCell(cellI_2);
										
				
				
				document.add(headerS);		
			}
			
			
	//+++++++++++++++++++++++++++++++++++++++++++++++++ETAT COMPTE GESTIONNAIRE+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//	
			if(type.equals("compteAG")) {
				
				PdfPTable headers = new PdfPTable(4);headers.setWidthPercentage(100);headers.setSpacingBefore(10f);			
				headers.setSpacingAfter(10f);float[] colonneWidthHeadrs = {1.1f,1.1f,1.1f,1.1f};headers.setWidths(colonneWidthHeadrs);
				
				PdfPCell celltIH = new PdfPCell(header);celltIH.setColspan(4);
				celltIH.setBorder(0);headers.addCell(celltIH);
				
				PdfPCell celltIH1 = new PdfPCell(new Paragraph(" "));celltIH1.setColspan(4);
				celltIH1.setBorder(0);headers.addCell(celltIH1);
				
				PdfPCell cellI1 = new PdfPCell(new Paragraph("GESTIONNAIRE",en));
				headers.addCell(cellI1);
				
				PdfPCell cellI2 = new PdfPCell(new Paragraph(""+gestionnaire.getNom()+" "+gestionnaire.getPrenom(),entete));
				headers.addCell(cellI2);
				
				PdfPCell celltII = new PdfPCell(new Paragraph(" "));celltII.setColspan(2);
				celltII.setRowspan(2);celltII.setBorder(0);headers.addCell(celltII);
				
				PdfPCell cellI3 = new PdfPCell(new Paragraph("SEXE",en));
				headers.addCell(cellI3);
				
				PdfPCell cellI4 = new PdfPCell(new Paragraph(""+gestionnaire.getSexe(),entete));
				headers.addCell(cellI4);
										
				PdfPCell cellI5 = new PdfPCell(new Paragraph("SITE ",en));
				headers.addCell(cellI5);
				
				PdfPCell cellI6 = new PdfPCell(new Paragraph(""+site.getSite(),entete));
				headers.addCell(cellI6);
				
				PdfPCell celltII1 = new PdfPCell(new Paragraph(" "));celltII1.setColspan(2);
				celltII1.setRowspan(2);celltII1.setBorder(0);headers.addCell(celltII1);
				
				PdfPCell cellI7 = new PdfPCell(new Paragraph("TOTAL APPARTEMENT ",en));
				headers.addCell(cellI7);
				
				PdfPCell cellI8 = new PdfPCell(new Paragraph(""+site.getNombre_porte(),entete));
				headers.addCell(cellI8);
										
				PdfPCell cellI9 = new PdfPCell(new Paragraph("TELEPHONE",en));
				headers.addCell(cellI9);
				
				PdfPCell cellI10 = new PdfPCell(new Paragraph(""+gestionnaire.getTel(),entete));
				headers.addCell(cellI10);
				
				PdfPCell celltII2 = new PdfPCell(new Paragraph(" "));celltII2.setColspan(2);
				celltII2.setRowspan(2);celltII2.setBorder(0);headers.addCell(celltII2);
				
				PdfPCell cellI11 = new PdfPCell(new Paragraph("EMAIL",en));
				headers.addCell(cellI11);
				
				PdfPCell cellI12 = new PdfPCell(new Paragraph(""+gestionnaire.getEmail(),entete));
				headers.addCell(cellI12);
												
				PdfPCell cellI13 = new PdfPCell(new Paragraph("LIEU RESIDENCE",en));
				headers.addCell(cellI13);
				
				PdfPCell cellI14 = new PdfPCell(new Paragraph(""+gestionnaire.getLieu_residence(),entete));
				headers.addCell(cellI14);
				
				PdfPCell celltII3 = new PdfPCell(new Paragraph(" "));celltII3.setColspan(2);
				celltII3.setRowspan(2);celltII3.setBorder(0);headers.addCell(celltII3);
				
				document.add(headers);
				
				PdfPTable tableI1 = new PdfPTable(3);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
				tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {0.3f,0.8f,0.8f};tableI1.setWidths(colonneWidthI1);
														
				PdfPCell cellI15 = new PdfPCell(new Paragraph("Num",en));
				cellI15.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI15);
				
				PdfPCell cellI16 = new PdfPCell(new Paragraph("PERIODE",en));
				cellI16.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI16);
				
				PdfPCell cellI17 = new PdfPCell(new Paragraph("MONTANT",en));
				cellI17.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI17);
				
								
				int ansU = Integer.parseInt(ans);
				List<Compte_gestionnaire> listCG = compte_gestionnaireDI.getAllCompte_gestionnaire(site.getSite(),ansU);
				int Sold = 0;
				for(Compte_gestionnaire clas : listCG) {
					
					int index	=	listCG.indexOf(clas) + 1;
					
					PdfPCell cellI21 = new PdfPCell(new Paragraph(""+index,en));
					cellI21.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI21);
					
					PdfPCell cellI22 = new PdfPCell(new Paragraph(""+clas.getMois()+""+clas.getAns(),en));
					cellI22.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI22);
					
					PdfPCell cellI23 = new PdfPCell(new Paragraph(""+clas.getMontant_commission(),en));
					cellI23.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI23);
										
					
					Sold += +clas.getMontant_commission(); 
				}
				
				document.add(tableI1);
				
				PdfPTable headerS = new PdfPTable(4);headerS.setWidthPercentage(100);headerS.setSpacingBefore(10f);			
				headerS.setSpacingAfter(10f);float[] colonneWidthHeadrS = {1.1f,1.1f,1.1f,1.1f};headerS.setWidths(colonneWidthHeadrS);
				
				PdfPCell celltII_1 = new PdfPCell(new Paragraph(" "));celltII_1.setColspan(2);
				celltII_1.setRowspan(2);celltII_1.setBorder(0);headerS.addCell(celltII_1);
				
				PdfPCell cellI_1 = new PdfPCell(new Paragraph("SOLDE ",en));
				headerS.addCell(cellI_1);
				
				PdfPCell cellI_2 = new PdfPCell(new Paragraph(""+Sold+" FCFA",e));
				headerS.addCell(cellI_2);
										
				
				
				document.add(headerS);						
				
			}
			

  //+++++++++++++++++++++++++++++++++++++++++++++++++ETAT COMPTE GESTIONNAIRE+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//	
			if(type.equals("compteG")) {
				
				PdfPTable headers = new PdfPTable(4);headers.setWidthPercentage(100);headers.setSpacingBefore(10f);			
				headers.setSpacingAfter(10f);float[] colonneWidthHeadrs = {1.1f,1.1f,1.1f,1.1f};headers.setWidths(colonneWidthHeadrs);
				
				PdfPCell celltIH = new PdfPCell(header);celltIH.setColspan(4);
				celltIH.setBorder(0);headers.addCell(celltIH);
				
				PdfPCell celltIH1 = new PdfPCell(new Paragraph(" "));celltIH1.setColspan(4);
				celltIH1.setBorder(0);headers.addCell(celltIH1);
				
				PdfPCell cellI1 = new PdfPCell(new Paragraph("GESTIONNAIRE",en));
				headers.addCell(cellI1);
				
				PdfPCell cellI2 = new PdfPCell(new Paragraph(""+gestionnaire.getNom()+" "+gestionnaire.getPrenom(),entete));
				headers.addCell(cellI2);
				
				PdfPCell celltII = new PdfPCell(new Paragraph(" "));celltII.setColspan(2);
				celltII.setRowspan(2);celltII.setBorder(0);headers.addCell(celltII);
				
				PdfPCell cellI3 = new PdfPCell(new Paragraph("SEXE",en));
				headers.addCell(cellI3);
				
				PdfPCell cellI4 = new PdfPCell(new Paragraph(""+gestionnaire.getSexe(),entete));
				headers.addCell(cellI4);
										
				PdfPCell cellI5 = new PdfPCell(new Paragraph("SITE ",en));
				headers.addCell(cellI5);
				
				PdfPCell cellI6 = new PdfPCell(new Paragraph(""+site.getSite(),entete));
				headers.addCell(cellI6);
				
				PdfPCell celltII1 = new PdfPCell(new Paragraph(" "));celltII1.setColspan(2);
				celltII1.setRowspan(2);celltII1.setBorder(0);headers.addCell(celltII1);
				
				PdfPCell cellI7 = new PdfPCell(new Paragraph("TOTAL APPARTEMENT ",en));
				headers.addCell(cellI7);
				
				PdfPCell cellI8 = new PdfPCell(new Paragraph(""+site.getNombre_porte(),entete));
				headers.addCell(cellI8);
										
				PdfPCell cellI9 = new PdfPCell(new Paragraph("TELEPHONE",en));
				headers.addCell(cellI9);
				
				PdfPCell cellI10 = new PdfPCell(new Paragraph(""+gestionnaire.getTel(),entete));
				headers.addCell(cellI10);
				
				PdfPCell celltII2 = new PdfPCell(new Paragraph(" "));celltII2.setColspan(2);
				celltII2.setRowspan(2);celltII2.setBorder(0);headers.addCell(celltII2);
				
				PdfPCell cellI11 = new PdfPCell(new Paragraph("EMAIL",en));
				headers.addCell(cellI11);
				
				PdfPCell cellI12 = new PdfPCell(new Paragraph(""+gestionnaire.getEmail(),entete));
				headers.addCell(cellI12);
												
				PdfPCell cellI13 = new PdfPCell(new Paragraph("LIEU RESIDENCE",en));
				headers.addCell(cellI13);
				
				PdfPCell cellI14 = new PdfPCell(new Paragraph(""+gestionnaire.getLieu_residence(),entete));
				headers.addCell(cellI14);
				
				PdfPCell celltII3 = new PdfPCell(new Paragraph(" "));celltII3.setColspan(2);
				celltII3.setRowspan(2);celltII3.setBorder(0);headers.addCell(celltII3);
				
				document.add(headers);
				
				PdfPTable tableI1 = new PdfPTable(3);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
				tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {0.3f,0.8f,0.8f};tableI1.setWidths(colonneWidthI1);
														
				PdfPCell cellI15 = new PdfPCell(new Paragraph("Num",en));
				cellI15.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI15);
				
				PdfPCell cellI16 = new PdfPCell(new Paragraph("PERIODE",en));
				cellI16.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI16);
				
				PdfPCell cellI17 = new PdfPCell(new Paragraph("MONTANT",en));
				cellI17.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI17);
												
				int ansU = Integer.parseInt(ans);
				List<Compte_gestionnaire> listCG = compte_gestionnaireDI.getAllCompte_gestionnaire(site.getSite(),ansU,mois);			
				for(Compte_gestionnaire clas : listCG) {
					
					int index	=	listCG.indexOf(clas) + 1;
					
					PdfPCell cellI21 = new PdfPCell(new Paragraph(""+index,en));
					cellI21.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI21);
					
					PdfPCell cellI22 = new PdfPCell(new Paragraph(""+clas.getMois()+""+clas.getAns(),en));
					cellI22.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI22);
					
					PdfPCell cellI23 = new PdfPCell(new Paragraph(""+clas.getMontant_commission(),en));
					cellI23.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI23);
										
														
				}
				
				document.add(tableI1);
				
			}			
			
			
   //+++++++++++++++++++++++++++++++++++++++++++++++++ETAT COMPTE IMPOT LOYER+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//	
			if(type.equals("compteI")) {
				
				PdfPTable headers = new PdfPTable(4);headers.setWidthPercentage(100);headers.setSpacingBefore(10f);			
				headers.setSpacingAfter(10f);float[] colonneWidthHeadrs = {1.1f,1.1f,1.1f,1.1f};headers.setWidths(colonneWidthHeadrs);
				
				PdfPCell celltIH = new PdfPCell(header);celltIH.setColspan(4);
				celltIH.setBorder(0);headers.addCell(celltIH);
				
				PdfPCell celltIH1 = new PdfPCell(new Paragraph(" "));celltIH1.setColspan(4);
				celltIH1.setBorder(0);headers.addCell(celltIH1);
				
				PdfPCell cellI1 = new PdfPCell(new Paragraph("PROPRIETAIRE",en));
				headers.addCell(cellI1);
				
				PdfPCell cellI2 = new PdfPCell(new Paragraph(""+proprietaire.getNom()+" "+proprietaire.getPrenom(),entete));
				headers.addCell(cellI2);
				
				PdfPCell celltII = new PdfPCell(new Paragraph(" "));celltII.setColspan(2);
				celltII.setRowspan(2);celltII.setBorder(0);headers.addCell(celltII);
				
				PdfPCell cellI3 = new PdfPCell(new Paragraph("SEXE",en));
				headers.addCell(cellI3);
				
				PdfPCell cellI4 = new PdfPCell(new Paragraph(""+proprietaire.getSexe(),entete));
				headers.addCell(cellI4);
										
				PdfPCell cellI5 = new PdfPCell(new Paragraph("SITE ",en));
				headers.addCell(cellI5);
				
				PdfPCell cellI6 = new PdfPCell(new Paragraph(""+site.getSite(),entete));
				headers.addCell(cellI6);
				
				PdfPCell celltII1 = new PdfPCell(new Paragraph(" "));celltII1.setColspan(2);
				celltII1.setRowspan(2);celltII1.setBorder(0);headers.addCell(celltII1);
				
				PdfPCell cellI7 = new PdfPCell(new Paragraph("TOTAL APPARTEMENT ",en));
				headers.addCell(cellI7);
				
				PdfPCell cellI8 = new PdfPCell(new Paragraph(""+site.getNombre_porte(),entete));
				headers.addCell(cellI8);
										
				PdfPCell cellI9 = new PdfPCell(new Paragraph("TELEPHONE",en));
				headers.addCell(cellI9);
				
				PdfPCell cellI10 = new PdfPCell(new Paragraph(""+proprietaire.getTel(),entete));
				headers.addCell(cellI10);
				
				PdfPCell celltII2 = new PdfPCell(new Paragraph(" "));celltII2.setColspan(2);
				celltII2.setRowspan(2);celltII2.setBorder(0);headers.addCell(celltII2);
				
				PdfPCell cellI11 = new PdfPCell(new Paragraph("EMAIL",en));
				headers.addCell(cellI11);
				
				PdfPCell cellI12 = new PdfPCell(new Paragraph(""+proprietaire.getEmail(),entete));
				headers.addCell(cellI12);
												
				PdfPCell cellI13 = new PdfPCell(new Paragraph("LIEU RESIDENCE",en));
				headers.addCell(cellI13);
				
				PdfPCell cellI14 = new PdfPCell(new Paragraph(""+proprietaire.getLieu_residence(),entete));
				headers.addCell(cellI14);
				
				PdfPCell celltII3 = new PdfPCell(new Paragraph(" "));celltII3.setColspan(2);
				celltII3.setRowspan(2);celltII3.setBorder(0);headers.addCell(celltII3);
				
				document.add(headers);
				
				PdfPTable tableI1 = new PdfPTable(3);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
				tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {0.3f,0.8f,0.8f};tableI1.setWidths(colonneWidthI1);
														
				PdfPCell cellI15 = new PdfPCell(new Paragraph("Num",en));
				cellI15.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI15);
				
				PdfPCell cellI16 = new PdfPCell(new Paragraph("PERIODE",en));
				cellI16.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI16);
				
				PdfPCell cellI17 = new PdfPCell(new Paragraph("SOLDE IMPÔT",en));
				cellI17.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI17);
								
				int ansU = Integer.parseInt(ans);
				List<Impot_loyer> listCI = impot_loyerDI.getAllImpot_loyer(site.getSite(),ansU,mois);

				for(Impot_loyer clas : listCI) {
					
					int index	=	listCI.indexOf(clas) + 1;
					
					PdfPCell cellI21 = new PdfPCell(new Paragraph(""+index,en));
					cellI21.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI21);
					
					PdfPCell cellI22 = new PdfPCell(new Paragraph(""+clas.getMois()+""+clas.getAns(),en));
					cellI22.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI22);
					
					PdfPCell cellI23 = new PdfPCell(new Paragraph(""+clas.getMontant(),en));
					cellI23.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI23);
														
				}
				
				document.add(tableI1);
				
				
			}
			
			
	//+++++++++++++++++++++++++++++++++++++++++++++++++ETAT COMPTE PROPRIETAIRE+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//	
			if(type.equals("compteP")) {
				int ansI = Integer.parseInt(ans);
				impot_loyer = impot_loyerDI.getImpot_loyer(site.getSite(),ansI,mois);
				
				PdfPTable headers = new PdfPTable(4);headers.setWidthPercentage(100);headers.setSpacingBefore(10f);			
				headers.setSpacingAfter(10f);float[] colonneWidthHeadrs = {1.1f,1.1f,1.1f,1.1f};headers.setWidths(colonneWidthHeadrs);
				
				PdfPCell celltIH = new PdfPCell(header);celltIH.setColspan(4);
				celltIH.setBorder(0);headers.addCell(celltIH);
				
				PdfPCell celltIH1 = new PdfPCell(new Paragraph(" "));celltIH1.setColspan(4);
				celltIH1.setBorder(0);headers.addCell(celltIH1);
				
				PdfPCell cellI1 = new PdfPCell(new Paragraph("PROPRIETAIRE",en));
				headers.addCell(cellI1);
				
				PdfPCell cellI2 = new PdfPCell(new Paragraph(""+proprietaire.getNom()+" "+proprietaire.getPrenom(),entete));
				headers.addCell(cellI2);
				
				PdfPCell celltII = new PdfPCell(new Paragraph(" "));celltII.setColspan(2);
				celltII.setRowspan(2);celltII.setBorder(0);headers.addCell(celltII);
				
				PdfPCell cellI3 = new PdfPCell(new Paragraph("SEXE",en));
				headers.addCell(cellI3);
				
				PdfPCell cellI4 = new PdfPCell(new Paragraph(""+proprietaire.getSexe(),entete));
				headers.addCell(cellI4);
										
				PdfPCell cellI5 = new PdfPCell(new Paragraph("SITE ",en));
				headers.addCell(cellI5);
				
				PdfPCell cellI6 = new PdfPCell(new Paragraph(""+site.getSite(),entete));
				headers.addCell(cellI6);
				
				PdfPCell celltII1 = new PdfPCell(new Paragraph(" "));celltII1.setColspan(2);
				celltII1.setRowspan(2);celltII1.setBorder(0);headers.addCell(celltII1);
				
				PdfPCell cellI7 = new PdfPCell(new Paragraph("TOTAL APPARTEMENT ",en));
				headers.addCell(cellI7);
				
				PdfPCell cellI8 = new PdfPCell(new Paragraph(""+site.getNombre_porte(),entete));
				headers.addCell(cellI8);
										
				PdfPCell cellI9 = new PdfPCell(new Paragraph("TELEPHONE",en));
				headers.addCell(cellI9);
				
				PdfPCell cellI10 = new PdfPCell(new Paragraph(""+proprietaire.getTel(),entete));
				headers.addCell(cellI10);
				
				PdfPCell celltII2 = new PdfPCell(new Paragraph(" "));celltII2.setColspan(2);
				celltII2.setRowspan(2);celltII2.setBorder(0);headers.addCell(celltII2);
				
				PdfPCell cellI11 = new PdfPCell(new Paragraph("EMAIL",en));
				headers.addCell(cellI11);
				
				PdfPCell cellI12 = new PdfPCell(new Paragraph(""+proprietaire.getEmail(),entete));
				headers.addCell(cellI12);
												
				PdfPCell cellI13 = new PdfPCell(new Paragraph("LIEU RESIDENCE",en));
				headers.addCell(cellI13);
				
				PdfPCell cellI14 = new PdfPCell(new Paragraph(""+proprietaire.getLieu_residence(),entete));
				headers.addCell(cellI14);
				
				PdfPCell celltII3 = new PdfPCell(new Paragraph(" "));celltII3.setColspan(2);
				celltII3.setRowspan(2);celltII3.setBorder(0);headers.addCell(celltII3);
				
				document.add(headers);
				
				PdfPTable tableI1 = new PdfPTable(5);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
				tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {0.3f,0.8f,0.8f,0.8f,0.8f};tableI1.setWidths(colonneWidthI1);
														
				PdfPCell cellI15 = new PdfPCell(new Paragraph("Num",en));
				cellI15.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI15);
				
				PdfPCell cellI16 = new PdfPCell(new Paragraph("PERIODE",en));
				cellI16.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI16);
				
				PdfPCell cellI17 = new PdfPCell(new Paragraph("MONTANT",en));
				cellI17.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI17);
				
				PdfPCell cellI18 = new PdfPCell(new Paragraph("MONTANT DECAISSER",en));
				cellI18.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI18);
				
				PdfPCell cellI19 = new PdfPCell(new Paragraph("IMPÔT LOYER",en));
				cellI19.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI19);
				
				int ansU = Integer.parseInt(ans);
				List<Compte_proprietaire> listCP = compte_proprietaireDI.getAllCompte_proprietaire(site.getSite(),ansU,mois);
				int Sold = 0;
				for(Compte_proprietaire clas : listCP) {
					
					int index	=	listCP.indexOf(clas) + 1;
					
					PdfPCell cellI21 = new PdfPCell(new Paragraph(""+index,en));
					cellI21.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI21);
					
					PdfPCell cellI22 = new PdfPCell(new Paragraph(""+clas.getMois()+""+clas.getAns(),en));
					cellI22.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI22);
					
					PdfPCell cellI23 = new PdfPCell(new Paragraph(""+clas.getMontant_APC(),en));
					cellI23.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI23);
					
					PdfPCell cellI24 = new PdfPCell(new Paragraph(""+clas.getRetraitM(),en));
					cellI24.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI24);
					
					PdfPCell cellI25 = new PdfPCell(new Paragraph(""+impot_loyer.getMontant(),en));
					cellI25.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI25);
					
					 Sold = clas.getMontant_APC() - clas.getRetraitM();
				}
				
				document.add(tableI1);
				
				PdfPTable headerS = new PdfPTable(4);headerS.setWidthPercentage(100);headerS.setSpacingBefore(10f);			
				headerS.setSpacingAfter(10f);float[] colonneWidthHeadrS = {1.1f,1.1f,1.1f,1.1f};headerS.setWidths(colonneWidthHeadrS);
				
				PdfPCell celltII_1 = new PdfPCell(new Paragraph(" "));celltII_1.setColspan(2);
				celltII_1.setRowspan(2);celltII_1.setBorder(0);headerS.addCell(celltII_1);
				
													
				PdfPCell cellI_3 = new PdfPCell(new Paragraph("SOLDE NET ",en));
				headerS.addCell(cellI_3);
				
				PdfPCell cellI_4 = new PdfPCell(new Paragraph(""+Sold+" FCFA",e));
				headerS.addCell(cellI_4);
				
				PdfPCell celltII_2 = new PdfPCell(new Paragraph(" "));celltII_2.setColspan(2);
				celltII_2.setRowspan(2);celltII_2.setBorder(0);headerS.addCell(celltII_2);
				
				document.add(headerS);
			}
			
			//+++++++++++++++++++++++++++++++++++++++++++++++++ETAT COMPTE PROPRIETAIRE+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//	
			if(type.equals("compteAP")) {	
				PdfPTable headers = new PdfPTable(4);headers.setWidthPercentage(100);headers.setSpacingBefore(10f);			
				headers.setSpacingAfter(10f);float[] colonneWidthHeadrs = {1.1f,1.1f,1.1f,1.1f};headers.setWidths(colonneWidthHeadrs);
				
				PdfPCell celltIH = new PdfPCell(header);celltIH.setColspan(4);
				celltIH.setBorder(0);headers.addCell(celltIH);
				
				PdfPCell celltIH1 = new PdfPCell(new Paragraph(" "));celltIH1.setColspan(4);
				celltIH1.setBorder(0);headers.addCell(celltIH1);
				
				PdfPCell cellI1 = new PdfPCell(new Paragraph("PROPRIETAIRE",en));
				headers.addCell(cellI1);
				
				PdfPCell cellI2 = new PdfPCell(new Paragraph(""+proprietaire.getNom()+" "+proprietaire.getPrenom(),entete));
				headers.addCell(cellI2);
				
				PdfPCell celltII = new PdfPCell(new Paragraph(" "));celltII.setColspan(2);
				celltII.setRowspan(2);celltII.setBorder(0);headers.addCell(celltII);
				
				PdfPCell cellI3 = new PdfPCell(new Paragraph("SEXE",en));
				headers.addCell(cellI3);
				
				PdfPCell cellI4 = new PdfPCell(new Paragraph(""+proprietaire.getSexe(),entete));
				headers.addCell(cellI4);
										
				PdfPCell cellI5 = new PdfPCell(new Paragraph("SITE ",en));
				headers.addCell(cellI5);
				
				PdfPCell cellI6 = new PdfPCell(new Paragraph(""+site.getSite(),entete));
				headers.addCell(cellI6);
				
				PdfPCell celltII1 = new PdfPCell(new Paragraph(" "));celltII1.setColspan(2);
				celltII1.setRowspan(2);celltII1.setBorder(0);headers.addCell(celltII1);
				
				PdfPCell cellI7 = new PdfPCell(new Paragraph("TOTAL APPARTEMENT ",en));
				headers.addCell(cellI7);
				
				PdfPCell cellI8 = new PdfPCell(new Paragraph(""+site.getNombre_porte(),entete));
				headers.addCell(cellI8);
										
				PdfPCell cellI9 = new PdfPCell(new Paragraph("TELEPHONE",en));
				headers.addCell(cellI9);
				
				PdfPCell cellI10 = new PdfPCell(new Paragraph(""+proprietaire.getTel(),entete));
				headers.addCell(cellI10);
				
				PdfPCell celltII2 = new PdfPCell(new Paragraph(" "));celltII2.setColspan(2);
				celltII2.setRowspan(2);celltII2.setBorder(0);headers.addCell(celltII2);
				
				PdfPCell cellI11 = new PdfPCell(new Paragraph("EMAIL",en));
				headers.addCell(cellI11);
				
				PdfPCell cellI12 = new PdfPCell(new Paragraph(""+proprietaire.getEmail(),entete));
				headers.addCell(cellI12);
												
				PdfPCell cellI13 = new PdfPCell(new Paragraph("LIEU RESIDENCE",en));
				headers.addCell(cellI13);
				
				PdfPCell cellI14 = new PdfPCell(new Paragraph(""+proprietaire.getLieu_residence(),entete));
				headers.addCell(cellI14);
				
				PdfPCell celltII3 = new PdfPCell(new Paragraph(" "));celltII3.setColspan(2);
				celltII3.setRowspan(2);celltII3.setBorder(0);headers.addCell(celltII3);
				
				document.add(headers);
				
				PdfPTable tableI1 = new PdfPTable(5);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
				tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {0.3f,0.8f,0.8f,0.8f,0.8f};tableI1.setWidths(colonneWidthI1);
														
				PdfPCell cellI15 = new PdfPCell(new Paragraph("Num",en));
				cellI15.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI15);
				
				PdfPCell cellI16 = new PdfPCell(new Paragraph("PERIODE",en));
				cellI16.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI16);
				
				PdfPCell cellI17 = new PdfPCell(new Paragraph("MONTANT",en));
				cellI17.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI17);
				
				PdfPCell cellI18 = new PdfPCell(new Paragraph("MONTANT DECAISSER",en));
				cellI18.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI18);
				
				PdfPCell cellI19 = new PdfPCell(new Paragraph("IMPÔT LOYER",en));
				cellI19.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI19);
				
				int ansU = Integer.parseInt(ans);
				List<Compte_proprietaire> listCP = compte_proprietaireDI.getAllCompte_proprietaire(site.getSite(),ansU);
				int Sold = 0,Sm = 0,Sr = 0,Si = 0,Sc = 0;
				for(Compte_proprietaire clas : listCP) {
					
					int ansI = Integer.parseInt(ans);
					impot_loyer = impot_loyerDI.getImpot_loyer(site.getSite(),ansI,clas.getMois());
					
					int index	=	listCP.indexOf(clas) + 1;
					
					PdfPCell cellI21 = new PdfPCell(new Paragraph(""+index,en));
					cellI21.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI21);
					
					PdfPCell cellI22 = new PdfPCell(new Paragraph(""+clas.getMois()+""+clas.getAns(),en));
					cellI22.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI22);
					
					PdfPCell cellI23 = new PdfPCell(new Paragraph(""+clas.getMontant_APC(),en));
					cellI23.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI23);
					
					PdfPCell cellI24 = new PdfPCell(new Paragraph(""+clas.getRetraitM(),en));
					cellI24.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI24);
					
					PdfPCell cellI25 = new PdfPCell(new Paragraph(""+impot_loyer.getMontant(),en));
					cellI25.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI25);
					
					Sm += clas.getMontant_APC();  Sr += clas.getRetraitM();  Sold = Sm - Sr; 
					Si += impot_loyer.getMontant();  Sc += clas.getMontant_APC();
				}
				
				document.add(tableI1);
				
				PdfPTable headerS = new PdfPTable(4);headerS.setWidthPercentage(100);headerS.setSpacingBefore(10f);			
				headerS.setSpacingAfter(10f);float[] colonneWidthHeadrS = {1.1f,1.1f,1.1f,1.1f};headerS.setWidths(colonneWidthHeadrS);
				
				PdfPCell celltII_1 = new PdfPCell(new Paragraph(" "));celltII_1.setColspan(2);
				celltII_1.setRowspan(2);celltII_1.setBorder(0);headerS.addCell(celltII_1);
				
				PdfPCell cellI_1 = new PdfPCell(new Paragraph("SOLDE DECAISSEMENT ",en));
				headerS.addCell(cellI_1);
				
				PdfPCell cellI_2 = new PdfPCell(new Paragraph(""+Sr+" FCFA",e));
				headerS.addCell(cellI_2);
										
				PdfPCell cellI_3 = new PdfPCell(new Paragraph("SOLDE IMPÔT LOYER ",en));
				headerS.addCell(cellI_3);
				
				PdfPCell cellI_4 = new PdfPCell(new Paragraph(""+Si+" FCFA",e));
				headerS.addCell(cellI_4);
				
				PdfPCell celltII_2 = new PdfPCell(new Paragraph(" "));celltII_2.setColspan(2);
				celltII_2.setRowspan(2);celltII_2.setBorder(0);headerS.addCell(celltII_2);
				
				PdfPCell cellI_5 = new PdfPCell(new Paragraph("SOLDE COMPTE ",en));
				headerS.addCell(cellI_5);
				
				PdfPCell cellI_6 = new PdfPCell(new Paragraph(""+Sc+" FCFA",e));
				headerS.addCell(cellI_6);
				
				PdfPCell cellI_7 = new PdfPCell(new Paragraph("SOLDE NET ",en));
				headerS.addCell(cellI_7);
				
				PdfPCell cellI_8 = new PdfPCell(new Paragraph(""+Sold+" FCFA",e));
				headerS.addCell(cellI_8);
				
				document.add(headerS);
				
				
			}
						
 					
			document.close();
		}  catch (DocumentException de) {
			throw new ServletException(de);
		}
	}
}
