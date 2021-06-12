package com.example.demo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
public class AuthenticationRequest {
    private String userName;
    private String password;
}
