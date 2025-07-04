/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectofinalprogramacionii;

import com.mycompany.proyectofinalprogramacionii.Controlador.Controlador;
import com.mycompany.proyectofinalprogramacionii.Vista.Vista;


public class Main {

    public static void main(String[] args) {
        Vista vista = new Vista();
        Controlador controlador = new Controlador(vista);
        controlador.crearTablaEquipoEnMySql();
        controlador.crearTablaJugadorEnMysql();
        controlador.crearTablaMapaMySql();
        controlador.crearTablaRolEnMySql();
        controlador.crearTablaTorneoEnMySql();
        controlador.traerEquiposBD(controlador.getEquipos());
        controlador.menu();
    }
}
