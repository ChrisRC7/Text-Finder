package Lectores;

import java.io.BufferedReader;
import java.io.FileReader;
import Arboles.AvlTree;
import Arboles.BinaryTree;

public class LectorTXT {
    AvlTree<String> AvlTree;
    BinaryTree<String> BinaryTree;

    public void LeerTXT(String Documento){
        this.AvlTree = new AvlTree<String>();
        this.AvlTree.SetDocName(Documento);

        this.BinaryTree= new BinaryTree<String>();
        this.BinaryTree.SetDocName(Documento);

        FileReader archivo;
        BufferedReader lector;
        try{
            archivo= new FileReader(Documento);

            if(archivo.ready()){
                lector = new BufferedReader(archivo);
                String cadena;
                while((cadena=lector.readLine())!=null){//Hay algo
                    String [] Separador= cadena.replaceAll("\\s*$", "").split(" ");
                    for (String Palabras : Separador) {
                        if(Palabras!=""){
                            AvlTree.insert(Palabras.toLowerCase());
                            BinaryTree.insert(Palabras.toLowerCase());
                        }
                    }
                }
            }else{
                System.out.println("El archivo no está listo para ser leído...");
            }

        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public AvlTree<String> GetAvl() {
        return this.AvlTree;
    }

    public BinaryTree<String> GetBinary() {
        return this.BinaryTree;
    }
}
