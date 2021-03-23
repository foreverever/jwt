package com.example.demo.support;

public class Result {
    private String resultMessage;

    public Result(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public static Result ok(String message) {
        return new Result(message);
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
