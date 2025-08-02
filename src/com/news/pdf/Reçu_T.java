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
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.beans_GT.Client_acheteur;
import com.news.beans_GT.Paiement_terrain;
import com.news.beans_GT.Terrain;
import com.news.dao.AgenceDI;
import com.news.dao.SiteDI;
import com.news.dao_GT.Client_acheteurDI;
import com.news.dao_GT.Paiement_terrainDI;
import com.news.dao_GT.TerrainDI;
import com.news.fonctions.VerifieSession;
import com.news.pdfs.entete.Synthese;

@WebServlet("/reçu_T")
public class Reçu_T extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	private static final String FORMAT_DATE 			= "dd-MM-yyy";
	private static final String FORMAT_DATES			= "dd/MM/yyyy";
	
    Users users	=	new Users();
	
	AgenceDI agenceDI = new AgenceDI();
	Agence agence = new Agence();
	
	SiteDI siteDI = new SiteDI();
	Site site = new Site();
	
	Client_acheteurDI client_acheteurDI= new Client_acheteurDI();
	Client_acheteur client_acheteur= new Client_acheteur();
	
	TerrainDI terrainDI= new TerrainDI();
	Terrain terrain= new Terrain();
	
	Paiement_terrainDI paiement_terrainDI= new Paiement_terrainDI();
	Paiement_terrain paiement_terrain= new Paiement_terrain();
	
	AtomicReference<String> errorMsg = new  AtomicReference<>("");
	VerifieSession verifieSess = new VerifieSession();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id	=	request.getParameter("id");
		String terrains	=	request.getParameter("terrain");
		String client	=	request.getParameter("client");
		
     String uploadDir = this.getServletContext().getInitParameter("upload_dir");
		
		users = (Users) session.getAttribute("users");
		site = (Site) session.getAttribute("site");
		agence = (Agence) session.getAttribute("agence");
		
		if(verifieSess.sessions(request, response) == false) {
			response.sendRedirect( request.getContextPath() + INDEX );
			return;
		}
		
		client_acheteur = client_acheteurDI.getClient_acheteur(client);				
		terrain = terrainDI.getTerrain(terrains);
		paiement_terrain = paiement_terrainDI.getPaiement_terrain(Integer.parseInt(id));
				
		Document document = new Document();
		try {
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline; filename=QUITANCE DE PAIEMENT.pdf");
			

			PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
			writer.setPageEvent(new Synthese());
			document.setMargins(Utilities.millimetersToPoints(45), Utilities.millimetersToPoints(45), Utilities.millimetersToPoints(45),
					Utilities.millimetersToPoints(25));
			document.open();
			
			Font font = new Font(FontFamily.TIMES_ROMAN,9.0f, Font.BOLD, BaseColor.BLACK);
			Font fon = new Font(FontFamily.TIMES_ROMAN,9.0f, Font.BOLD, BaseColor.BLACK);
			Font f = new Font(FontFamily.TIMES_ROMAN,10.0f, Font.BOLD, BaseColor.BLACK);
			Font en = new Font(FontFamily.TIMES_ROMAN,9.0f, Font.NORMAL, BaseColor.BLACK);
			Font entete = new Font(FontFamily.HELVETICA,8.0f, Font.BOLD, BaseColor.BLACK);
			Font e = new Font(FontFamily.TIMES_ROMAN,11.0f, Font.BOLD, BaseColor.BLUE);
			/*Font te = new Font(FontFamily.TIMES_ROMAN,10.0f, Font.BOLD, BaseColor.BLUE);*/
			
			PdfPTable header = new PdfPTable(4); header.setWidthPercentage(100);
			float[] colonneWidthHead = {0.8f,1.2f,2f,1.5f};header.setWidths(colonneWidthHead);
			
			PdfPCell cellt1 = new PdfPCell(new Paragraph("DIAKITE Immobilier et Conseils",font));
			cellt1.setBorderWidthRight(0);cellt1.setBorder(0);cellt1.setColspan(3);header.addCell(cellt1);
							
			if(agence.getLogo() != null) {
				Image logo = Image.getInstance(uploadDir+"erreur.png");
				File file = new File(uploadDir+"logos/"+agence.getLogo()+"");
				if(file.exists()) logo = Image.getInstance(uploadDir+"logos/"+agence.getLogo()+"");
				logo.scaleAbsolute(70,80);
				PdfPCell cell11 = new PdfPCell(logo);cell11.setBorder(0);cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell11.setRowspan(7);cell11.setPadding(1);header.addCell(cell11);
			}else {
				Image image0 = Image.getInstance(uploadDir+"erreur.png");
				image0.scaleAbsolute(70, 80);
				PdfPCell cell11 = new PdfPCell(image0);cell11.setHorizontalAlignment(Element.ALIGN_CENTER);cell11.setRowspan(7);
				cell11.setBorder(0);cell11.setPadding(1);header.addCell(cell11);
			}
			
			PdfPCell cellt2 = new PdfPCell(new Paragraph("(+225) 07 59 27 94 48",font));
			cellt2.setBorder(0);cellt2.setBorder(0);cellt2.setColspan(3);header.addCell(cellt2);
			
			PdfPCell cellt3 = new PdfPCell(new Paragraph("infos@dimconseils.com",font));
			cellt3.setBorder(0);cellt3.setBorder(0);cellt3.setColspan(3);header.addCell(cellt3);
			
			PdfPCell cellt4 = new PdfPCell(new Paragraph("",font));
			cellt4.setColspan(3);cellt4.setBorder(0);header.addCell(cellt4);
			
			PdfPCell cellt7 = new PdfPCell(new Paragraph("",font));
			cellt7.setColspan(3);cellt7.setBorder(0);header.addCell(cellt7);
			
			PdfPCell cellt5 = new PdfPCell(new Paragraph("Date : "+paiement_terrain.getDateP(),font));
			cellt5.setBorder(0);cellt5.setColspan(3);header.addCell(cellt5);
			
			PdfPCell cellt6 = new PdfPCell(new Paragraph("Client (e) : "+client_acheteur.getNom()+" "+client_acheteur.getPrenom(),font));
			cellt6.setBorder(0);cellt6.setBorder(0);cellt6.setColspan(3);header.addCell(cellt6);
						
			document.add(header);
			
			PdfPTable tableI1 = new PdfPTable(4);tableI1.setWidthPercentage(100);tableI1.setSpacingBefore(10f);
			tableI1.setSpacingAfter(10f);float[] colonneWidthI1 = {1.7f,0.6f,0.7f,0.7f};tableI1.setWidths(colonneWidthI1);
													
			PdfPCell cellI15 = new PdfPCell(new Paragraph("Lieu : "+terrain.getAdresse() +"( Côte d'Ivoire)",en));
			cellI15.setHorizontalAlignment(Element.ALIGN_CENTER);cellI15.setColspan(4);tableI1.addCell(cellI15);
			
			PdfPCell cellI16 = new PdfPCell(new Paragraph("Libellé",en));
			cellI16.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI16);
			
			PdfPCell cellI17 = new PdfPCell(new Paragraph("Quantité",en));
			cellI17.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI17);
			
			PdfPCell cellI18 = new PdfPCell(new Paragraph("Prix Unitaire en(FCFA)",en));
			cellI18.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI18);
			
			PdfPCell cellI19 = new PdfPCell(new Paragraph("Prix Total en(FCFA)",en));
			cellI19.setHorizontalAlignment(Element.ALIGN_CENTER);tableI1.addCell(cellI19);
			
			//----------------------------------LIGNE 2-------------------------//
			PdfPCell cellI20 = new PdfPCell(new Paragraph("Achat de terrain approuvé - "+terrain.getLot()+" ; ilot : "+terrain.getIlot() ,en));cellI20.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellI20.setBorderWidthTop(0);cellI20.setBorderWidthRight(0);cellI20.setBorderWidthBottom(0);tableI1.addCell(cellI20);
				
			PdfPCell cellI21 = new PdfPCell(new Paragraph("1",en));
			cellI21.setHorizontalAlignment(Element.ALIGN_CENTER);cellI21.setBorder(0);tableI1.addCell(cellI21);
			
			PdfPCell cellI22 = new PdfPCell(new Paragraph(""+terrain.getPrixG(),en));
			cellI22.setHorizontalAlignment(Element.ALIGN_CENTER);cellI22.setBorder(0);tableI1.addCell(cellI22);
			
			PdfPCell cellI23 = new PdfPCell(new Paragraph(""+terrain.getPrixG(),en));cellI23.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellI23.setBorderWidthBottom(0);cellI23.setBorderWidthLeft(0);cellI23.setBorderWidthTop(0);tableI1.addCell(cellI23);
			
			//----------------------------------LIGNE 3-------------------------//
			PdfPCell cellI24 = new PdfPCell(new Paragraph("Honoraires et Documentation",en));cellI24.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellI24.setBorderWidthRight(0);cellI24.setBorderWidthTop(0);tableI1.addCell(cellI24);
			//cellI24.setBorderWidthLeft(0);
									
			PdfPCell cellI25 = new PdfPCell(new Paragraph("1",en));cellI25.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellI25.setBorderWidthLeft(0);cellI25.setBorderWidthRight(0);cellI25.setBorderWidthTop(0);tableI1.addCell(cellI25);
			
			PdfPCell cellI26 = new PdfPCell(new Paragraph(""+client_acheteur.getHonoraire(),en));cellI26.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellI26.setBorderWidthLeft(0);cellI26.setBorderWidthRight(0);cellI26.setBorderWidthTop(0);tableI1.addCell(cellI26);
			
			PdfPCell cellI27 = new PdfPCell(new Paragraph(""+client_acheteur.getHonoraire(),en));cellI27.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellI27.setBorderWidthLeft(0);cellI27.setBorderWidthTop(0);tableI1.addCell(cellI27);
			
			document.add(tableI1);
			
			int tot=0;
			tot = client_acheteur.getHonoraire() + terrain.getPrixG();
			
			PdfPTable tableA1 = new PdfPTable(4);tableA1.setWidthPercentage(100);tableA1.setSpacingBefore(20f);
			tableA1.setSpacingAfter(10f);float[] colonneWidthA1 = {1.7f,0.6f,0.7f,0.7f};tableA1.setWidths(colonneWidthA1);
			
			PdfPCell cellA1 = new PdfPCell(new Paragraph("TOTAL TTC",f));cellA1.setColspan(2);
			cellA1.setBorder(0);cellA1.setHorizontalAlignment(Element.ALIGN_RIGHT);tableA1.addCell(cellA1);
			
			PdfPCell cellA2 = new PdfPCell(new Paragraph(""+tot+" FCFA",f));cellA2.setColspan(2);
			cellA2.setBorder(0);cellA2.setHorizontalAlignment(Element.ALIGN_CENTER);tableA1.addCell(cellA2);
			
			PdfPCell cellA3 = new PdfPCell(new Paragraph("TOTAL PAYER",f));cellA3.setColspan(2);
			cellA3.setBorder(0);cellA3.setHorizontalAlignment(Element.ALIGN_RIGHT);tableA1.addCell(cellA3);
			
			PdfPCell cellA4 = new PdfPCell(new Paragraph(""+paiement_terrain.getMontantP()+" FCFA",f));cellA4.setColspan(2);
			cellA4.setBorder(0);cellA4.setHorizontalAlignment(Element.ALIGN_CENTER);tableA1.addCell(cellA4);
			
			PdfPCell cellA5 = new PdfPCell(new Paragraph("TOTAL RESTANT",f));cellA5.setColspan(2);
			cellA5.setBorder(0);cellA5.setHorizontalAlignment(Element.ALIGN_RIGHT);tableA1.addCell(cellA5);
			
			PdfPCell cellA6 = new PdfPCell(new Paragraph(""+paiement_terrain.getMontantR()+" FCFA",f));cellA6.setColspan(2);
			cellA6.setBorder(0);cellA6.setHorizontalAlignment(Element.ALIGN_CENTER);tableA1.addCell(cellA6);
			
			PdfPCell cellA7 = new PdfPCell(new Paragraph("\n \n \n",en));cellA7.setColspan(4);
			cellA7.setBorder(0);tableA1.addCell(cellA7);
			
			/*
			PdfPCell cellA8 = new PdfPCell(new Paragraph("Arrêté la presente facture à la somme de : Six millions deux-cent mille Francs CFA",en));cellA8.setColspan(4);
			cellA8.setBorder(0);cellA8.setHorizontalAlignment(Element.ALIGN_CENTER);tableA1.addCell(cellA8);
			*/
			
			PdfPCell cellA9 = new PdfPCell(new Paragraph("\n \n \n \n \n \n",en));cellA9.setColspan(4);
			cellA9.setBorder(0);tableA1.addCell(cellA9);
			
			PdfPCell cellA10 = new PdfPCell(new Paragraph("Directeur Général :",en));cellA10.setColspan(4);
			cellA10.setBorder(0);cellA10.setHorizontalAlignment(Element.ALIGN_RIGHT);tableA1.addCell(cellA10);
			
			PdfPCell cellA11 = new PdfPCell(new Paragraph(""+agence.getDirecteur(),en));cellA11.setColspan(4);
			cellA11.setBorder(0);cellA11.setHorizontalAlignment(Element.ALIGN_RIGHT);tableA1.addCell(cellA11);
			
			
			document.add(tableA1);
			
		document.close();
		} catch(DocumentException de) {
			de.printStackTrace();
		} catch(IOException ie) {
			ie.printStackTrace();
		}
		
	}
}
