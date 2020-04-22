FROM gradle:6.3.0-jdk14 AS build
ADD  . /home/gamelibrary
WORKDIR /home/gamelibrary
RUN gradle build --no-daemon

FROM openjdk:14-jdk-alpine
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gamelibrary/build/libs/*.jar /app/spring-boot-application.jar

ENTRYPOINT ["java", "-jar","/app/spring-boot-application.jar"]