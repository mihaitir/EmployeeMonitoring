package com.raport;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.Random;
import com.backend.Echipa;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class RaportEchipa {
    
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    public Echipa echipa;
    
    public RaportEchipa(Echipa echipa){
        try {
        	this.echipa = echipa;
            Document document = new Document();
            Random rand = new Random(); 
            // Generate random integers in range 0 to 999 
            int r = rand.nextInt(1000); 
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\mihai\\Desktop/RaportEchipa"+r+".pdf"));
            document.open();
            addTitlePage(document,echipa);
            addContent(document,echipa);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTitlePage(Document document, Echipa echipa) throws DocumentException {
        Paragraph titlePage = new Paragraph();
        addEmptyLine(titlePage, 5);
        
        Paragraph title = new Paragraph("Raport privind situatia echipelor");
        title.setAlignment(Paragraph.ALIGN_CENTER);
        title.setFont(catFont);
//        titlePage.add(title);

        addEmptyLine(titlePage, 5);
        titlePage.add(new Paragraph("Raportul a fost generat in data de: " + new Date(), smallBold));
        addEmptyLine(titlePage, 5);
        
        titlePage.add(new Paragraph("Acest tabel contine situatia sub forma tabelara a echipei "+echipa.getNume()));
       
        document.add(titlePage);
//        document.newPage();
    }

    private static void addContent(Document document,Echipa echipa) throws DocumentException {
    	Paragraph p=new Paragraph();
    	addEmptyLine(p,3);
        Paragraph numeEchipa = new Paragraph(echipa.getNume());
        numeEchipa.setAlignment(Paragraph.ALIGN_CENTER);
        
        addEmptyLine(numeEchipa,5);
        
        createTable(numeEchipa,echipa);

        document.add(numeEchipa);
    }

    private static void createTable(Paragraph numeEchipa,Echipa echipa)
            throws BadElementException {
        PdfPTable table = new PdfPTable(4);

        PdfPCell c1 = new PdfPCell(new Phrase("Nume angajat"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("TimpInceput"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("TimpSfarsit"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Disponibilitate"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        for(int i=0; i<echipa.getListaAngajati().size();i++) {
        	table.addCell(echipa.getListaAngajati().get(i).getNume());
        	table.addCell(String.valueOf(echipa.getListaAngajati().get(i).getTimpInceput()));
        	table.addCell(String.valueOf(echipa.getListaAngajati().get(i).getTimpSfarsit()));
        	if(!echipa.getListaAngajati().get(i).isDisponibil())
        		table.addCell("Indisponibil");
        }
        numeEchipa.add(table);

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}