/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinalprogramacionii.Controlador;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.proyectofinalprogramacionii.Modelos.Equipo;
import com.mycompany.proyectofinalprogramacionii.Modelos.Jugador;
import com.mycompany.proyectofinalprogramacionii.Modelos.Torneo;
import com.mycompany.proyectofinalprogramacionii.Vista.Vista;


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
    
    // Creamos el método para crear la tabla equipo en la base de datos
    
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
    
    //creamos el método para que los datos que hay en los equipos de la base de datos no se elimine cuando reniciamos el pograma
    
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
    
    
    
    // Creamos el método para crear jugadores y guardalo en la listita
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
    
    //méetodo para mostrar jugadores de la lista
    public void mostrarJugadores(){
        if (jugadores.isEmpty()) { //lista vacia
            vista.mostrarTexto("No hay jugadores registrados.");
        } else {
            vista.mostrarTexto("Lista de jugadores:");
            for (Jugador j : jugadores) {
                vista.mostrarTexto(j.toString());
            }
        }

    }


// Crear la tabla de jugadores en la base de datos
public void crearTablaJugadorEnMySql(){
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        Statement stmt = con.createStatement();
        String tablaJugador = "CREATE TABLE IF NOT EXISTS Jugador (" +
                    "id int PRIMARY KEY AUTO_INCREMENT, " +
                    "nombre VARCHAR(50) not null," +
                    "apellido varchar(50) not null,"+
                    "tag varchar(50) unique not null,"+
                    "fecha Date not null,"+
                    "precio double not null,"+
                    "kills int not null,"+
                    "asistencia int not null,"+
                    "dead int not null)";
        stmt.executeUpdate(tablaJugador);
        con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }   

    }

// creamos la tabla de rol en la base de datos
public void crearTablaRolEnMySql(){ 
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        Statement stmt = con.createStatement();
        String tablaRol = "CREATE TABLE IF NOT EXISTS Rol (" +
                     "id int PRIMARY KEY AUTO_INCREMENT, " +
                     "nombre VARCHAR(50) unique not null," +
                     "descripcion varchar(100) not null)";
        stmt.executeUpdate(tablaRol);
        con.close();    
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }   
}

// Guardamos los jugadores en la base de datos
public void guardarJugadoresEnMysql(){
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        String mysql = "INSERT IGNORE INTO Jugador (nombre,apellido,tag,fecha,precio,kills,asistencia,dead) VALUES (?,?,?,?,?,?,?,?)"; //se guarda en cada posición de ? 
        PreparedStatement ps = con.prepareStatement(mysql);
        for (Jugador j : jugadores) {
            ps.setString(1, j.getNombre());
            ps.setString(2, j.getApellido());
            ps.setString(3, j.getTag());
            ps.setDate(4, java.sql.Date.valueOf(j.getFechaAlta()));
            ps.setDouble(5, j.getPrecio());
            ps.setInt(6, j.getKills());
            ps.setInt(7, j.getAsistencia());
            ps.setInt(8, j.getDead());
            ps.executeUpdate();
        }
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    System.out.println("Jugadores guardados en la base de datos.");
    }
