FROM java:openjdk-8u111-alpine

RUN mkdir /app

WORKDIR /app

COPY target/vod-2.0.0.jar /app

EXPOSE 8080

CMD [ "java", "-jar", "vod-2.0.0.jar" ]