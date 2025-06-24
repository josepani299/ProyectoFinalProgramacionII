
package com.mycompany.proyectofinalprogramacionii.Modelos;

import java.util.*;


public class Mapa {
    private String nombre;
    private String favorable;
    private int cantidadSites;
    private String descripcion;
    private List<Mapa> mapas= new ArrayList<>();
   
    
    public Mapa() {
    }

    public Mapa(String nombre, String favorable, int cantidadSites, String descripcion) {
        this.nombre = nombre;
        this.favorable = favorable;
        this.cantidadSites = cantidadSites;
        this.descripcion = descripcion;
        
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFavorable(String favorable) {
        this.favorable = favorable;
    }

    public void setCantidadSites(int cantidadSites) {
        this.cantidadSites = cantidadSites;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   

    public String getNombre() {
        return nombre;
    }

    public String getFavorable() {
        return favorable;
    }

    public int getCantidadSites() {
        return cantidadSites;
    }

    public String getDescripcion() {
        return descripcion;
    }

   

    @Override
    public String toString() {
        return "Mapa{" + "nombre=" + nombre + ", favorable=" + favorable + ", cantidadSites=" + cantidadSites + ", descripcion=" + descripcion + '}';
    }
     
    //agregar nuev mapa
   
    public void agregarnuevoMapa(Mapa mapa ){
        mapas.add(mapa);
        
    }
}
