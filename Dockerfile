FROM openjdk:17-slim
WORKDIR /app
EXPOSE 8080
ARG JAR_FILE=target/DiscountService-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app/DiscountService-0.0.1.jar
LABEL authors="Canberk"
ENTRYPOINT ["java","-jar","/app/DiscountService-0.0.1.jar"]