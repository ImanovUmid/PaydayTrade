FROM openjdk:17-alpine
COPY ./target/emailNotification-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app/
ENTRYPOINT ["java"]
CMD ["-jar", "/app/emailNotification-0.0.1-SNAPSHOT.jar"]