FROM openjdk:11-jre-slim
WORKDIR usr/src
EXPOSE 9000
ADD ./target/SpringCloudAPIGateway-0.0.1-SNAPSHOT.jar /usr/src/Gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Gateway-0.0.1-SNAPSHOT.jar"]
