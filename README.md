# Userverse 

## Overview
Userverse is a Java-based application that uses Spring Boot and MongoDB. The project is built using Maven and is containerized using Docker. This project provides an API for data analysis.erverse is a Java-based application that uses Spring Boot and MongoDB. The project is built using Maven and is containerized using Docker. This project provides an API for data analysis.

## Prerequisites
- Java 17
- Maven
- Docker (optional, for containerization)

## Configuration
The application configuration is managed through YAML files. The main configuration file is `application.yaml`, and environment-specific configurations can be found in files like `application-dev.yaml`.

### `application.yaml`
```yaml
server:
  port: ${PORT:2000}
spring:
  application:
    name: userverse
  profiles:
    active: ${PROFILE:dev}
```

### `application-dev.yaml`
```yaml
spring:
  data:
    mongodb:
      database: ${MONGODB_DATABASE:userverse}
      uri: ${MONGODB_URI:mongodb://localhost:27017}}
```

### `application-prod.yaml`
```yaml
spring:
  data:
    mongodb:
      database: ${MONGODB_DATABASE}
      uri: ${MONGODB_URI}
```

## Building the Project
To build the project, run the following command:
```sh
./mvnw clean install
```

## Running the Application
To run the application, use the following command:
```sh
./mvnw spring-boot:run
```

## Docker Support
The project includes a `Dockerfile` for containerization.

### Building the Docker Image
To build the Docker image, run:
```sh
docker build -t userverse .
```

### Running the Docker Container
To run the Docker container, use:
```sh
docker run --name my_userverse -p 2000:2000 -e MONGODB_URI=mongodb://host.docker.internal:27017 userverse
```

## Endpoints

### Example Endpoints

- `GET /user`: Retrieves a list of users.
- `GET /user/{id}`: Retrieves a user by ID.
- `GET /user/profile/{userid}`: Get user profile by ID.
- `GET /user/preference/{userid}`: Get user preferences by ID.
- `POST /user/preference/{userid}`: Create a user preference.

## .gitignore
The `.gitignore` file is configured to exclude build directories and IDE-specific files.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.