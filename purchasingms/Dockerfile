FROM maven:3-openjdk-17 as build
WORKDIR /home/app
COPY pom.xml .
COPY src ./src
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /home/app/target/app.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]