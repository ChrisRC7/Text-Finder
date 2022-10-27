package Interfaz;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.*;


public class Interfaz extends JFrame implements ActionListener{
    JButton Buscarbtn;
    private JTextField PalabraPorBuscar;

        public Interfaz() {

            setLayout(null);

            Buscarbtn= new JButton("<html>Detener Biblioteca<html>");
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
                    System.out.println(Palabra);
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

}
