package Lectores;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import Arboles.AvlTree;
import Arboles.BinaryTree;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class LectorDOCX {
    AvlTree<String> AvlTree;
    BinaryTree<String> BinaryTree;

    public void LeerDocx(String Documento){
        this.AvlTree = new AvlTree<String>();
        this.AvlTree.SetDocName(Documento);

        this.BinaryTree= new BinaryTree<String>();
        this.BinaryTree.SetDocName(Documento);
        try {
            FileInputStream file = new FileInputStream(Documento);

            XWPFDocument docx = new XWPFDocument(file);
            List<XWPFParagraph> paragraphList = docx.getParagraphs();

            String line = System.getProperty("line.separator");
            for(XWPFParagraph paragraph: paragraphList){
                String [] Palabras_separadas = paragraph.getText().replaceAll(line,"").split(" ");
                
                for (String Palabras : Palabras_separadas){
                    if (Palabras!=""){
                        this.AvlTree.insert(Palabras.toLowerCase());
                        this.BinaryTree.insert(Palabras.toLowerCase());
                    }
                }
            }
            docx.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AvlTree<String> GetAvl() {
        return this.AvlTree;
    }

    public BinaryTree<String> GetBinary() {
        return this.BinaryTree;
    }
}
