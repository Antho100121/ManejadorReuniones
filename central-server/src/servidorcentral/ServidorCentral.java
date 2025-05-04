package servidorcentral;

import modelo.Reunion;
import modelo.Empleado;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorCentral {

    private static final int PUERTO = 9090;
    private static Map<String, Empleado> empleados = new HashMap<>();

    public static void main(String[] args) {
        // Usamos la ruta absoluta dentro del contenedor
        cargarEmpleados("/app/employees.properties");
        System.out.println("Servidor Central iniciado en puerto " + PUERTO);

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> manejarConexion(socket)).start();
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor central: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void cargarEmpleados(String archivo) {
        try (InputStream input = new FileInputStream(archivo)) {
            Properties props = new Properties();
            props.load(input);

            for (String nombre : props.stringPropertyNames()) {
                String[] partes = props.getProperty(nombre).split(":");
                int puerto = Integer.parseInt(partes[0]);
                empleados.put(nombre, new Empleado(nombre, puerto));
            }

        } catch (IOException e) {
            System.err.println("Error cargando archivo employees.properties");
            e.printStackTrace();
        }
    }

    private static void manejarConexion(Socket socket) {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Reunion reunion = (Reunion) ois.readObject();
            System.out.println("Reunión recibida: " + reunion);

            reenviarReunion(reunion);

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al manejar conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void reenviarReunion(Reunion reunion) {
        Set<String> destinatarios = new HashSet<>(reunion.getInvitados());
        destinatarios.add(reunion.getOrganizador());

        for (String nombre : destinatarios) {
            if (empleados.containsKey(nombre)) {
                Empleado emp = empleados.get(nombre);
                System.out.println("Notificando a " + nombre + " en puerto " + emp.getPuerto());

                ClienteCentral.enviar(emp.getPuerto(), reunion);
            } else {
                System.out.println("Empleado desconocido: " + nombre);
            }
        }
    }
}
