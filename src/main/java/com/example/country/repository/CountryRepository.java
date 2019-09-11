package com.example.country.repository;

import com.example.country.dto.CountryDTOOut;

import java.util.Optional;

public interface CountryRepository {

    Optional<Integer> getIdByCode(String code);

    Optional<CountryDTOOut> getTranslateByCountryIdAndLanguageId(Integer countryId, Integer languageId);
}
