FROM openjdk:17-jdk-slim

MAINTAINER SKCodify

COPY target/product-service-0.0.1-SNAPSHOT.jar product-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "product-service.jar"]

