FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ADD target/DevOps_Project-2.1.jar DevOps_Project.jar
ENTRYPOINT [ "java", "-jar", "DevOps_Project.jar" ]
