package aplicacion;

import modelo.Reunion;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class ManejadorReuniones {

    private List<Reunion> reuniones;
    private final File archivo;

    public ManejadorReuniones(File archivo) {
        this.archivo = archivo;
        this.reuniones = new ArrayList<>();
        cargar();
    }

    public List<Reunion> getReuniones() {
        return reuniones;
    }

    public void agregarReunion(Reunion r) {
        reuniones.add(r);
        guardar();
    }

    public void modificarReunion(int indice, Reunion r) {
        if (indice >= 0 && indice < reuniones.size()) {
            reuniones.set(indice, r);
            guardar();
        } else {
            System.err.println("Índice fuera de rango: no se pudo modificar la reunión.");
        }
    }

    private void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(reuniones);
        } catch (IOException e) {
            System.err.println("Error guardando archivo de reuniones: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void cargar() {
        reuniones.clear();
        if (!archivo.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            reuniones = (List<Reunion>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("No se pudo cargar archivo de reuniones. Se usará una lista vacía.");
        }
    }
}
