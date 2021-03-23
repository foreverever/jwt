package com.example.demo.db;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class JwtRepository {
    private static Map<Long, String> jwtTokenDb = new HashMap<>();


    public void save(long id, String jwt) {
        jwtTokenDb.put(id, jwt);
    }
}
