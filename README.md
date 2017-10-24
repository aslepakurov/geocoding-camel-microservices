# Spring Boot Camel Microservice integration
[![Build Status](https://travis-ci.org/aslepakurov/geocoding-camel-microservices.svg?branch=master)](https://travis-ci.org/aslepakurov/geocoding-camel-microservices)

1. Clone project `https://github.com/aslepakurov/geocoding-camel-microservices.git`
2. Put Google Geocoding API into file `caller/src/main/resources/applicarion.yml` key `google.api.key`
3. Install maven (check it with `mvn -v`)
4. Get into project directory `cd geocoding-camel-microservices`
5. Build modules `mvn clean package spring-boot:repackage`
6. Run modules 

   `java -jar gateway/target/gateway-0.1-SNAPSHOT.jar > logs/gateway.log 2>&1 &` 

   and 

   `java -jar caller/target/caller-0.1-SNAPSHOT.jar > logs/caller.log 2>&1 &`

7. Check service with

   `curl -d '{"address":"maidan nezalegnosti 1 kyiv"}' -H "Content-Type: application/json" -X POST http://localhost:8000/callerAPI` 
   
   and
   
   `curl -d '{"address":"maidan nezalegnosti 1 kyiv"}' -H "Content-Type: application/json" -X POST http://localhost:8000/callerURL`

   You should get in both cases the following JSON:

   ```
   {
     "address" : "maidan nezalegnosti 1 kyiv",
     "lat" : 50.4509591,
     "lon" : 30.5226018,
     "formattedAddress" : "Maidan Nezalezhnosti, 1, Kyiv, Kyivs'ka oblast, Ukraine, 02000"
   }
   ```
   
**Sidenote:** */callerAPI uses java lib to call Google API, whereas /callerURL uses plain url GET request (xml)*
