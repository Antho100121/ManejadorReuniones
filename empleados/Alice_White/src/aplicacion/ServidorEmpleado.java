package aplicacion;

import modelo.Reunion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEmpleado {
    private int puerto;
    private ManejadorReuniones manejador;

    public ServidorEmpleado(int puerto, ManejadorReuniones manejador) {
        this.puerto = puerto;
        this.manejador = manejador;
    }

    public void escuchar() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("📡 ServidorEmpleado escuchando en el puerto " + puerto);

            while (true) {
                try (Socket cliente = serverSocket.accept();
                     ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream())) {

                    Reunion r = (Reunion) ois.readObject();
                    System.out.println("Reunión recibida: " + r);
                    manejador.agregarReunion(r);

                } catch (Exception e) {
                    System.err.println("Error al manejar una conexión:");
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor:");
            e.printStackTrace();
        }
    }
}
