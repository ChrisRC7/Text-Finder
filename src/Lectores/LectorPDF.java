package Lectores;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class LectorPDF {
    public static void main(String[] args){
        try {
            PDDocument document = PDDocument.load(new File("C:\\Users\\ignac\\Desktop\\Text-Finder-main\\Text-Finder-main\\src\\Lectores\\Proyectos_Proyecto_#2_-_Text_Finder.pdf"));
            PDFTextStripper pdftext = new PDFTextStripper();

            String pdftextdata = pdftext.getText(document);

            System.out.println(pdftextdata);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
