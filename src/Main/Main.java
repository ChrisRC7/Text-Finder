package Main;

import Sockets.Servidor;
import Interfaz.Interfaz;
public class Main {
    public static void main(String[] args) throws Exception {  
        Servidor Servidor = new Servidor();
        Servidor.IniciarServidor();
        Interfaz.VentanaInicio();
    }
}
