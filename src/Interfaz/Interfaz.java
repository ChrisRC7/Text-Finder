package Interfaz;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Arboles.*;
import ListasEnlazadas.LinkedList;
import Sockets.Cliente;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Interfaz extends JFrame implements ActionListener{
    JButton BuscarBtn, AgregarArchivoBtn, AgregarDirectorioBtn, ActualizarBibliotecaBtn;
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
                Cliente.EnviarPalabra(PalabraPorBuscar.getText());
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
                } finally{
                    if (entrada!=null){
                        entrada.close();
                    }
                }
            }

            if (btn.getSource() == ActualizarBibliotecaBtn) {
                Cliente.EnviarPalabra(0);
            }

            if (btn.getSource() == AgregarDirectorioBtn) {
                JFileChooser BuscarCarpeta = new JFileChooser();
                BuscarCarpeta.setCurrentDirectory(new File("."));
                BuscarCarpeta.setDialogTitle("Selecionar carpeta");
                BuscarCarpeta.setAcceptAllFileFilterUsed(false);
                BuscarCarpeta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (BuscarCarpeta.showOpenDialog(this)== JFileChooser.APPROVE_OPTION){
                    Cliente.EnviarPalabra(BuscarCarpeta);
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
