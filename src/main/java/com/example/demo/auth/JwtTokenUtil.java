package com.example.demo.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;


@Service
public class JwtTokenUtil {

    final String secret = "supersecret";

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                   .setClaims(new HashMap<>())
                   .setSubject(userDetails.getUsername())
                   .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                   .signWith(SignatureAlgorithm.HS512, secret)
                   .compact();
    }

    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }


    public <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
        Claims claims = Jwts.parser()
                            .setSigningKey(secret)
                            .parseClaimsJws(token)
                            .getBody();

        return claimResolver.apply(claims);

    }

    public boolean validate(String token, UserDetails userDetcails) {
        Date date = extractClaims(token, Claims::getExpiration);
        boolean before = date.before(new Date());

        if (!before && extractUserName(token).equals(userDetcails.getUsername())) {
            return true;
        }

        return false;
    }
}
