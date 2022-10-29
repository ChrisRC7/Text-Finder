package Lectores;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import Arboles.*;

import java.io.File;
import java.io.IOException;

public class LectorPDF {
    AvlTree<String> AvlTree;
    BinaryTree<String> BinaryTree;

    public  void LeerPDF(String Documento){
        this.AvlTree = new AvlTree<String>();
        this.AvlTree.SetDocName(Documento);

        this.BinaryTree= new BinaryTree<String>();
        this.BinaryTree.SetDocName(Documento);
        try {
            PDDocument document = PDDocument.load(new File(Documento));
            PDFTextStripper pdftext = new PDFTextStripper();

            String line = System.getProperty("line.separator");
            String pdftextdata = pdftext.getText(document);
            String [] Separador= pdftextdata.replaceAll(line, "").replaceAll("\\s*$","").split(" ");
            
            for (String Palabras : Separador){
                
                if (Palabras!=""){
                    this.AvlTree.insert(Palabras.toLowerCase());
                    this.BinaryTree.insert(Palabras.toLowerCase());
                }
            }
            document.close();

        } catch (IOException e) {
            System.out.println("error");
            //throw new RuntimeException(e);
        }

    }

    public AvlTree<String> GetAvl() {
        return this.AvlTree;
    }

    public BinaryTree<String> GetBinary() {
        return this.BinaryTree;
    }
}
