// Importing the necessary libraries for the program to run.
package Interfaz;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;
import arboles.AvlTree;
import arboles.BinaryTree;
import ListasEnlazadas.LinkedList;
import Sockets.Cliente;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


// Creating a class called Interfaz that extends JFrame and implements ActionListener and
// ListSelectionListener.
/**
 * It creates a GUI for the user to interact with
 */
public class Interfaz extends JFrame implements ActionListener, ListSelectionListener{
    // Declaring the variables that will be used in the program.
    JButton BuscarBtn, AgregarArchivoBtn, AgregarDirectorioBtn, ActualizarBibliotecaBtn, EliminarBtn, Startbtn;
    static LinkedList ArbolesAvl;
    static LinkedList ArbolesBinary;
    static ArrayList<String []> Resultados;
    AvlTree<String> AvlTree;
    BinaryTree<String> BinaryTree;
    private static JComboBox<String> SelecciónArchivo = new JComboBox<String>();
    static String [] Nombre_de_los_archivos;
    static String [] Fechas;
    static String [] CantidadResultados;
    static String Results;
    static JLabel Mostrar_Resultados;
    static JList<String> Opciones_de_ordenamiento, Archivos_Disponibles;;

    private JTextField PalabraPorBuscar;
    
        // Creating a GUI for the user to interact with.
        public Interfaz() {

            setLayout(null);

            JLabel agregar = new JLabel("-------------------------- Agregar --------------------------");
            agregar.setBounds(170, 0, 300, 50);
            add(agregar);

            JLabel buscar = new JLabel("-------------------------- Buscar --------------------------");
            buscar.setBounds(170, 110, 300, 50);
            add(buscar);


            BuscarBtn= new JButton("<html>|🔍<html>");
            BuscarBtn.setBounds(350, 150, 100, 30);
            BuscarBtn.addActionListener(this);
            add(BuscarBtn);

            AgregarArchivoBtn= new JButton("<html>Archivo<html>");
            AgregarArchivoBtn.setBounds(150, 50, 150, 30);
            AgregarArchivoBtn.addActionListener(this);
            add(AgregarArchivoBtn);

            ActualizarBibliotecaBtn= new JButton("<html>|🔁<html>");
            ActualizarBibliotecaBtn.setBounds(149, 150, 100, 30);
            ActualizarBibliotecaBtn.addActionListener(this);
            add(ActualizarBibliotecaBtn);

            EliminarBtn= new JButton("<html>❌<html>");
            EliminarBtn.setBounds(275, 80, 50, 30);
            EliminarBtn.addActionListener(this);
            add(EliminarBtn);

            AgregarDirectorioBtn= new JButton("<html>Directorio<html>");
            AgregarDirectorioBtn.setBounds(300, 50, 150, 30);
            AgregarDirectorioBtn.addActionListener(this);
            add(AgregarDirectorioBtn);

            PalabraPorBuscar= new JTextField();
            PalabraPorBuscar.setBounds(250, 150, 100,30);
            add(PalabraPorBuscar);
        }

        
        
