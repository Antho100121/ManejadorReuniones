package aplicacion;

import java.io.File;

public class ClienteEmpleado {

    public static void main(String[] args) {
        int puerto = 8083;
        String archivoReuniones = "reuniones_Carol.txt";

        ManejadorReuniones manejador = new ManejadorReuniones(new File(archivoReuniones));
        ServidorEmpleado servidor = new ServidorEmpleado(puerto, manejador);

        servidor.escuchar();
    }
}