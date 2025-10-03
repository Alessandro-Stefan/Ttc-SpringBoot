# Stage 1: build con Maven + JDK 21
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests clean package

# Stage 2: immagine runtime solo JRE
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Copia il jar con nome esplicito (aggiorna se cambi versioning)
COPY --from=build /workspace/target/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# optional: user non-root
RUN addgroup --system app && adduser --system --ingroup app app
USER app

ENTRYPOINT ["java","-jar","/app/app.jar"]