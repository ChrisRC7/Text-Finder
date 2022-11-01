// Importing the necessary libraries to read the PDF file.
package Lectores;

import Arboles.AvlTree;
import Arboles.BinaryTree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationTextMarkup;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;  
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;
import org.apache.tika.parser.pdf.PDFParser;  
import org.apache.tika.parser.ParseContext;  
// A class that reads a PDF file and stores the words in a binary tree and an AVL tree.
public class LectorPDF {
   // Creating a new object of the AvlTree and BinaryTree classes.
    AvlTree<String> AvlTree;
    BinaryTree<String> BinaryTree;

    
    /** 
     * It reads a PDF file and stores the words in a binary tree and an AVL tree
     * @param Documento
     * @throws SAXException
     * @throws TikaException
     */
    public  void LeerPDF(String Documento) throws SAXException, TikaException{
        this.AvlTree = new AvlTree<String>();
        this.AvlTree.SetDocName(Documento);

        this.BinaryTree= new BinaryTree<String>();
        this.BinaryTree.SetDocName(Documento);
        try {
            // Creating an object of the BodyContentHandler class  
            BodyContentHandler Contendedor = new BodyContentHandler();  
            // Creating a file object  
            File file = new File(Documento);  
            // Create a FileInputStream object on  
            // the path specified using the created file object file  
            FileInputStream Entrada_file = new FileInputStream(file);  
            // Creating an object of the type Metadata  
            Metadata datos = new Metadata();  
            // Creating an object of the ParseContext class  
            ParseContext ParseContext = new ParseContext();  
            // creating an object of the class PDFParser  
            PDFParser PdfParser = new PDFParser();  
            // calling the parse() method using the   
            // object of the PDFParser class  
            PdfParser.parse(Entrada_file, Contendedor, datos, ParseContext);  
            // Displaying the contents   
            // of the pdf file by invoking the toString() method  
            String pdftextdata = Contendedor.toString();
            String line = System.getProperty("line.separator");  
            //System.out.println("Extracting the contents from the file: \n" + Contendedor.toString());
            

            String [] Separador= pdftextdata.replaceAll(line, "").replaceAll("\\s*$","").split(" ");
            
            for (String Palabras : Separador){
                
                if (Palabras!=""){
                    this.AvlTree.insert(Palabras.toLowerCase());
                    this.BinaryTree.insert(Palabras.toLowerCase());
                }
            }

        } catch (IOException e) {
            System.out.println("error");
            //throw new RuntimeException(e);
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
