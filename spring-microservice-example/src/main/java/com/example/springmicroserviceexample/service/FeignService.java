package com.example.springmicroserviceexample.service;

import com.example.springmicroserviceexample.feigninterface.GoClient;
import com.example.springmicroserviceexample.feigninterface.NodejsClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeignService {

    private final NodejsClient nodejsClient;
    private final GoClient goClient;

    public String callNodejs(){
        return nodejsClient.callNodejs();
    }

    public String callGo(){
        return goClient.testGo();
    }
}
