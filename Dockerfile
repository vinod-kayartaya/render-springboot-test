# Build stage
FROM maven:3.8.4-openjdk-11-slim AS builder

# Set working directory
WORKDIR /build

# Copy pom.xml and source code
COPY pom.xml .
COPY src src/

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:11-jre-slim

# Set working directory
WORKDIR /app

# Copy the JAR from builder stage
COPY --from=builder /build/target/render-springboot-test-0.0.1.jar app.jar

# Create a volume for SQLite database
VOLUME /app/data

# Environment variables
ENV SPRING_DATASOURCE_URL=jdbc:sqlite:/app/data/health_monitor.db
ENV HEALTH_CHECK_URL=http://localhost:8080/actuator/health

# Expose port 8080
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"] 