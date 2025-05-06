package aplicacion;

import modelo.Reunion;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManejadorReuniones {

    private List<Reunion> reuniones;
    private final File archivo;

    public ManejadorReuniones(String nombreArchivo) {
        this.archivo = new File(nombreArchivo);
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
        }
    }

    private void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(reuniones);
        } catch (IOException e) {
            System.err.println("Error al guardar reuniones: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void cargar() {
        if (!archivo.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            reuniones = (List<Reunion>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("No se pudo cargar reuniones, se usará lista vacía.");
        }
    }
}
