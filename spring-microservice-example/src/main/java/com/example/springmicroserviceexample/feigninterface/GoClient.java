package com.example.springmicroserviceexample.feigninterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "callisto-admin-server")
public interface GoClient {

    @GetMapping("/go/bar?name=yubin")
    String testGo();

}
