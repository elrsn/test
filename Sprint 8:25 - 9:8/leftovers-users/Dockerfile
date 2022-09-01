#FROM adoptopenjdk/openjdk11:alpine-jre
#FROM openjdk:8-jdk-alpine
#COPY target/leftovers-users-0.1.0-SNAPSHOT.jar leftovers-users-0.1.0.jar
#ENTRYPOINT ["java","-jar","leftovers-users-0.1.0.jar"]


FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/leftovers-users-0.1.0-SNAPSHOT.jar leftovers-users-0.1.0.jar
WORKDIR /opt/app
COPY ${JAR_FILE} leftovers-users-0.1.0.jar
ENTRYPOINT ["java","-jar","leftovers-users-0.1.0.jar"]s