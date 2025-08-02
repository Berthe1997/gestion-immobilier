package com.news.pdfs.entete;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class Synthese extends PdfPageEventHelper{
	
	private static final String FORMAT_DATE 			= "dd/MM/yyy";

	public Synthese() {
		
	}
	
	@Override
	public void onStartPage(PdfWriter writer, Document document) {
		Font en = new Font(FontFamily.TIMES_ROMAN,9.0f, Font.ITALIC, BaseColor.LIGHT_GRAY);
		
		DateTime dt = new DateTime();
		DateTimeFormatter formatter = DateTimeFormat.forPattern( FORMAT_DATE );
		String date = dt.toString( formatter);
		
		PdfPTable headers = new PdfPTable(1);headers.setWidthPercentage(100);
		PdfPCell cell = new PdfPCell(new Paragraph("Imprimé le "+date,en));cell.setBorder(0);
		headers.addCell(cell);
		Rectangle page = document.getPageSize();
		
		headers.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
		headers.writeSelectedRows(0, -1, 10, page.getHeight() - 2, writer.getDirectContent());
	}
	
	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		Font font = new Font(FontFamily.TIMES_ROMAN,9.0f, Font.NORMAL, BaseColor.GRAY);
		Font f = new Font(FontFamily.TIMES_ROMAN,9.0f, Font.ITALIC, BaseColor.GRAY);
		int pageNumber = writer.getPageNumber();
		String text = "Page   " + pageNumber;
		Rectangle page = document.getPageSize();
		PdfPTable head = new PdfPTable(2);
		float[] colonneWidth5 = {0.8f,0.2f};
		try {
			head.setWidths(colonneWidth5);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PdfPCell cell00 = new PdfPCell(new Paragraph("DIAKITE Immobilier et Conseils (DIMCO) , situé à Yopougon Béago ,arrêt du bus 600 du petit marché de\r\n" + 
				"Béago , N° RCCM : CI-ABJ-03-2022-B12-05393 , N° CC: 2244022F\r\n" + 
				"infos@dimconseils.com , (+225) 07 59 27 94 48.",f));cell00.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell00.setBorder(0);head.addCell(cell00);
		
		PdfPCell cell = new PdfPCell(new Paragraph(text, font));cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorder(0);head.addCell(cell);
		
		head.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
		head.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());
	}
}
