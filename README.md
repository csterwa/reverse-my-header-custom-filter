# reverse-my-header-custom-filter

This example is a Post Filter that will transform the value of the header `X-Reverse-Me` to a new header `X-Reversed` in the intercepted response. 

## Using a local server

The configuration for a local server is located in the `src/main/resources/reverse-application.properties` file.

```
spring.cloud.gateway.routes[0].id=service_route
spring.cloud.gateway.routes[0].uri=https://httpbin.org/get
spring.cloud.gateway.routes[0].predicates[0]=Path=/api/reverse/**
spring.cloud.gateway.routes[0].filters[0]=StripPrefix=2
spring.cloud.gateway.routes[0].filters[1]=ReverseMyHeader
```

Just one route is configured to forward any request whose URL ends with "/api/reverse/**". Because we don't want to forward that part of the URL, we are applying the filter `StripPrefix=2` first and then the custom header we want to test `ReverseMyHeader`  

`./gradlew bootRun`

Checking filter
`curl --head -H 'X-Reverse-Me:Yeah' http://localhost:8090/api/reverse`

## Run test

`./gradlew test`

## A better way: using SCG-K8S

...