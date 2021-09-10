FROM openjdk:8
ADD /target/survey-rest-service-0.0.1.jar survey-rest-service-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "survey-rest-service-0.0.1.jar"]