package com.example.demo.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService service;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword());
            authenticationManager.authenticate(authentication);

            final UserDetails userDetails = service.loadUserByUsername(authenticationRequest.getUserName());
            final String accessToken = jwtTokenUtil.generateToken(userDetails, 10L);
            final String refreshToken = jwtTokenUtil.generateToken(userDetails, 30L);

            return new AuthenticationResponse(accessToken, refreshToken);
        } catch (Exception e) {
            log.error("Error {}", e);
            throw new RuntimeException(e.getMessage());

        }

    }

}
