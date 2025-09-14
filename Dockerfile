# syntax=docker/dockerfile:1
FROM maven:3.9-eclipse-temurin-24 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:24-jre-noble
RUN useradd -r -u 10001 appuser
WORKDIR /app
# COPY the only jar produced in target and name it app.jar
COPY --from=build /app/target/*jar /app/app.jar
EXPOSE 8080
USER appuser
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75 -XX:+UseG1GC -Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
