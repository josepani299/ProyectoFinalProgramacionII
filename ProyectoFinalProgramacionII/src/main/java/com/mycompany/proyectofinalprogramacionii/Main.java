/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectofinalprogramacionii;

import com.mycompany.proyectofinalprogramacionii.controlador.Controlador;
import com.mycompany.proyectofinalprogramacionii.Vista.Vista;


public class Main {

    public static void main(String[] args) {
        Vista vista = new Vista();
        Controlador controlador = new Controlador(vista);
        controlador.crearTablaEquipoEnMySql();
        controlador.traerEquiposBD(controlador.getEquipos());
        controlador.menu();
    }
}
