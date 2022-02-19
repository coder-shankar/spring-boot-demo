package com.example.demo.github;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaSenderConsumer {

    @KafkaListener(topics = "test-topic2", groupId = "group_id")
    public void consume(String message) {

        System.out.println("message at sender = ");
        System.out.println("message = " + message);
    }

}
