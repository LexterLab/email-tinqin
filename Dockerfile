FROM amazoncorretto:21-alpine

WORKDIR /email

COPY rest/target/rest-0.0.1-SNAPSHOT.jar /email/email-docker.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "/email/email-docker.jar"]
