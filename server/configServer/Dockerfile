FROM eclipse-temurin:21-jdk-ubi9-minimal

VOLUME /tmp

RUN microdnf update -y \
    && microdnf install -y curl-minimal \
    && microdnf clean all

COPY ./server/configServer/build/libs/*.jar config-server.jar

ENTRYPOINT ["java", "-jar", "/config-server.jar"]