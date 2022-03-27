package com.example.demo.kafka;

import com.example.demo.GlobalExceptionHandler;
import com.example.demo.github.Repo;
import com.example.demo.github.RepoDto;
import com.example.demo.github.RepoService;
import com.example.demo.github.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class KafkaConsumer {

    @Autowired
    private RepoService service;

    @Autowired
    @Qualifier("Raas")
    private WebClient client;

    @Autowired
    private TestService testService;


//    @KafkaListener(topics = "test-topic", groupId = "group_id")
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