# Build stage
FROM maven:3.8.1-jdk-17 AS build
COPY . .
RUN mvn --version

# Package stage
FROM openjdk:17-jdk
EXPOSE 8080
COPY --from=build auth_test/target/auth_test-0.0.1-SNAPSHOT.jar auth_test-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","auth_test-0.0.1-SNAPSHOT.jar"]