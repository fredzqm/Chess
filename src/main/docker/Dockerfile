FROM openjdk:8
RUN ls
RUN ./gradlew build
ADD build/libs/Chess.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
