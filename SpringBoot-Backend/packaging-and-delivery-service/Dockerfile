FROM maven:3.8.1-openjdk-17-slim  AS mvnbuild
WORKDIR ./
COPY src /project/src
COPY pom.xml /project
RUN mvn -file /project/pom.xml clean package -Dmaven.test.skip

FROM eclipse-temurin:17-jre-alpine
WORKDIR ./
COPY --from=mvnbuild /project/target/packaging-and-delivery-service-executable.jar ./
EXPOSE 8996
ENTRYPOINT ["java","-jar","packaging-and-delivery-service-executable.jar","-Dspring.config.additional-location=/application.yml"]