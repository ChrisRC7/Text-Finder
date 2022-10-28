package Lectores;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import Arboles.AvlTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class LectorDOCX {
    public static AvlTree<String> LeerDocx(String Documento){
        AvlTree<String> Tree;
        Tree = new AvlTree<String>();
        Tree.SetDocName(Documento);
        try {
            FileInputStream file = new FileInputStream("src/Lectores/"+Documento);

            XWPFDocument docx = new XWPFDocument(file);
            List<XWPFParagraph> paragraphList = docx.getParagraphs();

            String line = System.getProperty("line.separator");
            for(XWPFParagraph paragraph: paragraphList){
                String [] Palabras_separadas = paragraph.getText().replaceAll(line,"").split(" ");
                
                for (String Palabras : Palabras_separadas){
                    if (Palabras!=""){
                        Tree.insert(Palabras.toLowerCase());
                    }
                }
            }
            docx.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Tree;

    }
}
