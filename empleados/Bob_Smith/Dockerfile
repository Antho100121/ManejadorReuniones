# Imagen base con JDK
FROM openjdk:17

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos los archivos del proyecto Bob
COPY src/aplicacion/*.java ./src/aplicacion/
COPY src/modelo/*.java  ./src/modelo/

# Compilar el código fuente (modelo + aplicacion)
RUN javac -d . src/aplicacion/*.java src/modelo/*.java


# Exponer el puerto correspondiente a Bob_Smith (opcional si el cliente escucha)
EXPOSE 8082

# Comando por defecto para ejecutar el cliente
CMD ["java", "aplicacion.ClienteEmpleado"]
