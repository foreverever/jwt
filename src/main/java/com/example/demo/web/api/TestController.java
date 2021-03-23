package com.example.demo.web.api;

import com.example.demo.support.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("")
    public Result test() {
        return Result.ok("TEST");
    }
}
