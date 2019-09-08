package com.example.country.service;

import com.example.country.dto.CountryDTOOut;
import com.example.country.entity.*;
import com.example.country.exception.*;
import com.example.country.repository.CLRepository;
import com.example.country.repository.CountryRepository;
import com.example.country.repository.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    private final LanguageRepository languageRepository;

    private final CLRepository clRepository;

    @Override
    public CountryDTOOut getCountryNameByCodeAndLocalization(String code, String localization) {

        Country country = countryRepository.findByCode(code).
                orElseThrow(() -> new CountryNotFoundException("Country with code = " + code + " not found"));


        Language language = languageRepository.findByLocalization(localization)
                .orElseThrow(() -> new LanguageNotFoundException("Language = " + localization + " not found"));



        CountryLanguage countryLanguage =
                clRepository.findByCountryIdAndLanguageId(country.getId(), language.getId())
                        .orElseThrow(() -> new TranslateNotFoundException("Translate not found"));

        return new CountryDTOOut(countryLanguage.getTranslate());
    }

}
