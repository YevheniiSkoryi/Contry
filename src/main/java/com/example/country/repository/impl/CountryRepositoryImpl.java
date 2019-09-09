package com.example.country.repository.impl;

import com.example.country.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CountryRepositoryImpl implements CountryRepository {

    @org.intellij.lang.annotations.Language(value = "PostgreSQL")
    private final String FIND_COUNTRY_SQL = "SELECT id FROM country WHERE code = :code";

    @org.intellij.lang.annotations.Language(value = "PostgreSQL")
    private final String FIND_TRANSLATE_SQL = "select translate from country_language where country_id = :country_id and language_id = :language_id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Optional<Integer> getIdByCode(String code) {
        try {
            final SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("code", code);

            final Integer countryId = namedParameterJdbcTemplate
                .queryForObject(FIND_COUNTRY_SQL, namedParameters, Integer.class);

            return Optional.ofNullable(countryId);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> getTranslateByCountryIdAndLanguageId(Integer countryId, Integer languageId) {
        try{
            final SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("country_id", countryId)
                .addValue("language_id", languageId);

            final String translate = namedParameterJdbcTemplate
                .queryForObject(FIND_TRANSLATE_SQL, namedParameters, String.class);

            return Optional.ofNullable(translate);
        }catch (EmptyResultDataAccessException exception){
            return Optional.empty();
        }
    }
}
