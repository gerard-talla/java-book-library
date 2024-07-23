# Java-Book-Library
Demo project for Java-Book-Library with Gradle

## Authors
* **Gerard Talla Tchano** - [Linkedin Page](https://www.linkedin.com/in/gerard-talla-tchano-a028a8135/)

## Setup
### Requirements
* Should use Java 17 or higher.
* Use Gradle 9.0 or higher

### Running locally the application on commandline
To run the project locally. it is required to run the command in cli as following:
* `./gradlew clean openApiGenerate`
Generate code via Open API Tools Generator for Open API 3.0.3 specification documents form the contract that we define first.
* `./gradlew build`: to make sure the project compile and build as expected and generate the artifacts
* `./gradlew run`: to start the project.

#### Running the application with docker compose

This project contains a Docker Compose file named `compose.yaml`.
In this file, the following services have been defined:

* java-book-library-api: Implemented Api
* postgresql: [`postgres:latest`](https://hub.docker.com/_/postgres) as database
* `docker compose up`: To start and run an entire app on a standalone host that contains multiple services