// Traemos los jugadores de la base de datos
public void traerJugadoresBD(List<Jugador> jugadores){
    try {
        vista.mostrarTexto("Iniciando sistema");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM jugador";
        ResultSet rs = stmt.executeQuery(sql);  
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String tag = rs.getString("tag");
            Date fecha = rs.getDate("fecha");
            double precio = rs.getDouble("precio");
            int kills = rs.getInt("kills");
            int asistencia = rs.getInt("asistencia");
            int dead = rs.getInt("dead");
            LocalDate fechaLD = fecha.toLocalDate();
            
            Jugador j = new Jugador(nombre, apellido, tag, fechaLD, precio, kills, asistencia, dead);
            jugadores.add(j);
        }
        rs.close();
        stmt.close();   
        con.close();
        vista.mostrarTexto("El sistema se inicio correctamente");
    } catch (Exception e) {
        System.out.println("Error al mostrar los jugadores: " + e.getMessage());
    }
}   
// Creamos la tabla de mapa en la base de datos
public void crearTablaMapaEnMySql(){
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");    
        Statement stmt = con.createStatement();
        String tablaMapa = "CREATE TABLE IF NOT EXISTS Mapa (" +
                     "id int PRIMARY KEY AUTO_INCREMENT, " +
                     "nombre VARCHAR(50) unique not null," +
                     "descripcion varchar(100) not null)";  
        stmt.executeUpdate(tablaMapa);
        con.close();    
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
// Guardamos los mapas en la base de datos
public void guardarMapasEnMysql(){
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");            
        String mysql = "INSERT IGNORE INTO Mapa (nombre,descripcion) VALUES (?,?)"; //se guarda en cada posición de ?
        PreparedStatement ps = con.prepareStatement(mysql);
        for (Equipo e : equipos) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getPais());
            ps.executeUpdate();
        }
        ps.close();
        con.close();
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}   
// Traemos los mapas de la base de datos
public void traerMapasBD(List<Equipo> equipos){
    try {
        vista.mostrarTexto("Iniciando sistema");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        Statement stmt = con.createStatement();         
        String sql = "SELECT * FROM mapa";
        ResultSet rs = stmt.executeQuery(sql);      
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            
            Equipo e = new Equipo(nombre, descripcion);
            equipos.add(e);
        }
        rs.close();
        stmt.close();
        con.close();
        vista.mostrarTexto("El sistema se inicio correctamente");
    } catch (Exception e) {
        System.out.println("Error al mostrar los mapas: " + e.getMessage());
    }
}
// Creamos la tabla de torneo en la base de datos
public void crearTablaTorneoEnMySql(){
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        Statement stmt = con.createStatement();
        String tablaTorneo = "CREATE TABLE IF NOT EXISTS Torneo (" +
                        "id int PRIMARY KEY AUTO_INCREMENT, " +
                        "nombre VARCHAR(50) unique not null," +
                        "fecha Date not null," +
                        "premio double not null," +
                        "cantidadEquipos int not null)";    
        stmt.executeUpdate(tablaTorneo);
        con.close();
    } catch (Exception e) {

        System.out.println("Error: " + e.getMessage());
    }
}
// Guardamoss los torneos en la base de datos  
public void guardarTorneosEnMysql(){
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        String mysql = "INSERT IGNORE INTO Torneo (nombre,fecha,premio,cantidadEquipos) VALUES (?,?,?,?)"; 
        PreparedStatement ps = con.prepareStatement(mysql);
        for (Torneo t : torneos) {
            ps.setString(1, t.getNombre());
            ps.setDate(2, java.sql.Date.valueOf(t.getFecha()));
            ps.setDouble(3, t.getPremio());
            ps.setInt(4, t.getCantidadEquipos());
            ps.executeUpdate();
        }
        ps.close();
        con.close();
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}   
// Traemos los torneos de la base de datos
public void traerTorneosBD(List<Torneo> torneos){

    try {
        vista.mostrarTexto("Iniciando sistema");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BD
Proyecto", "root", "Admin123!");

        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM torneo";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            Date fecha = rs.getDate("fecha");
            double premio = rs.getDouble("premio");
            int cantidadEquipos = rs.getInt("cantidadEquipos");
            LocalDate fechaLD = fecha.toLocalDate();
            
            Torneo t = new Torneo(nombre, fechaLD, premio, cantidadEquipos);
            torneos.add(t);
        }
        rs.close();
        stmt.close();
        con.close();
        vista.mostrarTexto("El sistema se inicio correctamente");
    } catch (Exception e) {
        System.out.println("Error al mostrar los torneos: " + e.getMessage());
    }
}   
}   

