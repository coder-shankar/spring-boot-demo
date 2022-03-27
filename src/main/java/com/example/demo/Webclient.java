package com.example.demo;
 
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class Webclient {

    @Bean
    @Qualifier("Raas")
    public WebClient getWebclient() {
        return WebClient.builder()
                        .baseUrl("https://api.github.com/users")
                        .defaultHeader("clientid", "")
                        .defaultHeader("clientsecret", "")
                        .build();
    }

    @Bean
    @Qualifier("Analytics")
    public WebClient getWebclient2() {
        return WebClient.builder()
                        .baseUrl("https://api.github.com/users")
                        .defaultHeader("clientid", "")
                        .defaultHeader("clientsecret", "")
                        .build();
    }
}
