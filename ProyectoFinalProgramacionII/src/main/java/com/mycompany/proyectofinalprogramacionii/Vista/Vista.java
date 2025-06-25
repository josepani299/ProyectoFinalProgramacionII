
package com.mycompany.proyectofinalprogramacionii.Vista;
import java.util.*;

public class Vista {
    
    // Creamos la parte visual del menu 
    
    public void menu(){
        System.out.println("menu");
        // falta llenar con algunas cosas
        System.out.println("[1] Equipos");
        System.out.println("[2] Mapa");
        System.out.println("[3] Rol");
        System.out.println("[4] Jugador");
        System.out.println("[5] Torneo");
    }
    
    public void menuEquipo(){
        System.out.println("[1] Registrar un Equipo");
        System.out.println("[2] Guardar Equipos en la base de datos");
        System.out.println("[3] Mostrar Equipos guardados en la base de datos");
        
    }
    
    public void menuJugador(){
        System.out.println("[1] Registrar un Jugador");
        System.out.println("[2] Guardar Jugador en la base de datos");
        System.out.println("[3] Mostrar Jugadores guardados en la base de datos");
    }
    
    public void menuMapa(){
        System.out.println("[1] Registrar un Mapa");
        System.out.println("[2] Guardar Mapas en la base de datos");
        System.out.println("[3] Mostrar Mapas guardados en la base de datos");
    }
    
    public void menuRol(){
        System.out.println("[1] Registrar Rol");
        System.out.println("[2] Guardar Rol en la base de datos");
        System.out.println("[3] Mostrar Roles guardados en la base de datos");
    }
    
    public void menuTorneo(){
        System.out.println("[1] Registrar Torneo");
        System.out.println("[2] Guardar Torneo en la base de datos");
        System.out.println("[3] Mostrar Torneo guardados en la base de datos");
        System.out.println("[4] Estimar ganador de un enfrentamiento");
        System.out.println("[5] Simulador Torneo ");
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
