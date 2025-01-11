FROM openjdk:17-jdk-slim AS builder
WORKDIR /app

# Gradle Wrapper 복사
COPY gradlew .
COPY gradle gradle
RUN chmod +x gradlew

# 의존성 파일 복사 및 다운로드
COPY build.gradle .
COPY settings.gradle .

# 의존성 다운로드
RUN ./gradlew dependencies

# 소스코드 복사 및 빌드
COPY ./ ./
RUN ./gradlew clean build -x test

# 실행 스테이지
FROM openjdk:17-jdk-slim
COPY --from=builder /app/build/libs/*.jar /app/app.jar

ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul","-Dspring.profiles.active=prod", "-jar", "/app/app.jar"]
