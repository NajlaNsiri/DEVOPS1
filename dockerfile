FROM openjdk:8-jdk-alpine
EXPOSE 8080
WORKDIR /app
COPY target/DevOps_Project-2.1.jar /app/DevOps_Project.jar
CMD ["java", "-jar", "DevOps_Project.jar"]