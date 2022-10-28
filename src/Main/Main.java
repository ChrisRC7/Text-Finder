package Main;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

import Arboles.*;
import Interfaz.Interfaz;
import Lectores.LectorDOCX;
import Lectores.LectorPDF;
import Lectores.LectorTXT;
import ListasEnlazadas.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception {

        LinkedList ArbolesAvl= new LinkedList();
        LinkedList ArbolesBinary= new LinkedList();
        File Carpeta = new File("src/Lectores");
        LectorDOCX Docx= new LectorDOCX();
        LectorPDF Pdf= new LectorPDF();
        LectorTXT Txt= new LectorTXT();

        String[] archivos = Carpeta.list();

        for (int i = 0; i < archivos.length; i++) {
            String archivo_acutal= archivos[i];
            String tipo= FilenameUtils.getExtension(archivo_acutal);
            if (tipo.equals("docx")){
                Docx.LeerDocx(archivo_acutal);

                AvlTree<String> AvlTree= Docx.GetAvl();
                ArbolesAvl.insertLast(AvlTree);

                BinaryTree<String> BinaryTree= Docx.GetBinary();
                ArbolesBinary.insertLast(BinaryTree);

            }
            
            if (tipo.equals("pdf")){
                Pdf.LeerPDF(archivo_acutal);

                AvlTree<String> Tree= Pdf.GetAvl();
                ArbolesAvl.insertLast(Tree);

                BinaryTree<String> BinaryTree= Pdf.GetBinary();
                ArbolesBinary.insertLast(BinaryTree);

            }
            if (tipo.equals("txt")){
                Txt.LeerTXT(archivo_acutal);

                AvlTree<String> Tree= Txt.GetAvl();
                ArbolesAvl.insertLast(Tree);

                BinaryTree<String> BinaryTree= Txt.GetBinary();
                ArbolesBinary.insertLast(BinaryTree);
            }
        }
        
        Interfaz.VentanaInicio();
        Interfaz.SetListaAvl(ArbolesAvl);
        Interfaz.SetListaBinary(ArbolesBinary);

    }
}
