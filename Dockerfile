FROM openjdk:8
ADD target/RBC-assessment-1.0-SNAPSHOT.jar RBC-assessment-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "RBC-assessment-1.0-SNAPSHOT.jar"]