        /**
         * A method that is called when a button is pressed. 
         * @param btn
         */
        @Override
        public void actionPerformed(ActionEvent btn) {

            if(btn.getSource() == Startbtn){
                System.out.println("kkkkk");
            }
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

            if (btn.getSource() == EliminarBtn){
                ArrayList<String> Valor_Eliminar= new ArrayList<String>();
                Valor_Eliminar.add((String)SelecciónArchivo.getSelectedItem());
                try {
                    Cliente.EnviarPalabra(Valor_Eliminar);
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
         }

        /**
         * It creates a window with a button that opens a file explorer.
         * </code>
         */
        public static void VentanaInicio() {
            Interfaz Ventana = new Interfaz();
            Ventana.setTitle("Text Finder CE");
            Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Ventana.setSize(650,300);
            Ventana.getContentPane().setLayout(null);
            Ventana.setLocationRelativeTo(null);
            SelecciónArchivo.setBounds(90, 215, 300, 30);
            Ventana.add(SelecciónArchivo);
            Ventana.setVisible(true);

        }

        /**
         * It creates a JFrame, a JLabel, a JList, and a JButton. The JList has a ListSelectionListener
         * and the JButton has an ActionListener
         */
        public static void Lista(){
            JFrame VentanaLista= new JFrame("Archivos disponibles");
            VentanaLista.setBounds(0, 0, 800, 600);
            VentanaLista.setVisible(true);

            //create a new label
            JLabel Texto_Arcihvos= new JLabel("<html>Opciones de ordenamiento<html>");
            Mostrar_Resultados= new JLabel();
            
            String [] opciones= {"Nombre", "Fecha", "Cantidad de palabras"};
            //create lists
            Opciones_de_ordenamiento= new JList<String> (opciones);
            
           
         
            //set a selected index
            Opciones_de_ordenamiento.setSelectedIndex(0);
            OrdenarPorNombres();
            Mostrar_Resultados.setText(Results);
            
            Interfaz Ventana= new Interfaz();
            JButton Startbtn = new JButton("Prueba");

            //add item listener
            Opciones_de_ordenamiento.addListSelectionListener(Ventana);
            Startbtn.addActionListener(Ventana);

            //add list to panel
            JPanel Panel =new JPanel();
            Panel.add(Texto_Arcihvos);
            Panel.add(Opciones_de_ordenamiento);
            Panel.add(Mostrar_Resultados);
            Panel.add(Startbtn);

            VentanaLista.add(Panel);
        }

        /**
         * It takes the selected value from the JList, and then it calls the function that orders the
         * results by the selected value
         * 
         * @param e The ListSelectionEvent that characterizes the change.
         */
        public void valueChanged(ListSelectionEvent e) {
            String Opcion_selecionada= Opciones_de_ordenamiento.getSelectedValue();
            if(Opcion_selecionada.equals("Nombre")) {
                OrdenarPorNombres();
            } else if(Opcion_selecionada.equals("Fecha")) {
                OrdenarPorFechas();
            } else if (Opcion_selecionada.equals("Cantidad de palabras")) {
                OrdenarPorResultados();
            }
            Mostrar_Resultados.setText(Results);
            
        }
    
        /**
         * It takes an ArrayList of String arrays, and then it sorts the arrays in the ArrayList by the
         * first element of each array
         * 
         * @param Resultados_de_Busqueda ArrayList of String arrays.
         */
        public static void SetResultados(ArrayList<String []> Resultados_de_Busqueda) {
            Resultados= Resultados_de_Busqueda;
            Nombre_de_los_archivos= new String[Resultados.size()];
            Fechas= new String[Resultados.size()];
            CantidadResultados= new String[Resultados.size()];
            for (int i = 0; i < Resultados.size(); i++) {
                Nombre_de_los_archivos[i]= FilenameUtils.getBaseName(Resultados.get(i)[0]);
                Fechas[i]=(Resultados.get(i)[1]);
                CantidadResultados[i]=(Resultados.get(i)[2]);
            }
            BubbleSort();
            QuickSort(0 , Nombre_de_los_archivos.length-1);
            RadixSort();
            Lista();
        }

        /**
         * It compares the first element of the array with the rest of the elements, if the first
         * element is greater than the second element, it swaps them. Then it compares the second
         * element with the rest of the elements, if the second element is greater than the third
         * element, it swaps them. It continues this process until the array is sorted
         */
        public static void BubbleSort() {
            String temp;
            
            for (int i = 0; i< Fechas.length; i++) {
                for (int j = 0; j < Fechas.length; j++) {
                    if(Fechas[j].compareTo(Fechas[i])<0){
                        temp= Fechas[j];
                        Fechas[j]= Fechas[i];
                        Fechas[i]= temp;
                    }
                }
            }
        }

        /**
         * It takes a list of files and sorts them by date
         */
        public static void OrdenarPorFechas() {
            int Datos= 0;
            int actual= 0;
            String[] DatosAñadidos= new String[Nombre_de_los_archivos.length];
            Results= "<html>";
            while(Datos<Resultados.size()){
                if(Fechas[Datos].equals(Resultados.get(actual)[1])){
                    if(!DatoAñanido(DatosAñadidos, Resultados.get(actual)[3])){
                        DatosAñadidos[Datos]= Resultados.get(actual)[3];
                        Results= Results + Resultados.get(actual)[3]+"<br>";
                        Datos++;
                        actual= 0;
                    } else{
                        actual++;
                    }
                    
                } else{
                    actual++;
                }
            }
            Results= Results+"<html>";
        }

        /**
         * The function takes two parameters, the lower index and the higher index. It then sets the
         * pivot to the middle of the array. It then compares the pivot to the lower index and the
         * higher index. If the lower index is less than the pivot, it increments the lower index. If
         * the higher index is greater than the pivot, it decrements the higher index. If the lower
         * index is less than or equal to the higher index, it swaps the two values. If the lower index
         * is less than the higher index, it calls the function again with the lower index and the
         * higher index. If the higher index is less than the higher index, it calls the function again
         * with the higher index and the higher index
         * 
         * @param lowerIndex The lower index of the array
         * @param higherIndex The index of the last element in the array.
         */
        public static void QuickSort(int lowerIndex, int higherIndex) {
            int i= lowerIndex;
            int j= higherIndex;
            String pivot= Nombre_de_los_archivos[lowerIndex + (higherIndex - lowerIndex) / 2];
            while(i<=j) {
                while(Nombre_de_los_archivos[i].compareToIgnoreCase(pivot) < 0) {
                    i++;
                }

                while(Nombre_de_los_archivos[j].compareToIgnoreCase(pivot) > 0) {
                    j--;
                }

                if( i<=j ) {
                    String temp= Nombre_de_los_archivos[i];
                    Nombre_de_los_archivos[i]= Nombre_de_los_archivos[j];
                    Nombre_de_los_archivos[j] = temp;
                    i++;
                    j--;
                }
            }
            if (lowerIndex < j) {
                QuickSort(lowerIndex, j);
            }

            if (i < higherIndex) {
                QuickSort(i, higherIndex);
            }

        }

        /**
         * It takes a list of files and sorts them by name
         */
        public static void OrdenarPorNombres(){
            int Datos= 0;
            int actual= 0;
            String[] DatosAñadidos= new String[Nombre_de_los_archivos.length];
            Results= "<html>";
            while(Datos<Nombre_de_los_archivos.length){
                String Nombre= FilenameUtils.getBaseName(Resultados.get(actual)[0]);
                if(Nombre_de_los_archivos[Datos].equals(Nombre)){
                    if(!DatoAñanido(DatosAñadidos, Resultados.get(actual)[3])){
                        DatosAñadidos[Datos]= Resultados.get(actual)[3];
                        Results= Results + Resultados.get(actual)[3]+"<br>";
                        Datos++;
                        actual= 0;
                    } else {
                        actual++;
                    }
                    
                } else{
                    actual++;
                }
            }
            Results= Results+"<html>";
        }

        /**
         * RadixSort() is a function that sorts the array of strings by the last digit of each string
         */
        public static void RadixSort() {
            char lower= '0';
            char upper= '9';
            int maxIndex= 0;
            for (int i = 0; i < CantidadResultados.length; i++) {
                if(CantidadResultados[i].length()-1 > maxIndex) {
                    maxIndex= CantidadResultados[i].length()-1;
                }
            }

            for (int i = maxIndex; i >= 0; i--) {
                RadixSortAux(i, lower, upper);
            }
           
        }

        /**
         * The function takes in the index of the character to sort by, the lower bound of the
         * character set, and the upper bound of the character set. It then creates a count array and a
         * temporary array. It then fills the count array with zeros. It then increases the count for
         * the character at the index. It then sums up the count array. It then places the strings in
         * the temporary array. It then places the strings in the original array
         * 
         * @param index the index of the character to sort by
         * @param lower the lowest character in the string
         * @param upper the highest character in the string
         */
        public static void RadixSortAux(int index, char lower, char upper){
            int[] countArray = new int[(upper-lower)+2]; 
            String[] tempArray = new String[CantidadResultados.length]; 
            Arrays.fill(countArray,0); 
     
            //increase count for char at index 
            for(int i=0;i<CantidadResultados.length;i++){ 
            int charIndex = (CantidadResultados[i].length()-1 < index) ? 0 : ((CantidadResultados[i].charAt(index) - lower)+1); 
            countArray[charIndex]++; 
            } 
     
            //sum up countArray;countArray will hold last index for the char at each strings index 
            for(int i=1;i<countArray.length;i++){ 
            countArray[i] += countArray[i-1]; 
            } 
     
            for(int i=CantidadResultados.length-1;i>=0;i--){ 
            int charIndex = (CantidadResultados[i].length()-1 < index) ? 0 : (CantidadResultados[i].charAt(index) - lower)+1; 
            tempArray[countArray[charIndex]-1] = CantidadResultados[i]; 
            countArray[charIndex]--; 
            } 
     
            for(int i=0;i<tempArray.length;i++){ 
            CantidadResultados[i] = tempArray[i];    
            } 
        }

        /**
         * It takes a list of strings and sorts them by the number of times they appear in the list
         */
        public static void OrdenarPorResultados(){
            int Datos= 0;
            int actual= 0;
            String[] DatosAñadidos= new String[Nombre_de_los_archivos.length];
            Results= "<html>";
            while(Datos<Resultados.size()){
                if(CantidadResultados[Datos].equals(Resultados.get(actual)[2])){
                    if(!DatoAñanido(DatosAñadidos, Resultados.get(actual)[3])){
                        DatosAñadidos[Datos]= Resultados.get(actual)[3];
                        Results= Results + Resultados.get(actual)[3]+"<br>";
                        Datos++;
                        actual= 0;
                    } else{
                        actual++;
                    }
                    
                } else{
                    actual++;
                }
            }
            Results= Results+"<html>";
        }


        
        /**
         * It checks if the string is already in the array
         * 
         * @param DatosAñanidos The array that contains the data that has already been added.
         * @param Datos The data that will be added to the array.
         * @return The method is returning a boolean value.
         */
        public static boolean DatoAñanido(String [] DatosAñanidos, String Datos) {
            for (int index = 0; index < DatosAñanidos.length; index++) {
                if(DatosAñanidos[index]==null) {
                    break;
                }else if(DatosAñanidos[index].equals(Datos)){
                    return true;
                }
            }
            return false;
        }

        
        /**
         * It takes a string array and adds each element to a JComboBox
         * 
         * @param Datos String array with the data to be added to the JComboBox
         */
        public static void ActualizarLista(String[] Datos) {
            SelecciónArchivo.removeAllItems();
            for (int i = 0; i < Datos.length-1; i++) {
                SelecciónArchivo.addItem(Datos[i+1]);
            }
        }
        
        /**
         * It removes all items from a JComboBox
         */
        public static void Eliminar() {
            SelecciónArchivo.removeAllItems();
        }
}


