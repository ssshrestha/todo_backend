FROM openjdk:11-jre-slim
WORKDIR usr/src
ENV MONGO_URI=mongodb://localhost:27017/todoregisterdb
EXPOSE 8086
ADD ./target/UserMovieService-0.0.1-SNAPSHOT.jar /usr/src/Register-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Register-0.0.1-SNAPSHOT.jar"]