package com.example.demo.service;

import com.example.demo.db.JwtRepository;
import com.example.demo.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class JwtService {
    private static final String KEY = "TEST";

    @Autowired
    private JwtRepository jwtRepository;

    public String createToken(User loginUser) {
        String jwt = Jwts.builder()
                .setHeaderParam("typ", "jwt")
                .claim("userId", loginUser.getUserId())
                .signWith(SignatureAlgorithm.HS256, KEY.getBytes())
                .compact();
        jwtRepository.save(loginUser.getId(), jwt);
        return jwt;
    }

    public boolean isValid(String jwt) {
        try {
            Jwts.parser()
                    .setSigningKey(KEY.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(jwt);
            return true;
        } catch (RuntimeException e) {
            System.out.printf(e.getMessage());
        }
        return false;
    }
}
