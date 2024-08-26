# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the jar file into the container at /app
COPY university/target/university-0.0.1-SNAPSHOT.jar /app/application.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/application.jar"]
