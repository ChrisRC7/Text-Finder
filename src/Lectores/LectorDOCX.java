// Importing the necessary libraries to read the .docx file.
package Lectores;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import arboles.AvlTree;
import arboles.BinaryTree;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * It reads a .docx file.
 */
public class LectorDOCX {
    // Declaring the variables AvlTree and BinaryTree.
    AvlTree<String> AvlTree;
    BinaryTree<String> BinaryTree;

    /**
     * It reads a .docx file and inserts each word into an AVL Tree and a Binary Tree
     * 
     * @param Documento The name of the document to be read.
     */
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

    
    /**
     * This function returns the AvlTree object
     * 
     * @return The AvlTree object.
     */
    public AvlTree<String> GetAvl() {
        return this.AvlTree;
    }

    /**
     * This function returns the BinaryTree object that is stored in the BinaryTree class
     * 
     * @return The BinaryTree object.
     */
    public BinaryTree<String> GetBinary() {
        return this.BinaryTree;
    }
}
