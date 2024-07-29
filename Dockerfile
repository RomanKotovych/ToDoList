FROM maven:3.9.8-amazoncorretto-21 AS build
COPY src /ToDoList/src
COPY pom.xml /ToDoList
RUN mvn -f /ToDoList/pom.xml clean package
EXPOSE 8080
ENTRYPOINT ["java","-jar","/ToDoList/target/ToDoList-1.0.jar"]