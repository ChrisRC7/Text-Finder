package Main;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

import Arboles.AvlTree;
import Arboles.BinaryTree;
import Interfaz.Interfaz;

public class Main {
    public static void main(String[] args) throws Exception {

        String Ruta= System.getProperty("user.dir");
        File Carpeta = new File("src/Lectores");

        String[] archivos = Carpeta.list();

        for (int i = 0; i < archivos.length; i++) {
            String archivo_acutal= archivos[i];
            String tipo= FilenameUtils.getExtension(archivo_acutal);
            if (!tipo.equals("pdf") && !tipo.equals("java")){
                System.out.println(archivo_acutal);
            }
        }

        Interfaz.VentanaInicio();




        /* 
        AvlTree Tree;
        Tree = new AvlTree();
        Tree.insert("50000");
        Tree.insert("20");
        Tree.insert("600000");
        Tree.insert("1");
        Tree.insert("300");
        Tree.insert("4000");
        //Tree.contains("600001");

        BinaryTree<String> TreeB;
        TreeB= new BinaryTree<>();
        TreeB.insert("a");
        TreeB.insert("ab");
        TreeB.insert("abc");
        TreeB.insert("abcd");
        TreeB.insert("abcde");
        TreeB.insert("abcdef");
        TreeB.insert("abcdefg");

        //TreeB.remove("8");
        //TreeB.remove(10);
        //TreeB.remove("1");
        System.out.println("Valores dentro del arbol");
        
        //System.out.println("Valores eliminados: ");
        TreeB.contains("abcdefg");
        TreeB.contains("abcdef");
        TreeB.contains("abcde");

        TreeB.contains("abcd");
        TreeB.contains("abc");
        TreeB.contains("ab");
        TreeB.contains("a");

        System.out.println("Es mayor es: ");
        TreeB.findMax();
        System.out.println("El menor es: ");
        TreeB.findMin();
        */

    }
}
