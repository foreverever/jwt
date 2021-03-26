package com.example.demo.advice;

import com.example.demo.exception.ExpiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JwtAdvice {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public String tokenExpiredHandler(ExpiredException e) {
        logger.info("[ERROR] :: {} ", e.getMessage());
        return e.getMessage();
    }
}
