package Lectores;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import Arboles.AvlTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class LectorDOCX {
    public static void main(String[] args){
        AvlTree Tree;
        Tree = new AvlTree();
        try {
            FileInputStream fis = new FileInputStream("src/Lectores/Prueba docx.docx");

            XWPFDocument docx = new XWPFDocument(fis);
            List<XWPFParagraph> paragraphList = docx.getParagraphs();

            for(XWPFParagraph paragraph: paragraphList){
                String [] Palabras_separadas = paragraph.getText().split(" ");
                
                for (String Palabras : Palabras_separadas){
                    Tree.insert(Palabras);
                }
                //System.out.println(paragraph.getText());
            }
            Tree.contains("U+1F60A");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
