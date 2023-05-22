# drug-product-service

### REST APIS

GET drugs
http://localhost:8080/api/drugs

GET drug by Id
http://localhost:8080/api/drugs/1

POST drug
http://localhost:8080/api/drugs

PATCH drug
http://localhost:8080/api/drugs

DELETE drug
http://localhost:8080/api/drugs/1

### How to up Kafka zookeeper in cmd

.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

### How to up kafka server in cmd

.\bin\windows\kafka-server-start.bat .\config\server.properties

### How to read the events in cmd

.\bin\windows\kafka-console-consumer.bat --topic drugProductService --from-beginning --bootstrap-server localhost:9092