package com.mycompany.proyectofinalprogramacionii.Modelos;
// clase rol con los atribuutos: tipoRol y descripcionRol
public class Rol {
    private String tipoRol;
    private String descripcionRol;

    public Rol(String tipoRol, String descripcionRol) {
        this.tipoRol = tipoRol;
        this.descripcionRol = descripcionRol;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "tipoRol='" + tipoRol + '\'' +
                ", descripcionRol='" + descripcionRol + '\'' +
                '}';
    }
    
}
