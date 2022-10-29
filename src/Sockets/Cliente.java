package Sockets;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;

public class Cliente {
   

    public static void EnviarPalabra(Object Dato) {
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
                
            }  catch (IOException ex) {
                throw new RuntimeException(ex);
            }
    }

    
}

   

