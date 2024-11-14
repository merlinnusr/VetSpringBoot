# Build stage
FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /app

# Copy only pom.xml first to cache dependencies
COPY ./pom.xml .
COPY ./src ./src

# Build the application
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Create a non-root user
RUN useradd -r -u 1001 -g root springuser
USER springuser

# Copy the built artifact from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Environment variables
ENV JAVA_OPTS=""

# Health check
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Container configuration
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]