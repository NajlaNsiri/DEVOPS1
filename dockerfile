# Use the official OpenJDK base image
FROM openjdk:8-jdk-alpine

# Set the working directory within the container
WORKDIR /app

# Copy the JAR file from the build context to the container
COPY target/DevOps_Project-2.1.jar /app/DevOps_Project.jar

# Expose the port your application will listen on
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "DevOps_Project.jar"]
