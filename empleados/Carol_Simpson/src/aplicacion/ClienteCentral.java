package aplicacion;

import modelo.Reunion;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClienteCentral {

    public static void enviar(int puertoDestino, Reunion reunion) {
        try (Socket socket = new Socket("central-server", 9090);
             OutputStream os = socket.getOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(os)) {

            oos.writeObject(reunion);
            oos.flush();
            System.out.println("📨 Reunión enviada al servidor central");

        } catch (Exception e) {
            System.err.println("❌ Error al enviar al servidor central:");
            e.printStackTrace();
        }
    }
}
