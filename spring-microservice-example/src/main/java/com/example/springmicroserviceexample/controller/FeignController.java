package com.example.springmicroserviceexample.controller;

import com.example.springmicroserviceexample.service.FeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FeignController {

    private final FeignService feignService;

    // FeignClient 사용 가능
    @GetMapping("/spring/test-feign-nodejs")
    public String testFeignNodejs() {
        return feignService.callNodejs();
    };


    @GetMapping("/spring/test-feign-go")
    public String testFeignGo() {
        return feignService.callGo();
    }
}
