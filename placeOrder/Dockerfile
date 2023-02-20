FROM openjdk:17-alpine
COPY ./target/placeOrder-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app/
ENTRYPOINT ["java"]
CMD ["-jar", "/app/placeOrder-0.0.1-SNAPSHOT.jar"]