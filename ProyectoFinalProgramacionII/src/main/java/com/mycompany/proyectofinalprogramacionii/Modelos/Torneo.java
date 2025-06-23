/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinalprogramacionii.Modelos;
import java.util.*;


public class Torneo {
    private String nombre;
    private String ubicacion;
    private Equipo ganador;
    private Jugador mvp;
    private double premioTotal;
    private List<Torneo> torneos = new ArrayList<>();

    public Torneo() {
    }

    public Torneo(String nombre, String ubicacion, Equipo ganador, Jugador mvp, double premioTotal) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.ganador = ganador;
        this.mvp = mvp;
        this.premioTotal = premioTotal;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setGanador(Equipo ganador) {
        this.ganador = ganador;
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

    public Equipo getGanador() {
        return ganador;
    }

    public Jugador getMvp() {
        return mvp;
    }

    public double getPremioTotal() {
        return premioTotal;
    }

    @Override
    public String toString() {
        return "Torneo{" + "nombre=" + nombre + ", ubicacion=" + ubicacion + ", ganador=" + ganador + ", mvp=" + mvp + ", premioTotal=" + premioTotal + '}';
    }
    
    
    public void agregarNuevoTorneo(Torneo torneo){
        torneos.add(torneo);
    }
    
}
