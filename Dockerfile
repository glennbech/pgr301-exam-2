FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
# Copy the pom.xml file into the container at /app
COPY pom.xml .

# Copy the rest of the application code
COPY . .
RUN mvn clean package -DskipTests

FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
COPY --from=build /app/target/s3rekognition-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]