package aplicacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.ArrayList;

import modelo.Reunion;

public class ManejadorReuniones {
    //atributos
    private ArrayList<Reunion> reuniones;
    private File archivo;

    //constructor
    ManejadorReuniones(File archivo){
        this.archivo = new File("reunios_Alice.txt");
        this.reuniones = new ArrayList<>();
        cargar();
    }

    //metodos
    public ArrayList<Reunion> getReuniones() {
        return reuniones;
    }

    public void agregarReunion(Reunion r){
        reuniones.add(r);
        guardar();
    }

    public void modificarReunion(Reunion r){
        //metodos de reunion
        reuniones.add(r);
        guardar();
    }

    public void guardar(){
        try (PrintWriter out = new PrintWriter(archivo)) {
            for (Reunion r : reuniones) {
                // out.println(r.serialize());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargar() {
        reuniones.clear();
        if (!archivo.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // reuniones.add(Reunion.deserialize(linea));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
