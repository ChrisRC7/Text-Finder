/**
 * This class is a server that receives a file, a folder, a word or a number, and depending on the
 * input, it will do a specific action
 */
package Sockets;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import ListasEnlazadas.LinkedList;
import arboles.AvlTree;
import arboles.BinaryTree;
import Lectores.LectorDOCX;
import Lectores.LectorPDF;
import Lectores.LectorTXT;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import javax.swing.JFileChooser;



/**
 * A class that implements the Runnable interface.
 */
public class Servidor implements Runnable{
    LinkedList Archivos= new LinkedList();
    LinkedList ArbolesAvl= new LinkedList();
    LinkedList ArbolesBinary= new LinkedList();
    ArrayList<String []> Resultados= new ArrayList<String []>();
    static String Archivos_selecionados= "Datos,";
    AvlTree<String> AvlTree;
    BinaryTree<String> BinaryTree;
    LectorDOCX Docx= new LectorDOCX();
    LectorPDF Pdf= new LectorPDF();
    LectorTXT Txt= new LectorTXT();

    
    /**
     * IniciarServidor() creates a new thread and starts it.
     */
    public void IniciarServidor(){
        Thread mihilo = new Thread(this);
        mihilo.start();
    }

   /**
    * I'm listening for a connection, when I get one, I read the object, if it's a string, I search for
    * it, if it's a file, I add it to the list, if it's a folder, I add all the files in it to the
    * list, if it's an integer, I send the list of files, if it's an arraylist, I delete the file from
    * the list
    */
    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        System.out.println("estoy a la escucha");
        try(ServerSocket servidor = new ServerSocket(9998)) {
            while(true){
                Socket misocket = servidor.accept();
                InputStream Dato = misocket.getInputStream();
                ObjectInputStream ObjectRecive= new ObjectInputStream(Dato);
                Object object= ObjectRecive.readObject();
                DataOutputStream OutPut= new DataOutputStream(misocket.getOutputStream());
                ObjectOutputStream ObjectOutPut= new ObjectOutputStream(OutPut);
                if (object instanceof String) {
                    String mensaje_texto = (String) object;
                    Buscar(mensaje_texto);
                    if (!Resultados.isEmpty()){
                        ObjectOutPut.writeObject(Resultados);
                    } else {
                        ObjectOutPut.writeObject("No hay resultados");
                    }
                    misocket.close();
                    
                } else if (object instanceof File) {
                    String archivo = ((File) object).getAbsolutePath();
                    Archivos.InsertLastUnique(archivo);
                    if(!DatoAñanido(FilenameUtils.getBaseName(archivo))) {
                        Archivos_selecionados= Archivos_selecionados+FilenameUtils.getBaseName(archivo)+",";
                    }
                    ObjectOutPut.writeObject("Archivo procesado");
                    misocket.close();

                } else if (object instanceof JFileChooser) {
                    File Carpeta= ((JFileChooser) object).getSelectedFile();
                    AgregarDocumentos(Carpeta);
                    ObjectOutPut.writeObject("Se leyo la carperta");
                    misocket.close();
                    
                } else if (object instanceof Integer) {
                    int Opciones= (int) object;
                    switch(Opciones) {
                        case 0:
                            LeerDocumentos();
                            ObjectOutPut.writeObject(Archivos_selecionados);
                    }
                    ObjectOutPut.writeObject("");
                    misocket.close();
                } else if(object instanceof ArrayList) {
                    String Valor_Eliminar= ((ArrayList<String>)object).get(0);
                    Eliminar(Valor_Eliminar);
                    ObjectOutPut.writeObject(Archivos_selecionados);
                    misocket.close();

                }
                
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TikaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /** 
     * @param Carpeta
     * Adding files to a list.
     */
    public void AgregarDocumentos(File Carpeta) {
        String[] archivos = Carpeta.list();

        for (int i = 0; i < archivos.length; i++) {
            String archivo_acutal= archivos[i];
            String tipo= FilenameUtils.getExtension(archivo_acutal);
            if (tipo.equals("docx") || tipo.equals("pdf") || tipo.equals("txt")) {
                String FullPath= Carpeta+"\\"+archivo_acutal;
                Archivos.InsertLastUnique(FullPath);
                if(!DatoAñanido(FilenameUtils.getBaseName(FullPath))) {
                    Archivos_selecionados= Archivos_selecionados+FilenameUtils.getBaseName(FullPath)+",";
                }
                
            }
        }
    }

    /** 
     * @throws SAXException
     * @throws TikaException
     * The above code is reading the files in the folder and then it is creating a binary tree and an
     * AVL tree for each file.
     */
    public void LeerDocumentos() throws SAXException, TikaException {
        if (!Archivos.isEmpty()) {
            String Documento= (String) Archivos.GetHead();
            while(Documento!=null) {
                String tipo= FilenameUtils.getExtension(Documento);
                Boolean SeLeyo= !Contain(Documento);
                if (tipo.equals("docx") && SeLeyo){
                    Docx.LeerDocx(Documento);
    
                    AvlTree<String> AvlTree= Docx.GetAvl();
                    ArbolesAvl.insertLast(AvlTree);
    
                    BinaryTree<String> BinaryTree= Docx.GetBinary();
                    ArbolesBinary.insertLast(BinaryTree);


                }
                
                if (tipo.equals("pdf") && SeLeyo){
                    Pdf.LeerPDF(Documento);
    
                    AvlTree<String> Tree= Pdf.GetAvl();
                    ArbolesAvl.insertLast(Tree);
    
                    BinaryTree<String> BinaryTree= Pdf.GetBinary();
                    ArbolesBinary.insertLast(BinaryTree);

                }

                if (tipo.equals("txt") && SeLeyo){
                    Txt.LeerTXT(Documento);
    
                    AvlTree<String> Tree= Txt.GetAvl();
                    ArbolesAvl.insertLast(Tree);
    
                    BinaryTree<String> BinaryTree= Txt.GetBinary();
                    ArbolesBinary.insertLast(BinaryTree);


                }
                Documento= (String) Archivos.GetNext(Documento);
            }
            System.out.println("Se termino de leer\nPresione Buscar");
        }else{
            System.out.println("No hay documentos para leer");

        }
    }

    
    /** 
     * @param Palabra
     * @throws SAXException
     * @throws TikaException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public void Buscar(String Palabra) throws SAXException, TikaException, IOException {
        if (ArbolesAvl.isEmpty()) {
            LeerDocumentos();
        } else {
            Palabra= Palabra.replaceAll(" ", "").toLowerCase();
            Resultados= new ArrayList<String []>();
            if(Palabra.length()!=0) {
                AvlTree = (AvlTree<String>) ArbolesAvl.GetHead();
                BinaryTree= (BinaryTree<String>) ArbolesBinary.GetHead();
                while (AvlTree!=null){
                    if(AvlTree.contains(Palabra)){
                        BinaryTree.contains(Palabra);

                        String Ruta= AvlTree.GetDocName();
                        Path archivo= Paths.get(Ruta);
                        BasicFileAttributes atributos= Files.readAttributes(archivo, BasicFileAttributes.class);
                        String fecha= atributos.creationTime().toString().split("T")[0];
                        String[] Datos= {Ruta, fecha, AvlTree.GetComparaciones()+"", " En el documento " + FilenameUtils.getBaseName(AvlTree.GetDocName()) + " se encontro la palabra " +
                        Palabra + " " + AvlTree.GetResultados() + " veces, " + " con: " + AvlTree.GetComparaciones() + 
                        " comparaciones en el AvlTree y con: " + BinaryTree.GetComparaciones() + " comparaciones en el BinaryTree"};
                        Resultados.add(Datos);
                        //PDFTextStripper pdftext = new PDFTextStripper();
                        String Documento= (String) Archivos.GetHead();
                        String tipo= FilenameUtils.getExtension(Documento);
                        if (tipo.equals("txt")){
                            try {
                                String url = Documento;
                                ProcessBuilder opener = new ProcessBuilder();
                                opener.command("cmd.exe","/c",url);
                                opener.start();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }
                        if (tipo.equals("pdf")){
                            try {
                                String url = Documento;
                                ProcessBuilder opener = new ProcessBuilder();
                                opener.command("cmd.exe","/c",url);
                                opener.start();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if (tipo.equals("docx")){
                            try {
                                String url = Documento;
                                ProcessBuilder opener = new ProcessBuilder();
                                opener.command("cmd.exe","/c",url);
                                opener.start();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }
                    AvlTree= (AvlTree<String>) ArbolesAvl.GetNext(AvlTree);
                    BinaryTree= (BinaryTree<String>) ArbolesBinary.GetNext(BinaryTree);
                }   
            }
        }
    }

    /** 
     * @param DocName
     * @return boolean
     * Checking if the list of AVL trees is empty, if it is not empty it will get the head of the list and
     * check if the document name is the same as the one passed as a parameter. If it is not the same it
     * will get the next AVL tree in the list and check again. If the list is empty it will return false.
     */
    @SuppressWarnings("unchecked")
    public boolean Contain(String DocName) {
        if (!ArbolesAvl.isEmpty()){
            AvlTree= (AvlTree<String>) ArbolesAvl.GetHead();
            while(AvlTree!=null){
                if (AvlTree.GetDocName() == DocName) {
                    return true;
                } else {
                    AvlTree= (AvlTree<String>) ArbolesAvl.GetNext(AvlTree);
                }
            }
            return false;
        } else {
            return false;
        }
    }

    
    /** 
     * @param Datos
     * @return boolean
     * Checking if the string is already in the list
     */
    public static boolean DatoAñanido(String Datos) {
        if(Archivos_selecionados.length()>1){
            String[] Lista= Archivos_selecionados.split(",");
            for (int index = 0; index < Lista.length; index++) {
                if(Lista[index].equals(Datos)){
                     return true;
                 }
             }
             return false;
        }
        return false;
    }

    
    /** 
     * @param Valor_Eliminar
     * @throws SAXException
     * @throws TikaException
     * Deleting a document from the list of documents.
     */
    @SuppressWarnings("unchecked")
    public void Eliminar(String Valor_Eliminar) throws SAXException, TikaException {
        if (Archivos.isEmpty() || ArbolesAvl.isEmpty()) {
            LeerDocumentos();
        } else{
            System.out.println("procensando");
            String Documento= (String) Archivos.GetHead();
        
            while(Documento!=null) {
                System.out.println(FilenameUtils.getBaseName(Documento));
                System.out.println(Valor_Eliminar.equals(FilenameUtils.getBaseName(Documento)));
                if(Valor_Eliminar.equals(FilenameUtils.getBaseName(Documento))) {
                    Archivos.Delete(Documento);
                    System.out.println("Se elimino el documento");
                    break;
                } else {
                    Documento= (String) Archivos.GetNext(Documento);
                }
            }
            System.out.println("arboles");
            AvlTree = (AvlTree<String>) ArbolesAvl.GetHead();
            BinaryTree= (BinaryTree<String>) ArbolesBinary.GetHead();
            while(AvlTree!=null) {
                if(Valor_Eliminar.equals(FilenameUtils.getBaseName(AvlTree.GetDocName()))){
                    ArbolesAvl.Delete(AvlTree);
                    ArbolesBinary.Delete(BinaryTree);
                    System.out.println("Se eliminaron los arboles");
                    break;
                } else {
                    AvlTree= (AvlTree<String>) ArbolesAvl.GetNext(AvlTree);
                    BinaryTree= (BinaryTree<String>) ArbolesBinary.GetNext(BinaryTree);
                }
            }

            String[] lista= Archivos_selecionados.split(",");
            String temp= "";
            for (int i = 0; i < lista.length; i++) {
                String archivo= FilenameUtils.getBaseName(lista[i]);
                if(Valor_Eliminar.equals(archivo)){

                } else{
                    temp= temp+archivo+",";
                }
            }
            Archivos_selecionados= temp;
        }
        
    }

}
