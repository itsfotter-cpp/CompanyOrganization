FROM adoptopenjdk/openjdk11:latest
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]