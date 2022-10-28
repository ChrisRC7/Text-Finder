package Interfaz;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Arboles.*;
import ListasEnlazadas.LinkedList;

import java.awt.event.*;


public class Interfaz extends JFrame implements ActionListener{
    JButton Buscarbtn;
    static LinkedList ArbolesAvl;
    static LinkedList ArbolesBinary;
    AvlTree<String> AvlTree;
    BinaryTree<String> BinaryTree;

    private JTextField PalabraPorBuscar;
    
        public Interfaz() {

            setLayout(null);

            Buscarbtn= new JButton("<html>Buscar<html>");
            Buscarbtn.setBounds(100, 50, 100, 100);
            Buscarbtn.addActionListener(this);
            add(Buscarbtn);

            PalabraPorBuscar= new JTextField();
            PalabraPorBuscar.setBounds(200, 200, 100,30);
            add(PalabraPorBuscar);

        }

        @SuppressWarnings("unchecked")
        @Override
        public void actionPerformed(ActionEvent btn) {
            if (btn.getSource() == Buscarbtn) {
                String Palabra = PalabraPorBuscar.getText();
                Palabra= Palabra.replaceAll(" ", "").toLowerCase();
                if(Palabra.length()!=0) {
                    AvlTree = (AvlTree<String>) ArbolesAvl.GetHead();
                    BinaryTree= (BinaryTree<String>) ArbolesBinary.GetHead();
                    while (AvlTree!=null){
                        if(AvlTree.contains(Palabra)){
                            BinaryTree.contains(Palabra);
                            System.out.println(" En el documeto " + AvlTree.GetDocName() + " se encotro la palabra " + 
                            Palabra + " " + AvlTree.GetResultados() + " veces, " + " con: " + AvlTree.GetComparaciones() + 
                            " comparaciones en el AvlTree y con: " + BinaryTree.GetComparaciones() + " comparaciones en el BinaryTree");
                        }
                        AvlTree= (AvlTree<String>) ArbolesAvl.GetNext(AvlTree);
                        BinaryTree= (BinaryTree<String>) ArbolesBinary.GetNext(BinaryTree);
                    }    
                }
            }
         }

        public static void VentanaInicio() {
            Interfaz Ventana = new Interfaz();
            Ventana.setTitle("Text Finder CE");
            Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Ventana.setSize(650,500);
            Ventana.setVisible(true);

        }

        public static void SetListaAvl(LinkedList ListaAvl){
            ArbolesAvl= ListaAvl;
        }

        public static void SetListaBinary(LinkedList ListaBinary){
            ArbolesBinary= ListaBinary;
        }

}
