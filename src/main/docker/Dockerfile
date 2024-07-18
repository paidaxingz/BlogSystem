FROM openjdk:17-jdk-alpine
VOLUME /tmp
ADD blogsystem-1.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]