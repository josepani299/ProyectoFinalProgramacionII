package com.mycompany.proyectofinalprogramacionii.Controlador;

import com.mycompany.proyectofinalprogramacionii.Modelos.Equipo;
import com.mycompany.proyectofinalprogramacionii.Modelos.Jugador;
import com.mycompany.proyectofinalprogramacionii.Modelos.Mapa;
import com.mycompany.proyectofinalprogramacionii.Modelos.Torneo;
import com.mycompany.proyectofinalprogramacionii.Vista.Vista;
import com.mycompany.proyectofinalprogramacionii.Modelos.Rol;
import com.mycompany.proyectofinalprogramacionii.Modelos.Personaje;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;



public class Controlador {

    private Vista vista;
    private List<Equipo> equipos = new ArrayList<>();
    private List<Jugador> jugadores = new ArrayList<>();
    private List<Mapa> mapas = new ArrayList<>();
    private List<Torneo> torneos = new ArrayList<>();
    private List<Rol> roles = new ArrayList<>();
    private List<Personaje> personajes = new ArrayList<>();


    public List<Equipo> getEquipos() {
        return this.equipos;
    }

   
    public void menu() {
    int opcion;
    do {
        vista.menu();
        opcion = vista.pedirInt();

        switch (opcion) {
            case 1:
                // Equipo
                int opcion1;
                do {
                    vista.menuEquipo();
                    opcion1 = vista.pedirInt();
                    switch (opcion1) {
                        case 1:
                            crearEquipo();
                            break;
                        case 2:
                            guardarEquiposEnMysql();
                            break;
                        case 3:
                            mostrarEquipoGuardados();
                            break;
                        case 0:
                            break;
                        default:
                            vista.mostrarTexto("Opcion invalida.");
                            break;
                    }
                } while (opcion1 != 0);
                break;

            case 2:
                // Mapa
                int opcion2;
                do {
                    vista.menuMapa();
                    opcion2 = vista.pedirInt();
                    switch (opcion2) {
                        case 1:
                            crearMapa();
                            break;
                        case 2:
                            guardarMapasEnMysql();
                            break;
                        case 3:
                            mostrarMapasGuardados();
                            break;
                        case 0:
                            break;
                        default:
                            vista.mostrarTexto("Opcion invalida.");
                            break;
                    }
                } while (opcion2 != 0);
                break;

            case 3:
                // Rol
                int opcion3;
                do {
                    vista.menuRol();
                    opcion3 = vista.pedirInt();
                    switch (opcion3) {
                        case 1:
                            crearRol();
                            break;
                        case 2:
                            guardarRolEnMysql();
                            break;
                        case 3:
                            mostrarRolEnMysql();
                            break;
                        case 0:
                            break;
                        default:
                            vista.mostrarTexto("Opcion invalida.");
                            break;
                    }
                } while (opcion3 != 0);
                break;

            case 4:
                // Jugador
                int opcion4;
                do {
                    vista.menuJugador(); // Corregido
                    opcion4 = vista.pedirInt();
                    switch (opcion4) {
                        case 1:
                            crearJugador();
                            break;
                        case 2:
                            guardarJugadorEnMysql();
                            break;
                        case 3:
                            mostrarJugadorDeMysql();
                            break;
                        case 0:
                            break;
                        default:
                            vista.mostrarTexto("Opcion invalida.");
                            break;
                    }
                } while (opcion4 != 0);
                break;

            case 5:
                // Torneo
                int opcion5;
                do {
                    vista.menuTorneo();
                    opcion5 = vista.pedirInt();
                    switch (opcion5) {
                        case 1:
                            crearTorneo();
                            break;
                        case 2:
                            guardarTorneosEnMysql();
                            break;
                        case 3:
                            mostrarTorneosGuardados();
                            break;
                        case 4:
                            Equipo e1 = seleccionarEquipoDeLista(equipos);
                            Equipo e2 = seleccionarEquipoDeLista(equipos);
                            if (e1 != null && e2 != null) {
                                simularEnfrentamientoEstimacion(e1, e2);
                            }
                            break;
                        case 5:
                            Torneo t = seleccionarTorneoDeLista(torneos);
                            if (t != null) {
                                simularTorneo(t);
                            }
                            break;
                        case 0:
                            break;
                        default:
                            vista.mostrarTexto("Opcion invalida.");
                            break;
                    }
                } while (opcion5 != 0);
                break;

            case 0:
                vista.mostrarTexto("Saliendo del sistema...");
                break;

            default:
                vista.mostrarTexto("Opcion invalida.");
                break;
        }
    } while (opcion != 0);
}


