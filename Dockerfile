FROM mateus314/prontuario-ap:latest

ENV DATABASE_HOST=localhost
ENV DATABASE_PORT=5331
ENV DATABASE_NAME=prontuario
ENV DATABASE_USERNAME=postgres
ENV DATABASE_PASSWORD=password

WORKDIR /app

COPY target/prontuario-api-0.0.1-SNAPSHOT.jar /app/prontuario-api-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "prontuario-api-0.0.1-SNAPSHOT.jar"]