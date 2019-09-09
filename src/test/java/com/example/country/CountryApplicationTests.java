package com.example.country;

import com.example.country.dto.CountryDTOOut;
import com.example.country.exception.CountryNotFoundException;
import com.example.country.exception.LanguageNotFoundException;
import com.example.country.exception.TranslateNotFoundException;
import com.example.country.repository.CountryRepository;
import com.example.country.repository.LanguageRepository;
import com.example.country.service.CountryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryApplicationTests {


    @Mock
    private CountryRepository countryRepository;

    @Mock
    private LanguageRepository languageRepository;

    @InjectMocks
    private CountryServiceImpl countryService;

    @Test
    public void contextLoads() {
    }


    @Test
    public void testGetCountryWithCode001AndLocalizationEN(){

        when(countryRepository.getIdByCode("001")).thenReturn(Optional.of(1));
        when(languageRepository.getIdByLocalization("EN")).thenReturn(Optional.of(1));
        when(countryRepository.getTranslateByCountryIdAndLanguageId(1,1)).thenReturn(Optional.of("Ukraine"));

        CountryDTOOut country = countryService.getCountryNameByCodeAndLocalization("001","EN");

        CountryDTOOut countryDTOOut = new CountryDTOOut("Ukraine");
        assertEquals(countryDTOOut, country);
    }

    @Test(expected = CountryNotFoundException.class)
    public void testGetCountryWithCode004AndLocalizationEN(){
        when(countryRepository.getIdByCode("004")).thenThrow(CountryNotFoundException.class);

        CountryDTOOut country = countryService.getCountryNameByCodeAndLocalization("004","EN");
    }

    @Test(expected = LanguageNotFoundException.class)
    public void testGetCountryWithCode001AndLocalizationFV(){
        when(countryRepository.getIdByCode("001")).thenReturn(Optional.of(1));
        when(languageRepository.getIdByLocalization("FV")).thenThrow(LanguageNotFoundException.class);

        CountryDTOOut country = countryService.getCountryNameByCodeAndLocalization("001","FV");
    }


    @Test(expected = TranslateNotFoundException.class)
    public void testGetCountryWithCode003AndLocalizationEN(){

        when(countryRepository.getIdByCode("003")).thenReturn(Optional.of(3));
        when(languageRepository.getIdByLocalization("EN")).thenReturn(Optional.of(1));
        when(countryRepository.getTranslateByCountryIdAndLanguageId(3,1)).thenThrow(TranslateNotFoundException.class);

        CountryDTOOut country = countryService.getCountryNameByCodeAndLocalization("003","EN");
    }
}
