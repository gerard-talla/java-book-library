# Stage 1: Build stage
FROM gradle:7.3.3-jdk17 AS build

LABEL maintainer="Gerard Talla <gerard.tchano@gmail.com>"

# Set the working directory inside the container
WORKDIR /home/gradle/java-book-library

# Copy only the necessary files first to leverage Docker layer caching
COPY build.gradle settings.gradle gradlew ./
#  Copy the Gradle wrapper directory into the container.
COPY gradle ./gradle

# Copy the source code
COPY src ./src

# Copy the contract
COPY contracts ./contracts

# Generate code via Open API Tools Generator
RUN ./gradlew OpenApiGenerate --no-daemon

# Build the application
RUN ./gradlew build --no-daemon

# Stage 2: Run stage
FROM openjdk:17-ea-3-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the previous stage
COPY --from=build /home/gradle/java-book-library/build/libs/*.jar java-book-library.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "java-book-library.jar"]
