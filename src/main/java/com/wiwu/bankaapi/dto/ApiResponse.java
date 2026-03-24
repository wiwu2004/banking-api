package com.wiwu.bankaapi.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Objects;

public class ApiResponse<T> {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T date;

    public  ApiResponse(){}

    public  ApiResponse(int status, String message, T date){
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.date = date;

    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ApiResponse<?> that)) return false;
        return getStatus() == that.getStatus() && Objects.equals(getTimestamp(), that.getTimestamp()) && Objects.equals(getMessage(), that.getMessage()) && Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTimestamp(), getStatus(), getMessage(), getDate());
    }
}

