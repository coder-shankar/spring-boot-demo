package com.example.demo.auth;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return User.builder()
                   .username("shankar")
                   .password("$2a$10$A6pqUUqtXUnQnT5TrBvcv.OP6gJbnOqP82hywi2mWaq1ZznpXGlGm")
                   .roles(new String[]{})
                   .build();
    }

}
