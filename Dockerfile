FROM maven:3.6-openjdk-15 as builder

WORKDIR /myoutuber2020app

COPY pom.xml .
COPY src ./src

RUN pwd

RUN mvn package -DskipTests

FROM adoptopenjdk/openjdk15:alpine-slim

COPY --from=builder /myoutuber2020app/target/common/* /*

RUN ls -ltr /
RUN hostname -f

CMD ["java", "-jar", "-Dspring.profiles.active=dev", "/application.jar"]