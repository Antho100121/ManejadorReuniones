package aplicacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import modelo.Reunion;

public class ClienteEmpleado {
    //atributos
    private String servidorHost;
    private int servidorPuerto;

    //constructor
    ClienteEmpleado(String host, int puerto){
        this.servidorHost = host;
        this.servidorPuerto = puerto;
    }

    //metodos
    public void enviarCambios(Reunion r) {
        try (Socket socket = new Socket(servidorHost, servidorPuerto);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            // out.println(r.serialize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
