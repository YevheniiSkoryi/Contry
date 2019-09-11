package com.example.country.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryDTOOut {

    @JsonProperty("id")
    private String code;

    @JsonProperty("name")
    private String translate;
}
