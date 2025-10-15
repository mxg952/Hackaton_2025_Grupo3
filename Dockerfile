# Etapa 1: Construcción del proyecto con Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen para desarrollo
FROM eclipse-temurin:21-jre-jammy

# Instalar curl para health check (opcional pero útil)
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Variables de entorno específicas para H2 en Docker
ENV SPRING_H2_CONSOLE_ENABLED=true
ENV SPRING_H2_CONSOLE_SETTINGS_WEB_ALLOW_OTHERS=true
ENV SERVER_ADDRESS=0.0.0.0

# Health check mejorado
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || curl -f http://localhost:8080/api/formularios || exit 1

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]