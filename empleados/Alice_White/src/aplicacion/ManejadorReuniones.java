package aplicacion;

import modelo.Reunion;

import java.util.ArrayList;
import java.util.List;

public class ManejadorReuniones {

    private final List<Reunion> reuniones = new ArrayList<>();
    private final String nombreEmpleado;

    public ManejadorReuniones(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public void agregarReunion(Reunion r) {
        reuniones.add(r);
    }

    public void modificarReunion(int index, Reunion r) {
        if (index >= 0 && index < reuniones.size()) {
            reuniones.set(index, r);
        }
    }

    public List<Reunion> getReuniones() {
        return reuniones;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }
}
