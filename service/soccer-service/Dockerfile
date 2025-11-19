FROM eclipse-temurin:21-jdk-ubi9-minimal

VOLUME /tmp

RUN microdnf update -y \
    && microdnf install -y curl-minimal \
    && microdnf clean all

COPY ./service/soccer-service/build/libs/*.jar soccer-service.jar

ENTRYPOINT ["java", "-jar", "/soccer-service.jar"]