    public Controlador(Vista vista) {
        this.vista = vista;
    }
    //------------------------------------------------------------------------------------
    // Metodos para la clase Equipo:
    
    // Creamos el metodo para crear equipo
    public void crearEquipo() {
        vista.mostrarTexto("Ingrese el nombre del equipo");
        String nombreEquipo = vista.pedirString();
        vista.mostrarTexto("Ingrese el pais de origen del equipo");
        String paisEquipo = vista.pedirString();
        vista.mostrarTexto("Ingrese el nombre del coach del equipo");
        String nombreCoach = vista.pedirString();
        vista.mostrarTexto("Ingrese la fecha de creacion aa-mm-dd");
        String fecha = vista.pedirString();
        int cantidadGanado = 0;

        LocalDate fechaCreacion = LocalDate.parse(fecha);

        //Creamos el objeto equipo y lo agregamos a la lista de equipos
        Equipo e = new Equipo(nombreEquipo, paisEquipo, fechaCreacion, nombreCoach, cantidadGanado);
        equipos.add(e);
        vista.mostrarTexto("Se agrego el equipo");

    }

    // Creamos el emtrodo apra crear la tabla en mysql
    public void crearTablaEquipoEnMySql() {
        try {
            //vinculamos el archivo de java con la base de datos inventario.
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            // Creamos las tablas de la base de datos
            Statement stmt = con.createStatement();

            String tablaEquipo = "CREATE TABLE IF NOT EXISTS Equipo ("
                    + "id int PRIMARY KEY AUTO_INCREMENT, "
                    + "nombre VARCHAR(50) unique not null,"
                    + "pais varchar(50) not null,"
                    + "fecha Date not null,"
                    + "coach Varchar(50) not null,"
                    + "cantidadGanados int not null)";

            stmt.executeUpdate(tablaEquipo);
            con.close();
            System.out.println("Tabla creada correctamente.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Creamos el metodo para guardar los equipos en la base de datos
    public void guardarEquiposEnMysql() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            String mysql = "INSERT IGNORE INTO EQUIPO (nombre,pais, fecha,coach,cantidadGanados) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(mysql);

            for (Equipo e : equipos) {
                ps.setString(1, e.getNombre());
                ps.setString(2, e.getPais());
                ps.setDate(3, java.sql.Date.valueOf(e.getFechaCreacion()));
                ps.setString(4, e.getCoach());
                ps.setInt(5, e.getCantidadDeTorenoGanadador());
                ps.executeUpdate();
            }

            con.close();
            System.out.println("Equipos guardados en la base de datos.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Creamos el metodo para mostrar los equipos guardados en la base de datos
    public void mostrarEquipoGuardados() {
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
                System.out.println("ID Equipo: " + id + ",  Nombre: " + nombre + ", Fecha Creacion : " + fecha + ", Nombre coach: " + coach + " Cantidad Ganados: " + cantidad);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error al mostrar las categorías: " + e.getMessage());
        }
    }

    // Creamos el metodo para traer a el programa los datos guardados en la base de datos

    
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
        
    // Creamos el metodo para buscar un equipo en la lista por el nombre y retornarlo

    public Equipo buscarEquipo(String nombreEquipo){
        for (Equipo r : equipos) {
            if (nombreEquipo.equals(r.getNombre())) {
                return r;
            }
        }
        return null;
    }
    


//------------------------------------------------------------------------------------
    // Metodos para la clase Mapa

    // Creo el metodo para agregar mapas
    public void crearMapa() {
        vista.mostrarTexto("Ingrese nombre del mapa");
        String nombre = vista.pedirString();
        vista.mostrarTexto("Ingrese lado favorable del mapa : Atacante/Defensivo");
        String favorable = vista.pedirString();
        vista.mostrarTexto("Ingrese cantidad de sites: ");
        int cantidadSites = vista.pedirInt();
        vista.mostrarTexto("Ingrese descripcion del mapa: ");
        String descripcion = vista.pedirString();

        //metodo para agregar mapa
        Mapa m = new Mapa(nombre, favorable, cantidadSites, descripcion);
        mapas.add(m);
        vista.mostrarTexto("Se agrego mapa.");
    }

    // Creamo  el metodo para crear tabla mapa en base de datos
    public void crearTablaMapaMySql() {
        try {
            //vinculamos el archivo de java con la base de datos inventario.
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            // Creamos las tablas de la base de datos
            Statement stmt = con.createStatement();

            String tablaMapas = "CREATE TABLE IF NOT EXISTS mapa ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "nombre VARCHAR(50) UNIQUE NOT NULL, "
                    + "favorable VARCHAR(50) NOT NULL, "
                    + "cantidadSites int, "
                    + "descripcion VARCHAR(800)"
                    + ")";
            stmt.executeUpdate(tablaMapas);
            con.close();
            System.out.println("Tabla creada correctamente.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Creamos el metodo para guardar los equipos en la base de datos
    public void guardarMapasEnMysql() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            String mysql = "INSERT IGNORE INTO mapa (nombre,favorable,cantidadSites,descripcion) VALUES (?,?)"; //se guarda en cada posición de ?
            PreparedStatement ps = con.prepareStatement(mysql);
            for (Mapa m : mapas) {
                ps.setString(1, m.getNombre());
                ps.setString(2, m.getFavorable());
                ps.setInt(3,m.getCantidadSites());
                ps.setString(4, m.getDescripcion());
                ps.executeUpdate();
            }
            ps.close();
            con.close();
            vista.mostrarTexto("Se guardo con exito los mapas");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void mostrarMapasGuardados() {

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
        } catch (Exception e) {
            System.out.println("Error al mostrar los mapas: " + e.getMessage());
        }
    }

    // Creamos el metodo para traer los datos guardados en mysql
    
    public void traerMapasBD(){
        try {
            vista.mostrarTexto("Iniciando sistema");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM mapa";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int cantSite = rs.getInt("cantidadSites");
                String favorable = rs.getString("favorable");
                String descripcion = rs.getString("descripcion");
                Mapa e = new Mapa(nombre,favorable,cantSite, descripcion);
                mapas.add(e);
            }
            rs.close();
            stmt.close();
            con.close();
            vista.mostrarTexto("El sistema se inicio correctamente");
        } catch (Exception e) {
            System.out.println("Error al mostrar los mapas: " + e.getMessage());
        }
    }
    

    //------------------------------------------------------------------------------
    // Metodos para Rol

    // Creamos el metodo para crear un rol y guardarlo en la lista
    
    public void crearRol(){
          vista.mostrarTexto("Ingrese el tipo de Rol");
          String tipo = vista.pedirString();
          vista.mostrarTexto("Ingrese la descripcion del Rol");
          String DescRol = vista.pedirString();
          Rol r= new Rol(tipo,DescRol);
          roles.add(r);
          
}
    
    // Creamos el metodo para crear la tabla rol en mysql

    public void crearTablaRolEnMySql() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            Statement stmt = con.createStatement();
            String tablaRol = "CREATE TABLE IF NOT EXISTS Rol ("
                    + "id int PRIMARY KEY AUTO_INCREMENT, "
                    + "tipoRol VARCHAR(50) unique not null,"
                    + "descripcion varchar(100) not null)";
            stmt.executeUpdate(tablaRol);
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Creamos el metodo para guardar rol en mysql

    public void guardarRolEnMysql(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            String mysql = "INSERT IGNORE INTO Rol (tipoRol,descripcion) VALUES (?,?)"; //se guarda en cada posición de ?
            PreparedStatement ps = con.prepareStatement(mysql);
            for (Rol r: roles) {
                ps.setString(1, r.getTipoRol());
                ps.setString(2, r.getDescripcionRol());
                ps.executeUpdate();
            }
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
     
    }

    // Creamos el metoddo para mostrar los roles guardados en mysql

    public void mostrarRolEnMysql(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Rol";  // también podés poner solo "categoria"
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Listado de Roles:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String tipoRol = rs.getString("tipoRol");
                String descripcion = rs.getString("descripcion");
                

                System.out.println("ID: " + id + " Nombre: " + tipoRol + "Descripcion:"+ descripcion); 
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error al mostrar los mapas: " + e.getMessage());
        }
        
}

    // Creamos el metodo para traer los datosde la base de datos

    public void traerRolDeMysql(){
        try {
            vista.mostrarTexto("Iniciando sistema");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Rol";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String rol = rs.getString("tipoRol");
                String descripcion = rs.getString("descripcion");
                Rol r = new Rol(rol, descripcion);
                roles.add(r);
            }
            rs.close();
            stmt.close();
            con.close();
            vista.mostrarTexto("El sistema se inicio correctamente");
        } catch (Exception e) {
            System.out.println("Error al mostrar los mapas: " + e.getMessage());
        }
    
}
    
    //------------------------------------------------------------------------------------
    // Metodos para Jugador
    

    // Metodo para crear jugador
    
    public void crearJugador() {

        vista.mostrarTexto("Ingrese el nombre del Jugador");
        String nombre = vista.pedirString();
        vista.mostrarTexto("Ingrese el apellido del jugador");
        String apellido = vista.pedirString();
        vista.mostrarTexto("Ingrese el tag del jugador");
        String tag = vista.pedirString();
        vista.mostrarTexto("Ingrese la fecha de alta del jugador aa-mm-dd");
        String fecha = vista.pedirString();
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
        Jugador j = new Jugador(nombre, apellido, tag, fechaCreacion, precio, kills, asistencia, dead);
        jugadores.add(j);
        vista.mostrarTexto("Se agrego el jugador " + j.getTag() + " a la lista de jugadores");

    }
    
    // Metodo para crear la tabla jugador en mysql
    
    public void crearTablaJugadorEnMysql(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            Statement stmt = con.createStatement();
            String tablaJugador = "CREATE TABLE IF NOT EXISTS Jugador ("
                    + "nombre varchar(50) not null, "
                    + "apellido VARCHAR(50) not null,"
                    + "tag varchar(100) primary key unique not null,"
                    + "fecha Date,"
                    + "precio double not null,"
                    + "kills int,"
                    + "asistencia int,"
                    + "dead int )";
            stmt.executeUpdate(tablaJugador);
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
}

    // Creamos el metodo para guardar datos en la tabla jugador en mysql

    public void guardarJugadorEnMysql(){
    
     try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            String mysql = "INSERT IGNORE INTO Jugador (nombre,apellido,tag,fecha,precio,kills,asistencia,dead) VALUES (?,?,?,?,?,?,?,?)"; //se guarda en cada posición de ?
            PreparedStatement ps = con.prepareStatement(mysql);
            for (Jugador j: jugadores) {
                ps.setString(1, j.getNombre());
                ps.setString(2, j.getApellido());
                ps.setString(3,j.getTag());
                ps.setDate(4,java.sql.Date.valueOf(j.getFecha()));
                ps.setDouble(5, j.getPrecio());
                ps.setInt(6, j.getKills());
                ps.setInt(7,j.getAsistencia());
                ps.setInt(8,j.getDead());
                ps.executeUpdate();
            }
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    
}

    // Creamos el metodo para mostrar los jugadores de Mysql
    
    public void mostrarJugadorDeMysql(){
        try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        Statement stmt = con.createStatement();

        String sql = "SELECT * FROM Jugador";
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("Listado de jugadores:");
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String tag = rs.getString("tag");
            Date fecha = rs.getDate("fecha"); // java.sql.Date
            double precio = rs.getDouble("precio");
            int kills = rs.getInt("kills");
            int asistencia = rs.getInt("asistencia");
            int dead = rs.getInt("dead");

            System.out.println("Tag: " + tag + " | Nombre: " + nombre + " " + apellido);
            System.out.println("Fecha de nacimiento: " + fecha + " | Precio: $" + precio);
            System.out.println("Kills: " + kills + " | Asistencias: " + asistencia + " | Muertes: " + dead);
            System.out.println("---------------------------------------------");
        }

        rs.close();
        stmt.close();
        con.close();
    } catch (Exception e) {
        System.out.println("Error al mostrar los jugadores: " + e.getMessage());
    }
    
    }

