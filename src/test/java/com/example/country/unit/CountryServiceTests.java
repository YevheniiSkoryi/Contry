package com.example.country.unit;

import com.example.country.dto.CountryDTOOut;
import com.example.country.exception.CountryNotFoundException;
import com.example.country.exception.LanguageNotFoundException;
import com.example.country.exception.TranslateNotFoundException;
import com.example.country.repository.CountryRepository;
import com.example.country.repository.LanguageRepository;
import com.example.country.service.CountryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CountryServiceTests {
    private final static int COUNTRY_ID = 1;
    private final static String COUNTRY_CODE = "001";
    private final static String UNKNOWN_COUNTRY_CODE = "004";

    private final static int LANGUAGE_ID = 10;
    private final static String LANGUAGE_CODE = "UK";
    private final static String UNKNOWN_LANGUAGE_CODE = "FI";

    private static final String EXPECTED_TRANSLATE = "Ukraine";

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private LanguageRepository languageRepository;

    @InjectMocks
    private CountryServiceImpl countryService;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetCountryWithCode001AndLocalizationEN() {

        when(countryRepository.getIdByCode(COUNTRY_CODE))
            .thenReturn(Optional.of(COUNTRY_ID));
        when(languageRepository.getIdByLocalization(LANGUAGE_CODE))
            .thenReturn(Optional.of(LANGUAGE_ID));

        CountryDTOOut expected = new CountryDTOOut(COUNTRY_CODE, EXPECTED_TRANSLATE);
        when(countryRepository.getTranslateByCountryIdAndLanguageId(COUNTRY_ID, LANGUAGE_ID))
            .thenReturn(Optional.of(expected));

        CountryDTOOut country = countryService.getByCodeAndLocalization(COUNTRY_CODE, LANGUAGE_CODE);

        assertEquals(expected, country);
    }

    @Test
    void testGetCountryWithCode004AndLocalizationEN() {
        when(countryRepository.getIdByCode(UNKNOWN_COUNTRY_CODE))
            .thenThrow(new CountryNotFoundException("country not found"));

        CountryNotFoundException exception = assertThrows(
            CountryNotFoundException.class,
            () -> countryService.getByCodeAndLocalization(UNKNOWN_COUNTRY_CODE, LANGUAGE_CODE)
        );

        assertEquals("country not found", exception.getMessage());
    }

    @Test
    void testGetCountryWithCode001AndLocalizationFV() {
        when(countryRepository.getIdByCode(COUNTRY_CODE))
            .thenReturn(Optional.of(1));
        when(languageRepository.getIdByLocalization(UNKNOWN_LANGUAGE_CODE))
            .thenThrow(new LanguageNotFoundException("language not found"));

        LanguageNotFoundException exp = assertThrows(
            LanguageNotFoundException.class,
            () -> countryService.getByCodeAndLocalization(COUNTRY_CODE, UNKNOWN_LANGUAGE_CODE)
        );

        assertEquals("language not found", exp.getMessage());
    }

    @Test
    void testGetCountryWithCode003AndLocalizationEN() {

        when(countryRepository.getIdByCode(COUNTRY_CODE))
            .thenReturn(Optional.of(COUNTRY_ID));
        when(languageRepository.getIdByLocalization(LANGUAGE_CODE))
            .thenReturn(Optional.of(LANGUAGE_ID));
        when(countryRepository.getTranslateByCountryIdAndLanguageId(COUNTRY_ID, LANGUAGE_ID))
            .thenThrow(new TranslateNotFoundException("Translate not found"));

        TranslateNotFoundException exp = assertThrows(
            TranslateNotFoundException.class,
            () -> countryService.getByCodeAndLocalization(COUNTRY_CODE, LANGUAGE_CODE)
        );
        assertEquals("Translate not found", exp.getMessage());
    }
}
