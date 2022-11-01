/**
 * It creates a new instance of the class Servidor and calls the method IniciarServidor() from it.
 */
package Main;

import Sockets.Servidor;
import Interfaz.Interfaz;
public class Main {
    
    /** 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {  
        Servidor Servidor = new Servidor();
        Servidor.IniciarServidor();
        Interfaz.VentanaInicio();
    }
}
