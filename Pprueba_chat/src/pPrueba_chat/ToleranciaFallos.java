/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pPrueba_chat;

import java.util.Random;

/**
 *
 * @author Esme
 */
public class ToleranciaFallos {
    public static boolean conectarServicio(){
        Random rnd = new Random();
        return rnd.nextBoolean();
    }
    
    public static boolean manejarConexion(){
        byte intentos = 0;
        boolean conectado = false;
        while(intentos < 3 && !conectado){
            try{
                intentos++;
                System.out.println("Intentando conectar" + "al servicio, intento No." + intentos);
                conectado = conectarServicio();
                if(conectado){
                    System.out.println("Conexión exitosa");
                }else{
                    System.out.println("Error al conectar, reintentando...");
                }
            }catch(Exception e){
                System.out.println("Error al intentar conectar:"  + e.getMessage());
            }
        }
        if (!conectado){
            System.out.println("No se pudo conectar despues de varios intentos");
        }
        return conectado; // Devolver el estado de la conexión
    }
    
}
