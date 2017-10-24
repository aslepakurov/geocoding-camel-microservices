# Spring Boot Camel Microservice integration


1. Clone project `https://github.com/aslepakurov/geocoding-camel-microservices.git`
2. Install maven (check it with `mvn -v`)
3. Get into project directory `cd geocoding-camel-microservices`
4. Build modules `mvn clean package spring-boot:repackage`
5. Run modules 
`java -jar gateway/target/gateway-0.1-SNAPSHOT.jar > gateway.log 2>&1 &` 

and 

`java -jar caller/target/caller-0.1-SNAPSHOT.jar > caller.log 2>&1 &`

6. Check service with

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
   
_Sidenote:_*/callerAPI uses java lib to call Google API, whereas /callerURL uses plain url GET request (xml)*
