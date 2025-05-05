package modelo;

public class Empleado {
    private String nombre;
    private int puerto;

    public Empleado(String nombre, int puerto) {
        this.nombre = nombre;
        this.puerto = puerto;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuerto() {
        return puerto;
    }
}