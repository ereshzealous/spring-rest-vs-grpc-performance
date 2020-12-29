# Spring Rest vs gRPC Performance Testing

This repo has code base of gRPC proto, gRPC server, rest server and rest-gRPC aggregator.

### Start application 

First do gradle build for all the applications excluding user-details-proto.

```
gradle clean bootJar
```

Docker compose for all the applications

```
docker-compose up
```

For more details please visit the medium blog
* [Spring Rest vs gRPC Performance Testing](https://eresh-zealous.medium.com/performance-comparison-on-spring-rest-vs-grpc-unary-and-stream-processing-4ea032777051)
