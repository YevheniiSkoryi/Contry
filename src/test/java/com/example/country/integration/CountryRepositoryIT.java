package com.example.country.integration;

import com.example.country.configuration.PostgresConf;
import com.example.country.dto.CountryDTOOut;
import com.example.country.repository.CountryRepository;
import com.example.country.repository.impl.CountryRepositoryImpl;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.*;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class CountryRepositoryIT {

    @Language("PostgreSQL")
    private static final String INSERT_COUNTRY = "INSERT INTO country(code) VALUES ('001')";
    @Language("PostgreSQL")
    private static final String INSERT_LANGUAGE = "INSERT INTO language(localization) VALUES ('UK')";
    @Language("PostgreSQL")
    private static final String INSERT_COUNTRY_LANGUAGE = "INSERT INTO country_language(country_id, language_id, translate) VALUES (1, 1, 'Ukraine')";

    private static final String COUNTRY_VALID_CODE_001 = "001";
    private static final String COUNTRY_INVALID_CODE_002 = "002";
    private static final int COUNTRY_ID_1 = 1;

    private static final int LANGUAGE_VALID_ID_1 = 1;
    private static final int LANGUAGE_INVALID_ID_2 = 2;

    private static final String EXPECTED_TRANSLATE = "Ukraine";

    private CountryRepository countryRepository;
    private Liquibase liquibase;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @BeforeAll
    void init() {
        countryRepository = new CountryRepositoryImpl(PostgresConf.namedParameterJdbcTemplate);
        liquibase = PostgresConf.liquibase;
        namedParameterJdbcTemplate = PostgresConf.namedParameterJdbcTemplate;
    }

    @BeforeEach
    void beforeEach() throws LiquibaseException {
        liquibase.update(new Contexts("test"));
    }

    @AfterEach
    void afterEach() throws DatabaseException {
        liquibase.dropAll();
    }

    @Test
    @DisplayName("test get by code from table country without insertion and invalid code")
    void testGetByCodeWithoutInsertionAndInvalidCode() {
        Optional<Integer> id = countryRepository.getIdByCode(COUNTRY_INVALID_CODE_002);

        assertFalse(id.isPresent());
    }

    @Test
    @DisplayName("test get by code from table country with insertion and invalid code")
    void testGetByCodeWithInsertion() {
        namedParameterJdbcTemplate.update(INSERT_COUNTRY, Collections.emptyMap());
        Optional<Integer> id = countryRepository.getIdByCode(COUNTRY_INVALID_CODE_002);

        assertFalse(id.isPresent());
    }

    @Test
    @DisplayName("test get by code from table country without insertion and valid code")
    void testGetByCodeWithoutInsertion() {
        Optional<Integer> id = countryRepository.getIdByCode(COUNTRY_VALID_CODE_001);

        assertFalse(id.isPresent());
    }

    @Test
    @DisplayName("test get by code from table country with insertion and valid code")
    void testGetByCode() {
        namedParameterJdbcTemplate.update(INSERT_COUNTRY, Collections.emptyMap());
        Optional<Integer> countryId = countryRepository.getIdByCode(COUNTRY_VALID_CODE_001);

        assertTrue(countryId.isPresent());
        assertEquals(COUNTRY_ID_1, countryId.get());
    }

    @Test
    @DisplayName("test get translate by country ID and language ID from table country_language with insertion, " +
        "valid countryID, invalid LanguageID")
    void testGetTranslateByCountryIdAndLanguageIdWithInsertion() {
        namedParameterJdbcTemplate.update(INSERT_COUNTRY, Collections.emptyMap());
        namedParameterJdbcTemplate.update(INSERT_LANGUAGE, Collections.emptyMap());
        namedParameterJdbcTemplate.update(INSERT_COUNTRY_LANGUAGE, Collections.emptyMap());

        Optional<CountryDTOOut> countryDTOOut =
            countryRepository.getTranslateByCountryIdAndLanguageId(COUNTRY_ID_1, LANGUAGE_INVALID_ID_2);

        assertFalse(countryDTOOut.isPresent());
    }

    @Test
    @DisplayName("test get translate by country ID and language ID from table country_language without insertion")
    void testGetTranslateByCountryIdAndLanguageIdWithoutInsertion() {
        Optional<CountryDTOOut> countryDTOOut =
            countryRepository.getTranslateByCountryIdAndLanguageId(COUNTRY_ID_1, LANGUAGE_VALID_ID_1);

        assertFalse(countryDTOOut.isPresent());
    }

    @Test
    @DisplayName("test get translate by country ID and language ID from table country_language without insertion " +
        "and invalid languageID")
    void testGetTranslateByCountryIdAndLanguageIdWithoutInsertionAndInvalidLanguageID() {
        Optional<CountryDTOOut> countryDTOOut =
            countryRepository.getTranslateByCountryIdAndLanguageId(COUNTRY_ID_1, LANGUAGE_INVALID_ID_2);

        assertFalse(countryDTOOut.isPresent());
    }

    @Test
    @DisplayName("test get translate by country ID and language ID from table country_language with insertion, " +
        "valid countryID, valid LanguageID")
    void testGetTranslateByCountryIdAndLanguageId() {
        namedParameterJdbcTemplate.update(INSERT_COUNTRY, Collections.emptyMap());
        namedParameterJdbcTemplate.update(INSERT_LANGUAGE, Collections.emptyMap());
        namedParameterJdbcTemplate.update(INSERT_COUNTRY_LANGUAGE, Collections.emptyMap());

        Optional<CountryDTOOut> countryDTOOut =
            countryRepository.getTranslateByCountryIdAndLanguageId(COUNTRY_ID_1, LANGUAGE_VALID_ID_1);

        CountryDTOOut countryDTOOutExcepted = new CountryDTOOut(COUNTRY_VALID_CODE_001, EXPECTED_TRANSLATE);

        assertTrue(countryDTOOut.isPresent());
        assertEquals(countryDTOOutExcepted, countryDTOOut.get());
    }
}
