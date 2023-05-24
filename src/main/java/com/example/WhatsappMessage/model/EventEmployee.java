package com.example.WhatsappMessage.model;

public class EventEmployee {
    private String statusCode;
    private String message;
    private ResultEmployee result;

    public EventEmployee() {
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

    public ResultEmployee getResult() {
        return result;
    }

    public void setResult(ResultEmployee result) {
        this.result = result;
    }

    public EventEmployee(String statusCode, String message, ResultEmployee result) {
        this.statusCode = statusCode;
        this.message = message;
        this.result = result;
    }
}
