package com.example.WhatsappMessage.model;

import java.util.List;

public class Event {
    private String statusCode;
    private String message;
    private Result result;

    public Event() {
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Event(String statusCode, String message, Result result) {
        this.statusCode = statusCode;
        this.message = message;
        this.result = result;
    }
}
