package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> toBadRequestException(BadRequestException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.valueOf(ex.getCode()));
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ExceptionResponse> toInternalServerException(InternalServerException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.valueOf(ex.getCode()));
    }


    public static Mono<? extends Throwable> handle4xxError(ClientResponse clientResponse) {
        Mono<String> stringMono = clientResponse.bodyToMono(String.class);

        return stringMono.flatMap(error -> Mono.error(new BadRequestException(error, 400)));
    }

    public static Mono<? extends Throwable> handle5xxError(ClientResponse clientResponse) {
        Mono<String> stringMono = clientResponse.bodyToMono(String.class);


        return stringMono.flatMap(error -> Mono.error(new InternalServerException(error, 500)));
    }
}
