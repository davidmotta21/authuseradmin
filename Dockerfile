FROM eclipse-temurin:17-jdk-focal
 
WORKDIR /app
COPY ./target/authuseradmin-data-service-1.0.0.jar /app

EXPOSE 8080

CMD ["java", "-jar", "authuseradmin-data-service-1.0.0.jar"]