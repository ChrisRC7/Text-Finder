package Interfaz;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Arboles.*;
import ListasEnlazadas.LinkedList;

import java.awt.event.*;
import java.io.File;


public class Interfaz extends JFrame implements ActionListener{
    JButton BuscarBtn, AgregarBtn;
    static LinkedList ArbolesAvl;
    static LinkedList ArbolesBinary;
    AvlTree<String> AvlTree;
    BinaryTree<String> BinaryTree;

    private JTextField PalabraPorBuscar;
    
        public Interfaz() {

            setLayout(null);

            BuscarBtn= new JButton("<html>Buscar<html>");
            BuscarBtn.setBounds(100, 50, 100, 100);
            BuscarBtn.addActionListener(this);
            add(BuscarBtn);

            AgregarBtn= new JButton("<html>Agregar<html>");
            AgregarBtn.setBounds(300, 50, 100, 100);
            AgregarBtn.addActionListener(this);
            add(AgregarBtn);

            PalabraPorBuscar= new JTextField();
            PalabraPorBuscar.setBounds(200, 200, 100,30);
            add(PalabraPorBuscar);

        }

        @SuppressWarnings("unchecked")
        @Override
        public void actionPerformed(ActionEvent btn) {
            if (btn.getSource() == BuscarBtn) {
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

            if (btn.getSource() == AgregarBtn) {
                JFileChooser BuscarArchivos = new JFileChooser();
                BuscarArchivos.setCurrentDirectory(new File("."));
                BuscarArchivos.setDialogTitle("Selecionar archivos docx, pdf o txt");
                BuscarArchivos.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("Filtro", "docx", "pdf", "txt");
                BuscarArchivos.addChoosableFileFilter(filtro);
                BuscarArchivos.showOpenDialog(this);
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
