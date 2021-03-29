package com.example.demo.web.api;

import com.example.demo.domain.User;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    private static User TEST_USER;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private UserService userService;

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
    void loginTest() throws Exception {
        when(userService.login(anyString(), anyString())).thenReturn(TEST_USER);
        when(jwtService.createToken(TEST_USER)).thenReturn("tokenValue");

        String userJson = "{\"userId\":\"kts327\",\"password\":\"123456\"}";

        MvcResult result = mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        String token = result.getResponse().getHeader("Authorization");
        assertThat(token).isEqualTo("tokenValue");
    }
}