## Ubuntu 24.04 + OpenJDK 17 (JRE)
FROM ubuntu:24.04

# 필수 패키지 설치
RUN apt-get update && apt-get install -y openjdk-17-jdk && apt-get clean

WORKDIR /app

# 빌드된 Spring Boot fat JAR 복사 (plain JAR 제외)
COPY build/libs/th-web-app-1.0.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
