FROM openjdk
COPY ./target/CustomerService-0.0.1-SNAPSHOT.jar customer-service.jar 
ENTRYPOINT ["java","-jar","/customer-service.jar"]