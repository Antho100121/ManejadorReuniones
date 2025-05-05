package aplicacion;

import java.io.File;

public class ClienteEmpleado {

    public static void main(String[] args) {
        int puerto = 8082;
        String archivoReuniones = "reuniones_Bob.txt";

        ManejadorReuniones manejador = new ManejadorReuniones(new File(archivoReuniones));
        ServidorEmpleado servidor = new ServidorEmpleado(puerto, manejador);

        servidor.escuchar();
    }
}