package com.example.country.service;


import com.example.country.dto.CountryDTOOut;

public interface CountryService {

    CountryDTOOut getByCodeAndLocalization(String code, String localization);
}
