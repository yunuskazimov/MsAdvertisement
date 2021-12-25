FROM openjdk:17-slim-buster

COPY build/libs/MsAdvertisement-0.0.1-SNAPSHOT.jar .

ENTRYPOINT java -jar MsAdvertisement-0.0.1-SNAPSHOT.jar