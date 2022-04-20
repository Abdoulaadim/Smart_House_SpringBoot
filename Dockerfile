FROM openjdk
EXPOSE 8080
ADD target/smart_house.jar smart_house.jar
ENTRYPOINT ["java","-jar","/smart_house.jar"]