package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InternalServerException extends RuntimeException {
    private String message;
    private int code;
}
