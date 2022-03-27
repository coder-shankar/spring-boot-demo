package com.example.demo.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController("github")
public class GithubRepoResource {

    @Autowired
    private RepoService service;

    @Autowired
    @Qualifier("Raas")
    private WebClient client;

//    @Autowired
//    KafkaProducer kafkaProducer;

    @Autowired
    @Qualifier("Analytics")
    private WebClient client1;


    @RequestMapping(value = "/username/{name}", method = RequestMethod.GET)
    public Flux<RepoDto> getName(@PathVariable("name") String userName) {

//        kafkaProducer.send(userName);

        return null;
    }


}
