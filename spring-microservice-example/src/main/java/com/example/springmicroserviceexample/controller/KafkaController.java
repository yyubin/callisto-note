package com.example.springmicroserviceexample.controller;

import com.example.springmicroserviceexample.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    @GetMapping("/spring/kafka/{mention}")
    public void kafkaTestController(@PathVariable String mention) {
        kafkaProducer.sendMessage(mention);
    }

}
