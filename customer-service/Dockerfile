FROM openjdk:17-jdk-slim

MAINTAINER SKCodify

COPY target/customer-service-0.0.1-SNAPSHOT.jar customer-service.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "customer-service.jar"]