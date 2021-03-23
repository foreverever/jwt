package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class User {
    private long id;
    private final String userId;
    private final String name;
    private final String password;
    private final int age;

    public void addId(long id) {
        this.id = id;
    }

    public boolean isEqualUserId(String userId) {
        return this.userId.equals(userId);
    }

    public boolean isEqualPassword(String password) {
        return this.password.equals(password);
    }

    public boolean isValidUser(String userId, String password) {
        return isEqualUserId(userId) && isEqualPassword(password);
    }
}
