FROM openjdk:17-alpine
COPY ./target/depositCash-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app/
ENTRYPOINT ["java"]
CMD ["-jar", "/app/depositCash-0.0.1-SNAPSHOT.jar"]
