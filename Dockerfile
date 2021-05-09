FROM openjdk:11
EXPOSE 8080
VOLUME /main-app
ADD target/app.jar app.jar
RUN mkdir /img
RUN chmod 755 /img
ENTRYPOINT ["java", "-jar", "app.jar"]