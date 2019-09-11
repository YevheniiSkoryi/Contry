package com.example.country.integration;

import com.example.country.configuration.PostgresConf;
import com.example.country.repository.LanguageRepository;
import com.example.country.repository.impl.LanguageRepositoryImpl;
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
class LanguageRepositoryIT {

    @Language("PostgreSQL")
    private final static String SQL = "INSERT INTO language(localization) VALUES ('UK')";

    private final static int LANGUAGE_ID = 1;
    private final static String LANGUAGE_LOCALIZATION = "UK";
    private final static String UNKNOWN_LANGUAGE_LOCALIZATION = "FI";

    private LanguageRepository languageRepository;
    private Liquibase liquibase;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @BeforeAll
    void init() {
        languageRepository = new LanguageRepositoryImpl(PostgresConf.namedParameterJdbcTemplate);
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
    @DisplayName("test get id from table language with known localization, with insertion")
    void testGetById() {
        namedParameterJdbcTemplate.update(SQL, Collections.emptyMap());
        Optional<Integer> languageId = languageRepository.getIdByLocalization(LANGUAGE_LOCALIZATION);

        assertTrue(languageId.isPresent());
        assertEquals(LANGUAGE_ID, languageId.get());
    }

    @Test
    @DisplayName("test get id from table language with known localization")
    void testGetById2() {
        Optional<Integer> languageId = languageRepository.getIdByLocalization(LANGUAGE_LOCALIZATION);

        assertFalse(languageId.isPresent());
    }

    @Test
    @DisplayName("test get id from table language with unknown localization, with insertion")
     void testGetWhenIdNotFoundWithInsertion() {
        namedParameterJdbcTemplate.update(SQL, Collections.emptyMap());

        Optional<Integer> languageId = languageRepository.getIdByLocalization(UNKNOWN_LANGUAGE_LOCALIZATION);

        assertFalse(languageId.isPresent());
    }

    @Test
    @DisplayName("test get id from table language with unknown localization")
    void testGetWhenIdNotFoundWithoutInsertion() {
        Optional<Integer> languageId = languageRepository.getIdByLocalization(UNKNOWN_LANGUAGE_LOCALIZATION);

        assertFalse(languageId.isPresent());
    }
}
