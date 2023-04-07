package com.example.springmicroserviceexample.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ExampleController {

    // gateway 통해서 restTemplate으로 요청시 불가능
    @GetMapping("/spring/example")
    public String RestTemplateExampleController() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/go/bar?name=yubin";
        return restTemplate.getForObject(url, String.class);
    }

    // restTemplate으로 직접 마이크로서비스 요청시 가능
    @GetMapping("/spring/to-go-example")
    public String RestTemplateExampleControllerV2() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:3000/go/bar?name=iphone";
        return restTemplate.getForObject(url, String.class);
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.setBasicAuth("username", "password");
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        String url = "http://localhost:8080/go/";
//
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//        return response.toString();
    }

    @GetMapping("/spring/to-node-example")
    public String RestTemplateExampleControllerV3() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/nodejs/api";
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/spring/hello")
    public String helloController() {
        return "Hello Spring!";
    }
}
