FROM eclipse-temurin:25-jdk AS builder
WORKDIR /workspace

# Copy only necessary files for Maven wrapper build to leverage layer caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x mvnw && ./mvnw -DskipTests -B package

FROM eclipse-temurin:25-jdk AS runtime
WORKDIR /app
ARG JAR_FILE=forohub-0.0.1-SNAPSHOT.jar
COPY --from=builder /workspace/target/${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
