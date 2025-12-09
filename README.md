# TH Web Application

Spring Boot 기반의 웹 애플리케이션 프로젝트입니다. 게시판, 상담, 네이버 지도 API 연동 등의 기능을 제공합니다.

## 🚀 기술 스택

### Backend
- **Java 17**
- **Spring Boot 3.0.2**
- **Spring Security** - 인증 및 보안
- **Spring Data JPA** - 데이터 접근 계층
- **MyBatis** - SQL 매퍼
- **MySQL** - 메인 데이터베이스
- **H2 Database** - 인메모리 데이터베이스 (개발용)
- **Lombok** - 보일러플레이트 코드 제거

### Frontend
- **Thymeleaf** - 서버사이드 템플릿 엔진
- **Bootstrap 5** - UI 프레임워크
- **jQuery** - JavaScript 라이브러리
- **Flatpickr** - 날짜 선택기

### API & 외부 연동
- **SpringDoc OpenAPI** - API 문서화
- **Naver Map API** - 지도 서비스
- **CORS** - 크로스 오리진 리소스 공유

## 📁 프로젝트 구조

```
th-web-app/
├── Dockerfile                 # 앱 실행 이미지 빌드
├── docker-compose.yml         # 앱 + MySQL 로컬 실행
├── .env                       # 환경변수 (직접 생성)
├── src/
│   └── main/
│       ├── java/
│       │   └── com/developerleetaehee/th_web_app/
│       │       ├── batch/             # 배치
│       │       │   └── scheduler/     # 스케줄러 모음
│       │       ├── config/            # 설정 클래스들
│       │       ├── controller/        # 컨트롤러
│       │       │   ├── api/           # 외부 API 컨트롤러
│       │       │   ├── board/         # 게시판 관련 컨트롤러
│       │       │   ├── intro/         # 소개 페이지 컨트롤러
│       │       │   ├── lof/           # 로그 테스트
│       │       │   ├── shop/          # 쇼핑몰 컨트롤러
│       │       │   ├── test/          # 테스트
│       │       │   ├── user/          # 회원정보
│       │       │   └── HomeController # 메인화면 index
│       │       ├── di/             # 의존성 주입 객체 
│       │       ├── domain/         # 엔티티 클래스
│       │       ├── dto/            # 데이터 전송 객체
│       │       ├── repository/     # 데이터 접근 계층
│       │       ├── service/        # 비즈니스 로직
│       │       ├── specification/  # JPA 스펙
│       │       ├── study/          # 스프링부트 Java 학습 코드
│       │       └── utility/        # 공통 메서드
│       └── resources/
│           ├── static/             # 정적 리소스 (CSS, JS, 이미지)
│           ├── templates/          # Thymeleaf 템플릿
│           └── mapper/             # MyBatis XML 매퍼
└── build.gradle 등 Gradle 설정 파일
```

## 🛠️ 주요 기능

### 1. 게시판 시스템
- 게시글 CRUD 기능
- 페이징 처리
- 검색 기능
- 상담 게시판

### 2. 네이버 지도 연동
- 회사 위치 표시
- 지도 API 연동

### 3. 보안 기능
- Spring Security 기반 인증
- CSRF 토큰 보호
- CORS 설정

### 4. 관리자 기능
- 게시글 관리
- 상담 상태 관리

## 🚀 실행 방법

### 1. 환경 요구사항
- Java 17 이상
- MySQL 8.0 이상
- Gradle 7.0 이상

### 2. 환경 변수 설정
프로젝트 루트에 `.env` 파일을 생성하고 다음 환경 변수를 설정하세요:

```bash
# Database
DB_HOST=localhost:3306
DB_USERNAME=your_username
DB_PASSWORD=your_password

# Naver Map API
NAVER_MAP_CLIENT_ID=your_naver_map_client_id
NAVER_MAP_LAT=37.5665
NAVER_MAP_LNG=126.9780

# CORS
CORS_ALLOWED_ORIGINS=http://localhost:3000,http://localhost:8080
```

### 3. 프로젝트 실행

```bash
# 프로젝트 클론
git clone [repository-url]
cd th-web-app

# 의존성 설치 및 실행
./gradlew bootRun
```

또는

```bash
# JAR 파일 빌드
./gradlew build

# JAR 파일 실행 (운영 prod, 개발 dev, 로컬 local, 도커 docker)
java -jar -Dspring.profiles.active=local build/libs/th-web-app-1.0.jar
```

### 4. 접속
- 메인 페이지: http://localhost:8080
- API 문서: http://localhost:8080/swagger-ui.html

## 🐳 Docker로 실행하기

### 1) 사전 준비
- Docker, Docker Compose 설치
- `.env` 파일 작성 (루트 경로). 예시:

```bash
# DB (docker-compose에서 기본값을 덮어쓰려면 수정)
DB_HOST=db:3306
DB_USERNAME=admin
DB_PASSWORD=springboot1234Q@

# Naver Map API
NAVER_MAP_CLIENT_ID=your_naver_map_client_id
NAVER_MAP_LAT=37.5665
NAVER_MAP_LNG=126.9780

# CORS 허용 도메인
CORS_ALLOWED_ORIGINS=http://localhost:3000,http://localhost:8080
```

### 2) 빌드 & 기동
```bash
# Spring Boot fat JAR 빌드
./gradlew build

# Docker Compose 실행 (이미지 빌드 포함)
docker compose up --build

# 백그라운드로 실행하려면
docker compose up -d --build
```

- 애플리케이션: `http://localhost:8080`
- MySQL: `localhost:3306` (컨테이너명 `th-study-web-app-db`)

### 3) 종료
```bash
docker compose down
```

## 📚 API 문서

SpringDoc OpenAPI를 통해 자동 생성되는 API 문서를 제공합니다:
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

## 🗄️ 데이터베이스

### MySQL 설정
- 데이터베이스명: `th_study_web_app`
- 문자셋: `UTF-8`
- 타임존: `Asia/Seoul`

### 주요 테이블
- `board` - 게시글 정보
- `counsel` - 상담 정보

## 🔧 개발 환경 설정

### IntelliJ IDEA 설정
1. 프로젝트를 IntelliJ IDEA에서 열기
2. Gradle 프로젝트로 인식되도록 설정
3. Java 17 SDK 설정
4. Lombok 플러그인 설치

### VS Code 설정
1. Java Extension Pack 설치
2. Spring Boot Extension Pack 설치
3. Gradle for Java 설치

## 📝 개발 가이드

### 코드 컨벤션
- Java 클래스명: PascalCase
- 메서드명: camelCase
- 상수: UPPER_SNAKE_CASE
- 패키지명: 소문자

### 브랜치 전략
- `main`: 메인 브랜치
- `dev`: 개발 브랜치
- `README.md`: README.md를 수정할 때 사용 
- `유지보수`: 가벼운 유지보수 브랜치
- `{YYYYMMDD}_개발주제명`: 신규 기능 개발할때 (공통의 경우 공통주제로 적용)

## 🤝 기여 방법

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다. 자세한 내용은 `LICENSE` 파일을 참조하세요.

## 👨‍💻 개발자

- **이태희** - [GitHub Profile](https://github.com/developerleetaehee)

## 📞 문의

프로젝트에 대한 문의사항이 있으시면 이슈를 생성해 주세요.

---

⭐ 이 프로젝트가 도움이 되었다면 스타를 눌러주세요!
