package com.example.demo.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void print(String message) {
        System.out.println("message = " + message);

        kafkaTemplate.send("test-topic2", "hello ");
    }
}
