FROM openjdk:8
COPY /target/outreach_zuul_service-0.0.1-SNAPSHOT.jar /
EXPOSE 8080
CMD ["java","-jar","-Deureka.datacenter=cloud","-Dspring.profiles.active=aws","outreach_zuul_service-0.0.1-SNAPSHOT.jar"]