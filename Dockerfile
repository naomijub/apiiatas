FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/apiiatas-0.0.1-SNAPSHOT-standalone.jar /apiiatas/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/apiiatas/app.jar"]
