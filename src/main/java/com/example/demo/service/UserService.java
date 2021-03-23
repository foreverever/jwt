package com.example.demo.service;

import com.example.demo.db.UserRepository;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void join(User joinUser) {
        userRepository.insert(joinUser);
    }

    public void delete(long id) {
        userRepository.deleteUser(id);
    }

    public User login(String id, String password) {
        Optional<User> maybeUser = userRepository.findUserById(id);
        if (!maybeUser.isPresent()) throw new IllegalStateException();
        if (!maybeUser.get().isValidUser(id, password)) throw new RuntimeException();
        return maybeUser.get();
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
