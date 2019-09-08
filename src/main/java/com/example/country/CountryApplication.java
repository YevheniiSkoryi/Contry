package com.example.country;

import com.example.country.entity.Country;
import com.example.country.entity.CountryLanguage;
import com.example.country.entity.Language;
import com.example.country.repository.CLRepository;
import com.example.country.repository.CountryRepository;
import com.example.country.repository.LanguageRepository;
import com.example.country.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@AllArgsConstructor
public class CountryApplication  {

    private CountryRepository countryRepository;

    private LanguageRepository languageRepository;

    private CLRepository countryLanguageRepo;

    public static void main(String[] args) {
        SpringApplication.run(CountryApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//
//        Country country = new Country("001");
//        Country country1 = new Country("002");
//
//        Language language = new Language("UK");
//        Language language1 = new Language("RU");
//        Language language2 = new Language("EN");
//
//        countryRepository.save(country);
//        countryRepository.save(country1);
//        languageRepository.save(language);
//        languageRepository.save(language1);
//        languageRepository.save(language2);
//
//        CountryLanguage countryLanguage = new CountryLanguage(country, language, "Україна");
//        CountryLanguage countryLanguage1 = new CountryLanguage(country, language1, "Украина");
//        CountryLanguage countryLanguage2 = new CountryLanguage(country, language2, "Ukraine");
//        CountryLanguage countryLanguage3 = new CountryLanguage(country1, language, "Фінляндія");
//        CountryLanguage countryLanguage4 = new CountryLanguage(country1, language1, "Финляндия");
//        CountryLanguage countryLanguage5 = new CountryLanguage(country1, language2, "Finland");
//
//        countryLanguageRepo.save(countryLanguage);
//        countryLanguageRepo.save(countryLanguage1);
//        countryLanguageRepo.save(countryLanguage2);
//        countryLanguageRepo.save(countryLanguage3);
//        countryLanguageRepo.save(countryLanguage4);
//        countryLanguageRepo.save(countryLanguage5);
//
//    }
}
