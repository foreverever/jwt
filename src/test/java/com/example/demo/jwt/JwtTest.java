package com.example.demo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtTest {

    private static String KEY = "testKey";

    @Test
    void create() {
        System.out.printf(createJwtToken());
    }

    @Test
    void JWTTest() {
        String jwt = createJwtToken();
        System.out.println("jwt : " + jwt);
        Map<String, Object> claim = verifyJwt(jwt);
        System.out.println("claim : " + claim);
    }

    @Test
    void checkValidToken() {
        String jwt = createJwtToken();

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(KEY.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(jwt)
                    .getBody();
            assertThat(claims.get("userId")).isEqualTo("kts327");
        } catch (ExpiredJwtException e) {
            System.out.printf(e.getMessage());
        }
    }

    private Map<String, Object> verifyJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey(KEY.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(jwt)
                .getBody();
    }

    private String createJwtToken() {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject("kts327@worldvision.or.kr")
                .claim("userId", "kts327")
                .signWith(SignatureAlgorithm.HS256, KEY.getBytes())
                .compact();
    }

    @Test
    void expiredTimeTest() {
        Date dt = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24));
        long a = System.currentTimeMillis() + (1000 * 60 * 60 * 24);
        System.out.printf(dt.toString());
        System.out.println(a);
    }
}