    // Creamos el metodo para poder traer los datos de mysql

    public void traerJugadorDeMysql(){
        try {
            vista.mostrarTexto("Trayendo jugadores de la base de datos");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Jugador";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String tag = rs.getString("tag");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                Double precio = rs.getDouble("precio");
                int kills = rs.getInt("kills");
                int asistencia = rs.getInt("asistencia");
                int dead = rs.getInt("dead");
                Jugador j = new Jugador(nombre,apellido,tag,fecha,precio,kills,asistencia,dead);
                jugadores.add(j);
            }
            rs.close();
            stmt.close();
            con.close();
            vista.mostrarTexto("Se cargo exitosamente los jugadores de la base de datos");
        } catch (Exception e) {
            System.out.println("Error al mostrar los mapas: " + e.getMessage());
        }


}
    
    // Creamos el metodo para buscar un jugador de la lista por su tag
    
    public Jugador buscarJugador(String tag) {
        for (Jugador j : jugadores) {
            if (tag.equals(j.getTag())) {
                return j;
            }
        }
        return null;

    }
    
    //------------------------------------------------------------------------------------
    // Metodos para Torneo
  

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
        
        vista.mostrarTexto("Ingrese la fecha del torneo");
        String fecha = vista.pedirString();

        LocalDate fechaLD = LocalDate.parse(fecha);

        // metodo para agregar torneo
        Torneo t = new Torneo(nombre, ubicacion, ganador, mvp, premio,fechaLD);
        torneos.add(t);
        vista.mostrarTexto("Se agrego torneo.");
    }

    // Creamo  el metodo para crear tabla Torneo en base de datos
    public void crearTablaTorneoEnMySql() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            Statement stmt = con.createStatement();
            String tablaTorneo = "CREATE TABLE IF NOT EXISTS Torneo ("
                    + "id int PRIMARY KEY AUTO_INCREMENT, "
                    + "ubicacion varchar(50),"
                    + "mvp varchar(50),"
                    + "nombre VARCHAR(50) unique not null,"
                    + "fecha Date not null,"
                    + "premio double not null,"
                    + "cantidadEquipos int not null,"   
                    + "ganador varchar(50),"
                    + "FOREIGN KEY(mvp) REFERENCES jugador(tag),"
                    + "FOREIGN KEY(ganador) references equipo(nombre))";
            stmt.executeUpdate(tablaTorneo);
            con.close();
        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    // Creamos el metodo para mostrar los torneos guardados
    public void mostrarTorneosGuardados() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM Torneos";  // también podés poner solo "categoria"
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Listado de torneos:");
            while (rs.next()) {
                
                String nombre = rs.getString("nombre");
                String ubicacion = rs.getString("ubicacion");
                double premio = rs.getDouble("premio");
                Date fecha = rs.getDate("fecha");
                int cantEquipo = rs.getInt("cantidadEquipos");
                String mvp = rs.getString("mvp");

                System.out.println("Nombre: "+ nombre + "Ubicacion: " + ubicacion + " Premio: "+ premio);
                System.out.println("Fecha" + fecha + "Cantidad Equipos: " + cantEquipo + "Mvp: " + mvp);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error al mostrar los mapas: " + e.getMessage());
        }

    }

    // Creamos el metodo para guardar los datos en mysql.
    public void guardarTorneosEnMysql() {
        try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
        String mysql = "INSERT INTO torneo (ubicacion, mvp, nombre, fecha, premio, cantidadEquipos, ganador) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(mysql);

        for (Torneo t : torneos) {
            ps.setString(1, t.getUbicacion());
            ps.setString(2, t.getMvp() != null ? t.getMvp().getTag() : null);
            ps.setString(3, t.getNombre());
            ps.setDate(4, java.sql.Date.valueOf(t.getFecha())); 
            ps.setDouble(5, t.getPremioTotal());
            ps.setInt(6, t.getEquipos().size());
            ps.setString(7, t.getGanador() != null ? t.getGanador().getNombre() : null);

            ps.executeUpdate();
        }

        ps.close();
        con.close();
        System.out.println("Torneos guardados correctamente en la base de datos.");
    } catch (Exception e) {
        System.out.println("Error al guardar torneos en MySQL: " + e.getMessage());
    }
}
    
    // Creamos el metodod para traer los torneos desde la base de datos
    
    public void traerTorneosBD(List<Torneo> torneos) {
//
        try {
            vista.mostrarTexto("Iniciando sistema");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDProyecto", "root", "Admin123!");
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM torneo";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String ubicacion = rs.getString("ubicacion");
                String vmp = rs.getString("mvp");
                String ganador = rs.getString("ganador");
                Jugador j = buscarJugador(vmp);
                Equipo e = buscarEquipo(ganador);
                Date fecha = rs.getDate("fecha");
                double premio = rs.getDouble("premio");
                LocalDate fechaLD = fecha.toLocalDate();
                
                Torneo t = new Torneo(nombre,ubicacion,e,j,premio,fechaLD);
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
 
    // Creamos el metodo para estimar el procentaje de victoria de un equipo
    
    public void simularEnfrentamientoEstimacion(Equipo e1, Equipo e2){
        double kda1 = e1.getJugadoresEquipo().stream().mapToDouble(Jugador::getKda).sum();
        double kda2 = e2.getJugadoresEquipo().stream().mapToDouble(Jugador::getKda).sum();
        double total = kda1 + kda2;
    if (kda1 > kda2) {
        System.out.println("El equipo " + e1.getNombre() + "gana con un  " + (kda1 * 100)/total );
    } else if (kda2 > kda1) {
        System.out.println("El equipo " + e2.getNombre() + "gana con un  " + (kda2 * 100)/total );
    } else {
        System.out.println("Los equipos tienen la misma probabiliad de ganar");
    }
    }
    
    
    // Creamos el metodo para devolcer el equipo ganador luego de un enfrentamiento
    
    public Equipo simularEnfrentamiento(Equipo e1, Equipo e2) {
        double kda1 = e1.getJugadoresEquipo().stream().mapToDouble(Jugador::getKda).sum();
        double kda2 = e2.getJugadoresEquipo().stream().mapToDouble(Jugador::getKda).sum();

        if (kda1 > kda2) {
            System.out.println("El equipo ganador es " + e1.getNombre());
            return e1;
        } else if (kda2 > kda1) {
            System.out.println("El equipo ganador es " + e2.getNombre());
        } else {
            System.out.println("El ganador es aleatorio tienen las mismas probabilidades");
            Equipo e =  Math.random() < 0.5 ? e1 : e2;
            return e;
        }
        return null;
    }
    
    public void simularTorneo(Torneo torneo) {
    if (torneo.getEquipos().size() < 8) {
        vista.mostrarTexto("No se puede simular el torneo porque se dispone de pocos equipos");
        return;
    }

    // Mezclar equipos para que el emparejamiento sea aleatorio
    List<Equipo> rondaActual = new ArrayList<>(torneo.getEquipos());
    Collections.shuffle(rondaActual);

    // Mapa para registrar posiciones: 1 = campeón, ..., 8 = eliminado primera ronda
    Map<Integer, Equipo> posiciones = new HashMap<>();
    int posicionActual = rondaActual.size();

    // Simulación de rondas
    while (rondaActual.size() > 1) {
        List<Equipo> ganadores = new ArrayList<>();
        List<Equipo> eliminados = new ArrayList<>();

        for (int i = 0; i < rondaActual.size(); i += 2) {
            Equipo e1 = rondaActual.get(i);
            Equipo e2 = rondaActual.get(i + 1);

            // Estimación previa
            simularEnfrentamientoEstimacion(e1, e2);

            // Resultado final
            Equipo ganador = simularEnfrentamiento(e1, e2);
            Equipo perdedor = (ganador == e1) ? e2 : e1;

            ganadores.add(ganador);
            eliminados.add(perdedor);

            vista.mostrarTexto(" Avanza: " + ganador.getNombre());
        }

        for (Equipo eliminado : eliminados) {
            posiciones.put(posicionActual, eliminado);
            posicionActual--;
        }

        rondaActual = ganadores;
    }

    // El último que queda es el campeón
    Equipo campeon = rondaActual.get(0);
    posiciones.put(1, campeon);
    torneo.setGanador(campeon);

    vista.mostrarTexto("El campeón del torneo es: " + campeon.getNombre());

    // MVP del equipo campeón
    Jugador mvp = campeon.getJugadoresEquipo().stream()
            .max(Comparator.comparingDouble(Jugador::getKda))
            .orElse(null);

    torneo.setMvp(mvp);
    vista.mostrarTexto("MVP del torneo: " + mvp.getNombre() + " con KDA: " + mvp.getKda());

    // Sumar un torneo ganado al campeón
    campeon.setCantidadDeTorenoGanadador(campeon.getCantidadDeTorenoGanadador() + 1);

    // Mostrar posiciones finales
    vista.mostrarTexto("Tabla de posiciones del torneo:");
    for (int i = 1; i <= posiciones.size(); i++) {
        Equipo eq = posiciones.get(i);
        if (eq != null) {
            vista.mostrarTexto(i + "° - " + eq.getNombre());
        }
    }
}
    
    public Equipo seleccionarEquipoDeLista(List<Equipo> equipos) {
    if (equipos == null || equipos.isEmpty()) {
        vista.mostrarTexto("No hay equipos disponibles.");
        return null;
    }

    vista.mostrarTexto("Lista de equipos:");
    for (int i = 0; i < equipos.size(); i++) {
        vista.mostrarTexto((i + 1) + ". " + equipos.get(i).getNombre());
    }

    vista.mostrarTexto("Ingrese el numero del equipo que desea seleccionar:");
    int opcion = vista.pedirInt();  // Asegurate de tener este método en tu clase `vista`

    if (opcion >= 1 && opcion <= equipos.size()) {
        return equipos.get(opcion - 1);
    } else {
        vista.mostrarTexto("Opción no válida.");
        return null;
    }
}
    
    
    public Torneo seleccionarTorneoDeLista(List<Torneo> torneos) {
    if (torneos == null || torneos.isEmpty()) {
        vista.mostrarTexto("No hay torneos disponibles.");
        return null;
    }

    vista.mostrarTexto("Lista de torneos:");
    for (int i = 0; i < torneos.size(); i++) {
        vista.mostrarTexto((i + 1) + ". " + torneos.get(i).getNombre());
    }

    vista.mostrarTexto("Ingrese el numero del torneo que desea seleccionar:");
    int opcion = vista.pedirInt();

    if (opcion >= 1 && opcion <= torneos.size()) {
        return torneos.get(opcion - 1);
    } else {
        vista.mostrarTexto("Opcion no valida.");
        return null;
    }
}


    
}