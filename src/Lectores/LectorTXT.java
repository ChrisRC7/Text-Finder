// Importing the necessary libraries to read the file.
package Lectores;

import java.io.BufferedReader;
import java.io.FileReader;
import arboles.AvlTree;
import arboles.BinaryTree;

/**
 * It creates a class called LectorTXT that has two variables, AvlTree and BinaryTree.
 */
public class LectorTXT {
 // Declaring the variables AvlTree and BinaryTree.
    AvlTree<String> AvlTree;
    BinaryTree<String> BinaryTree;

    
    /**
     * It reads a text file and inserts each word into an AVL tree and a Binary tree
     * 
     * @param Documento The name of the file to be read.
     */
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

   /**
    * This function returns the AvlTree object
    * 
    * @return The AvlTree object.
    */
    public AvlTree<String> GetAvl() {
        return this.AvlTree;
    }

    
   /**
    * This function returns the BinaryTree object that is a member of the class.
    * 
    * @return The BinaryTree object.
    */
    public BinaryTree<String> GetBinary() {
        return this.BinaryTree;
    }
}
