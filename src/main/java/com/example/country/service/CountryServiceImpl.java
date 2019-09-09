package com.example.country.service;

import com.example.country.dto.CountryDTOOut;
import com.example.country.exception.*;
import com.example.country.repository.CountryRepository;
import com.example.country.repository.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    private final LanguageRepository languageRepository;

    @Override
    public CountryDTOOut getCountryNameByCodeAndLocalization(String code, String localization) {

        Integer countryId = countryRepository.getIdByCode(code)
            .orElseThrow(() -> new CountryNotFoundException("Country with code = " + code + " not found"));

        Integer languageId = languageRepository.getIdByLocalization(localization)
            .orElseThrow(() -> new LanguageNotFoundException("Language = " + localization + " not found"));

        String translate = countryRepository.getTranslateByCountryIdAndLanguageId(countryId, languageId)
            .orElseThrow(() -> new TranslateNotFoundException("Translate not found"));

        return new CountryDTOOut(translate);
    }

}
