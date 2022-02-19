package com.example.demo.github;

import com.example.demo.kafka.KafkaConsumer;
import com.example.demo.kafka.KafkaProducer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.Producer;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.concurrent.TimeUnit;
//
//@EmbeddedKafka
//@SpringBootTest(properties = "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)

@SpringBootTest
@EmbeddedKafka(topics = "test-topic")
public class GithubKafkaConsumerTest {
    private final String TOPIC_NAME = "test-topic";

    private Producer<String, String> producer;

    @Captor
    ArgumentCaptor<String> recordCaptor;


    @Autowired
    KafkaConsumer kafkaConsumer;

    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    @Qualifier("Raas")
    WebClient client;

    @SpyBean
    private RepoService service;

    @SpyBean
    private TestService testService;

    @SpyBean
    private KafkaSenderConsumer senderConsumer;


    @Test
    public void testSomething() throws InterruptedException {
        kafkaProducer.send("rkuhn");

//
//        Mockito.verify(kafkaConsumer, Mockito.timeout(40000l).times(1)).consume(recordCaptor.capture());
//        String value = recordCaptor.getValue();
//        System.out.println("value = " + value);
        TimeUnit.SECONDS.sleep(15);

        Mockito.verify(service, Mockito.atLeast(1)).save(Mockito.any());
        Mockito.verify(testService, Mockito.atLeast(1)).print(Mockito.any());
        Mockito.verify(senderConsumer).consume("hello ");

    }
}
