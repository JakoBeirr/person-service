FROM maven:3.6.3-openjdk-8 AS build
COPY . /app/
RUN mvn -f /app/pom.xml clean package

FROM openjdk:8-jdk-alpine
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
