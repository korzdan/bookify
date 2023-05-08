FROM amazoncorretto:19
COPY /src/main/resources/db/migration /src/main/resources/db/migration
COPY /src/main/images /src/main/images
ADD /build/libs/bookify-0.0.1-SNAPSHOT.jar bookify-backend.jar
ENTRYPOINT ["java", "-jar", "bookify-backend.jar"]
