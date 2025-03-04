FROM openjdk:21-jdk-slim

# create user to run app as (instead of root)
RUN groupadd -r app && useradd -r -g app app

# use user app
USER app

# copy jar file into docker image
COPY target/*.jar app.jar

# run jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]
