# Usar una imagen base de Java
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el JAR generado por Maven/Gradle
COPY target/ticketmanagement-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto usado por la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]