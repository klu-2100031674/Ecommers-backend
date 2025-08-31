# Use an official JDK runtime as a base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy Maven wrapper and project files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Give execution permission to mvnw
RUN chmod +x mvnw

# Download project dependencies (cached separately for faster builds)
RUN ./mvnw dependency:go-offline -B

# Copy the rest of the source code
COPY src src

# Package the application
RUN ./mvnw clean package -DskipTests

# Expose Render's dynamic port
EXPOSE 8080

# Run the packaged JAR file
CMD ["sh", "-c", "java -jar target/*.jar"]
