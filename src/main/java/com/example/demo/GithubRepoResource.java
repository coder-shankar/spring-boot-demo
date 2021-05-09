package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController("github")
public class GithubRepoResource {

    @Autowired
    private RepoService service;

    private static Repo mapper(RepoDto repoDto) {
        Repo repo = new Repo();
        repo.setName(repoDto.getName());

        return repo;
    }

    @RequestMapping(value = "/username/{name}", method = RequestMethod.GET)
    public List<Repo> getName(@PathVariable("name") String userName) {

        WebClient client = WebClient.builder()
                                    .baseUrl("https://api.github.com/users")
                                    .build();
        List<RepoDto> repos = client.get()
                                    .uri("/" + userName + "/repos")
                                    .retrieve()
                                    .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(RuntimeException::new))
                                    .bodyToFlux(RepoDto.class)
                                    .collect(Collectors.toList())
                                    .block();


        List<Repo> collect = repos.stream()
                                  .map(GithubRepoResource::mapper)
                                  .collect(Collectors.toList());

        return service.save(collect);
    }


}
