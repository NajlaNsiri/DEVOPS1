FROM openjdk:8
EXPOSE 8089

ENV NEXUS_USERNAME=admin
ENV NEXUS_PASSWORD=nexus
ENV NEXUS_REPO_URL=http://192.168.33.10:8081/repository/maven-releases/tn/esprit/DevOps_Project/2.1/DevOps_Project-2.1.jar

RUN curl -L -o achat.jar -u $NEXUS_USERNAME:$NEXUS_PASSWORD $NEXUS_REPO_URL


ENTRYPOINT ["java", "-jar", "DevOps_Project-2.1.jar"]