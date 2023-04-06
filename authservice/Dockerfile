FROM openjdk:17
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/authservice-0.0.1-SNAPSHOT.jar /app/authservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/authservice-0.0.1-SNAPSHOT.jar"]