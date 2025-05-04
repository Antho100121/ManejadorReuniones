# Dockerfile for Employee Server
FROM openjdk:17
WORKDIR /app
COPY ./src/EmployeeServer.java .
RUN javac EmployeeServer.java
EXPOSE 8081
CMD ["java", "EmployeeServer"]
