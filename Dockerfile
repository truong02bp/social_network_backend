FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} social-network-backend.jar
ENTRYPOINT ["java", "-jar", "/social-network-backend.jar"]
EXPOSE 8081
