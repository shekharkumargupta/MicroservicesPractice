FROM openjdk:17-jdk-slim

MAINTAINER SKCodify

COPY target/gateway-service-0.0.1-SNAPSHOT.jar gateway-service.jar

EXPOSE 8222

ENTRYPOINT ["java", "-jar", "gateway-service.jar"]

