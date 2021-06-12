package com.example.demo.github;

import com.example.demo.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController("github")
public class GithubRepoResource {

    @Autowired
    private RepoService service;

    @Autowired
    @Qualifier("Raas")
    private WebClient client;


    @Autowired
    @Qualifier("Analytics")
    private WebClient client1;

    private static Repo mapper(RepoDto repoDto) {
        Repo repo = new Repo();
        repo.setName(repoDto.getName());

        return repo;
    }


    @RequestMapping(value = "/username/{name}", method = RequestMethod.GET)
    public Flux<RepoDto> getName(@PathVariable("name") String userName) {
        Flux<RepoDto> repoDtoFlux = client.get()
                                          .uri('/' + userName + "/repos")
                                          .retrieve()
                                          .onStatus(HttpStatus::is4xxClientError, GlobalExceptionHandler::handle4xxError)
                                          .onStatus(HttpStatus::is5xxServerError,GlobalExceptionHandler::handle5xxError)
                                          .bodyToFlux(RepoDto.class)
                                          .doOnError(RuntimeException::new);



        repoDtoFlux.subscribe(repoDto -> {
            Repo mapper = GithubRepoResource.mapper(repoDto);
            service.save(mapper);
        });

        return repoDtoFlux;
    }


}
