# Base Alpine Linux based image with OpenJDK JRE only
FROM openjdk:8-jre-alpine
COPY target/stoom-*.jar /app.jar
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=test", "/app.jar"]
EXPOSE 8080