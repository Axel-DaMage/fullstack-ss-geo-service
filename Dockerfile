FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY geo-service.jar app.jar
EXPOSE 3002
ENTRYPOINT ["java", "-jar", "app.jar"]