FROM java:alpine
VOLUME /tmp
ADD pika-auth-1.0.0.jar app.jar
ENTRYPOINT ["java","-Xms256m","-Xmx256m","-Xmn200m","-Xss128k","-jar","/app.jar"]
