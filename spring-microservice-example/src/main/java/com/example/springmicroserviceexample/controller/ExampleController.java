package com.example.springmicroserviceexample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ExampleController {

    @GetMapping("/example")
    public String RestTemplateExampleController() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:3000/go/bar?name=yubin";
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/hello")
    public String helloController() {
        return "Hello Spring!";
    }
}
