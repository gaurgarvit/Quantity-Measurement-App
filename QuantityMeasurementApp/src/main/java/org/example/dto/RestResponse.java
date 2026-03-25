package org.example.dto;

public class RestResponse<T> {
    public boolean success;
    public String message;
    public T data;

    public RestResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
