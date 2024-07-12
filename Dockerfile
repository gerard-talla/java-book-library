# Stage 1: Build stage
FROM gradle:7.3.3-jdk11 AS build

LABEL maintainer="Gerard Talla <gerard.tchano@gmail.com>"

# Set the working directory inside the container
WORKDIR /home/gradle/project

# Copy only the necessary files first to leverage Docker layer caching
COPY build.gradle settings.gradle gradlew ./
#  Copy the Gradle wrapper directory into the container.
COPY gradle ./gradle

# Download dependencies (Docker will cache these layers, speeding up subsequent builds)
RUN ./gradlew build -x test --no-daemon

# Copy the source code
COPY src ./src

# Build the application
RUN ./gradlew build --no-daemon

# Stage 2: Run stage
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the previous stage
COPY --from=build /home/gradle/project/build/libs/*.jar .

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "java-book-library.jar"]
