# OpenJDK 21 Alpine 이미지 사용
FROM eclipse-temurin:21-alpine

# JAR 파일을 컨테이너에 추가
COPY ./api/build/libs/api-0.0.1.jar eggCoachApi.jar

# 컨테이너가 시작될 때 실행할 명령어
CMD ["java", "-jar", "eggCoachApi.jar"]