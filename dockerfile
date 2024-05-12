FROM openjdk:21-jdk
MAINTAINER mugdho.com
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]