# spring-cloud

### How to run?

```
$ git clone https://github.com/wonwoo/spring-cloud.git
$ cd spring-cloud
$ mvn clean install
```

#### java run 
```
java -jar config-service/target/config-service-0.0.1-SNAPSHOT.jar
java -jar eureka-service/target/eureka-service-0.0.1-SNAPSHOT.jar
java -jar user-service/target/user-service-0.0.1-SNAPSHOT.jar
java -jar message-service/target/message-service-0.0.1-SNAPSHOT.jar
java -jar api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar
java -jar hystrix-dashboard/target/hystrix-dashboard-0.0.1-SNAPSHOT.jar

```

#### docker run 
```
docker-compose up
```


### config-server
 
http://localhost:8888/user-service/default

### eureka-server 

http://localhost:8761/

### user-server

http://localhost:8081/users

```
[
    {
        name: "wonwoo"
    },
    {
        name: "kevin"
    }
]
```

### message-server

http://localhost:8082/message
```
hello
```

### api-gateway-server

http://localhost:8080/users

```
[
    {
        name: "wonwoo"
    },
    {
        name: "kevin"
    }
]
```

#### proxy

http://localhost:8080/legacy/message
```
hello 
```

### hystrix-server

http://localhost:8001/hystrix.html

#### local

http://localhost:8080/hystrix.stream/

#### docker

http://api-gateway:8080/hystrix.stream/