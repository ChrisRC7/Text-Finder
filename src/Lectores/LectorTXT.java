package Lectores;

import java.io.BufferedReader;
import java.io.FileReader;

public class LectorTXT {
    public static void main(String[] args){
        FileReader archivo;
        BufferedReader lector;

        try{
            archivo= new FileReader("src/Lectores/resultados investigacion.txt");

            if(archivo.ready()){
                lector = new BufferedReader(archivo);
                String cadena;
                while((cadena=lector.readLine())!=null){//Hay algo
                    System.out.println(cadena);
                }
            }else{
                System.out.println("El archivo no está listo para ser leído...");
            }

        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }

    }
}
