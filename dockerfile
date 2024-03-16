# Build stage
FROM maven:3.8.1-jdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Package stage
FROM openjdk:18
EXPOSE 8080
COPY --from=build /app/target/auth_test-0.0.1-SNAPSHOT.jar auth_test-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","auth_test-0.0.1-SNAPSHOT.jar"]