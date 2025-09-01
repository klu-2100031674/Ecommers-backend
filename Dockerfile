# -------------------------
# Stage 1: Build
# -------------------------
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy only pom.xml and Maven wrapper first for caching dependencies
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Give execution permission to mvnw
RUN chmod +x mvnw

# Download project dependencies (cached separately)
RUN ./mvnw dependency:go-offline -B

# Copy the rest of the source code
COPY src ./src

# Build the application (skip tests to speed up builds)
RUN ./mvnw clean package -DskipTests

# -------------------------
# Stage 2: Run
# -------------------------
FROM eclipse-temurin:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your app uses
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java","-jar","app.jar"]
