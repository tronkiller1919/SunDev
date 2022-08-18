FROM maven:3.8.1-openjdk-17-slim  AS mvnbuild
WORKDIR ./
COPY src /project/src
COPY pom.xml /project
RUN mvn -file /project/pom.xml clean package -Dmaven.test.skip

FROM eclipse-temurin:17-jre-alpine
WORKDIR ./
COPY --from=mvnbuild /project/target/api-gateway-service-executable.jar ./
EXPOSE 8991
ENTRYPOINT ["java","-jar","api-gateway-service-executable.jar","-Dspring.config.additional-location=/application.yml"]
