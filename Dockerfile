########build stage########
FROM maven:3.8-openjdk-17 as maven_build
WORKDIR /app

COPY pom.xml .

# To package the application
COPY src ./src
COPY src/main/resources/application.properties ./src/main/resources/application.properties
RUN mvn clean package -Dmaven.test.skip

########run stage########
FROM openjdk:17.0.2
WORKDIR /app

COPY --from=maven_build /app/target/*.jar app.jar

#run the app
ENV JAVA_OPTS ""
CMD [ "bash", "-c", "java ${JAVA_OPTS} -jar app.jar -v"]