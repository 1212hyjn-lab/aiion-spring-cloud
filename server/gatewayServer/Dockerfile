FROM eclipse-temurin:21-jdk-ubi9-minimal

VOLUME /tmp

RUN microdnf update -y \
    && microdnf install -y curl-minimal \
    && microdnf clean all

COPY ./server/gatewayServer/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]