# ETAPA 1: 
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ETAPA 2: Ejecución 
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copiamos el .jar que se generó en la etapa 1
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]