package Interfaz;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import Arboles.*;
import ListasEnlazadas.LinkedList;
import Sockets.Cliente;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Interfaz extends JFrame implements ActionListener, ListSelectionListener{
    JButton BuscarBtn, AgregarArchivoBtn, AgregarDirectorioBtn, ActualizarBibliotecaBtn;
    static LinkedList ArbolesAvl;
    static LinkedList ArbolesBinary;
    static ArrayList<String []> Resultados;
    AvlTree<String> AvlTree;
    BinaryTree<String> BinaryTree;
    static String [] Nombre_de_los_archivos;
    static JLabel Mostrar_Resultados;
    static JList<String> Nombre_de_archivo, b1, b2;

    private JTextField PalabraPorBuscar;
    
        public Interfaz() {

            setLayout(null);

            BuscarBtn= new JButton("<html>Buscar<html>");
            BuscarBtn.setBounds(100, 50, 100, 100);
            BuscarBtn.addActionListener(this);
            add(BuscarBtn);

            AgregarArchivoBtn= new JButton("<html>Agregar Arhicvo<html>");
            AgregarArchivoBtn.setBounds(300, 50, 100, 100);
            AgregarArchivoBtn.addActionListener(this);
            add(AgregarArchivoBtn);

            ActualizarBibliotecaBtn= new JButton("<html>Actualizar Bibliotecas<html>");
            ActualizarBibliotecaBtn.setBounds(100, 300, 100, 100);
            ActualizarBibliotecaBtn.addActionListener(this);
            add(ActualizarBibliotecaBtn);

            AgregarDirectorioBtn= new JButton("<html>Agregar Directorio<html>");
            AgregarDirectorioBtn.setBounds(300, 300, 100, 100);
            AgregarDirectorioBtn.addActionListener(this);
            add(AgregarDirectorioBtn);

            PalabraPorBuscar= new JTextField();
            PalabraPorBuscar.setBounds(200, 200, 100,30);
            add(PalabraPorBuscar);

        }

        
        @Override
        public void actionPerformed(ActionEvent btn) {
            if (btn.getSource() == BuscarBtn) {
                try {
                    Cliente.EnviarPalabra(PalabraPorBuscar.getText());
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (btn.getSource() == AgregarArchivoBtn) {
                Scanner entrada = null;
                String ruta= "";
                JFileChooser BuscarArchivos = new JFileChooser();
                BuscarArchivos.setCurrentDirectory(new File("."));
                BuscarArchivos.setDialogTitle("Selecionar archivos docx, pdf o txt");
                BuscarArchivos.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("Filtro", "docx", "pdf", "txt");
                BuscarArchivos.addChoosableFileFilter(filtro);
                BuscarArchivos.showOpenDialog(this);
                try {
                    ruta = BuscarArchivos.getSelectedFile().getAbsolutePath();
                    File archivo= new File(ruta);
                    entrada = new Scanner(archivo);
                    Cliente.EnviarPalabra(archivo);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    System.out.println("No se ha seleccionado ningún fichero");
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally{
                    if (entrada!=null){
                        entrada.close();
                    }
                }
            }

            if (btn.getSource() == ActualizarBibliotecaBtn) {
                try {
                    Cliente.EnviarPalabra(0);
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (btn.getSource() == AgregarDirectorioBtn) {
                JFileChooser BuscarCarpeta = new JFileChooser();
                BuscarCarpeta.setCurrentDirectory(new File("."));
                BuscarCarpeta.setDialogTitle("Selecionar carpeta");
                BuscarCarpeta.setAcceptAllFileFilterUsed(false);
                BuscarCarpeta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (BuscarCarpeta.showOpenDialog(this)== JFileChooser.APPROVE_OPTION){
                    try {
                        Cliente.EnviarPalabra(BuscarCarpeta);
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
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

        public static void Lista() {
            JFrame VentanaLista= new JFrame("Archivos disponibles");
            VentanaLista.setBounds(0, 0, 800, 600);
            VentanaLista.setVisible(true);

            //create a new label
            JLabel Texto_Arcihvos= new JLabel("<html>Nombre<html>");
            Mostrar_Resultados= new JLabel();
         
            //create lists
            Nombre_de_archivo= new JList<String> (Nombre_de_los_archivos);
           
         
            //set a selected index
            Nombre_de_archivo.setSelectedIndex(0);
            Mostrar_Resultados.setText(Nombre_de_archivo.getSelectedValue()+ Resultados.get(0)[1]);
            
            Interfaz Ventana= new Interfaz();

            //add item listener
            Nombre_de_archivo.addListSelectionListener(Ventana);
         
            //add list to panel
            JPanel Panel =new JPanel();
            Panel.add(Texto_Arcihvos);
            Panel.add(Nombre_de_archivo);
            Panel.add(Mostrar_Resultados);
  
            VentanaLista.add(Panel);
        
            
        
        }

        public void valueChanged(ListSelectionEvent e) {
            String Archivo_selecionado= Nombre_de_archivo.getSelectedValue();
            for (int i = 0; i < Resultados.size(); i++) {
                if(Resultados.get(i)[0] == Archivo_selecionado) {
                    Mostrar_Resultados.setText(Archivo_selecionado + Resultados.get(i)[1] );
                    break;
                }
            }

        }

        public void Mostrar() {
            
        }

        public static void SetResultados(ArrayList<String []> Resultados_de_Busqueda) {
            Resultados= Resultados_de_Busqueda;
            Nombre_de_los_archivos= new String[Resultados.size()];
            for (int i = 0; i < Resultados.size(); i++) {
                Nombre_de_los_archivos[i]= Resultados.get(i)[0];
            }
            Lista();
        }
}


