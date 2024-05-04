FROM eclipse-temurin:21-jdk AS BUILD

WORKDIR /opt/app

COPY . .

RUN ./gradlew bootJar

FROM eclipse-temurin:21-jre AS RUNTIME

WORKDIR /opt/app

COPY --from=BUILD /opt/app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]