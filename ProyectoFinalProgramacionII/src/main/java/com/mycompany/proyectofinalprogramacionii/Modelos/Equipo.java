
package com.mycompany.proyectofinalprogramacionii.Modelos;
import java.time.LocalDate;
import java.util.*;


public class Equipo {
    private String nombre;
    private String pais;
    private LocalDate fechaCreacion;
    private List<Jugador> jugadoresEquipo = new ArrayList<>();
    private List<Torneo> torneosJugados = new ArrayList<>();
    private String coach;
    private int cantidadDeTorenoGanadador;

    public Equipo() {
    }

    public Equipo(String nombre, String pais, LocalDate fechaCreacion, String coach) {
        this.nombre = nombre;
        this.pais = pais;
        this.fechaCreacion = fechaCreacion;
        this.coach = coach;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    

    public List<Torneo> getTorneosJugados() {
        return torneosJugados;
    }

    public String getCoach() {
        return coach;
    }

    public int getCantidadDeTorenoGanadador() {
        return cantidadDeTorenoGanadador;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    @Override
    public String toString() {
        return "Equipo{" + "nombre=" + nombre + ", pais=" + pais + ", fechaCreacion=" + fechaCreacion + ", coach=" + coach + ", cantidadDeTorenoGanadador=" + cantidadDeTorenoGanadador + '}';
    }
    //Metodo para mostrar los valores de la lista de jugadores
    public void mostrarJugadoresEquipo(){
        for (Jugador j: jugadoresEquipo){
            System.out.println(j.toString());
        }
    }
    
    // Metodo para mostrar la lista de torneos jugados
    public void mostrarTorneosJugados(){
        for (Torneo t: torneosJugados){
            System.out.println(t.toString());
        }
    }
    
    
    
    
    
    
}
