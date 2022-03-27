package com.example.demo.github;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.stereotype.Service;

@Service
public class KafkaSenderConsumer {

    KafkaMessageListenerContainer<String, String> container;
    KafkaConsumer<String, String> kafkaConsumer;

    @KafkaListener(topics = "${com.esrx.dqm.mif.mtc.topic.push}", groupId = "group_id")
    public void consume(String message) {
        System.out.println("message at sender = ");
        System.out.println("message = " + message);
    }

    @KafkaListener(topics = "some_topic", groupId = "group_id")
    public void consume1(String message) {
        System.out.println("message at sender = ");
        System.out.println("message = " + message);
    }


}
