package Lectores;

import Arboles.AvlTree;
import Arboles.BinaryTree;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;  
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;
import org.apache.tika.parser.pdf.PDFParser;  
import org.apache.tika.parser.ParseContext;  
public class LectorPDF {
    AvlTree<String> AvlTree;
    BinaryTree<String> BinaryTree;

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

    public AvlTree<String> GetAvl() {
        return this.AvlTree;
    }

    public BinaryTree<String> GetBinary() {
        return this.BinaryTree;
    }
}
