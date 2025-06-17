
package com.mycompany.proyectofinalprogramacionii.Vista;
import java.util.*;

public class Vista {
    
    // Creamos la parte visual del menu 
    
    public void menu(){
        System.out.println("menu");
        // falta llenar con algunas cosas
        System.out.println("[1] Registra un Equipo");
        System.out.println("[2] Guardar Equipos cargados en la base de datos");
        System.out.println("[3] Mostrar Equipos guardados en la base de datos");
        System.out.println("[4] Registrar Jugador");
    }
   // creamos una instancia de la clase Scanner
    Scanner sc = new Scanner(System.in);
    
    // Metodo para poder mostrar texto teniendo en cuenta la vista.
    public void mostrarTexto(String dato){
        System.out.println(dato);
    }
    
    // Metodo para poder pedir que ingrese un dato del tipo String.
    
    public String pedirString(){
        return sc.nextLine();
    }
    
    // Metodo para poder pedir que ingrese un dato del tipo entero.
    public int pedirInt(){
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada invalida. Ingrese un numero: ");
            }
        }
    }
    
    // Metodo para poder pedir que ingrese un dato del tipo entero.
    public double pedirDouble(){
        return sc.nextDouble();
    }
    
}
