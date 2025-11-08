FROM openjdk:17-jdk-slim
COPY build/libs/*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=local", "-jar", "/app.jar"]