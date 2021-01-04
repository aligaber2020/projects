FROM FROM openjdk:8-jdk-alpine
COPY ./target/ecare-auth-1.0.jar ./auth.jar
COPY ./target/classes/application.properties ./1.properties
COPY ./target/classes/application-dev.properties ./2.properties
RUN cat 1.properties 2.properties > application.properties
EXPOSE 8763
CMD ["java", "-jar", "-Dspring.profiles.active=dev", "-Deureka.client.serviceUrl.defaultZone=http://service-discovery:8761/sd/eureka/", "-Dspring.config.location=/application.properties", "auth.jar"]
