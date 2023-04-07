package com.example.springmicroserviceexample.feigninterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "nodejs-example-server", url = "http://localhost:3001")
public interface NodejsClient {

    @GetMapping("/nodejs/example")
    String callNodejs();
}
