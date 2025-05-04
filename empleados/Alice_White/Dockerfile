FROM openjdk:17

WORKDIR /app

# Copiamos los archivos del proyecto de Alicia
COPY ./src/aplicacion/*.java ./src/aplicacion/
COPY ./src/modelo/*.java ./src/modelo/

# Compilamos los .java dentro del contenedor
RUN javac -d . src/aplicacion/*.java src/modelo/*.java

# Exponemos el puerto de este empleado
EXPOSE 8081

# Comando de inicio
CMD ["java", "aplicacion.ClienteEmpleado"]
