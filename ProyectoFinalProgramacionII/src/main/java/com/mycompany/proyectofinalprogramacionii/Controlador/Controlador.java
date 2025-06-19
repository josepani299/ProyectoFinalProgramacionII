
package com.mycompany.proyectofinalprogramacionii.Controlador;
import com.mycompany.proyectofinalprogramacionii.Modelos.Equipo;
import com.mycompany.proyectofinalprogramacionii.Modelos.Jugador;
import com.mycompany.proyectofinalprogramacionii.Modelos.Mapa;
import com.mycompany.proyectofinalprogramacionii.Modelos.Torneo;
import com.mycompany.proyectofinalprogramacionii.Vista.Vista;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Controlador {
    private Vista vista;
    private List<Torneo> torneos = new ArrayList<>();
    private List<Equipo> equipos = new ArrayList<>();
    private List<Jugador> jugadores = new ArrayList<>();
    private List<Mapa> mapas = new ArrayList<>();
    private List<Torneo> torneo = new ArrayList<>();

    // falta crear las listas de Rol, mapas, personajes
    
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
                    

                   
                    break;
                case 5:
                   
                    break;
                case 6:
                    
                    break;
                case 7:
                    crearMapa();

                    
                    break;
                case 8: 
                    crearTorneo();
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
                     "id  PRIMARY KEY AUTO_INCREMENT, " +
                     "nombre VARCHAR(50) unique not null," +
                     "fecha Date not null,"+
                     "coach Varchar(50) not null,"+
                     "cantidadGanados int not null)";
        
        stmt.executeUpdate(tablaEquipo);
        con.close();
        System.out.println("Tabla creada correctamente.");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    }
    
    // Creamos el metodo para guardar los equipos en la base de datos
    
    public void guardarEquiposEnMysql(){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            String mysql = "INSERT INTO EQUIPO (nombre, fecha,coach,cantidadGanados) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(mysql);
        
        for (Equipo e : equipos) {
            ps.setString(1, e.getNombre());
            ps.setDate(2, java.sql.Date.valueOf(e.getFechaCreacion()));
            ps.setString(3, e.getCoach());
            ps.setInt(4,e.getCantidadDeTorenoGanadador());
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
    
    // Creo el metodo para agregar mapas
    
    public void crearMapa(){
    vista.mostrarTexto("Ingrese nombre del mapa");
    String nombre = vista.pedirString();
    vista.mostrarTexto("Ingrese lado favorable del mapa : Atacante/Defensivo");
    String favorable = vista.pedirString();
    vista.mostrarTexto("Ingrese cantidad de sites: ");
    String cantidadSites = vista.pedirString();
    vista.mostrarTexto("Ingrese descripcion del mapa: ");
    String descripcion = vista.pedirString();
    
    //metodo para agregar mapa
    Mapa m = new Mapa(nombre,favorable,cantidadSites,descripcion);
    mapas.add(m);
    vista.mostrarTexto("Se agrego mapa.");
    }
    
    // Creamo  el metodo para crear tabla mapa en base de datos
   /* public void crearTablaMapaMySql (){
        //vinculamos el archivo de java con la base de datos inventario.
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        // Creamos las tablas de la base de datos
        Statement stmt = con.createStatement();
        
        String tablaMapas = "CREATE TABLE IF NOT EXISTS Mapa (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "nombre VARCHAR(50) UNIQUE NOT NULL, " +
                        "favorable VARCHAR(20) NOT NULL, " +
                        "cantidadSites VARCHAR(10), " +
                        "descripcion VARCHAR(100)," +
                        ")";
        stmt.executeUpdate(tablaMapa);
        con.close();
        System.out.println("Tabla creada correctamente.");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    
    // Creamos el metodo para guardar los equipos en la base de datos
    
    public void guardarMapasEnMysql(){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            String mysql = "INSERT INTO Mapa (nombre, favorable, cantidadSites, descripcion) VALUES (?, ?, ?, ?)";;
            PreparedStatement ps = con.prepareStatement(mysql);
        for (Mapa e : mapas) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getFavorable());
            ps.setString(3, m.getCantidadSites());
            ps.setString(4, m.getDescripcion());

        }
        con.close();
        System.out.println("Mapas guardados en la base de datos.");

        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void mostrarMapasGuardados(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM Mapa";  // también podés poner solo "categoria"
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Listado de mapas:");
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String favorable = rs.getString("favorable");
            String cantidadSites = rs.getString("cantidadSites");
            String descripcion = rs.getString("descripcion");
            
            System.out.println("ID: " + id + " Nombre: " + nombre + "Favorable: " + favorable + " Cantidad Sites: " + cantidadSites + " Descripción: " + descripcion);
            }
        rs.close();
        stmt.close();
        con.close();
        }catch (Exception e) {
            System.out.println("Error al mostrar los mapas: " + e.getMessage());
            }
        }
*/
       

    //creamos meteodo para crear torneo

    public void crearTorneo() {
    vista.mostrarTexto("Ingrese nombre del torneo:");
    String nombre = vista.pedirString();

    vista.mostrarTexto("Ingrese ubicación:");
    String ubicacion = vista.pedirString();
    
    Equipo ganador = null;
    Jugador mvp = null;

    vista.mostrarTexto("Ingrese el premio total del torneo:");
    double premio = vista.pedirDouble();
    
    // metodo para agregar torneo
    Torneo t = new Torneo(nombre, ubicacion, ganador, mvp, premio);
    torneo.add(t);
    vista.mostrarTexto("Se agrego torneo.");
    }
    
    // Creamo  el metodo para crear tabla mapa en base de datos
    public void crearTablaTorneoMySql (){
        //vinculamos el archivo de java con la base de datos inventario.
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        // Creamos las tablas de la base de datos
        Statement stmt = con.createStatement();
        
        String tablaTorneos = "CREATE TABLE IF NOT EXISTS Torneo (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "nombre VARCHAR(50) UNIQUE NOT NULL, " +
                        "Ubicacion VARCHAR(50) NOT NULL, " +
                        "Ganador VARCHAR(50), " +
                        "Mvp VARCHAR(100)," +
                        ")";
        stmt.executeUpdate(tablaTorneo);
        con.close();
        System.out.println("Tabla creada correctamente.");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    
    // Creamos el metodo para guardar los equipos en la base de datos
    
    public void guardarTorneosEnMysql(){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            String mysql = "INSERT INTO torneo (nombre, ubicacion, premio_total, id_ganador, id_mvp) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(mysql);
        for (Torneo t : torneo) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getFavorable());
            ps.setString(3, m.getCantidadSites());
            ps.setString(4, m.getDescripcion());

        }
        con.close();
        System.out.println("Torneo guardados en la base de datos.");

        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void mostrarTorneosGuardados(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM Torneos";  // también podés poner solo "categoria"
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Listado de torneos:");
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String ubicacion = rs.getString("ubicacion");
            double premio = rs.getDouble("premio_total");
            int idGanador = rs.getInt("id_ganador");
            int idMvp = rs.getInt("id_mvp");

            
            System.out.println("ID: " + id + " | Nombre: " + nombre + " | Ubicación: " + ubicacion + " | Premio: $" + premio + " | ID Ganador: " + (rs.wasNull() ? "No asignado" : idGanador) + " | ID MVP: " + (rs.wasNull() ? "No asignado" : idMvp));
            
            System.out.println("ID del Equipo Ganador: " + (rs.wasNull() ? "No asignado" : idGanador));
            System.out.println("ID del MVP: " + (rs.wasNull() ? "No asignado" : idMvp));
            }
        rs.close();
        stmt.close();
        con.close();
        }catch (Exception e) {
            System.out.println("Error al mostrar los mapas: " + e.getMessage());
            }
        }
    
    
}

       
            
   
        
    
    

                
                
    


    
     
    
    
    
    



