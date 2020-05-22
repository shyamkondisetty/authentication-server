FROM java:8-jdk-alpine
COPY build/libs/* app.jar
EXPOSE 4441
ENTRYPOINT ["java", "-jar", "/app.jar"]