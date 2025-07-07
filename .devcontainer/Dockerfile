FROM eclipse-temurin:17-jdk-alpine

WORKDIR /workspace

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline -B

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
