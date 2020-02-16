From openjdk:8
EXPOSE 8080
copy ./target/roombookingservice-0.0.1-SNAPSHOT.jar roombookingservice-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","roombookingservice-0.0.1-SNAPSHOT.jar"]