package com.example.country.repository.impl;

import com.example.country.repository.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class LanguageRepositoryImpl implements LanguageRepository {

    @org.intellij.lang.annotations.Language(value = "PostgreSQL")
    private final String FIND_LANGUAGE_SQL = "select id from Language where localization = :localization";


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Optional<Integer> getIdByLocalization(String localization) {

        try{
            final SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("localization", localization);
            final Integer languageId = namedParameterJdbcTemplate
                .queryForObject(FIND_LANGUAGE_SQL, namedParameters, Integer.class);

            return Optional.ofNullable(languageId);
        }catch (EmptyResultDataAccessException exception){
            return Optional.empty();
        }

    }
}
