FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/leftovers-restaurants-0.0.1-SNAPSHOT.jar leftovers-restaurants-0.0.1.jar
WORKDIR /opt/app
COPY ${JAR_FILE} leftovers-restaurants-0.0.1.jar
ENTRYPOINT ["java","-jar","leftovers-restaurants-0.0.1.jar"]s