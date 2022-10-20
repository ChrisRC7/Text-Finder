package Sockets;

import javax.swing.*;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

class LaminaMarcoCliente extends JPanel{
    private JTextField campo1;
    private JButton miboton;
    public LaminaMarcoCliente() {

        JLabel texto = new JLabel("Cliente");
        add(texto);
        campo1 = new JTextField(20);
        add(campo1);
        miboton = new JButton("Enviar");

        EnviaTexto mievento = new EnviaTexto();
        miboton.addActionListener(mievento);
        add(miboton);
        }
        private class EnviaTexto implements ActionListener {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    Socket misocket = new Socket("192.168.98.112",9998);

                    DataOutputStream flujo_salida = new DataOutputStream(misocket.getOutputStream());
                    flujo_salida.writeUTF(campo1.getText());
                    flujo_salida.close();
                }  catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
    class MarcoCliente extends JFrame{
        public MarcoCliente(){
            setBounds(600,300,280,350);
            LaminaMarcoCliente milamina = new LaminaMarcoCliente();
            add(milamina);
            setVisible(true);
        }
    }
    public class Cliente{
        public static void main(String[] args){
            MarcoCliente mimarco = new MarcoCliente();
            mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

