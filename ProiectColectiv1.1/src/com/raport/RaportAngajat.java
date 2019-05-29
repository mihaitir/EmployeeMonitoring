package com.raport;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.backend.Angajat;
import com.backend.IstoricMuncaHandler;
import com.backend.IstoricMunca;
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

public class RaportAngajat {
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    public Angajat angajat;
    
    public RaportAngajat(Angajat angajat){
        try {
        	
            Document document = new Document();
            Random rand = new Random(); 
            // Generate random integers in range 0 to 999 
            int r = rand.nextInt(1000); 
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\mihai\\Desktop/RaportAngajat"+r+".pdf"));
            document.open();
            addTitlePage(document,angajat);
            addContent(document,angajat);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTitlePage(Document document, Angajat angajat) throws DocumentException {
        Paragraph titlePage = new Paragraph();
        addEmptyLine(titlePage, 5);
        
        Paragraph title = new Paragraph("Raport privind situatia echipelor in care a activat angajatul " +angajat.getNume());
        title.setAlignment(Paragraph.ALIGN_CENTER);
        title.setFont(catFont);

        addEmptyLine(titlePage, 5);
        titlePage.add(new Paragraph("Raportul a fost generat in data de: " + new Date(), smallBold));
        addEmptyLine(titlePage, 5);
        
        titlePage.add(new Paragraph("Acest tabel contine situatia sub forma tabelara a angajatului "+angajat.getNume()));
       
        document.add(titlePage);
//        document.newPage();
    }

    private static void addContent(Document document,Angajat angajat) throws DocumentException {
    	Paragraph p=new Paragraph();
    	addEmptyLine(p,3);
        Paragraph numeAngajat = new Paragraph(angajat.getNume());
        numeAngajat.setAlignment(Paragraph.ALIGN_CENTER);
        
        addEmptyLine(numeAngajat,5);
        
        createTable(numeAngajat,angajat);

        document.add(numeAngajat);
    }

    private static void createTable(Paragraph numeEchipa,Angajat angajat)
            throws BadElementException {
        PdfPTable table = new PdfPTable(5);

        PdfPCell c1 = new PdfPCell(new Phrase("Nume angajat"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("TimpInceput"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("TimpSfarsit"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Echipa"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Departament"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
       
        table.setHeaderRows(1);

        IstoricMuncaHandler imh = new IstoricMuncaHandler();
        ArrayList<IstoricMunca> istoric = imh.getIsoriceRealizatePentruAnumitAngajat(angajat.getId());
        System.out.println(istoric);
        
        for(int i=0; i<istoric.size();i++) {
        	table.addCell(String.valueOf(imh.getNumeAngajatDupaId(istoric.get(i).getIdAngajat())));
        	table.addCell(String.valueOf(istoric.get(i).getTimpInceput()));
        	table.addCell(String.valueOf(istoric.get(i).getTimpSfarsit()));
        	table.addCell(String.valueOf(imh.getNumeEchipaDupaId(istoric.get(i).getIdEchipa())));
        	table.addCell(String.valueOf(imh.getDepartamentDupaId(istoric.get(i).getIdEchipa())));
        	
        }
        numeEchipa.add(table);

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
	

}
