
# Use the official Maven image to build the application
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests




FROM openjdk:18
COPY target/auth_test-0.0.1-SNAPSHOT.jar /auth_test-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/auth_test-0.0.1-SNAPSHOT.jar"]