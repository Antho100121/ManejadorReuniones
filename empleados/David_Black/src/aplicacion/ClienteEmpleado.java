package aplicacion;

import modelo.Reunion;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.*;

public class ClienteEmpleado {

    public static void main(String[] args) {
        String nombreEmpleado = "David_Black";
        String rutaInput = "empleados/" + nombreEmpleado + "/input.txt";
        Scanner scanner;

        try {
            scanner = new Scanner(new File(rutaInput));
        } catch (FileNotFoundException e) {
            System.out.println("❌ No se encontró el archivo de entrada en: " + rutaInput);
            return;
        }

        esperarServidorDisponible();

        ManejadorReuniones manejador = new ManejadorReuniones(nombreEmpleado);

        boolean salir = false;
        while (!salir && scanner.hasNextLine()) {
            System.out.println("👋 Bienvenido, " + nombreEmpleado);
            System.out.println("1. Ver reuniones");
            System.out.println("2. Crear nueva reunión");
            System.out.println("3. Modificar una reunión");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    List<Reunion> reuniones = manejador.getReuniones();
                    if (reuniones.isEmpty()) {
                        System.out.println("❗ No hay reuniones.");
                    } else {
                        for (int i = 0; i < reuniones.size(); i++) {
                            System.out.println((i + 1) + ". " + reuniones.get(i));
                        }
                    }
                    break;

                case "2":
                    Reunion nueva = crearReunion(scanner, nombreEmpleado);
                    manejador.agregarReunion(nueva);
                    enviarAlServidorCentral(nueva);
                    break;

                case "3":
                    List<Reunion> todas = manejador.getReuniones();
                    if (todas.isEmpty()) {
                        System.out.println("❗ No hay reuniones para modificar.");
                        break;
                    }
                    for (int i = 0; i < todas.size(); i++) {
                        System.out.println((i + 1) + ". " + todas.get(i));
                    }
                    System.out.print("Seleccione el número de la reunión a modificar: ");
                    int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
                    Reunion modificada = crearReunion(scanner, nombreEmpleado);
                    manejador.modificarReunion(index, modificada);
                    enviarAlServidorCentral(modificada);
                    break;

                case "4":
                    salir = true;
                    System.out.println("👋 Saliendo...");
                    break;

                default:
                    System.out.println("❗ Opción no válida.");
            }
        }
    }

    private static Reunion crearReunion(Scanner scanner, String organizador) {
        System.out.print("Tema: ");
        String tema = scanner.nextLine();

        System.out.print("Organizador: ");
        String org = scanner.nextLine();

        System.out.print("Invitados (separados por coma): ");
        String[] invitados = scanner.nextLine().split(",");

        System.out.print("Lugar: ");
        String lugar = scanner.nextLine();

        System.out.print("Inicio (formato 2025-05-05T14:00): ");
        LocalDateTime inicio = LocalDateTime.parse(scanner.nextLine());

        System.out.print("Fin (formato 2025-05-05T15:00): ");
        LocalDateTime fin = LocalDateTime.parse(scanner.nextLine());

        return new Reunion(tema, org, Arrays.asList(invitados), lugar, inicio, fin);
    }

    private static void enviarAlServidorCentral(Reunion reunion) {
        final int MAX_INTENTOS = 3;

        for (int intento = 1; intento <= MAX_INTENTOS; intento++) {
            try (Socket socket = new Socket("central-server", 9090);
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

                out.writeObject(reunion);
                System.out.println("📤 Reunión enviada al servidor central.");
                return;

            } catch (IOException e) {
                System.out.println("⚠️ Intento " + intento + " fallido al enviar al servidor central: " + e.getMessage());
                if (intento < MAX_INTENTOS) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    System.out.println("❌ Todos los intentos fallaron.");
                    e.printStackTrace();
                }
            }
        }
    }

    private static void esperarServidorDisponible() {
        int intentos = 10;
        while (intentos > 0) {
            try (Socket s = new Socket("central-server", 9090)) {
                return;
            } catch (IOException e) {
                System.out.println("⏳ Esperando que el servidor central esté disponible...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            intentos--;
        }
        System.out.println("❌ No se pudo conectar con el servidor central tras varios intentos.");
    }
}
