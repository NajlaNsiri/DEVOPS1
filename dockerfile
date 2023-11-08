FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY target/DevOps_Project-2.1.jar /app/DevOps_Project.jar
EXPOSE 8080
CMD ["java", "-jar", "DevOps_Project.jar"]
