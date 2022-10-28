package Lectores;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import Arboles.AvlTree;

import java.io.File;
import java.io.IOException;

public class LectorPDF {
    public static AvlTree<String> LeerPDF(String Documento){
        AvlTree<String> Tree;
        Tree = new AvlTree<String>();
        Tree.SetDocName(Documento);
        try {
            PDDocument document = PDDocument.load(new File("src/Lectores/"+Documento));
            PDFTextStripper pdftext = new PDFTextStripper();

            String line = System.getProperty("line.separator");
            String pdftextdata = pdftext.getText(document);
            String [] Separador= pdftextdata.replaceAll(line,"").split(" ");
            
            for (String Palabras : Separador){
                
                if (Palabras!=""){
                    Tree.insert(Palabras);
                }
            }
            document.close();

        } catch (IOException e) {
            System.out.println("error");
            //throw new RuntimeException(e);
        }
        return Tree;

    }
}
