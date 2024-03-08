package com.wpCorp.dsCommerce.Resource.Exceptions;

import java.time.Instant;

public class StandarError {
    private Instant timeStamp;
    private String path;
    private String message;
    private String error;
    private Integer status;

    public StandarError() {
    }

    public StandarError(Instant timeStamp, String path, String message, String error, Integer status) {
        this.timeStamp = timeStamp;
        this.path = path;
        this.message = message;
        this.error = error;
        this.status = status;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
