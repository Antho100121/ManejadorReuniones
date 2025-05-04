package aplicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import modelo.Reunion;

public class ServidorEmpleado {
    private int puerto;
    private ManejadorReuniones manejador;

    public ServidorEmpleado(int puerto, ManejadorReuniones manejador) {
        this.puerto = puerto;
        this.manejador = manejador;
    }

    public void escuchar() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            while (true) {
                try (Socket cliente = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()))) {

                    String linea = in.readLine();
                    // Reunion r = Reunion.deserialize(linea);
                    // manejador.modificarReunion(r);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

