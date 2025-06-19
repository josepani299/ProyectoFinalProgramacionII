// crear la clase Personaje. Sus atributos: rol, nombre y descripcion.
public class Personaje {
    private String rol;
    private String nombre;
    private String descripcion;

    public Personaje(String rol, String nombre, String descripcion) {
        this.rol = rol;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    @Override
    public String toString() {
        return "Personaje{" +
                "rol='" + rol + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
// los metodos: +crearPersonaje() +actualizarDatos()
    public void crearPersonaje(String rol, String nombre, String descripcion) {
        this.rol = rol;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public void actualizarDatos(String nuevoRol, String nuevoNombre, String nuevaDescripcion) {
        this.rol = nuevoRol;
        this.nombre = nuevoNombre;
        this.descripcion = nuevaDescripcion;
    }
}           