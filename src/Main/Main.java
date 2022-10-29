package Main;

import Sockets.*;
import Interfaz.*;


public class Main {
    public static void main(String[] args) throws Exception {  
        Servidor Servidor = new Servidor();
        Servidor.IniciarServidor();
        Interfaz.VentanaInicio();
    }
}
