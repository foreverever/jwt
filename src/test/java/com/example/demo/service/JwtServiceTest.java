package com.example.demo.service;

import com.example.demo.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JwtServiceTest {
    private static User TEST_USER;

    @Autowired
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        TEST_USER = User.builder()
                .id(1L)
                .userId("kts327")
                .password("123456")
                .name("testName")
                .age(20)
                .build();
    }

    @Test
    void createTokenTest() {
        String jwt = jwtService.createToken(TEST_USER);
        assertThat(jwt).isNotNull();
    }

    @Test
    void isValidToken() {
        String jwt = jwtService.createToken(TEST_USER);
        assertThat(jwtService.isValid(jwt)).isTrue();
    }

    @Test
    void isNotValidToken() {
        assertThat(jwtService.isValid("notValidValue")).isFalse();
    }
}