# syntax=docker/dockerfile:1

# ===== Build stage =====
FROM gradle:8.10.2-jdk21 AS build
WORKDIR /home/gradle/project

# Leverage Docker layer caching for dependencies
COPY build.gradle settings.gradle ./
COPY gradle gradle
COPY gradlew gradlew
RUN chmod +x gradlew

# Pre-fetch dependencies
RUN ./gradlew --no-daemon dependencies || true

# Copy sources
COPY src src

# Build fat JAR (shadow)
RUN ./gradlew --no-daemon clean shadowJar

# ===== Runtime stage =====
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy fat jar from build stage
COPY --from=build /home/gradle/project/build/libs/*-all.jar /app/app.jar

# Expose nothing; the app is TTY based when run in a container

# Default: run headless (TTY). Provide a sensible default heap.
ENV JAVA_OPTS="-Xms256m -Xmx512m"

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar /app/app.jar"]
