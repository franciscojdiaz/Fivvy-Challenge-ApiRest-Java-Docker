FROM openjdk:17-jdk-alpine
COPY target/fivvy-0.0.1-SNAPSHOT.jar fivvyapp.jar
ENTRYPOINT ["java", "-jar", "fivvyapp.jar"]
