package Interfaz;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Arboles.AvlTree;
import ListasEnlazadas.LinkedList;

import java.awt.event.*;


public class Interfaz extends JFrame implements ActionListener{
    JButton Buscarbtn;
    static LinkedList Arboles;
    AvlTree<String> Tree;

    private JTextField PalabraPorBuscar;
    
        public Interfaz() {

            setLayout(null);

            Buscarbtn= new JButton("<htmlBuscar<html>");
            Buscarbtn.setBounds(100, 50, 100, 100);
            Buscarbtn.addActionListener(this);
            add(Buscarbtn);

            PalabraPorBuscar= new JTextField();
            PalabraPorBuscar.setBounds(200, 200, 100,30);
            add(PalabraPorBuscar);

        }

        @Override
        public void actionPerformed(ActionEvent btn) {
            if (btn.getSource() == Buscarbtn) {
                String Palabra = PalabraPorBuscar.getText();
                Palabra= Palabra.replaceAll(" ", "");
                if(Palabra.length()!=0) {
                    Tree = (AvlTree<String>) Arboles.GetHead();
                    while (Tree!=null){
                        Tree.contains(Palabra);
                        if(Tree.GetResultados()>0){
                            System.out.println(" En el documeto " + Tree.GetDocName() + " se encotro la palabra " + 
                            Palabra + " " + Tree.GetResultados() + " veces, " + " con " + Tree.GetComparaciones() + " comparaciones");
                        }
                        Tree= (AvlTree<String>) Arboles.GetNext(Tree);
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

        public static void SetLista(LinkedList Lista){
            Arboles= Lista;
        }

}
