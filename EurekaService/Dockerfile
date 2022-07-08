FROM openjdk
WORKDIR usr/lib
#ENV SQL_DATABASE=user
#ENV sql_URL=jdbc:mysql://localhost:3306/user
ADD ./target/EurekaService-0.0.1-SNAPSHOT.jar /usr/lib/EurekaService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","EurekaService-0.0.1-SNAPSHOT.jar"]
