FROM openjdk:17-alpine
COPY ./target/listStocks-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app/
ENTRYPOINT ["java"]
CMD ["-jar", "/app/listStocks-0.0.1-SNAPSHOT.jar"]