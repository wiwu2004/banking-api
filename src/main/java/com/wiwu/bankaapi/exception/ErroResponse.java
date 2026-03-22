package com.wiwu.bankaapi.exception;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class ErroResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> erros;

    public ErroResponse(){}

    public ErroResponse(LocalDateTime timestamp, int status, String error, String message, String path, Map<String, String > erros){
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.erros = erros;

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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getErros() {
        return erros;
    }

    public void setErros(Map<String, String> erros) {
        this.erros = erros;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ErroResponse that)) return false;
        return getStatus() == that.getStatus() && Objects.equals(getTimestamp(), that.getTimestamp()) && Objects.equals(getError(), that.getError()) && Objects.equals(getMessage(), that.getMessage()) && Objects.equals(getPath(), that.getPath()) && Objects.equals(getErros(), that.getErros());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTimestamp(), getStatus(), getError(), getMessage(), getPath(), getErros());
    }
}
