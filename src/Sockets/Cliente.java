package Sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Interfaz.Interfaz;


public class Cliente  {
   
    @SuppressWarnings("unchecked")
    public static void EnviarPalabra(Object Dato) throws ClassNotFoundException {
        String IP = new String();
            try {
                IP = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try(Socket misocket = new Socket(IP,9998)) {

                OutputStream OutPut= misocket.getOutputStream();
                ObjectOutputStream ObjectOutPut= new ObjectOutputStream(OutPut);
                ObjectOutPut.writeObject(Dato);

                InputStream Respuesta= misocket.getInputStream();
                ObjectInputStream info= new ObjectInputStream(Respuesta);
                Object object= info.readObject();

                if ((object instanceof ArrayList)){
                    Interfaz.SetResultados((ArrayList<String[]>) object);
                }
                
            }  catch (IOException ex) {
                throw new RuntimeException(ex);
            }
    }

   
    
}

   

