package com.example.test.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name = "LORDS")
@Getter
@Setter
public class Lord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private Integer age;

    @OneToMany(mappedBy = "lord", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Planet> planets;

    public Lord() {}

    public Lord(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


    public Lord(String name, Integer age, Set<Planet> planets) {
        this.name = name;
        this.age = age;
        this.planets = planets;
    }



}
