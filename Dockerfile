FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app

COPY . /app

RUN mvn clean package

FROM tomcat:9-jdk21-openjdk

COPY --from=build /app/target/tienda.war /usr/local/tomcat/webapps/tienda.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
