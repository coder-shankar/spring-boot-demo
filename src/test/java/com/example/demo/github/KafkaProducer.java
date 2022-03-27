package com.example.demo.github;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    private String topic = "TP.MIF.CUSTOMER.INBOUND";

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String model) {
        kafkaTemplate.send(topic, model);
        kafkaTemplate.send("some_topic", "how are you");
        kafkaTemplate.flush();
    }

}