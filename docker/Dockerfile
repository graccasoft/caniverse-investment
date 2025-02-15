# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY *.jar app.jar
EXPOSE 8888
CMD ["java", "-jar", "app.jar"]
