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

public class EnteteBilan extends PdfPageEventHelper {
	private static final String FORMAT_DATE 			= "dd/MM/yyyy HH:mm:ss";
	
	public EnteteBilan() {}
	
	@Override
	public void onStartPage(PdfWriter writer, Document document) {
		Font font = new Font(FontFamily.TIMES_ROMAN,8.0f, Font.NORMAL, BaseColor.BLACK);
		
		DateTime dt = new DateTime();
		DateTimeFormatter formatter = DateTimeFormat.forPattern( FORMAT_DATE );
		String date = dt.toString( formatter);
		
		PdfPTable headers = new PdfPTable(1);
		headers.setWidthPercentage(100);
		headers.setSpacingAfter(2f);
		
		PdfPCell cell29 = new PdfPCell(new Paragraph("Brouillard du "+date,font));cell29.setBorder(0);
		//cell29.setBorderWidthTop(0);cell29.setBorderWidthLeft(0);cell29.setBorderWidthRight(0);
		//cell29.setBorderWidthBottom(2);cell29.setBorderColorBottom(BaseColor.DARK_GRAY);
		
		headers.addCell(cell29);
		Rectangle page = document.getPageSize();
	
		headers.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
		headers.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight() - 5, writer.getDirectContent());
	}
	
	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		Font font = new Font(FontFamily.TIMES_ROMAN,9.0f, Font.NORMAL, BaseColor.GRAY);
		
		DateTime dt = new DateTime();
		DateTimeFormatter formatter = DateTimeFormat.forPattern( FORMAT_DATE );
		String date = dt.toString( formatter);
		
		int pageNumber = writer.getPageNumber();
		String text = "Page   " + pageNumber;
		Rectangle page = document.getPageSize();
		PdfPTable head = new PdfPTable(2);float[] colonneWidth = {0.7f,0.3f};
		try {
			head.setWidths(colonneWidth);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PdfPCell celli = new PdfPCell(new Paragraph("imprimé le "+date, font));
		celli.setHorizontalAlignment(Element.ALIGN_LEFT);
		celli.setBorder(0);head.addCell(celli);
		
		PdfPCell cell = new PdfPCell(new Paragraph(text, font));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorder(0);head.addCell(cell);
		
		head.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
		head.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());
	}
}
