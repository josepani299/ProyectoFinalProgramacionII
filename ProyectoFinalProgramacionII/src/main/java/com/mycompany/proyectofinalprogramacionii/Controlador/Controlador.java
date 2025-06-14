/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinalprogramacionii.Controlador;
import com.mycompany.proyectofinalprogramacionii.Modelos.Equipo;
import com.mycompany.proyectofinalprogramacionii.Modelos.Jugador;
import com.mycompany.proyectofinalprogramacionii.Modelos.Torneo;
import com.mycompany.proyectofinalprogramacionii.Vista.Vista;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;



public class Controlador {
    private Vista vista;
    private List<Torneo> torneos = new ArrayList<>();
    private List<Equipo> equipos = new ArrayList<>();
    private List<Jugador> jugadores = new ArrayList<>();
    // falta crear las listas de Rol, mapas, personajes
    

    public Controlador(Vista vista) {
        this.vista = vista;
    }
    
    // Creamos el metodo para crear equipo
    
    public void crearEquipo(){
        vista.mostrarTexto("Ingrese el nombre del equipo");
        String nombreEquipo = vista.pedirString();
        vista.mostrarTexto("Ingrese el pais de origen del equipo");
        String paisEquipo = vista.pedirString();
        vista.mostrarTexto("Ingrese el nombre del coach del equipo");
        String nombreCoach = vista.pedirString();
        vista.mostrarTexto("Ingrese la fecha de creacion aa/mm/dd");
        String fecha =vista.pedirString();
        
        // Establecemos el tipo de formato para presetnar en el tipo local date y lo convertimos a el tipo Local Date
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yy/MM/dd");
        LocalDate fechaCreacion = LocalDate.parse(fecha,formato);
        
        //Creamos el objeto equipo y lo agregamos a la lista de equipos
        
        Equipo e = new Equipo(nombreEquipo,paisEquipo,fechaCreacion, nombreCoach);
        equipos.add(e);
        vista.mostrarTexto("Se agrego el equipo");
        
        
    }
    
    
    
    
    
    
}
