package com.example.test.model.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateLordRequest {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Min(value = 0)
    private Integer age;

}
