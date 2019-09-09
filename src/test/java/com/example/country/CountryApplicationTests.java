package com.example.country;

import com.example.country.exception.CountryNotFoundException;
import com.example.country.exception.LanguageNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryApplicationTests {


    @Test
    public void contextLoads() {
    }


    @Test
    public void testGetCountryWithCode001AndLocalizationEN(){
//        List<Language> languages = new ArrayList<>();
//        languages.add(new Language("EN","Ukraine"));
//        Country country = new Country("001");
//        country.setLanguages(languages);
//
//        Optional<Country> optionalCountry = Optional.of(country);
//        when(countryRepository.findByCode("001")).thenReturn(optionalCountry);
//
//        String nameOfCountry = countryService.getCountryNameByCodeAndLocalization("001","EN");
//
//        assertEquals("Ukraine", nameOfCountry);
    }

    @Test(expected = CountryNotFoundException.class)
    public void testGetCountryWithCode003AndLocalizationEN(){
//        List<Language> languages = new ArrayList<>();
//        languages.add(new Language("EN","Ukraine"));
//        Country country = new Country("001");
//        country.setLanguages(languages);
//
//        Optional<Country> optionalCountry = Optional.of(country);
//        when(countryRepository.findByCode("001")).thenReturn(optionalCountry);
//
//        String nameOfCountry = countryService.getCountryNameByCodeAndLocalization("003","EN");
    }

    @Test(expected = LanguageNotFoundException.class)
    public void testGetCountryWithCode001AndLocalizationFV(){
//        List<Language> languages = new ArrayList<>();
//        languages.add(new Language("EN","Ukraine"));
//        Country country = new Country("001");
//        country.setLanguages(languages);
//
//        Optional<Country> optionalCountry = Optional.of(country);
//        when(countryRepository.findByCode("001")).thenReturn(optionalCountry);
//
//        String nameOfCountry = countryService.getCountryNameByCodeAndLocalization("001","FI");
    }
}
