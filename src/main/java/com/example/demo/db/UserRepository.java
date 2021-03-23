package com.example.demo.db;

import com.example.demo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private static Map<Long, User> userDb = new HashMap<>();
    private AtomicLong atomicLong = new AtomicLong();

    public void insert(User user) {
        long id = atomicLong.incrementAndGet();
        user.addId(id);
        userDb.put(id, user);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userDb.values());
    }

    public void deleteUser(long id) {
        userDb.remove(id);
    }

    public Optional<User> findUserById(String id) {
        for (User user : userDb.values()) {
            if (user.isEqualUserId(id)) {
                return Optional.ofNullable(user);
            }
        }
        return Optional.empty();
    }
}
