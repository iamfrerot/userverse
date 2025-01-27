FROM openjdk:17-jdk-alpine3.13

# Set the working directory
WORKDIR /app

# Copy the jar file to the working directory
COPY target/*.jar userverse.jar

# Expose the port
EXPOSE 2000


# Run the jar file
CMD ["java", "-jar", "userverse.jar"]