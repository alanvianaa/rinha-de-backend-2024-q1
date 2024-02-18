FROM openjdk:11-jre-slim
LABEL authors="Alan Viana"

RUN mkdir /app
COPY ./build/install/backend /app/
WORKDIR /app/bin

EXPOSE 8080
CMD ["./backend"]