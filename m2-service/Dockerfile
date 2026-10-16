# Stage 1: Build the Java application using Gradle and Java 21
FROM gradle:8-jdk21 AS build
WORKDIR /app
COPY . .
RUN chmod +x gradlew && ./gradlew clean build -x test

# Stage 2: Create a minimal execution runtime environment for Java 21
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Copies your freshly built app from Stage 1 into Stage 2
COPY --from=build /app/build/libs/*.jar app.jar

# Render forces web services to expose a port. 8080 is standard.
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
