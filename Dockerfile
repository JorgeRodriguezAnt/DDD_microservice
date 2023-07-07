FROM openjdk:17-jdk-slim

# Instala Git en el contenedor
RUN apt-get update && apt-get install -y git

# Copia tu archivo JAR al contenedor
COPY "./target/generate-ms-0.0.1-SNAPSHOT.jar" "app.jar"


# Inicializa el repositorio Git
RUN git init

# Expone el puerto 8080
EXPOSE 8080

# Ejecuta tu aplicación Java
ENTRYPOINT ["java", "-jar", "app.jar"]