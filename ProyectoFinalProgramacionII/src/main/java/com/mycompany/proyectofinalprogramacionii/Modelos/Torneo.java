
package com.mycompany.proyectofinalprogramacionii.Modelos;
import java.time.LocalDate;
import java.util.*;

public class Torneo {
    private String nombre;
    private String ubicacion;
    private Equipo ganador;
    private Jugador mvp;
    private double premioTotal;
    private List<Equipo> posiciones = new ArrayList<>();
    private List<Torneo> torneo = new ArrayList<>();
    private List<Torneo> agregarTorneo = new ArrayList<>();

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

    public void setPosiciones(List<Equipo> posiciones) {
        this.posiciones = posiciones;
    }

    public void setTorneo(List<Torneo> torneo) {
        this.torneo = torneo;
    }

    public void setAgregarTorneo(List<Torneo> agregarTorneo) {
        this.agregarTorneo = agregarTorneo;
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

    public List<Equipo> getPosiciones() {
        return posiciones;
    }

    public List<Torneo> getTorneo() {
        return torneo;
    }

    public List<Torneo> getAgregarTorneo() {
        return agregarTorneo;
    }

    @Override
    public String toString() {
        return "Torneo{" + "nombre=" + nombre + ", ubicacion=" + ubicacion + ", ganador=" + ganador + ", mvp=" + mvp + ", premioTotal=" + premioTotal + ", posiciones=" + posiciones + ", torneo=" + torneo + ", agregarTorneo=" + agregarTorneo + '}';
    }
    
    public void agregarNuevoTorneo(Torneo torneo){
        agregarTorneo.add(torneo);
    }
    
    
    
}
