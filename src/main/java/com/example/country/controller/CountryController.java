package com.example.country.controller;

import com.example.country.dto.CountryDTOOut;
import com.example.country.exception.LocalizationNotValid;
import com.example.country.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

        if(localization.isEmpty())
            throw new LocalizationNotValid("Localization not valid");

        return countryService.getByCodeAndLocalization(code, localization);
    }
}
