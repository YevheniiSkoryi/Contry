package com.example.country;

import com.example.country.entity.Country;
import com.example.country.entity.Language;
import com.example.country.exception.CountryNotFoundException;
import com.example.country.exception.LanguageNotFoundException;
import com.example.country.repository.CountryRepository;
import com.example.country.service.CountryService;
import com.example.country.service.CountryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryApplicationTests {

    @InjectMocks
    private CountryServiceImpl countryService;

    @Mock
    private CountryRepository countryRepository;

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
