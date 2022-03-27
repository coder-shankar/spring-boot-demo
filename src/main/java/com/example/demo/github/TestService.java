package com.example.demo.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private String topic2 ="";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void print(String message) {
        System.out.println("message = " + message);

        kafkaTemplate.send(topic2, "hello ");
    }
}
