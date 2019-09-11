package com.example.country.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Language {

    private Integer id;
    private String localization;

    public Language(String localization) {
        this.localization = localization;
    }
}
