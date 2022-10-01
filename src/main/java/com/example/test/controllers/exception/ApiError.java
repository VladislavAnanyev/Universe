package com.example.test.controllers.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class ApiError {
    private String title;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;

    public ApiError() {}

    public ApiError(String title, String message){
        this.title = title;
        this.message = message;
    }

    public ApiError(String title, String message, List<String> errors) {
        this.title = title;
        this.message = message;
        this.errors = errors;
    }
}