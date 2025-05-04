# Imagen base con JDK
FROM openjdk:17

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar clases compartidas desde el central-server
COPY ../../central-server/src/modelo/*.java ./src/modelo/

# Copiar el código fuente del empleado Carol_Simpson
COPY ./src/aplicacion/*.java ./src/aplicacion/

# Compilar el código fuente (modelo + aplicacion)
RUN javac -d . src/modelo/*.java src/aplicacion/*.java

# Exponer el puerto correspondiente a Carol_Simpson
EXPOSE 8083

# Comando por defecto para ejecutar el cliente
CMD ["java", "aplicacion.ClienteEmpleado"]
