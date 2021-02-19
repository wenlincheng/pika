FROM java:alpine
VOLUME /tmp
ADD pika-item-1.0.0.jar app.jar
ENTRYPOINT ["java","-Xms512m","-Xmx512m","-Xmn200m","-Xss128k","-jar","/app.jar"]
