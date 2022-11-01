package Sockets;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import ListasEnlazadas.LinkedList;
import Arboles.AvlTree;
import Arboles.BinaryTree;
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
import java.util.ArrayList;

import javax.swing.JFileChooser;



public class Servidor implements Runnable{
    LinkedList Archivos= new LinkedList();
    LinkedList ArbolesAvl= new LinkedList();
    LinkedList ArbolesBinary= new LinkedList();
    ArrayList<String []> Resultados= new ArrayList<String []>();
    AvlTree<String> AvlTree;
    BinaryTree<String> BinaryTree;
    LectorDOCX Docx= new LectorDOCX();
    LectorPDF Pdf= new LectorPDF();
    LectorTXT Txt= new LectorTXT();

    
    public void IniciarServidor(){
        Thread mihilo = new Thread(this);
        mihilo.start();
    }

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
                    }
                    ObjectOutPut.writeObject("");
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

    public void AgregarDocumentos(File Carpeta) {
        String[] archivos = Carpeta.list();

        for (int i = 0; i < archivos.length; i++) {
            String archivo_acutal= archivos[i];
            String tipo= FilenameUtils.getExtension(archivo_acutal);
            if (tipo.equals("docx") || tipo.equals("pdf") || tipo.equals("txt")) {
                String FullPath= Carpeta+"\\"+archivo_acutal;
                Archivos.InsertLastUnique(FullPath);
            }
        }
    }

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

    @SuppressWarnings("unchecked")
    public void Buscar(String Palabra) throws SAXException, TikaException {
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
                        String[] Datos= {" En el documento " + FilenameUtils.getBaseName(AvlTree.GetDocName()), " se encontro la palabra " +
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

}
