Apache Kafka example for Spring Boot
# HOW TO START
## DEPENDENCIES
Install Zookeeper and Apache Kafka
```
docker-compose -up -d
```
## MODULES
### CONSUMER
```
./gradlew clean :consumer:bootRun
```
### PRODUCER
```
./gradlew clean :producer:bootRun
```
