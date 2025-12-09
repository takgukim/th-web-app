## Ubuntu 24.04 + OpenJDK 17 (JRE)
FROM ubuntu:24.04

# 필수 패키지 설치
RUN apt-get update && apt-get install -y openjdk-17-jdk && apt-get clean

WORKDIR /app

# 로그 디렉토리 생성 및 권한 설정
RUN mkdir -p /opt/web/logs/app /opt/web/logs/batch \
    && chmod -R 777 /opt/web/logs

# 빌드된 Spring Boot fat JAR 복사 (plain JAR 제외)
COPY build/libs/th-web-app-1.0.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.profiles.active=docker"]
