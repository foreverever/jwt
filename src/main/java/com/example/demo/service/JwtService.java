package com.example.demo.service;

import com.example.demo.db.JwtRepository;
import com.example.demo.domain.User;
import com.example.demo.exception.ExpiredException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String KEY = "TEST";
    private static final int ONE_DAY = (1000 * 60 * 60 * 24);
    private static final int ONE_MINUTE = (1000 * 60);

    @Autowired
    private JwtRepository jwtRepository;

    public String createToken(User loginUser) {
        String jwt = Jwts.builder()
                .setHeaderParam("typ", "jwt")
                .claim("userId", loginUser.getUserId())
                .signWith(SignatureAlgorithm.HS256, KEY.getBytes())
                .setExpiration(new Date(System.currentTimeMillis() + ONE_MINUTE))
                .compact();
        return jwt;
    }

    public boolean isValid(String jwt) {
        try {
            Jwts.parser()
                    .setSigningKey(KEY.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(jwt);
            return true;
        } catch (ExpiredJwtException e) {
            throw new ExpiredException("Token Expired");
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}
