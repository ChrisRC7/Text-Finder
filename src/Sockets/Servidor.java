package Sockets;

import javax.swing.*;

import org.apache.commons.io.FilenameUtils;

import Lectores.*;
import ListasEnlazadas.*;
import Arboles.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor implements Runnable{
    LinkedList Archivos= new LinkedList();
    LinkedList ArbolesAvl= new LinkedList();
    LinkedList ArbolesBinary= new LinkedList();
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
                if (object instanceof String) {
                    String mensaje_texto = (String) object;
                    Buscar(mensaje_texto);
                    misocket.close();
                    
                } else if (object instanceof File) {
                    String archivo = ((File) object).getAbsolutePath();
                    Archivos.InsertLastUnique(archivo);
                    misocket.close();

                } else if (object instanceof JFileChooser) {
                    File Carpeta= ((JFileChooser) object).getSelectedFile();
                    AgregarDocumentos(Carpeta);
                    misocket.close();
                    
                } else if (object instanceof Integer) {
                    int Opciones= (int) object;
                    switch(Opciones) {
                        case 0:
                            LeerDocumentos();
                    }
                }
                
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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

    public void LeerDocumentos() {
        if (!Archivos.isEmpty()) {
            String Documento= (String) Archivos.GetHead();
            while(Documento!=null) {
                String tipo= FilenameUtils.getExtension(Documento);
                if (tipo.equals("docx") && !Contain(Documento)){
                    Docx.LeerDocx(Documento);
    
                    AvlTree<String> AvlTree= Docx.GetAvl();
                    ArbolesAvl.insertLast(AvlTree);
    
                    BinaryTree<String> BinaryTree= Docx.GetBinary();
                    ArbolesBinary.insertLast(BinaryTree);
    
                }
                
                if (tipo.equals("pdf") && !Contain(Documento)){
                    Pdf.LeerPDF(Documento);
    
                    AvlTree<String> Tree= Pdf.GetAvl();
                    ArbolesAvl.insertLast(Tree);
    
                    BinaryTree<String> BinaryTree= Pdf.GetBinary();
                    ArbolesBinary.insertLast(BinaryTree);
    
                }

                if (tipo.equals("txt") && !Contain(Documento)){
                    Txt.LeerTXT(Documento);
    
                    AvlTree<String> Tree= Txt.GetAvl();
                    ArbolesAvl.insertLast(Tree);
    
                    BinaryTree<String> BinaryTree= Txt.GetBinary();
                    ArbolesBinary.insertLast(BinaryTree);
                }
                Documento= (String) Archivos.GetNext(Documento);
            }
            System.out.println("Se termino de leer");
        }else{
            System.out.println("No hay documentos para leer");
        }
    }

    @SuppressWarnings("unchecked")
    public void Buscar(String Palabra) {
        if (ArbolesAvl.isEmpty()) {
            LeerDocumentos();
        } else {
        Palabra= Palabra.replaceAll(" ", "").toLowerCase();
            if(Palabra.length()!=0) {
                AvlTree = (AvlTree<String>) ArbolesAvl.GetHead();
                BinaryTree= (BinaryTree<String>) ArbolesBinary.GetHead();
                while (AvlTree!=null){
                    if(AvlTree.contains(Palabra)){
                        BinaryTree.contains(Palabra);
                        System.out.println(" En el documeto " + FilenameUtils.getBaseName(AvlTree.GetDocName())  + " se encotro la palabra " + 
                        Palabra + " " + AvlTree.GetResultados() + " veces, " + " con: " + AvlTree.GetComparaciones() + 
                        " comparaciones en el AvlTree y con: " + BinaryTree.GetComparaciones() + " comparaciones en el BinaryTree");
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
