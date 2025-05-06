package servidorcentral;

import modelo.Reunion;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


public class ServidorCentral {
    public static void main(String[] args) {
        int puerto = 9090;

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("🛰️ Servidor Central iniciado en puerto " + puerto);

            while (true) {
                Socket cliente = serverSocket.accept();
                new Thread(() -> manejarConexion(cliente)).start();
            }
        } catch (Exception e) {
            System.err.println("❌ Error en el servidor central:");
            e.printStackTrace();
        }
    }

    private static void manejarConexion(Socket cliente) {
        try (ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream())) {
            System.out.println("📥 Nueva conexión recibida...");

            Reunion reunion = (Reunion) ois.readObject();
            System.out.println("✅ Reunión recibida: " + reunion);

            // Aquí puedes agregar lógica para almacenar o reenviar la reunión

        } catch (Exception e) {
            System.err.println("❌ Error al manejar conexión:");
            e.printStackTrace();
        } finally {
            try {
                cliente.close();
            } catch (Exception ex) {
                System.err.println("❗ Error al cerrar el socket del cliente.");
            }
        }
    }

    public static void reenviarReunion(Reunion reunion, List<String> empleados, List<Integer> puertos) {
        for (int i = 0; i < empleados.size(); i++) {
            String host = empleados.get(i).toLowerCase(); // nombre del contenedor en Docker
            int puerto = puertos.get(i);

            System.out.println("📤 Notificando a " + empleados.get(i) + " en puerto " + puerto);
            try (Socket socket = new Socket(host, puerto);
                 ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {

                oos.writeObject(reunion);
                System.out.println("✔ Reunión reenviada a " + empleados.get(i));

            } catch (Exception e) {
                System.err.println("❌ Error al enviar reunión al puerto " + puerto + ": " + e.getMessage());
            }
        }
    }
}
