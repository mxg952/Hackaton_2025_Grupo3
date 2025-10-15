# Etapa 1: Construcción del proyecto con Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Directorio de trabajo
WORKDIR /app

# Copiar archivos de Maven y el código fuente
COPY pom.xml .
COPY src ./src

# Compilar y empaquetar el proyecto, sin ejecutar tests
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final para ejecutar la aplicación
FROM eclipse-temurin:21-jre

# Crear directorio de trabajo
WORKDIR /app

# Copiar el .jar generado desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto por defecto de Spring Boot
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
