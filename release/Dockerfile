FROM eclipse-temurin:21-jre

WORKDIR /app

COPY compiled/pcbuild.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]