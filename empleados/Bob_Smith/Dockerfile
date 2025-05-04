# Imagen base con JDK
FROM openjdk:17

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar clases compartidas del modelo desde el central-server
# Asegúrate de que la ruta relativa sea correcta
COPY ../../central-server/src/modelo/*.java ./src/modelo/

# Copiar el código fuente del empleado (aplicación propia)
COPY ./src/aplicacion/*.java ./src/aplicacion/

# Compilar el código fuente (modelo + aplicacion)
RUN javac -d . src/modelo/*.java src/aplicacion/*.java

# Exponer el puerto correspondiente a Bob_Smith (opcional si el cliente escucha)
EXPOSE 8082

# Comando por defecto para ejecutar el cliente
CMD ["java", "aplicacion.ClienteEmpleado"]
