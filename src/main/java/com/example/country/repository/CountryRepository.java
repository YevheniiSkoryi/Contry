package com.example.country.repository;

import java.util.Optional;

public interface CountryRepository {

    Optional<Integer> getIdByCode(String code);

    Optional<String> getTranslateByCountryIdAndLanguageId(Integer countryId, Integer languageId);
}
