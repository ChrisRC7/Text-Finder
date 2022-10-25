package Lectores;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class LectorDOCX {
    public static void main(String[] args){
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\ignac\\IdeaProjects\\TextFinder\\src\\Lectores\\Prueba docx.docx");

            XWPFDocument docx = new XWPFDocument(fis);
            List<XWPFParagraph> paragraphList = docx.getParagraphs();

            for(XWPFParagraph paragraph: paragraphList){
                System.out.println(paragraph.getText());
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
