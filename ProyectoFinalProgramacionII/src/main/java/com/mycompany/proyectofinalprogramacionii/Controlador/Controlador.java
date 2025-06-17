/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinalprogramacionii.Controlador;
import com.mycompany.proyectofinalprogramacionii.Modelos.Equipo;
import com.mycompany.proyectofinalprogramacionii.Modelos.Jugador;
import com.mycompany.proyectofinalprogramacionii.Modelos.Torneo;
import com.mycompany.proyectofinalprogramacionii.Vista.Vista;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Controlador {
    private Vista vista;
    private List<Torneo> torneos = new ArrayList<>();
    private List<Equipo> equipos = new ArrayList<>();
    private List<Jugador> jugadores = new ArrayList<>();
    // falta crear las listas de Rol, mapas, personajes

    public List<Equipo> getEquipos() {
        return equipos;
    }
    
    
    
    public void menu() {
        int opcion;
        do {
            vista.menu();
            opcion = vista.pedirInt();
            switch (opcion) {
                case 1:
                    crearEquipo();
                    break;
                case 2:
                    guardarEquiposEnMysql();
                    break;
                case 3:
                    mostrarEquipoGuardados();
                    break;
                case 4:
                    crearJugador();
                    break;
                case 5:
                   
                    break;
                case 6:
                    
                    break;
                case 7:
                    
                    break;
            }
        } while (opcion != 0);
    }
    

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
        vista.mostrarTexto("Ingrese la fecha de creacion aa-mm-dd");
        String fecha =vista.pedirString();
        int cantidadGanado = 0;
        
        LocalDate fechaCreacion = LocalDate.parse(fecha);
        
        //Creamos el objeto equipo y lo agregamos a la lista de equipos
        
        Equipo e = new Equipo(nombreEquipo,paisEquipo,fechaCreacion, nombreCoach, cantidadGanado);
        equipos.add(e);
        vista.mostrarTexto("Se agrego el equipo");
        
        
    }
    
    // Creamos el metodo para crear la tabla equipo en la base de datos
    
    public void crearTablaEquipoEnMySql(){
        try {
        //vinculamos el archivo de java con la base de datos inventario.
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        // Creamos las tablas de la base de datos
        Statement stmt = con.createStatement();
        
        String tablaEquipo = "CREATE TABLE IF NOT EXISTS Equipo (" +
                     "id int PRIMARY KEY AUTO_INCREMENT, " +
                     "nombre VARCHAR(50) unique not null," +
                     "pais varchar(50) not null,"+
                     "fecha Date not null,"+
                     "coach Varchar(50) not null,"+
                     "cantidadGanados int not null)";
        
        stmt.executeUpdate(tablaEquipo);
        con.close();
        

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    }
    
    // Creamos el metodo para guardar los equipos en la base de datos
    
    public void guardarEquiposEnMysql(){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            String mysql = "INSERT IGNORE INTO EQUIPO (nombre,pais,fecha,coach,cantidadGanados) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(mysql);
        
        for (Equipo e : equipos) {
            ps.setString(1, e.getNombre());
            ps.setString(2,e.getPais());
            ps.setDate(3, java.sql.Date.valueOf(e.getFechaCreacion()));
            ps.setString(4, e.getCoach());
            ps.setInt(5,e.getCantidadDeTorenoGanadador());
            ps.executeUpdate();
        }

        con.close();
        System.out.println("Equipos guardados en la base de datos.");

        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Creamos el metodo para mostrar los equipos guardados en la base de datos

    public void mostrarEquipoGuardados(){
        try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        Statement stmt = con.createStatement();

        String sql = "SELECT * FROM equipo";  // también podés poner solo "categoria"
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("Listado de equipos:");
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            Date fecha = rs.getDate("fecha");
            String coach = rs.getString("coach");
            int cantidad = rs.getInt("cantidadGanados");
            System.out.println("ID Equipo: " + id + ",  Nombre: " + nombre + ", Fecha Creacion : " + fecha + ", Nombre coach: "+ coach + " Cantidad Ganados: "+ cantidad);
        }
        rs.close();
        stmt.close();
        con.close();
    } catch (Exception e) {
        System.out.println("Error al mostrar las categorías: " + e.getMessage());
    }
    }
    
    // Creamos el metodo para que la informacion de los equipos de la base de datos siempre ente cuando se ejecute el programa
    
    public void traerEquiposBD(List<Equipo> equipos){
       try
       {
        vista.mostrarTexto("Iniciando sistema");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        Statement stmt = con.createStatement();

        String sql = "SELECT * FROM equipo";  
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String pais = rs.getString("pais");
            Date fecha = rs.getDate("fecha");
            String coach = rs.getString("coach");
            int cantidadGanados = rs.getInt("cantidadGanados");
            LocalDate fechaLD = fecha.toLocalDate();
            
            Equipo e = new Equipo(nombre,pais,fechaLD,coach,cantidadGanados);
            equipos.add(e);
            
        }
        rs.close();
        stmt.close();
        con.close();
        vista.mostrarTexto("El sistema s einicio correctamente");
       }
       catch (Exception e) {
        System.out.println("Error al mostrar las categorías: " + e.getMessage());
    }
        
        
    }
    
    
    
    // Creamos el metodo para crear jugadores y guardalo en la lista.
    public void crearJugador(){
        
        vista.mostrarTexto("Ingrese el nombre del Jugador");
        String nombre= vista.pedirString();
        vista.mostrarTexto("Ingrese el apellido del jugador");
        String apellido = vista.pedirString();
        vista.mostrarTexto("Ingrese el tag del jugador");
        String tag = vista.pedirString();
        vista.mostrarTexto("Ingrese la fecha de alta del jugador aa-mm-dd");
        String fecha =vista.pedirString();
        vista.mostrarTexto("Ingrese el precio del jugador");
        double precio = vista.pedirDouble();
        vista.mostrarTexto("Ingrese la cantidad de kills del jugador");
        int kills = vista.pedirInt();
        vista.mostrarTexto("Ingrese el numero de asistensias");
        int asistencia = vista.pedirInt();
        vista.mostrarTexto("Ingrese el numero de muertes registradas");
        int dead = vista.pedirInt();
       
        
        LocalDate fechaCreacion = LocalDate.parse(fecha);
        
        //Creamos el objeto jugador y  lo agregamos a la lista de equipos
        Jugador j = new Jugador(nombre,apellido,tag,fechaCreacion,precio,kills,asistencia,dead);
        jugadores.add(j);
        vista.mostrarTexto("Se agrego el jugador "+ j.getNombre() + " a la lista de jugadores");
        
    }
    
    //Creamos el metodo para mostrar jugadores de la lista
    
    
}


