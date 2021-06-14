FROM openjdk:11-jre-slim-buster
COPY ./build/libs/*.jar /customer-service-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-XX:MinRAMPercentage=50", "-XX:MaxRAMPercentage=50", "-Djava.security.egd=file:/dev/./urandom", "-Dfile.encoding=UTF8", "-Duser.timezone=America/Sao_Paulo", "-jar", "/customer-service-0.0.1-SNAPSHOT.jar"]