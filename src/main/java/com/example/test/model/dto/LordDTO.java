package com.example.test.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LordDTO {
    private Long id;
    private String name;
    private Integer age;
}
