
FROM openjdk:17-jdk-slim-buster
LABEL authors="Irlon"

EXPOSE 8080

ADD target/testcontainers-0.0.1-SNAPSHOT.jar myapp.jar

ENTRYPOINT ["java","-jar","/myapp.jar"]