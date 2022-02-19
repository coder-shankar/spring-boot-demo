package com.example.demo.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;


    //    @Value("${spring.kafka.topic.name}")
    private String topic = "test-topic";

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String model) {
        kafkaTemplate.send(topic, model);
        kafkaTemplate.flush();
    }

}