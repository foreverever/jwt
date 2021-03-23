package com.example.demo.web.api;

import com.example.demo.domain.User;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import com.example.demo.support.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("")
    public ResponseEntity<User> get() {
        User sample = User.builder()
                .name("test")
                .age(10)
                .build();
        return new ResponseEntity<>(sample, HttpStatus.OK);
    }

    @PostMapping("")
    public Result join(@RequestBody User joinUser) {
        userService.join(joinUser);
        return Result.ok("user create success!!");
    }

    @GetMapping("/list")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id) {
        userService.delete(id);
        return Result.ok("user delete success!!");
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpServletResponse response) {
        User loginUser = userService.login(user.getUserId(), user.getPassword());
        String jwt = jwtService.createToken(loginUser);
        response.setHeader("Authorization", jwt);
        return Result.ok("login success!!");
    }
}