// creamos la tabla de personajes en la base de datos
public void crearTablaPersonajeEnMySql(){
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        Statement stmt = con.createStatement();
        String tablaPersonaje = "CREATE TABLE IF NOT EXISTS Personaje (" +
                        "id int PRIMARY KEY AUTO_INCREMENT, " +
                        "nombre VARCHAR(50) unique not null," +
                        "edad int not null," +
                        "clase VARCHAR(50) not null," +
                        "nivel int not null)";    
        stmt.executeUpdate(tablaPersonaje);
        con.close();
    } catch (Exception e) {

        System.out.println("Error: " + e.getMessage());
    }
}
// Guardamos los personajes en la base de datos
public void guardarPersonajesEnMysql(){
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");

        String mysql = "INSERT IGNORE INTO Personaje (nombre,edad,clase,nivel) VALUES (?,?,?,?)"; //se guarda en cada posición de ?
        PreparedStatement ps = con.prepareStatement(mysql);             
        for (Personaje p : personajes) {
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getEdad());
            ps.setString(3, p.getClase());
            ps.setInt(4, p.getNivel());
            ps.executeUpdate();
        }
        ps.close();
        con.close();
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
// Traemos los personajes de la base de datos
public void traerPersonajesBD(List<Personaje> personajes){
    try {
        vista.mostrarTexto("Iniciando sistema");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM personaje";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            int edad = rs.getInt("edad");
            String clase = rs.getString("clase");
            int nivel = rs.getInt("nivel");
            
            Personaje p = new Personaje(nombre, edad, clase, nivel);
            personajes.add(p);
        }
        rs.close();
        stmt.close();
        con.close();
        vista.mostrarTexto("El sistema se inicio correctamente");
    } catch (Exception e) {
        System.out.println("Error al mostrar los personajes: " + e.getMessage());
    }
}

// Creamos la tabla de rol en la base de datos
public void crearTablaRolEnMySql(){
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");    
        Statement stmt = con.createStatement();
        String tablaRol = "CREATE TABLE IF NOT EXISTS Rol (" +
                        "id int PRIMARY KEY AUTO_INCREMENT, " +
                        "nombre VARCHAR(50) unique not null," +
                        "descripcion varchar(100) not null)";
        stmt.executeUpdate(tablaRol);
        con.close();
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
// Guardamos los roles en la base de datos
public void guardarRolesEnMysql(){
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        String mysql = "INSERT IGNORE INTO Rol (nombre,descripcion) VALUES (?,?)"; 
        PreparedStatement ps = con.prepareStatement(mysql);
        for (Rol r : roles) {
            ps.setString(1, r.getNombre());
            ps.setString(2, r.getDescripcion());
            ps.executeUpdate();
        }
        ps.close();
        con.close();
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
// Traemos los roles de la base de datos
public void traerRolesBD(List<Rol> roles){
    try {
        vista.mostrarTexto("Iniciando sistema");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM rol";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            
            Rol r = new Rol(nombre, descripcion);
            roles.add(r);
        }
        rs.close();
        stmt.close();
        con.close();
        vista.mostrarTexto("El sistema se inicio correctamente");
    } catch (Exception e) {
        System.out.println("Error al mostrar los roles: " + e.getMessage());
    }
}
// Creamos la tabla de personaje en la base de datos
public void crearTablaPersonajeEnMySql(){
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        Statement stmt = con.createStatement();
        String tablaPersonaje = "CREATE TABLE IF NOT EXISTS Personaje (" +  
                        "id int PRIMARY KEY AUTO_INCREMENT, " +
                        "nombre VARCHAR(50) unique not null," +
                        "edad int not null," +
                        "clase VARCHAR(50) not null," +
                        "nivel int not null)";
        stmt.executeUpdate(tablaPersonaje);
        con.close();
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
// Guardamos los personajes en la base de datos
public void guardarPersonajesEnMysql(){
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");    
        String mysql = "INSERT IGNORE INTO Personaje (nombre,edad,clase,nivel) VALUES (?,?,?,?)"; 
        PreparedStatement ps = con.prepareStatement(mysql);
        for (Personaje p : personajes) {
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getDescripcion());
            ps.setString(3, p.getRol());
            ps.executeUpdate();

        }
        ps.close();
        con.close();
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    System.out.println("Personajes guardados en la base de datos.");
}
// Traemos los personajes de la base de datos   
public void traerPersonajesBD(List<Personaje> personajes){
    try {
        vista.mostrarTexto("Iniciando sistema");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM personaje";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String clase = rs.getString("Descripcion");
            String rol = rs.getString("rol");

            Personaje p = new Personaje(nombre, descripcion, rol);
            personajes.add(p);
        }
        rs.close();
        stmt.close();
        con.close();
        vista.mostrarTexto("El sistema se inicio correctamente");
    } catch (Exception e) {
        System.out.println("Error al mostrar los personajes: " + e.getMessage());
    }
}   
}
