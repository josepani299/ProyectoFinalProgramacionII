/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinalprogramacionii.Modelos;
import java.time.LocalDate;
import com.mycompany.proyectofinalprogramacionii.Modelos.Equipo;


public class Jugador {
    private String nombre;
    private String apellido;
    private String tag;
    private LocalDate fecha;
    private Equipo equipo;
    private double precio;
    private int kills;
    private int asistencia;
    private int dead;
    private double kda;

    public Jugador() {
    }
    
    public Jugador(String nombre, String apellido, String tag, LocalDate fecha, double precio, int kills, int asistencia, int dead) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tag = tag;
        this.fecha = fecha;
        this.precio = precio;
        this.kills = kills;
        this.asistencia = asistencia;
        this.dead = dead;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTag() {
        return tag;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public double getPrecio() {
        return precio;
    }

    public int getKills() {
        return kills;
    }

    public int getAsistencia() {
        return asistencia;
    }

    public int getDead() {
        return dead;
    }

    public double getKda() {
        return kda;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setAsistencia(int asistencia) {
        this.asistencia = asistencia;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }

    public void setKda(double kda) {
        this.kda = kda;
    }
    
    public void setEquipo(Equipo equipo){
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", apellido=" + apellido + ", tag=" + tag + ", fecha=" + fecha + ", equipo=" + equipo + ", precio=" + precio + ", kills=" + kills + ", asistencia=" + asistencia + ", dead=" + dead + ", kda=" + kda + '}';
    }
    
    
    
    }
   
    
    
