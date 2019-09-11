package com.example.country.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    private Integer id;
    private String code;

    public Country(String code) {
        this.code = code;
    }
}
