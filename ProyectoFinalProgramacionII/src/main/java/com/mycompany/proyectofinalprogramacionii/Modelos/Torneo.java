/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinalprogramacionii.Modelos;
import java.time.LocalDate;
import java.util.*;



public class Torneo {
    private String nombre;
    private String ubicacion;
    private Equipo ganador;
    private Jugador mvp;
    private LocalDate fecha;
    private double premioTotal;
    private List<Equipo> equipos = new ArrayList<>();

    public void setGanador(Equipo ganador) {
        this.ganador = ganador;
    }
    
    
    
    
    public Torneo() {
    }

    public Torneo(String nombre, String ubicacion, Equipo ganador, Jugador mvp, double premioTotal, LocalDate fecha) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.mvp = mvp;
        this.premioTotal = premioTotal;
        this.fecha = fecha;
        this.ganador = ganador;
    }
    
   
    public List<Equipo> getEquipos() {
        return equipos;
    }
    
    // metodo que no fue util.
//    public int cantidadEquiposTorneo(){
//        return equipos.size();
//    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Equipo getGanador() {
        return ganador;
    }

   

    public void setMvp(Jugador mvp) {
        this.mvp = mvp;
    }

    public void setPremioTotal(double premioTotal) {
        this.premioTotal = premioTotal;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

   

    public Jugador getMvp() {
        return mvp;
    }

    public double getPremioTotal() {
        return premioTotal;
    }

    @Override
    public String toString() {
        return "Torneo{" + "nombre=" + nombre + ", ubicacion=" + ubicacion + ", ganador=" + ganador + ", mvp=" + mvp + ", fecha=" + fecha + ", premioTotal=" + premioTotal + '}';
    }
    
    
   
    
}
