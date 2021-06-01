FROM openjdk:8-jdk-alpine
ADD ["target/*.jar", "app.jar"]
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]

