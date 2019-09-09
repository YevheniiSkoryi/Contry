package com.example.country.controller;

import com.example.country.dto.CountryDTOOut;
import com.example.country.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@AllArgsConstructor
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/{code}")
    public CountryDTOOut getCountryName(
            @PathVariable("code") final String code,
            @RequestParam(defaultValue = "") final String localization
    ) {

        return countryService.getCountryNameByCodeAndLocalization(code, localization);
    }
}
