FROM openjdk
WORKDIR usr/lib
#ENV SQL_DATABASE=user
#ENV sql_URL=jdbc:mysql://localhost:3306/user
ADD ./target/Gateway-0.0.1-SNAPSHOT.jar /usr/lib/Gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Gateway-0.0.1-SNAPSHOT.jar"]
