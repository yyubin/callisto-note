package com.example.springmicroserviceexample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private static final String TOPIC = "example-topic";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message){
        this.kafkaTemplate.send(TOPIC, message);
    }

}
