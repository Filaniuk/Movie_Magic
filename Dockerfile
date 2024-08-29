FROM openjdk:17-jdk-alpine3.14
LABEL authors="Nikolai"

ENTRYPOINT ["top", "-b"]