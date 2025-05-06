FROM openjdk:17

WORKDIR /app

COPY src/aplicacion/*.java ./src/aplicacion/
COPY src/modelo/*.java ./src/modelo/
COPY input.txt .

RUN javac -d . src/aplicacion/*.java src/modelo/*.java

CMD ["sh", "-c", "java aplicacion.ClienteEmpleado < input.txt"]
