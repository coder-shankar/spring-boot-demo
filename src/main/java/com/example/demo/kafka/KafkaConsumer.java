package com.example.demo.kafka;

import com.example.demo.GlobalExceptionHandler;
import com.example.demo.github.*;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
public class KafkaConsumer {

    @Autowired
    private RepoService service;

    @Autowired
    @Qualifier("Raas")
    private WebClient client;

    @Autowired
    private TestService testService;


    @KafkaListener(topics = "test-topic", groupId = "group_id")
    public void consume(String message) {
        System.out.println("message = " + message);

        String userName = message;


        Flux<RepoDto> repoDtoFlux = client.get()
                .uri('/' + userName + "/repos")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, GlobalExceptionHandler::handle4xxError)
                .onStatus(HttpStatus::is5xxServerError, GlobalExceptionHandler::handle5xxError)
                .bodyToFlux(RepoDto.class)
                .doOnError(RuntimeException::new);

        try {
            repoDtoFlux.map(KafkaConsumer::mapper)
                    .subscribe(service::save);

            mockPrivateMethod();
        } catch (Exception e) {
            System.out.println("e = " + e);
        }


    }

    private static Repo mapper(RepoDto repoDto) {
        Repo repo = new Repo();
        repo.setName(repoDto.getName());

        return repo;
    }


    private void mockPrivateMethod() {
        testService.print("hello");
    }
}