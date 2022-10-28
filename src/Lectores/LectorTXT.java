package Lectores;

import java.io.BufferedReader;
import java.io.FileReader;

import Arboles.AvlTree;

public class LectorTXT {
    public static AvlTree<String> LeerTXT(String Documento){
        FileReader archivo;
        BufferedReader lector;
        AvlTree<String> Tree;
        Tree = new AvlTree<String>();
        Tree.SetDocName(Documento);
        try{
            archivo= new FileReader("src/Lectores/"+Documento);

            if(archivo.ready()){
                lector = new BufferedReader(archivo);
                String cadena;
                while((cadena=lector.readLine())!=null){//Hay algo
                    String [] Separador= cadena.replaceAll("\\s*$", "").split(" ");
                    for (String Palabras : Separador) {
                        if(Palabras!=""){
                            Tree.insert(Palabras);
                        }
                    }
                }
            }else{
                System.out.println("El archivo no está listo para ser leído...");
            }

        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return Tree;

    }
}
