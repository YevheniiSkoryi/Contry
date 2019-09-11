package com.example.country.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryLanguage {

    private Integer id;

    private Country country;

    private Language language;

    private String translate;

    public CountryLanguage(Country country, Language language, String translate) {
        this.country = country;
        this.language = language;
        this.translate = translate;
    }
}

