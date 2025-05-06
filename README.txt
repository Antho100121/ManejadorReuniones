# ManejadorReuniones
Aplicación en Java para gestionar reuniones corporativas entre empleados.
README - Sistema de Gestión de Reuniones Distribuido

Descripción del sistema

    Este sistema simula un entorno distribuido para gestionar reuniones empresariales. Cada empleado (Alice, Bob, Carol, David y Eva) ejecuta su propio cliente Java dentro de un contenedor Docker y se comunica con un servidor central que coordina las reuniones.

Estructura del proyecto

├── empleados/
│   ├── Alice_White/
│   ├── Bob_Smith/
│   ├── Carol_Simpson/
│   ├── David_Black/
│   └── Eva_Brown/
│       └── Dockerfile
│       └── input.txt
│       └── src/
│           ├── aplicacion/
│           └── modelo/
├── central-server/
│   └── Dockerfile
│   └── src/
│       └── servidorcentral/
│       └── modelo/
│   └── employees.properties
├── docker-compose.yml

Requisitos

    Docker

    Docker Compose

Instrucciones de Ejecución

    Clona este repositorio:

        git clone <https://github.com/Antho100121/ManejadorReuniones>
        cd <directorio-proyecto>

    Ejecuta los contenedores:

        docker-compose up --build

    Esto compilará todos los servicios y ejecutará automáticamente:

        El servidor central

        Los clientes de cada empleado (Alice, Bob, Carol, David y Eva)

    Monitorea los logs para ver la interacción entre los empleados y el servidor central:

        docker-compose logs -f

    Para detener el sistema:

        docker-compose down --remove-orphans

Pruebas del Sistema

    El archivo input.txt en cada contenedor simula la interacción del usuario con el menú de reuniones. Se automatizan las siguientes acciones:

        Ver reuniones

        Crear reunión (con datos de prueba)

        Modificar reunión

        Salir

Notas

    Los contenedores esperan a que el servidor central esté disponible antes de intentar conectarse.

    Los puertos del servidor central están expuestos solo internamente en la red de Docker.

