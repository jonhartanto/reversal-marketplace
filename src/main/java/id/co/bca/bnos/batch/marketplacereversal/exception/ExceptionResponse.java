package id.co.bca.bnos.batch.marketplacereversal.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import id.co.bca.bnos.batch.marketplacereversal.util.DateTimeSerializer;

import java.time.LocalDateTime;

public class ExceptionResponse {

    @JsonSerialize(using = DateTimeSerializer.class)
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public ExceptionResponse(Integer status, String error, String message, String path) {
        super();
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    
}