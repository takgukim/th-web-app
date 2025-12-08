# Ubuntu 24.04 기반 OpenJDK 17
FROM ubuntu:24.04

# 필수 패키지 설치
RUN apt-get update && apt-get install -y openjdk-17-jdk && apt-get clean

# 작업 디렉토리
WORKDIR /app

# 빌드된 JAR 복사
COPY build/libs/*.jar app.jar

# 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
