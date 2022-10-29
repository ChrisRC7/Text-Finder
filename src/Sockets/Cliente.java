package Sockets;

import javax.swing.*;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Cliente extends JFrame implements ActionListener{
    private JTextField campo1;
    private JButton miboton;
    
    public Cliente() {
        setLayout(null);

        campo1 = new JTextField(20);
        campo1.setBounds(30,150,100,50);
        add(campo1);
        miboton = new JButton("Enviar");
        miboton.setBounds(30, 200, 100, 50);
        miboton.addActionListener(this);
        add(miboton);
    }

    @Override
    public void actionPerformed(ActionEvent btn) {
        if (btn.getSource()== miboton){
            String IP = new String();
            try {
                IP = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try(Socket misocket = new Socket(IP,9998)) {
                
                

                DataOutputStream flujo_salida = new DataOutputStream(misocket.getOutputStream());
                flujo_salida.writeUTF(campo1.getText());
                flujo_salida.close();
            }  catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    

    public static void IniciarCliente() {
        Cliente Ventana = new Cliente();
        Ventana.setTitle("Cliente");
        Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Ventana.setSize(650,500);
        Ventana.setVisible(true);

    }
}

   

